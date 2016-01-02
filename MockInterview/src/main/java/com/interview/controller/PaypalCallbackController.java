package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES.TRANSACTION;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.services.Services;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;

@Controller

public class PaypalCallbackController extends BaseController {

  private Logger log = Logger.getLogger(PaypalCallbackController.class);

  @RequestMapping(value = "/paypalSuccess.do", method = RequestMethod.GET)
  public ModelAndView paypalsuccess(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    try {

      Map<String, String> sdkConfig = new HashMap<String, String>();
      sdkConfig.put("mode", "sandbox");

      APIContext apiContext =
          new APIContext(req.getSession().getAttribute("accessToken").toString());
      apiContext.setConfigurationMap(sdkConfig);

      Payment payment = new Payment();
      payment.setId(req.getParameter("paymentId"));

      PaymentExecution paymentExecute = new PaymentExecution();
      paymentExecute.setPayerId(req.getParameter("PayerID"));
      payment = payment.execute(apiContext, paymentExecute);

      log.info(" Paypal Payment ({}) " + payment);

      for (Transaction transaction : payment.getTransactions()) {
        Map<Object, Object> reqMap = new HashMap<Object, Object>();
        reqMap.put("transactionid", payment.getId());
        reqMap.put(USER.USERNAME, getLoginUser());
        reqMap.put(TRANSACTION.NETAMOUNT, transaction.getAmount().getTotal());


        Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.DEPOSIT_FUNDS);
      }



    } catch (Exception e) {
      log.debug("Exception : ", e);
    }

    return new ModelAndView("redirect:finance.do?ps=1");
  }

  @RequestMapping(value = "/paypalCancel.do", method = RequestMethod.GET)
  public ModelAndView paypalcancle(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    try {
      Map<Object, Object> reqMap = new HashMap<Object, Object>();
      reqMap.put("transactionid", req.getSession().getAttribute("transactionid"));
      reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.DEPOSIT_FUNDS_SUB_REQ.CANCEL_TRANSACTION);

      Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
          .handleRequest(reqMap, REQUEST_TYPES.DEPOSIT_FUNDS);
    } catch (Exception e) {
      log.error("Exception :", e);
    }
    return new ModelAndView("redirect:/dashboard.html?ps=2");
  }
}

