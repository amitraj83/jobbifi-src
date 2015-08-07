package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Rating;

public interface IRatingStore extends Remote {

  String NAME = "rating";

  public ObjectId save(Rating r) throws RemoteException;

  public double getAvgRating(String user) throws RemoteException;

  public Rating getRating(ObjectId _id) throws RemoteException;

  public boolean isRatingDone(String ratedByUser, ObjectId iid) throws RemoteException;

  public Rating getRating(String ratedBy, ObjectId iid) throws RemoteException;

  public List<Rating> getAllRatings(String user) throws RemoteException;

  public int getReviewsCount(String user) throws RemoteException;
}
