package com.interview.framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RETURN_VALUES {

  public static final int INTERVIEW_DELETE_SUCCESS = 0;
  public static final int INTERVIEW_DELETE_FAIL_INVALID_STATE = 1;
  public static final int INTERVIEW_DELETE_FAIL = 2;
  public static final int INTERVIEW_EDIT_SUCCESS = 3;
  public static final int INTERVIEW_EDIT_FAIL = 4;
  public static final int INTERVIEW_EDIT_FAIL_INVALID_STATE = 5;
  public static final int REPOST_SUCCESS = 6;
  public static final int REPOST_FAILED_INVALID_STATE = 7;
  public static final int REPOST_FAILED = 8;
  public static final int CANCEL_INTERVIEW_SUCCESS = 9;
  public static final int CANCEL_INTERVIEW_FAIL = 10;
  public static final int CANCEL_INTERVIEW_FAIL_INVALID_STATE = 11;
  public static final int ACCEPT_BID_SUCCESS = 12;
  public static final int ACCEPT_BID_FAIL = 13;
  public static final int DISPUTE_START_SUCCESS = 14;
  public static final int DISPUTE_START_FAIL = 15;
  public static final int ESCROW_DEPOSIT_SUCCESS = 16;
  public static final int ESCROW_DEPOSIT_FAIL = 17;
  public static final int RELEASE_FUNDS_FAIL = 18;
  public static final int RELEASE_FUNDS_SUCCESS = 19;
  public static final int COMPLETE_INTERVIEW_FAIL = 20;
  public static final int COMPLETE_INTERVIEW_SUCCESS = 21;
  public static final int INSUFFICIENT_BALANCE = 22;
  public static final int RATING_SAVED_SUCCESS = 23;
  public static final int RATING_SAVED_FAILURE = 24;
  public static final int RATING_FAILURE_INVALID_STATE = 25;
  public static final int REVERT_FUNDS_SUCCESS = 26;
  public static final int REVERT_FUNDS_FAILURE = 27;



  private static Map<Integer, String> returnMessages = new ConcurrentHashMap();

  static {
    returnMessages.put(INTERVIEW_DELETE_SUCCESS, "The interview has been successfully deleted");
    returnMessages.put(INTERVIEW_DELETE_FAIL_INVALID_STATE,
        "The interview cannot be deleted as it is not in the PENDING state");
    returnMessages.put(INTERVIEW_DELETE_FAIL, "The interview deletion failed");
    returnMessages.put(INTERVIEW_EDIT_SUCCESS, "The interview has been successfuly updated");
    returnMessages.put(INTERVIEW_EDIT_FAIL, "The interview cannot be updated");
    returnMessages.put(INTERVIEW_EDIT_FAIL_INVALID_STATE,
        "The interview cannot be updated as it is not in the PENDING state");
    returnMessages.put(REPOST_SUCCESS, "The interview has been re-posted successfully");
    returnMessages.put(REPOST_FAILED_INVALID_STATE,
        "The interview cannot be re-posted as it is not in the PENDING or CANCELLED state");
    returnMessages.put(REPOST_FAILED, "The interview cannot be re-posted");
    returnMessages.put(CANCEL_INTERVIEW_SUCCESS,
        "The interview has been cancelled. However, it can be revoked in future again");
    returnMessages.put(CANCEL_INTERVIEW_FAIL, "The interview cannot be cancelled");
    returnMessages.put(CANCEL_INTERVIEW_FAIL_INVALID_STATE,
        "The interview cannot be deleted as it is not in the PENDING state");
    returnMessages.put(ACCEPT_BID_SUCCESS, "The interview has been successfully awarded");
    returnMessages.put(ACCEPT_BID_FAIL, "The interview cannot be awarded");
    returnMessages.put(DISPUTE_START_SUCCESS, "You have successfully started the dispute");
    returnMessages.put(DISPUTE_START_FAIL, "Your dispute cannot be started at the moment");
    returnMessages.put(ESCROW_DEPOSIT_SUCCESS,
        "You have successfully deposited money in your escrow account");
    returnMessages.put(ESCROW_DEPOSIT_FAIL, "The money deposit in escow has been failed");
    returnMessages.put(RELEASE_FUNDS_FAIL, "Your funds cannot be transferred to the interviewer");
    returnMessages.put(RELEASE_FUNDS_SUCCESS,
        "Your funds have been successfully transferred to the interviewer");
    returnMessages.put(COMPLETE_INTERVIEW_FAIL, "Your interview cannot be completed at the moment");
    returnMessages
        .put(COMPLETE_INTERVIEW_SUCCESS, "Your interview has been successfully completed");
    returnMessages.put(INSUFFICIENT_BALANCE, "You have insufficient balance");
    returnMessages.put(RATING_SAVED_SUCCESS, "Your ratings have been successfully saved");
    returnMessages.put(RATING_SAVED_FAILURE, "Failure while rating the user");
    returnMessages.put(RATING_FAILURE_INVALID_STATE,
        "At this state, you are not allowed to rate the user");
    returnMessages
        .put(REVERT_FUNDS_SUCCESS,
            "Your funds have been successfully reverted to you account. A processing charge may be applied.");
    returnMessages.put(REVERT_FUNDS_FAILURE,
        "Your funds are not reverted at the moment. Please contact the helpdesk.");

  }

  public static String getResponseMessage(int code) {
    return returnMessages.get(code);
  }

}
