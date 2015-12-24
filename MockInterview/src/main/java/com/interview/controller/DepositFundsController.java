package com.interview.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.BankWithdrawFundRequest;
import com.interview.framework.pojo.Transactions;
import com.interview.services.Services;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;


@Controller
public class DepositFundsController extends BaseController {

  @Autowired
  private Properties myProps;

  private Logger log = Logger.getLogger(DepositFundsController.class);

  @RequestMapping(value = "/depositfund.do", method = RequestMethod.GET)
  public String getdepositfunds() {
    return "depositFunds";
  }

  @RequestMapping(value = "/withdrawfund.do", method = RequestMethod.GET)
  public String getWithdrowfunds(Model map) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(USER.USERNAME, getLoginUser());
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.USER_REQ_SUB_REQ.USER_BALANCE);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.USER_REQ);
    map.addAllAttributes(resMap);
    return "withdrowFunds";
  }

  @RequestMapping(value = "/withdrawfunds.do", method = RequestMethod.POST)
  public ModelAndView withdrawfunds(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    Transactions transaction = new Transactions();
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    transaction.setAmount(new Double(req.getParameter("amount")));
    transaction.setPaypal_address(req.getParameter("paypalAddress"));
    transaction.setOwner(getLoginUser());
    transaction.setStatus(DATASTORES.WITHDRAW_FUND.STATUS_PENDING);
    reqMap.put("transaction", transaction);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.DEPOSIT_FUNDS_SUB_REQ.WITHDRAW_TRANSACTION);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.DEPOSIT_FUNDS);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/withdrawbankfunds.do", method = RequestMethod.POST)
  public ModelAndView withdrawFundToBank(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    BankWithdrawFundRequest fundRequest = new BankWithdrawFundRequest();
    fundRequest.setUsername(getLoginUser());
    fundRequest.setAccountName(req.getParameter("accountName"));
    fundRequest.setAccountNumber(req.getParameter("accountNumber"));
    fundRequest.setAccountType(req.getParameter("accountType"));
    fundRequest.setIfscCode(req.getParameter("ifscCode"));
    fundRequest.setBankName(req.getParameter("bankName"));
    fundRequest.setBankBranch(req.getParameter("bankBranch"));
    fundRequest.setBankCity(req.getParameter("bankCity"));
    fundRequest.setAmount(new BigDecimal(req.getParameter("amount")));

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("fundRequest", fundRequest);
    reqMap.put(REQUEST_TYPES.SUB_REQ,
        REQUEST_TYPES.BANK_WITHDRAW_FUND_REQUEST_SUB_REQ.SAVE_REQUEST);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.BANK_WITHDRAW_FUND_REQUEST);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }


  @RequestMapping(value = "/depositfunds.do", method = RequestMethod.POST)
  public ModelAndView depositFunds(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {
    Transactions deposittransactions = new Transactions();
    String username = getLoginUser();
    String amount = req.getParameter("amount");

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    deposittransactions.setOtherParty(getLoginUser());
    deposittransactions.setAmount(new Double(req.getParameter("amount")));
    reqMap.put("transactions", deposittransactions);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.DEPOSIT_FUNDS_SUB_REQ.LOG_TRANSACTION);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.DEPOSIT_FUNDS);
    req.getSession().setAttribute("transactionid", resMap.get("transactionId"));

    try {
      Map<String, String> sdkConfig = new HashMap<String, String>();
      sdkConfig.put("mode", myProps.getProperty("paypalsdkmode"));

      String accessToken = new OAuthTokenCredential(myProps.getProperty("paypalaccesskey"),
          myProps.getProperty("paypalsecurekey"), sdkConfig).getAccessToken();
      req.getSession().setAttribute("accessToken", accessToken);

      log.debug(" Access Token is: " + accessToken);
      APIContext apiContext = new APIContext(accessToken);
      apiContext.setConfigurationMap(sdkConfig);

      Amount fundamount = new Amount();
      fundamount.setCurrency("INR"); // check later
      fundamount.setTotal(amount);

      Transaction transaction = new Transaction();
      transaction.setDescription("Deposit Fund in Your Account " + amount + " INR");
      transaction.setAmount(fundamount);

      List<Transaction> transactions = new ArrayList<Transaction>();
      transactions.add(transaction);

      Payer payer = new Payer();
      payer.setPaymentMethod("paypal");

      Payment payment = new Payment();
      payment.setIntent("sale");
      payment.setPayer(payer);
      payment.setTransactions(transactions);

      RedirectUrls redirectUrls = new RedirectUrls();
      redirectUrls.setCancelUrl(myProps.getProperty("domainurl") + "/paypalCancel.do");
      redirectUrls.setReturnUrl(myProps.getProperty("domainurl") + "/paypalSuccess.do");
      payment.setRedirectUrls(redirectUrls);

      Payment createdPayment = payment.create(apiContext);
      log.debug("Payment Created : " + createdPayment.toString());

      for (int i = 0; i < createdPayment.getLinks().size(); i++) {
        if (createdPayment.getLinks() != null
            && createdPayment.getLinks().get(i).getRel().equals("approval_url"))
          resMap.put("REDIRECT_URL", createdPayment.getLinks().get(i).getHref());
      }
      resMap.put("STATE", createdPayment.getState());


    } catch (PayPalRESTException e) {
      log.debug(" PayPalRESTException : ", e);
      e.printStackTrace();
    }

    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }
}
