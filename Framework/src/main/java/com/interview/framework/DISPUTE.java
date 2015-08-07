package com.interview.framework;

public interface DISPUTE {

  String CRETE_DISPUTE_EMAIL_SUBJECT = "[Mockinterview] A new dispute has been created";
  String CREATE_DISPUTE_EMAIL_BODY = "Dear $user$, \r\n\r\n Your interview \"$ititle$\" "
      + "has been marked in dispute by $disputer$. A detailed reason "
      + "for dispute is below: \r\n\r\n " + "$message$\r\n\r\n"
      + "The dispute id is $did$ that you can use in further communication "
      + "with us. The dispute will last for 4 days and a decision will be "
      + "taken on the last day. You have 4 days to present you "
      + "arguments and supportive document.\r\n\r\n" + "Thanks,\r\n" + "Mock Interview Team";
}
