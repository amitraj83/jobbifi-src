package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.interview.framework.DATASTORES;
import com.interview.framework.rmi.common.ISkillsDataStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

// @Service("")
public class SkillsDataStore extends UnicastRemoteObject implements ISkillsDataStore {

  /**
   * 
   */
  private static final long serialVersionUID = 8122725167612127714L;

  protected SkillsDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  public List<String> getRelatedSkills(String searchterm) throws RemoteException {
    List<String> list = new ArrayList<String>();
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.SKILLS.COLLECTION);
    DBObject query = new BasicDBObject(DATASTORES.SKILLS.SKILLS_FIELD, java.util.regex.Pattern
        .compile("^" + searchterm, java.util.regex.Pattern.CASE_INSENSITIVE));
    DBCursor cursor = collection.find(query).limit(7);
    while (cursor.hasNext()) {
      list.add(cursor.next().get("sk").toString());
    }
    return list;
  }

}
