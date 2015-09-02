package com.interview.framework;

public class REQUEST_TYPES {

  public static final String USER_CREDENTIALS = "USER_CREDENTIALS";
  public static final String USER_INFO = "USER_INFO";

  public static final String USER_REQ = "USER_REQ";

  public interface USER_REQ_SUB_REQ {
    String IS_USER_REGISTERED = "IS_USER_REGISTERED";
    String USER_BALANCE = "USER_BALANCE";
  }

  public static final String SEARCH_INTERVIEW_INFO = "SEARCH_INTERVIEW_INFO";
  
  public interface SEARCH_INTERVIEW_SUB_INFO {
  public static final String SEARCH_INTERVIEW_SUB_INFO ="SEARCH_INTERVIEW_SUB_INFO";
  }
  public static final String LINKEDIN_USER_REG = "LINKEDIN_USER_REG";
  public static final String INTERVIEWER_REGISTRATION = "INTERVIEWER_REGISTRATION";
  public static final String SEARCH_INTERVIEWER = "SEARCH_INTERVIEWER";
  public static final String SEARCH_INTERVIEWS = "SEARCH_INTERVIEWS";
  public static final String SEARCH_JOBS = "SEARCH_JOBS";
  public static final String FIRST_REQUEST = "FIRST_REQUEST";
  public static final String RETRIEVE_FIRST_REQUEST = "RETRIEVE_FIRST_REQUEST";
  public static final String PROCESS_FIRST_REQUEST = "PROCESS_FIRST_REQUEST";
  
  public static final String SAVE_CHAT_MESSAGE = "SAVE_CHAT_MESSAGE";
  public static final String SAVE_QUICK_CHAT_MESSAGE = "CHAT_MESSAGE";
  public static final String SAVE_CHAT_MESSAGES = "SAVE_CHAT_MESSAGES";
  public static final String CHAT_HISTORY = "CHAT_HISTORY";
  
  public static final String MESSAGE = "MESSAGE";
  public interface MESSAGE_SUB_REQ {
	  public static final String JOB_MESSAGE = "JOB_MESSAGE";
	  public static final String GET_JOB_MESSAGE = "GET_JOB_MESSAGE"; 
	  public static final String GET_SUB_MESSAGE = "GET_SUB_MESSAGE";
	  public static final String GET_NEW_MESSAGE_COUNT = "GET_NEW_MESSAGE_COUNT";
	  public static final String CHANGE_MESSAGE_STATUS = "CHANGE_MESSAGE_STATUS";
  }
  
  public interface CHAT_HISTORY_SUB_REQ {
    String GET_OFFLINE_CHAT_COUNT = "GET_OFFLINE_CHAT_COUNT";
  }

  public static final String FRIENDLIST_TO_UPDATE = "FL";
  public static final String POST_INTERVIEW = "POST_INTERVIEW";
  public static final String GET_INTERVIEW = "GET_INTERVIEW";
  public interface GET_INTERVIEW_SUB_REQ{
	  String GET_DISPUTABLE_INTERVIEW = "GET_DISPUTABLE_INTERVIEW";
	  String GET_INTERVIEW_LIST="GET_INTERVIEW_LIST";
  }
  public static final String INTERVIEW_SCHEDULE = "INTERVIEW_SCHEDULE";

  public interface INTERVIEW_SCHEDULE_SUB_REQ {
    String SAVE_SCHEDULE = "SAVE_SCHEDULE";
    String GET_SCHEDULE = "GET_SCHEDULE";
    String SAVE_INTERVIEWEE_OPTION = "SAVE_INTERVIEWEE_OPTION";
    String GET_INTERVIEW_LIST="GET_INTERVIEW_LIST";
  }

  public static final String BID_REQ = "BID_REQ";
  public interface BID_SUB_REQ {
	  public static final String MAKE_BID = "MAKE_BID";
	  public static final String GET_BID_BY_BIDDER_AND_INTERVIEW = "GET_BID_BY_BIDDER_AND_INTERVIEW";
  }
  
  public static final String GET_MY_BIDS = "GET_MY_BIDS";
  public static final String GET_ALL_BIDS="GET_ALL_BIDS";
  public static final String GET_AWARDED_BIDS="GET_AWARDED_BIDS";
  public static final String STATUS_CHANGE = "STATUS_CHANGE";
  public static final String AWARD_INTERVIEW = "AWARD_INTERVIEW";
  public static final String ESCROW_DEPOSIT = "ESCROW_DEPOSIT";
  public static final String RELEASE_FUNDS = "RELEASE_FUNDS"; 
  public static final String DEPOSIT_FUNDS = "DEPOSIT_FUNDS"; 
  
  public static final String CONTACT_LIST="CONTACT_LIST";
  public static final String SAVE_CONTACT_LIST="SAVE_CONTACT_LIST";
  
  public interface DEPOSIT_FUNDS_SUB_REQ {
	  public static final String DEPOSIT_FUND = "DEPOSIT_FUND";
	  public static final String LOG_TRANSACTION = "LOG_TRANSACTION";
	  public static final String CANCEL_TRANSACTION = "CANCEL_TRANSACTION";
	  public static final String WITHDRAW_TRANSACTION= "WITHDRAW_TRANSACTION";
  }
  
  public static final String COMPLETE_INTERVIEW = "COMPLETE_INTERVIEW";
  
  public static final String RATING = "RATING";
  
  public interface RATING_SUB_REQ {
	  public static final String GET_USER_RATING = "GET_USER_RATING";
	  public static final String RATE_USER = "RATE_USER";
  }
  
  public static final String SEARCH_ADDITIONAL_DATA = "SEARCH_ADDITIONAL_DATA";
  public static final String CHECK_RATING_ALLOWED = "CHECK_RATING_ALLOWED";
  public static final String DELETE_INTERVIEW = "DELETE_INTERVIEW";
  public static final String CANCEL_INTERVIEW = "CANCEL_INTERVIEW";
  public static final String DELETE_INTERVIEW_SOLR = "DELETE_INTERVIEW_SOLR";
  public static final String TRANSACTION_HISTORY = "TH";
  public static final String RETRIEVE_NOTIFICATIONS = "RET_NOTIF";
  public static final String GET_WALL = "GET_WALL";
  public static final String INTERVIEW_DETAILS = "INTERVIEW_DETAILS";
  public static final String RESET_PASSWORD = "RESET_PASSWORD";
  public static final String SUB_REQ_RESET_PASSWORD = "SUB_REQ_RESET_PASSWORD";
  public static final String UPDATE_USER_PROFILE = "UPDATE_USER_PROFILE";
  public static final String UPDATE_USER_PROFILE_PIC = "UPDATE_USER_PROFILE_PIC";
  public static final String AUTO_COMPLETE = "AUTO_COMPLETE";
  public static final String AVAILABLE_USERS = "AVAILABLE_USERS";
  public static final String DISPUTE = "DISPUTE";
  public static final String CREATE_DISPUTE = "CREATE_DISPUTE";
  public static final String CLOSED_DISPUTE_LIST="CLOSED_DISPUTE_LIST";
  public static final String RETRIEVE_DISPUTE = "RETRIEVE_DISPUTE";
  public static final String GET_DISPUTE_LIST = "GET_DISPUTE_LIST";
  public static final String SEND_DISPUTE_MSG = "SEND_DISPUTE_MSG";
  public static final String DISPUTE_CLOSED_BY_INTERVIEWER = "DISPUTE_CLOSED_BY_INTERVIEWER";
  public static final String DISPUTE_CLOSED_BY_INTERVIEWEE = "DISPUTE_CLOSED_BY_INTERVIEWEE";
  public static final String DISPUTES_RETRIEVE_ALL = "DISPUTES_RETRIEVE_ALL";


  public static final String CALENDAR_EVENTS = "CALENDAR_EVENTS";
  public interface CALENDAR_SUB_REQ {
    String SAVE_EVENT = "SAVE_EVENT";
    String GET_EVENTS = "GET_EVENTS";
    String DELETE_EVENT = "DELETE_EVENT";
    String UPDATE_EVENT = "UPDATE_EVENT";
  }

  public static final String SUB_REQ = "SUB_REQ";

  public static final String FILE_UP_DOWN = "FILE_UP_DOWN";
  public static final String SAVE_UPLOAD_INFO = "SAVE_UPLOAD_INFO";
  public static final String GET_UPLOAD_INFO = "GET_UPLOAD_INFO";

  private static final String FILESERVER = "FILESERVER_";
  public static final String FILESERVER_SAVE_INTERVIEW_FILE = FILESERVER + "SAVE_INTERVIEW_FILE";
  public static final String FILESERVER_UPDATE_INTERVIEW_FILE = FILESERVER
      + "UPDATE_INTERVIEW_FILE";
  public static final String FILESERVER_DOWNLOAD_INTERVIEW_FILE = FILESERVER
      + "DOWNLOAD_INTERVIEW_FILE";
  public static final String FILESERVER_SAVE_CHAT_FILE = FILESERVER + "SAVE_CHAT_FILE";
  public static final String FILESERVER_DOWNLOAD_CHAT_FILE = FILESERVER + "DOWNLOAD_CHAT_FILE";
  public static final String FILESERVER_SAVE_BID_FILE = FILESERVER + "SAVE_BID_FILE";
  public static final String FILESERVER_UPDATE_BID_FILE = FILESERVER + "UPDATE_BID_FILE";
  public static final String FILESERVER_SAVE_DISPUTE_FILE = FILESERVER + "SAVE_DISPUTE_FILE";
  
  public static final String FILESERVER_JOB_FILE = FILESERVER + "JOB_FILE";
  public static final String FILESERVER_SAVE_JOB_FILE = FILESERVER + "SAVE_JOB_FILE";
  
  public static final String FILESERVER_JOB_APPLICATION_FILE = FILESERVER + "JOB_APPLICATION_FILE";
  public static final String FILESERVER_SAVE_JOB_APPLICATION_FILE = FILESERVER + "SAVE_JOB_APPLICATION_FILE";

  public static final String JOB = "JOB";
  public static final String POST_JOB = "POST_JOB";
  public static final String GET_JOBS_OFFERED = "GET_JOBS_OFFERED";
  public static final String SEARCH_JOB_INFO = "SEARCH_JOB_INFO";
  public static final String GET_JOB = "GET_JOB";
  
  public static final String JOB_APPLICATION_REQ = "JOB_APPLICATION_REQ";  
  public interface JOB_APPLICATION_SUB_REQ {
	  public static final String SAVE_APPLICATION = "SAVE_APPLICATION";
	  public static final String GET_APPLICATION_BY_JOB = "GET_APPLICATION_BY_JOB";
	  public static final String GET_APPLICATION_BY_JOB_AND_USER = "GET_APPLICATION_BY_JOB_AND_USER";	  
  }
  
  public static final String TEST_REQ = "TEST_REQ";
  public interface TEST_SUB_REQ {
	  	public static final String SAVE_TEST = "SAVE_TEST";
  		public static final String GET_ALL_TESTS = "GET_ALL_TESTS";
  		public static final String GET_ALL_PUBLISH_TESTS = "GET_ALL_PUBLISH_TESTS";
  		public static final String GET_TEST_BY_ID = "GET_TEST_BY_ID";
  		public static final String DELETE_TEST = "DELETE_TEST";
  		public static final String PUBLISH_TEST = "PUBLISH_TEST";
  }
  
  public static final String QUESTION_REQ = "QUESTION_REQ";
  public interface QUESTION_SUB_REQ {
	  	public static final String SAVE_QUESTION = "SAVE_QUESTION";
  		public static final String GET_QUESTIONS_BY_TEST_ID = "GET_QUESTIONS_BY_TEST_ID";
  		public static final String GET_QUESTION_BY_ID = "GET_QUESTION_BY_ID";
  		public static final String DELETE_QUESTION = "DELETE_QUESTION";
  		public static final String GET_QUESTIONS_FOR_TEST = "GET_QUESTIONS_FOR_TEST";
  		public static final String GET_QUESTIONS_COUNT_BY_TEST_ID = "QUESTIONS_COUNT_BY_TEST_ID";
  }
  
  public static final String USER_TEST_REQ = "USER_TEST_REQ";
  public interface USER_TEST_SUB_REQ {
	  	public static final String SAVE_USER_TEST = "SAVE_USER_TEST";
		public static final String GET_USER_TESTS = "GET_USER_TESTS";
		public static final String GET_USER_TEST_BY_ID = "GET_USER_TEST_BY_ID";
  }
  
  public static final String PASSWORD = "PASSWORD";
  public interface PASSWORD_SUB_REQ {
	  public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
  }
  
}