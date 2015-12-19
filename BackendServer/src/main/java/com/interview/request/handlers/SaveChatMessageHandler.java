package com.interview.request.handlers;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.bosh.XMPPBOSHConnection;
import org.springframework.beans.factory.annotation.Autowired;

import com.interview.framework.CHAT;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.chat.ChatMessage;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class SaveChatMessageHandler extends RequestHandler {

  public SaveChatMessageHandler() {
    addHandler(this, REQUEST_TYPES.SAVE_CHAT_MESSAGES);
  }

  @Autowired
  Properties myProps;

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();

    String subRequest = (String) data.get(REQUEST_TYPES.SUB_REQ);
    if (null != subRequest && REQUEST_TYPES.SAVE_CHAT_MESSAGE.equals(subRequest)) {
      try {
        ChatMessage cm = new ChatMessage();
        cm.setRid((String) data.get(CHAT.RID));
        cm.setFrom((String) data.get(CHAT.FROM));
        cm.setTo((String) data.get(CHAT.TO));
        cm.setMessage((String) data.get(CHAT.MESSAGE));
        cm.setTime(new Date(new Long(data.get(CHAT.TIME).toString())));
        cm.setType((String) data.get(CHAT.TYPE));

        int result = DataStoreRegistry.getInstance().getChatStore().saveChatMessage(cm);
        if (result == 1)
          resMap.put(CHAT.SAVE_STATUS, "1");
        else
          resMap.put(CHAT.SAVE_STATUS, "-1");
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    } else if (null != subRequest && REQUEST_TYPES.SAVE_QUICK_CHAT_MESSAGE.equals(subRequest)) {
      try {
        ChatMessage cm = new ChatMessage();
        cm.setRid((String) data.get(CHAT.RID));
        cm.setFrom((String) data.get(CHAT.FROM));
        cm.setTo((String) data.get(CHAT.TO));
        cm.setMessage((String) data.get(CHAT.MESSAGE));
        cm.setTime(new Date(new Long(data.get(CHAT.TIME).toString())));
        cm.setType((String) data.get(CHAT.TYPE));

        try {
          Map<String, Object> responseMap =
              DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(cm.getTo());
          XMPPConnection connection = new XMPPBOSHConnection(false, "bosh.metajack.im", 5280,
              "xmpp-httpbind", "162.243.74.91");
          connection.connect();
          connection.login(cm.getTo(), (String) responseMap.get(USER.CHATPASS));
          Roster roster = connection.getRoster();
          roster.createEntry(cm.getFrom() + "@162.243.74.91", cm.getFrom(), null);
          connection.disconnect();
        } catch (SmackException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (XMPPException e) {
          e.printStackTrace();
        }

        int result = DataStoreRegistry.getInstance().getChatStore().saveChatMessage(cm);
        if (result == 1) {
          String toUserEmail =
              DataStoreRegistry.getInstance().getInterviewerDataStore().getUserEmail(cm.getTo());
          String fromUserEmail =
              DataStoreRegistry.getInstance().getInterviewerDataStore().getUserEmail(cm.getFrom());
          Map<AttributeType, String> emailData = new HashMap<AttributeType, String>();
          emailData.put(AttributeType.MESSAGE_RECEIVER, cm.getTo());
          emailData.put(AttributeType.MESSAGE_SENDER, cm.getFrom());
          emailData.put(AttributeType.MESSAGE_CONTENT, cm.getMessage());
          Services.getInstance().getEmailService().sendMail(Mailer.EmailType.NEW_MESSAGE, emailData,
              toUserEmail);
          Services.getInstance().getEmailService().sendMail(Mailer.EmailType.NEW_MESSAGE, emailData,
              fromUserEmail);
          resMap.put(CHAT.SAVE_STATUS, "1");
        } else {
          resMap.put(CHAT.SAVE_STATUS, "-1");
        }
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return resMap;
  }
}
