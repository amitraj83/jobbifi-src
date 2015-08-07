package com.interview.framework;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoService {

  private MongoClient mongoClient = null;

  public MongoService(String host, int port) {
    try {
      mongoClient = new MongoClient(host, port);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

  }

  public DB getDataBase(String dbName) {
    return mongoClient.getDB(dbName);
  }

  public Set<String> getCollectionNamesInDatabase(String dbName) {
    DB db = mongoClient.getDB(dbName);
    return db.getCollectionNames();
  }
}
