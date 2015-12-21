package com.interview.lifecycle;

import java.rmi.RemoteException;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.pojo.Interview;
import com.interview.framework.rmi.common.IInterviewDataStore;
import com.interview.rmi.DataStoreRegistry;

public class EditInterview {

  public void editInterview(ObjectId _id, Map<String, Object> changes) throws RemoteException {

    IInterviewDataStore is = DataStoreRegistry.getInstance().getInterviewDataStore();
    Interview interview = is.getInterview(_id);
    if (interview.getStatus() == INTERVIEW_STATUS.PENDING) {
      is.updateInterview(_id, changes);
    }

  }

}
