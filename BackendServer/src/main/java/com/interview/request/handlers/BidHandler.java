package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class BidHandler extends RequestHandler implements DisposableBean {
	
	 @Autowired
	  private Properties myProps;
	 
	  private static final Logger logger = Logger.getLogger(BidHandler.class);

	  public BidHandler() {
	    addHandler(this, REQUEST_TYPES.BID_REQ);
	  }

	  @Override
	  public Map<String, Object> handleRequest(Map<Object, Object> data) {
	    Map<String, Object> resMap = new HashMap<String, Object>();
	    
	    Object subReq = data.get(REQUEST_TYPES.SUB_REQ);
	    if(null != subReq && subReq.toString().equals(REQUEST_TYPES.BID_SUB_REQ.MAKE_BID)){
	    	
	    	try {
	  	      Bid bid = new Bid();
	  	      bid.setBidder((String) data.get(VARIABLES.Bid.BIDDER));
	  	      bid.setIid((String) data.get(VARIABLES.Bid.INTERVIEW_ID));
	  	      bid.setMsg((String) data.get(VARIABLES.Bid.MSG));
	  	      bid.setPrice((String) data.get(VARIABLES.Bid.PRICE));
	  	      bid.setDate(new Date().getTime());
	  	      bid.setStatus(0);
	  	      bid.setAttachmentID(data.get(VARIABLES.Bid.BID_FID).toString());
	  	
	  	      ObjectId id = DataStoreRegistry.getInstance().getBidStore().saveBid(bid);
	  	      if (id != null) {
	  	        resMap.put("success", 1);
	  	        resMap.put(VARIABLES.Bid.BID_ID, id.toString());
	  	        resMap.put(VARIABLES.Bid.BIDDER, bid.getBidder());
	  	        resMap.put(VARIABLES.Bid.INTERVIEW_ID, bid.getIid());
	  	        resMap.put(VARIABLES.Bid.MSG, bid.getMsg());
	  	        resMap.put(VARIABLES.Bid.PRICE, bid.getPrice());
	  	        resMap.put(VARIABLES.Bid.STATUS, bid.getStatus());
	  	        resMap.put(VARIABLES.Bid.DATE, bid.getDate());
	  	        bid.setId(id.toString());
	  	
	  	        Services.getInstance().getNotificationService()
	  	            .processNotification(bid, VARIABLES.NOTIFICATION.TYPE.NEW_BID_NOTIFICATION);
	  	        Map<String, Object> model = new HashMap<String, Object>(); 
	  	        model.put("bidder", bid.getBidder());
	  	        model.put("msg", bid.getMsg());
	  	        model.put("price", bid.getPrice());
	  	        	        
	  	        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	  	        model.put("date", dateFormat.format(new Date(bid.getDate())));	        
	  	        Interview interview =DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(data.get(VARIABLES.Bid.INTERVIEW_ID).toString());
	  	        
	  	        Map<String, Object>  userMap = DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(interview.getInterviewee());
	  	        model.put("interviewer", interview.getInterviewee());
	  	        model.put("title", interview.getTitle());
	  	  	   	Services.getInstance().getEmailService().sendMail(userMap.get(USER.EMAIL).toString(), 
	  	  	   			myProps.getProperty("mail.bidplaced.subject"), "bidplaced.ftl",model);
	  	  	   	
	  	  	   	//send mail to interviewee about bid
	  	  	Map<String, String> param = new HashMap<String, String>(); 
	    	 param.put("username", interview.getInterviewee());
	    	 param.put("interviewtitle", interview.getTitle());
	    	 param.put("interviewurl",data.get("baseURL").toString()+"/interviewdetail.do?iid="+data.get(VARIABLES.Bid.INTERVIEW_ID).toString());
	    	 int highestBidAmount = DataStoreRegistry.getInstance().getBidStore().getMaxBidOfInterview(data.get(VARIABLES.Bid.INTERVIEW_ID).toString());
	    	 param.put("highestbidamount", ""+highestBidAmount);
	    	 String email =  DataStoreRegistry.getInstance().getInterviewerDataStore().getUserEmail(interview.getInterviewee());
	    	 	List<String> resEmail = new ArrayList<String>();
	    	 	resEmail.add(email);
	    	 Services.getInstance().getEmailService().sendMailChannelOnEvent("6", param, resEmail, "You received a new bid for "
	    	 		+ interview.getTitle());
	         
	    	//send mail to bidder 
		  	  	Map<String, String> param1 = new HashMap<String, String>(); 
		    	 param1.put("username", bid.getBidder());
		    	 param1.put("bidamount", bid.getPrice());
		    	 param1.put("interviewtitle", interview.getTitle());
		    	 param1.put("interviewurl",data.get("baseURL").toString()+"/interviewdetail.do?iid="+data.get(VARIABLES.Bid.INTERVIEW_ID).toString());
		    	 param1.put("highestbidamount", ""+highestBidAmount);
		    	 String email1 =  DataStoreRegistry.getInstance().getInterviewerDataStore().getUserEmail(bid.getBidder());
		    	 	List<String> resEmail1 = new ArrayList<String>();
		    	 	resEmail.add(email1);
		    	 Services.getInstance().getEmailService().sendMailChannelOnEvent("7", param, resEmail1, "Your bid has been placed successfully for "
		    	 		+ interview.getTitle());
	  	  	   
	  	      }
	  	    } catch (Exception e) {    
	  	    	logger.error(" Exception MAKE BID: ", e);	      
	  	    }
	    	
	    } else if(null != subReq && subReq.toString().equals(REQUEST_TYPES.BID_SUB_REQ.GET_BID_BY_BIDDER_AND_INTERVIEW)){
	    	String bidder = (String) data.get(VARIABLES.Bid.BIDDER);
	    	String iid = (String) data.get(VARIABLES.Bid.INTERVIEW_ID);
	    	Bid bid = null;
	    	try {
				bid = DataStoreRegistry.getInstance().getBidStore().getBidByBidderAndInterview(bidder, iid);
			} catch (RemoteException e) {
				logger.error("Exception GET BID : ", e);				
			}
	    	resMap.put("bid", bid);
	    }
	    
	    return resMap;
	  }
	  
	  public void destroy() {
		  logger.debug("Destroying Bidhander Bean.......");
	  }
}