package com.interview.datastore;

import com.interview.framework.MongoService;
import com.mongodb.DB;

public class BaseDataStore {

  public DB db = null;

  // C:\\
  public BaseDataStore(MongoService mongoService) {
    db = mongoService.getDataBase("interviewbackend");

  }

  public BaseDataStore() {
    System.out.println("DB:" + db);
  }
}
