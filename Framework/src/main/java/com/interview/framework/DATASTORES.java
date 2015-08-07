package com.interview.framework;


public interface DATASTORES {

	// for the pagination purpose
  String PAGE_SIZE = "PAGE_SIZE";
  String PAGE_NO = "PAGE_NO";  
	
  interface INTERVIEW {
    String TITLE = "title";
    String INTERVIEWEE = "interviewee";
    String INTERVIEWER = "interviewer";
    String SKILLS = "skills";
    String DESCRIPTION = "description";
    String DBCollection = "interview";
    String DATE = "dt";
    String STATUS = "status";
    String STATUS_STRING = "status_string";
    String ESCROW_BALANCE = "eb";
    String FILE = "ifile";
    String INDUSTRY = "industry";
    String BUDGET = "budget";
    String EXPERIENCE = "experience";
    String ID="_id";
  }

  interface BID {
    String DBCollection = "bid";
  }

  interface RATING {
    String DBCOllection = "rating";
    String USERNAME = "username";
    String RATEDBY = "ratedBy";
    String RATE1 = "rate1";
    String RATE2 = "rate2";
    String RATE3 = "rate3";
    String RATE4 = "rate4";
    String AVERAGE = "average";
    String MESSAGE = "message";
    String IID = "iid";
    String TIME = "time";
    String ID = "id";
  }

  interface TRANSACTION {

    String DBCollection = "transaction";
    String TIME = "time";
    String TYPE = "type";
    String OWNER = "owner";
    String OTHERPARTY = "otherParty";
    String STATUS = "status";
    String DETAILS = "details";
    String GROSS = "gross";
    String FEE = "fee";
    String NETAMOUNT = "netamount";
    String BALANCE = "balance";
    String TID = "tid";
    String ID = "_id";

    // SHould keep interview id as well

    interface TTYPE {
      String CREDIT = "CREDIT";
      String DEBIT = "DEBIT";
    }

    interface TSTATUS {
      String PENDING = "PENDING";
      String DONE = "DONE";
      String CANCELLED = "CANCELLED";
    }
  }

  interface RESET_PASSWORD_ENTITY {
    String ID = "_id";
    String USERNAME = "username";
    String SECTOKEN = "sectoken";
    String SECRETKEY = "secretKey";
    String TIME = "dt";
    String EXPIRED = "expired";
    String IPADDRESS = "ipAddress";
    String COLLECTION = "resetpassword";
  }

  interface SKILLS {
    String COLLECTION = "skills";
    String SKILLS_FIELD = "sk";
  }
  
  interface WITHDRAW_FUND{
	  String ID = "_id";
	  String USERNAME = "username";
	  String AMOUNT = "amount";
	  String PAYPAL_ADDRESS = "paypaladdress";
	  String REQUESTED_DATE = "requesteddate";
	  String PROCESSED_DATE = "processeddate";
	  String STATUS = "status";
	  
	  String COLLECTION = "withdrawfund";
	  
	  String STATUS_PENDING = "PENDING";
	  String STATUS_DONE = "DONE";
  }
  interface CONTACT_LIST{
	  String ID = "_id";
	  String COLLECTION ="contactlist";
	  String USER="user";
	  String CONTACT="contact";
  }

  interface UPLOAD_FILE {
    String COLLECTION = "uploadfile";
    String ID = "_id";
    String FILENAME = "filename";
    String PATH_ON_DISK = "path";
    String EXTENSION = "extension";
    String MIMETYPE = "mime";
    String URL = "url";
    String SIZE = "size";
    String OWNER = "owner";
    String TIME = "dt";
    String THUMBNAIL_URL = "thumbnail";
    String CLASSIFICATION = "class";
    String ARTIFACT_ID = "artifactid";
    String ORIGINAL_FN = "original_name";

    interface CLASS_TYPE {
      String INTERVIEW_DOCUMENT = "INTERVIEW_DOCUMENT";
      String BID_DOCUMENT = "BID_DOCUMENT";
      String CHAT_DOCUMENT = "CHAT_DOCUMENT";
      String DISPUTE_DOCUMENT = "DISPUTE_DOCUMENT";
      String JOB_DOCUMENT = "JOB_DOCUMENT";
    }

  }

  interface DISPUTE {
    String ID = "_id";
    String IID = "iid";
    String CREATEDBY = "createdby";
    String CLOSEDBY = "closedBy";
    String TIME = "dt";
    String STATUS = "status";
    String INTERVIEW_ORIGINAL_STATUS = "istatus";
    String RESULT = "result";
    String DISPUTE_AMOUNT = "amount";
    String VISIBLE_ID = "visibleid";
    String WITH = "with";
    String TIME_CLOSED = "timeclosed";

    interface STATUS_TYPE {
      String OPEN = "OPEN";
      String CLOSED = "CLOSED";
    }
    interface RESULT_TYPE {
      String WIN = "WIN";
      String LOST = "LOST";
      String PENDING = "PENDING";
    }
  }

  interface DISPUTE_MESSAGE {

    String DISPUTEID = "disputeId";
    String MESSAGEBY = "messageBy";
    String MESSAGE = "message";
    String FID = "fid";
    String TIME = "time";

  }
  interface MESSAGE{
	  String Collection="message";
	  
	  String TYPE = "type";
	  String TITLE = "title";
	  String REF_ID = "ref_id";
	  String MESSAGE = "message";
	  String FROM = "from";
	  String TO = "to";
	  String PARENTMESSAGEID = "parentMessageId";
	  String STATUS = "status";
	  String REFENTITY = "refentity";
	  String CREATIONDATE = "creationdate";
	  String LASTREPLYTODATE = "lastReplyToDate";
	  String ID = "_id";
	  
	  interface MESSAGE_STATUS {
		  String READ = "READ";
		  String UNREAD = "UNREAD";
	  }
	  
	  interface MESSAGE_TYPE {
		  String ORIGINAL = "ORIGINAL";
		  String REPLY = "REPLY";
	  }	  
  }
  
  interface USER_TRANSACTION {
    String ID = "_id";
    String USERNAME = "username";
    String TRANSACTION_ID = "tid";
    String TRANSACTION_PURPOSE = "purpose";
    String ARTIFACT = "artifact";
    String ARTIFACT_ID = "artifactid";
    String THIRD_PARTY_TID = "thirdPartyTID";
    String TIME = "time";

    interface TRANSACTION_PURPOSE_TYPE {
      String ESCROW_DEPOSIT = "ESCROW_DEPOSIT";
      String ESCROW_REVERT = "ESCROW_REVERT";
      String ESCROW_RELEASE = "ESCROW_RELEASE";
      String DIRECT_PAYMENT = "DIRECT_PAYMENT";
      String ACCOUNT_DEPOSIT = "ACCOUNT_DEPOSIT";
    }
    interface ARTIFACT_TYPE {
      String INTERVIEW = "INTERVIEW";
      String USER_DEPOSIT = "DEPOSIT";
      String THIRD_PARTY_DEPOSIT = "THIRD_PARTY_DEPOSIT";
      String WITHDRAW = "WITHDRAW";
    }
  }

  interface TICKET {
    String ID = "_id";
    String SERVICE_TYPE = "servicetype";
    String DESCRIPTION = "description";
    String CREATEDBY = "createdBy";
    String AGENT = "agent";
    String STATUS = "status";
    String TIME = "time";

    interface SERVICE_TYPES {
      String UPDATE_BALANCE = "UB";
      String TRANSACTION_HISTORY_MISSING = "THM";
      String COMPANY_ACCOUNT_ENTRY = "CAE";
      String TRANSACTION_STATUS = "TRANSACTION_STATUS";
      String TRANSACTION_DELETE = "TRANSACTION_DELETE";
    }
    interface STATUS_TYPES {
      String UNATTENDED = "UNATTENDED";
      String PENDING = "PENDING";
      String RESOLVED = "RESOLVED";
      String UNRESOLVED = "UNRESOLVED";
    }
  }

  interface COMPANY_ACCOUNT {
    String ID = "_id";
    String PURPOSE = "purpose";
    String DEBIT_OR_CREDIT = "debitOrCredit";
    String INTERVIEW_ID = "interviewId";
    String AMOUNT = "amount";
    String INITIATOR = "initiator";
    String STATUS = "status";
    String TIME = "time";

    interface TTYPE {
      String CREDIT = "CREDIT";
      String DEBIT = "DEBIT";
    }

    interface TSTATUS {
      String PENDING = "PENDING";
      String DONE = "DONE";
      String CANCELLED = "CANCELLED";
    }
    interface PURPOSE_TYPE {
      String ESCROW_DEPOSIT = "ESCROW_DEPOSIT";
      String ESCROW_REVERT = "ESCROW_REVERT";
      String ESCROW_RELEASE = "ESCROW_RELEASE";
      String DIRECT_PAYMENT = "DIRECT_PAYMENT";
      String ACCOUNT_DEPOSIT = "ACCOUNT_DEPOSIT";
    }
  }

  interface CALENDAR_EVENT {
    String ID = "_id";
    String USERID = "userid";
    String START_TIME = "starttime";
    String END_TIME = "endtime";
    String DAY_OF_YEAR = "dayofyear";
    String YEAR = "year";
    String EVENT_TYPE = "eventtype"; // : available or occupied
    String IS_RECURSIVE = "isrecursive"; // : 0 or 1
    String RECUR_DAYS = "recurdays";
    String TITLE = "title";
    String TIME = "time";

    interface EVENT_TYPES {
      String AVAILABLE = "available";
      String INTERVIEW = "interview";
    }
    interface IS_RECURSIVE_TYPES {
      boolean YES = true;
      boolean NO = false;
    }
  }

  interface INTERVIEW_SCHEDULE {
    String ID = "_id";
    String IID = "iid";
    String DATE1 = "date1";
    String DATE2 = "date2";
    String DATE3 = "date3";
    String OTHER_OPTED = "oth_opted";
    String OTHER_OPTION1 = "oth_option1";
    String OTHER_OPTION2 = "oth_option2";
    String OTHER_OPTION3 = "oth_option3";
    String FINAL_OPTION = "final_option";
    String FINAL_DATE = "finaldate";
    String TIME = "time";
  }

  interface EDUCATION {
    String DEGREE = "degree";
    String FIELDOFSTUDY = "fieldOfStudy";
    String SCHOOLNAME = "schoolname";
    String STARTYEAR = "startYear";
    String ENDYEAR = "endYear";

  }

  interface POSITION {
    String COMPANY_NAME = "companyName";
    String DESCRIPTION = "description";
    String TITLE = "title";
    String STARTYEAR = "startYear";
    String ENDYEAR = "endYear";

  }

  interface SKILL {
    String SKILL = "skill";
    String SKILL_YEAR = "skillYear";
  }

  interface ESCROW {
    String OBJECT_ID = "_id";
    String VISIBLE_ID = "visibleId";
    String AMOUNT = "amount";
    String STATUS = "status";
    String DATE = "date";
    String IID = "iid";

    interface STATUSES {
      int PENDING = 0;
      int RELEASED = 1;

    }
  }

  interface JOB {
    String DBCollection = "job";
    String TITLE = "title";
    String INTERVIEWER = "interviewer";
    String SKILLS = "skills";
    String DESCRIPTION = "description";
    String DATE = "dt";
    String STATUS = "status";
    String SALARY = "salary";
    String COMPANY_NAME = "companyname";
    String APPLY_URL = "applyurl";
    String INDUSTRY = "industry";
    String FILE = "ifile";
    String EXPERIENCE = "experience";
    String LOCATION = "location";
    String ID = "id";
  }
  
  interface TEST {
	String DBCollection = "test";
    String TITLE = "title";    
    String SKILLS = "skills";
    String DESCRIPTION = "description";
    String CREATED_DATE = "createdDate";
    String DIFFICULTY_LEVEL= "difficultyLevel";
    String DURATION = "duration";
    String NO_OF_QUESTIONS = "noOfQuestions";
    String PUBLISH = "publish";
    String ID = "id";
  }
  
  interface QUESTION {
	  String DBCollection = "question";
	  String QUESTION = "question";
	  String QUESTION_TYPE = "questionType";
	  String ANSWER_1 = "answer1";
	  String ANSWER_2 = "answer2";
	  String ANSWER_3 = "answer3";
	  String ANSWER_4 = "answer4";
	  String CORRECT_ANSWERS = "correctAnswers";
	  String TEST_ID = "testId";
	  String ID = "_id";
  } 
  
  interface USER_TEST {
	  String DBCollection = "usertest";	  	
	  String USER_ID =  "userId";		
	  String START_TIME =  "startTime";
	  String END_TIME = "endTime";		
	  String DURATION = "duration";
	  String TOTAL_QUESTIONS = "totalQuestions";
	  String MARKS_PER_QUESTION = "marksPerQuestion";
	  String MARKS_OBTAINED = "marksObtained";	  
	  String TEST_ID = "testId";
	  String ID = "_id";
  } 
}
