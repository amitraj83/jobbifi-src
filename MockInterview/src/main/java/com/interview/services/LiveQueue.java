package com.interview.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.UPDATE_TYPES;


@Service("liveservice")
// @Scope("session")
public class LiveQueue {


  // private final Queue<Update> q = new ConcurrentLinkedQueue<Update>();

  private final Map<String, Queue<Update>> updates = new ConcurrentHashMap<String, Queue<Update>>();

  public Queue<Update> getUpdateQueue(String user) {
    return updates.get(user);
  }

  public synchronized String getAllUpdatesInJSON(String user) {
    Queue<Update> q = updates.get(user);
    List<Update> list = new ArrayList<Update>();
    Map<String, Object> map = new HashMap<String, Object>();
    JSONObject json = new JSONObject();
    int counter = 0;
    while (q.size() != 0) {
      try {
        json.put(counter + "",
            Services.getInstance().getJSONUtilityService().writeValueAsString(q.remove()));
        // map.put(counter+"",
        // Services.getInstance().getJSONUtilityService().writeValueAsString(q.remove()));
        System.out.println("[Update]:" + json.get(counter + "").toString());
      } catch (JSONException e) {
        e.printStackTrace();
      } catch (JsonGenerationException e) {
        e.printStackTrace();
      } catch (JsonMappingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      counter++;
    }
    return json.toString();
    // return Services.getInstance().getJSONUtilityService().getJSONStringOfMap(map);

  }

  public Queue<Update> getQueue(String user) {
    return updates.get(user);
  }

  public boolean isUpdateEmpty(String user) {
    if (updates.get(user) == null)
      return true;
    else {
      if (updates.get(user).size() == 0)
        return true;
      else
        return false;
    }
  }

  public void insertUpdate(String author, Update update) {

    List<String> recipients = null;
    if (update.getRecipients() == null)
      recipients = getFriendListToUpdate(author, update.getType());
    else
      recipients = update.getRecipients();
    // List<Object> onlineUsers = Services.getInstance().getSessionRegistry().getAllPrincipals();
    for (String recipient : recipients) {
      if (updates.get(recipient) == null) {
        Queue<Update> newQ = new ConcurrentLinkedQueue<Update>();
        newQ.add(update);
        updates.put(recipient, newQ);
      } else {
        Queue<Update> existingQ = updates.get(recipient);
        existingQ.add(update);
      }
    }


  }

  private List<String> getFriendListToUpdate(String author, String type) {
    List<String> list = new ArrayList<String>();

    if (type.equals(UPDATE_TYPES.CHAT_ONLINE) || type.equals(UPDATE_TYPES.CHAT_OFFLINE)) {
      Map<Object, Object> reqMap = new HashMap<Object, Object>();
      reqMap.put("user", author);

      Map<String, Object> resMap =
          Services.getInstance().getRequestHandlerService()
              .handleRequest(reqMap, REQUEST_TYPES.FRIENDLIST_TO_UPDATE);
      return (List<String>) resMap.get("FL");
    } else if (type.equals(UPDATE_TYPES.FIRST_REQUEST_ACCEPT)) {

    }
    return list;
  }

}
