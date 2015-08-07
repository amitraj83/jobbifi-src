package com.interview.lifecycle;

import java.rmi.RemoteException;
import java.util.Date;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;

public class RepostInterview {

  public ObjectId repostInterview(ObjectId _id) throws RemoteException {

    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(_id);
    if (interview.getStatus() == INTERVIEW_STATUS.PENDING
        || interview.getStatus() == INTERVIEW_STATUS.CANCELLED) {

      interview.setTitle(interview.getTitle() + " (repost)");
      interview.setDt(new Date().getTime());
      ObjectId id =
          DataStoreRegistry.getInstance().getInterviewDataStore().saveInterview(interview);
      if (id != null) {
        DataStoreRegistry.getInstance().getInterviewDataStore()
            .updateInterviewStatus(id, INTERVIEW_STATUS.PENDING);
        return id;
      } else
        return null;
    } else
      return null;
  }

}
