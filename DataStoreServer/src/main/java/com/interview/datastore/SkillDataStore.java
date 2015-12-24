package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Skill;
import com.interview.framework.rmi.common.ISkillDataStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class SkillDataStore extends UnicastRemoteObject implements ISkillDataStore {

  /**
   * 
   */
  private static final long serialVersionUID = 1001269397809510720L;

  protected SkillDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  public ObjectId insert(Skill skill) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put("_id", _id);
    dbObject.put(DATASTORES.SKILL.SKILL, skill.getSkill());
    dbObject.put(DATASTORES.SKILL.SKILL_YEAR, skill.getSkillYear());
    CommandResult cr = collection.save(dbObject).getCachedLastError();
    if (cr.ok()) {
      return _id;
    } else {
      return null;
    }
  }

  public Skill getSkill(ObjectId _id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);
    Skill skill = new Skill();
    DBObject row = collection.findOne(query);
    if (row != null) {
      skill.set_id(row.get("_id").toString());
      skill.setSkill(row.get(DATASTORES.SKILL.SKILL).toString());
      skill.setSkillYear(Integer.parseInt(row.get(DATASTORES.SKILL.SKILL_YEAR).toString()));
      return skill;
    } else {
      return null;
    }
  }

  public void deleteSkill(ObjectId id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    BasicDBObject query = new BasicDBObject();
    query.put("_id", id);
    collection.remove(query);
  }
}
