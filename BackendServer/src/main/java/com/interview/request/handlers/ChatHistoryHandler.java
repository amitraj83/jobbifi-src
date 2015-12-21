package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.REQUEST_TYPES.CHAT_HISTORY_SUB_REQ;
import com.interview.framework.USER;
import com.interview.framework.chat.ChatMessage;
import com.interview.rmi.DataStoreRegistry;

public class ChatHistoryHandler extends RequestHandler {

  public ChatHistoryHandler() {
    addHandler(this, REQUEST_TYPES.CHAT_HISTORY);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    if (CHAT_HISTORY_SUB_REQ.GET_OFFLINE_CHAT_COUNT.equals(data.get(REQUEST_TYPES.SUB_REQ))) {
      String username = data.get(USER.USERNAME).toString();
      long count;
      try {
        count = DataStoreRegistry.getInstance().getChatStore().getOfflineCount(username);
        resMap.put("count", count);
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    } else {

      String user = data.get("user").toString();
      String otheruser = data.get("otheruser").toString();
      try {
        List<ChatMessage> cm =
            DataStoreRegistry.getInstance().getChatStore().getChatHistory(user, otheruser, 100);
        resMap.put("data", cm);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return resMap;
  }
}
