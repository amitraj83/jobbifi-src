package com.interview.request.handlers;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Dispute;
import com.interview.framework.pojo.DisputeMessage;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.UploadedFile;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service
public class DisputeHandler extends RequestHandler {
  Logger log = Logger.getLogger(DisputeHandler.class);

  public DisputeHandler() {
    addHandler(this, REQUEST_TYPES.DISPUTE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> res = new HashMap<String, Object>();
    try {
      String SUB_REQ = data.get(REQUEST_TYPES.SUB_REQ).toString();
      String iid = "";
      Interview interview = null;
      if (data.get(DATASTORES.DISPUTE.IID) != null) {
        iid = data.get(DATASTORES.DISPUTE.IID).toString();
        interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
      }
      if ((SUB_REQ.equals(REQUEST_TYPES.CREATE_DISPUTE))
          && (interview.getStatus() != INTERVIEW_STATUS.DISPUTE)) {
        DataStoreRegistry.getInstance().getInterviewDataStore()
            .updateInterviewStatus(new ObjectId(iid), INTERVIEW_STATUS.DISPUTE);
        Dispute dispute = new Dispute();
        dispute.setCreatedBy(data.get(USER.USERNAME).toString());
        dispute.setIID(iid);
        dispute.setInterviewOriginalStatus(interview.getStatus());
        dispute.setStatus(DATASTORES.DISPUTE.STATUS_TYPE.OPEN);
        dispute.setTime(new Date().getTime());
        dispute.setAmount(new Double(data.get(DATASTORES.DISPUTE.DISPUTE_AMOUNT).toString()));
        dispute.setWith(interview.getInterviewee().equals(dispute.getCreatedBy())
            ? interview.getInterviewer() : interview.getInterviewee());
        SecureRandom random = new SecureRandom();
        dispute.setVisibleID(new BigInteger(40, random).toString(32).toUpperCase());
        ObjectId _id = DataStoreRegistry.getInstance().getDisputeStore().createDispute(dispute);
        if (_id != null) {
          dispute.setId(_id.toString());
          DisputeMessage dMessage = new DisputeMessage();
          dMessage.setDisputeId(_id.toString());
          dMessage.setFid(data.get(DATASTORES.DISPUTE_MESSAGE.FID).toString());
          dMessage.setMessage(data.get(DATASTORES.DISPUTE_MESSAGE.MESSAGE).toString());
          dMessage.setMessageBy(data.get(USER.USERNAME).toString());
          dMessage.setTime(new Date().getTime());
          ObjectId _did =
              DataStoreRegistry.getInstance().getDisputeMessageStore().insertMessage(dMessage);
          if (_did != null) {
            res.put("id", _id.toString().toUpperCase());
            res.put("vid", dispute.getVisibleID());
            res.put("status", "1");
            res.put("interview", interview.getTitle());
            res.put("disputewith", interview.getInterviewee().equals(dispute.getCreatedBy())
                ? interview.getInterviewer() : interview.getInterviewee());
            res.put("amount", dispute.getAmount());
            Map<AttributeType, String> model = new HashMap<AttributeType, String>();
            model.put(AttributeType.DISPUTE_AMOUNT, String.valueOf(dispute.getAmount()));
            model.put(AttributeType.DISPUTE_MESSAGE, dMessage.getMessage());
            model.put(AttributeType.DISPUTE_DISPUTEBY, dMessage.getMessageBy());
            model.put(AttributeType.DISPUTE_INTERVIEW_TITLE, interview.getTitle());
            model.put(AttributeType.DISPUTE_DISPUTEWITH, dispute.getWith());
            Services.getInstance().getEmailService().sendMail(Mailer.EmailType.NEW_DISPUTE, model,
                DataStoreRegistry.getInstance().getInterviewerDataStore()
                    .getUserEmail(dispute.getWith()));
            if (dMessage.getFid() != null && !dMessage.getFid().equals("")) {
              DataStoreRegistry.getInstance().getUploadedFileDataStore()
                  .updateArtifactId(new ObjectId(dMessage.getFid()), _id.toString());
            }
          }
        } else
          res.put("status", "0");
      } else if (SUB_REQ.equals(REQUEST_TYPES.RETRIEVE_DISPUTE)) {
        String did = data.get(DATASTORES.DISPUTE.ID).toString();
        String user = data.get(USER.USERNAME).toString();
        Dispute dispute =
            DataStoreRegistry.getInstance().getDisputeStore().getDispute(new ObjectId(did));
        List<DisputeMessage> listMsg =
            DataStoreRegistry.getInstance().getDisputeMessageStore().getMessages(dispute.getId());
        res.put("title", DataStoreRegistry.getInstance().getInterviewDataStore()
            .getInterview(dispute.getIID()).getTitle());
        res.put("size", listMsg.size());
        res.put("list", listMsg);
        res.put("did", dispute.getId());
        res.put("iid", dispute.getIID());
        res.put("disputeStatus", dispute.getStatus());
        res.put("amount", dispute.getAmount());
        res.put("disputewith", dispute.getWith());
        res.put("createdby", dispute.getCreatedBy());
        res.put("username", user);
        if (dispute.getTimeclosed() != 0)
          res.put("timeclosed", dispute.getTimeclosed());
        if (dispute.getStatus().equals(DATASTORES.DISPUTE.STATUS_TYPE.CLOSED)) {
          if (user.equals(dispute.getCreatedBy()))
            res.put("disputeResult", dispute.getResult());
          else
            res.put("disputeResult", dispute.getResult().equals(DATASTORES.DISPUTE.RESULT_TYPE.LOST)
                ? DATASTORES.DISPUTE.RESULT_TYPE.WIN : DATASTORES.DISPUTE.RESULT_TYPE.LOST);
        } else
          res.put("disputeResult", dispute.getResult());
        for (DisputeMessage disputeMessage : listMsg) {
          Map<String, String> map = new HashMap<String, String>();
          String fid = disputeMessage.getFid();
          if (fid != null && !fid.equals("")) {
            UploadedFile file = DataStoreRegistry.getInstance().getUploadedFileDataStore()
                .getUploadedFile(new ObjectId(fid));
            map.put("fn", file.getOriginalFileName());
            map.put("url", file.getURL());
            res.put(fid, map);
          }
        }
      } else if (SUB_REQ.equals(REQUEST_TYPES.SEND_DISPUTE_MSG)) {
        DisputeMessage dMessage = new DisputeMessage();
        dMessage.setDisputeId(data.get(DATASTORES.DISPUTE_MESSAGE.DISPUTEID).toString());
        dMessage.setFid(data.get(DATASTORES.DISPUTE_MESSAGE.FID).toString());
        dMessage.setMessage(data.get(DATASTORES.DISPUTE_MESSAGE.MESSAGE).toString());
        dMessage.setMessageBy(data.get(DATASTORES.DISPUTE_MESSAGE.MESSAGEBY).toString());
        dMessage.setTime(new Date().getTime());
        ObjectId _id =
            DataStoreRegistry.getInstance().getDisputeMessageStore().insertMessage(dMessage);
        if (_id != null) {
          res.put("status", "1");
          res.put("message", dMessage);
          Map<String, String> map = new HashMap<String, String>();
          String fid = dMessage.getFid();
          if (fid != null && !fid.equals("")) {
            DataStoreRegistry.getInstance().getUploadedFileDataStore()
                .updateArtifactId(new ObjectId(fid), dMessage.getDisputeId());
            UploadedFile file = DataStoreRegistry.getInstance().getUploadedFileDataStore()
                .getUploadedFile(new ObjectId(fid));
            map.put("fn", file.getOriginalFileName());
            map.put("url", file.getURL());
            res.put(fid, map);
          }
        } else
          res.put("status", "0");
      } else if (SUB_REQ.equals(REQUEST_TYPES.DISPUTE_CLOSED_BY_INTERVIEWEE)) {
        String disputeId = data.get(DATASTORES.DISPUTE.ID).toString();
        String closedBy = data.get(DATASTORES.DISPUTE.CLOSEDBY).toString();
        Dispute dispute =
            DataStoreRegistry.getInstance().getDisputeStore().getDispute(new ObjectId(disputeId));
        Interview interviewToComplete =
            DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(dispute.getIID());
        int status = Services.getInstance().getReleaseFundsService().releaseFundsInDispute(
            new ObjectId(interviewToComplete.getId()), interviewToComplete.getEb());
        if (status == RETURN_VALUES.RELEASE_FUNDS_SUCCESS) {
          String result = "";
          if (dispute.getCreatedBy().equals(interviewToComplete.getInterviewee()))
            result = DATASTORES.DISPUTE.RESULT_TYPE.LOST;
          else
            result = DATASTORES.DISPUTE.RESULT_TYPE.WIN;
          DataStoreRegistry.getInstance().getDisputeStore().closeDispute(new ObjectId(disputeId),
              result, closedBy);
          Interview updateInterview = DataStoreRegistry.getInstance().getInterviewDataStore()
              .getInterview(dispute.getIID());
          res.put("status", "1");
          res.put("iid", interviewToComplete.getId());
          res.put("istatus", updateInterview.getStatus());
          res.put("message", RETURN_VALUES.getResponseMessage(RETURN_VALUES.RELEASE_FUNDS_SUCCESS));
        } else {
          res.put("status", "0");
          res.put("message", RETURN_VALUES.getResponseMessage(RETURN_VALUES.RELEASE_FUNDS_FAIL));
        }
      } else if (SUB_REQ.equals(REQUEST_TYPES.GET_DISPUTE_LIST)) {
        String user = data.get(USER.USERNAME).toString();
        List<Dispute> list = DataStoreRegistry.getInstance().getDisputeStore().getDisputelist(user);
        if (list != null) {
          Iterator<Dispute> it = list.iterator();
          while (it.hasNext()) {
            Dispute dispute = (Dispute) it.next();
            dispute.setTitle(DataStoreRegistry.getInstance().getInterviewDataStore()
                .getInterview(dispute.getIID()).getTitle());
          }
        }
        res.put("disputeList", list);
      } else if (SUB_REQ.equals(REQUEST_TYPES.CLOSED_DISPUTE_LIST)) {
        String user = data.get(USER.USERNAME).toString();
        List<Dispute> list =
            DataStoreRegistry.getInstance().getDisputeStore().getClosedDisputelist(user);
        if (list != null) {
          Iterator<Dispute> it = list.iterator();
          while (it.hasNext()) {
            Dispute dispute = (Dispute) it.next();
            dispute.setTitle(DataStoreRegistry.getInstance().getInterviewDataStore()
                .getInterview(dispute.getIID()).getTitle());
          }
        }
        res.put("closed_dispute_List", list);
      } else if (SUB_REQ.equals(REQUEST_TYPES.DISPUTE_CLOSED_BY_INTERVIEWER)) {
        String disputeId = data.get(DATASTORES.DISPUTE.ID).toString();
        String closedBy = data.get(DATASTORES.DISPUTE.CLOSEDBY).toString();
        Dispute dispute =
            DataStoreRegistry.getInstance().getDisputeStore().getDispute(new ObjectId(disputeId));
        Interview interviewToComplete =
            DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(dispute.getIID());
        int status = Services.getInstance().getReleaseFundsService().revertFundsInDispute(
            new ObjectId(interviewToComplete.getId()), interviewToComplete.getEb());
        if (status == RETURN_VALUES.REVERT_FUNDS_SUCCESS) {
          String result = "";
          if (dispute.getCreatedBy().equals(interviewToComplete.getInterviewee()))
            result = DATASTORES.DISPUTE.RESULT_TYPE.WIN;
          else
            result = DATASTORES.DISPUTE.RESULT_TYPE.LOST;
          DataStoreRegistry.getInstance().getDisputeStore().closeDispute(new ObjectId(disputeId),
              result, closedBy);
          DataStoreRegistry.getInstance().getInterviewDataStore().updateInterviewStatus(
              new ObjectId(interviewToComplete.getId()), INTERVIEW_STATUS.COMPLETED);
          Interview updateInterview = DataStoreRegistry.getInstance().getInterviewDataStore()
              .getInterview(dispute.getIID());
          res.put("status", "1");
          res.put("iid", updateInterview.getId());
          res.put("istatus", updateInterview.getStatus());
          res.put("message", RETURN_VALUES.getResponseMessage(RETURN_VALUES.REVERT_FUNDS_SUCCESS));
        } else {
          res.put("status", "0");
          res.put("message", RETURN_VALUES.getResponseMessage(RETURN_VALUES.REVERT_FUNDS_FAILURE));
        }
      } else {
        res.put("status", "2");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return res;
  }
}
