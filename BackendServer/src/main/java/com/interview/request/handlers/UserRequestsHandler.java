package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service
public class UserRequestsHandler extends RequestHandler {

  public UserRequestsHandler() {
    addHandler(this, REQUEST_TYPES.USER_REQ);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> res = new HashMap<String, Object>();
    String subReq = (String) data.get(REQUEST_TYPES.SUB_REQ);
    if (null != subReq && REQUEST_TYPES.USER_REQ_SUB_REQ.USER_BALANCE.equals(subReq)) {
      try {
        Map<String, Object> userMap = DataStoreRegistry.getInstance().getInterviewerDataStore()
            .getUserInfo(data.get(USER.USERNAME).toString());
        res.put("User_Balance", userMap.get(USER.BALANCE));
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    } else {
      try {
        String SUB_REQ = data.get(REQUEST_TYPES.SUB_REQ).toString();
        if (SUB_REQ.equals(REQUEST_TYPES.USER_REQ_SUB_REQ.IS_USER_REGISTERED)) {

          boolean result = DataStoreRegistry.getInstance().getInterviewerDataStore()
              .isUserEmailExist(data.get(USER.EMAIL).toString());
          res.put("exist", result);

          if (!result) {
            boolean usernameExist = false;
            String newUserName = "";
            do {
              newUserName = Services.getInstance().getUserNameGeneratorService().getUserName();
              usernameExist = DataStoreRegistry.getInstance().getInterviewerDataStore()
                  .isUserNameExist(newUserName);

            } while (usernameExist);
            res.put(USER.USERNAME, newUserName);
          }
        }
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

    return res;
  }

}
