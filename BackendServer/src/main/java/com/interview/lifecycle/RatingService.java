package com.interview.lifecycle;

import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.Rating;
import com.interview.rmi.DataStoreRegistry;

public class RatingService {

  public ObjectId doRating(ObjectId iid, String user, Rating rating) throws RemoteException {

    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.COMPLETED
        && (isRatingAllowed(rating.getRatedBy(), iid))) {

      ObjectId rid = DataStoreRegistry.getInstance().getRatingStore().save(rating);
      if (rid != null) {
        double currentAvgRating =
            DataStoreRegistry.getInstance().getRatingStore().getAvgRating(user);
        DataStoreRegistry.getInstance().getInterviewerDataStore().setRating(user, currentAvgRating);
        return rid;
      }

    }
    return null;

  }

  public boolean isRatingAllowed(String ratedByUser, ObjectId iid) throws RemoteException {
    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() < INTERVIEW_STATUS.COMPLETED)
      return false;

    return !DataStoreRegistry.getInstance().getRatingStore().isRatingDone(ratedByUser, iid);
  }
}
