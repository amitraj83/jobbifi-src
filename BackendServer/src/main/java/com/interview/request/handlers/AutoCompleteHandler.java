package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.DataStoreRegistry;

@Service
public class AutoCompleteHandler extends RequestHandler {

  public AutoCompleteHandler() {
    addHandler(this, REQUEST_TYPES.AUTO_COMPLETE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      String type = data.get("type").toString();
      if (type.equals("SKILLS")) {

        String skillTerm = data.get("term").toString();
        List<String> skills =
            DataStoreRegistry.getInstance().getSkillsDataStore().getRelatedSkills(skillTerm);
        resMap.put("list", skills);
        return resMap;
      }
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
