package com.interview.framework;

public interface VARIABLES {
  String REGISTRATION_INFO = "REGISTRATION_INFO";
  String INTERVIEWER = "INTERVIEWER";
  String INTERVIEWEE = "INTERVIEWEE";
  String REQ_SENT = "REQ_SENT";
  String REQ_RECEIVED = "REQ_RECEIVED";
  String ACCEPTED_FRIENDS = "ACCEPTED_FRIENDS";
  String MY_INTERVIEW = "MY_INTERVIEW";
  String MY_INTERVIEW_AS_INTERVIEWER = "MY_INTERVIEW_AS_INTERVIEWER";
  String MY_INTERVIEW_AS_INTERVIEWEE = "MY_INTERVIEW_AS_INTERVIEWEE";
  String MY_BIDS = "MY_BIDS";
  String BIDS_RECEIVED = "BIDS_RECEIVED";
  String BIDS_POSTED = "BIDS_POSTED";
  String INTERVIEWS_WHERE_I_BID = "ibid";
  String AMOUNT = "AMOUNT";
  String BIDDERS_RATING = "reputation";
  String FROM_DATE = "from";
  String TO_DATE = "to";
  double FEE_PERCENTAGE = 10;
  double ESCROW_REVERT_FEE = 5;
  String IID = "iid";
  String BID_ID = "bidid";
  String WALL = "WALL";
  String RESET_PASS_SEC_TOKEN = "RPS_TOKEN";
  String NEW_PASSWORD = "new_password";
  String ENCRYPTED_FILE_ID = "enc_fid";
  String AVAILABLE_USERS = "AVAILABLE_USERS";
  String BID_ATTACHMENT_URL = "bid_atch_url";
  String BID_ATTACHMENT_NAME = "bid_atch_name";
  String DISPUTES = "DISPUTES";
  String ALLREVIEWS = "allreviews";
  int TRNSACTION_PAGE_SIZE = 10;
  String OFFLINE_CHAT_COUNT = "OFFLINE_CHAT_COUNT";
  int ADD = 1;
  int SUB = 2;

  interface JOB_DETAIL {
    String TITLE = "title";
    String JOB_ID = "_id";
    String MESSAGE = "message";
    String FROM = "from";
    String TO = "to";
  }
  interface MESSAGE {
    String TYPE = "type";
    String TITLE = "title";
    String ID = "id";
    String MESSAGE = "message";
    String FROM = "from";
    String TO = "to";
    String PARENTMESSAGEID = "parentMessageId";
    String STATUS_READ = "read";
    String STATUS_UNREAD = "unread";
    String REFENTITY = "JOB";
  }
  interface POST_INTERVIEW {
    String TITLE = "title";
    String INTERVIEWEE = "interviewee";
    String INTERVIEWER = "interviewer";
    String SKILLS = "skills";
    String DESCRIPTION = "description";
    String INDUSTRY = "industry";
    String BUDGET = "budget";
    String EXPERIENCE = "experience";
  }
  interface POST_JOB {
    String TITLE = "title";
    String INTERVIEWER = "interviewer";
    String SKILLS = "skills";
    String DESCRIPTION = "description";
    String INDUSTRY = "industry";
    String SALARY = "salary";
    String COMPANY_NAME = "companyname";
    String COMPANY_DESCRIPTION = "companydescription";
    String COMPANY_VIDEO = "companyvideo";
    String APPLY_URL = "applyurl";
    String EXPERIENCE = "experience";
    String LOCATION = "location";
  }
  interface Bid {
    String BID_ID = "id";
    String BIDDER = "bidder";
    String INTERVIEW_ID = "iid";
    String MSG = "msg";
    String PRICE = "price";
    String DATE = "dt";
    String STATUS = "status";
    String BID_FID = "BID_FID";
    String STATUS_STRING = "STATUS_STRING";
    String INTERVIEWEE = "INTERVIEWEE";
  }
  interface STATUS_CHANGE_TYPE {
    String INTERVIEW = "interview";
    String BID = "bid";
  }
  interface RESET_PASS {
    String AUTH_INSTANCE = "authinstance";
    String AUTH_ID = "authid";
    String AUTH_TOKEN = "authtoken";
  }
  interface NOTIFICATION {
    interface DATASTORE {
      String NOTIFICATIONID = "_id";
      String INTERVIEWID = "interviewId";
      String CREATEDBY = "createdBy";
      String TYPE = "type";
      String CONTENT = "content";
      String RECEPIENTS_USER = "recepientUser";
      String ENTRY_DATE = "entryDate";
      String COLLECTION = "notification";
      String HAS_READ = "hasRead";
    }
    interface TYPE {
      String GLOBAL_NOTIFICATION = "GN";
      String NEW_INTERVIEW_NOTIFICATION = "NIN";
      String FINANCIAL_NOTIFICATION = "FIN";
      String NEW_BID_NOTIFICATION = "NBN";
      String AWARD_INTERVIEW = "AIN";
      String DEPOSIT_FUNDS_NOTIFICATION = "DFN";
      String ESCROW_NOTIFICATION = "ESN";
      String RATING_NOTIFICATION = "RTN";
    }
  }

  final String NORBERT_SEARCH_SERVICE_NAME = "search";
  final String NORBERT_BACKEND_SERVICE_NAME = "backend";
  final String NORBERT_DATASTORE_SERVICE_NAME = "datastore";
  final String NORBERT_FILESERVER_SERVICE_NAME = "fileserver";
  final String NORBERT_ZK_CONNECTION_STRING = "localhost:2181";
  final String SUPPORT_REQUEST_NAME = "SUPPORT_REQUEST_NAME";
  final String SUPPORT_REQUEST_MESSAGE = "SUPPORT_REQUEST_MESSAGE";
  final String SUPPORT_REQUEST_EMAIL = "SUPPORT_REQUEST_EMAIL";
  final String SUPPORT_EMAIL = "support@jobbifi.com";
}
