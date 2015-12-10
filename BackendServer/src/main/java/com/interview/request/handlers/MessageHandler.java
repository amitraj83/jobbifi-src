package com.interview.request.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Message;
import com.interview.rmi.DataStoreRegistry;

@Service
public class MessageHandler extends RequestHandler {

  public MessageHandler() {
    addHandler(this, REQUEST_TYPES.MESSAGE);
  }

  private static final Logger logger = Logger.getLogger(MessageHandler.class);

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> res = new HashMap<String, Object>();
    String SUB_REQ = (data.get(REQUEST_TYPES.SUB_REQ) != null)
        ? (data.get(REQUEST_TYPES.SUB_REQ).toString()) : null;

    if (SUB_REQ != null && SUB_REQ.equals(REQUEST_TYPES.MESSAGE_SUB_REQ.GET_JOB_MESSAGE)) {
      try {
        Message msg = new Message();
        msg = (Message) (data.get("Message"));
        List<Message> list = DataStoreRegistry.getInstance().getMessageStore().getMessages(msg);
        res.put("JOB_LIST", list);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return res;

    } else if (SUB_REQ != null && SUB_REQ.equals(REQUEST_TYPES.MESSAGE_SUB_REQ.JOB_MESSAGE)) {
      try {
        Message msg = new Message();
        msg = (Message) (data.get("Message"));
        DataStoreRegistry.getInstance().getMessageStore().saveMessage(msg);

        logger.info("Message Type : " + msg.getType());

        if (msg.getType() != null && !msg.getType().equals("original")) {
          DataStoreRegistry.getInstance().getMessageStore().updateOriginalMessage(msg);
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
      return res;

    } else if (SUB_REQ != null && SUB_REQ.equals(REQUEST_TYPES.MESSAGE_SUB_REQ.GET_SUB_MESSAGE)) {
      try {
        Message msg = new Message();
        msg = (Message) (data.get("Message"));
        List<Message> list =
            DataStoreRegistry.getInstance().getMessageStore().getMessage(msg.getParentMessageId());
        res.put("SUB_MESSAGE_LIST", list);

      } catch (Exception e) {
        e.printStackTrace();
      }
      return res;

    } else if (SUB_REQ != null
        && SUB_REQ.equals(REQUEST_TYPES.MESSAGE_SUB_REQ.GET_NEW_MESSAGE_COUNT)) {
      try {
        String username = data.get(USER.USERNAME).toString();
        int count = DataStoreRegistry.getInstance().getMessageStore().getNewMessageCount(username);
        res.put("NEW_MESSAGE_COUNT", count);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return res;

    } else if (SUB_REQ != null
        && SUB_REQ.equals(REQUEST_TYPES.MESSAGE_SUB_REQ.CHANGE_MESSAGE_STATUS)) {
      try {
        String id = data.get(VARIABLES.MESSAGE.ID).toString();
        DataStoreRegistry.getInstance().getMessageStore().changeMessageStatus(id);

      } catch (Exception e) {
        e.printStackTrace();
      }
      return res;

    } else if (SUB_REQ != null
        && SUB_REQ.equals(REQUEST_TYPES.MESSAGE_SUB_REQ.GET_MESSAGE_BETWEEN)) {
      try {
        List<Message> list = DataStoreRegistry.getInstance().getMessageStore()
            .getChatMessages(data.get("user1").toString(), data.get("user2").toString());
        res.put("MESSAGE_LIST", list);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return res;
    } else {
      try {
        Message msg = new Message();
        msg = (Message) (data.get("Message"));
        int pageNum = Integer.parseInt(data.get("pagenum").toString());
        List<Message> list =
            DataStoreRegistry.getInstance().getMessageStore().getMessage1(msg, pageNum);
        res.put("MESSAGE_LIST", list);
        res.put("MESSAGE_LIST_COUNT", list.size());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return res;
    }
  }
}
