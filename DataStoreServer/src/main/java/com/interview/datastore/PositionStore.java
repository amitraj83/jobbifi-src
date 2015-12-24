package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Position;
import com.interview.framework.rmi.common.IPositionStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class PositionStore extends UnicastRemoteObject implements IPositionStore {

  /**
   * 
   */
  private static final long serialVersionUID = 7872951323467549990L;

  protected PositionStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }


  @Override
  public Position getPosition(ObjectId id) throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put("_id", id);
    DBObject row = collection.findOne(query);
    if (row != null) {
      Position position = new Position();
      position.set_id(row.get("_id").toString());
      position.setCompanyName(row.get(DATASTORES.POSITION.COMPANY_NAME).toString());
      position.setDescription(row.get(DATASTORES.POSITION.DESCRIPTION).toString());
      position.setEndYear(new Integer(row.get(DATASTORES.POSITION.ENDYEAR).toString()));
      position.setStartYear(new Integer(row.get(DATASTORES.POSITION.STARTYEAR).toString()));
      position.setTitle(row.get(DATASTORES.POSITION.TITLE).toString());
      return position;
    } else
      return null;
  }

  @Override
  public ObjectId insert(Position position) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    ObjectId _id = new ObjectId();

    DBObject row = new BasicDBObject();
    row.put("_id", _id);
    row.put(DATASTORES.POSITION.COMPANY_NAME, position.getCompanyName());
    row.put(DATASTORES.POSITION.DESCRIPTION, position.getDescription());
    row.put(DATASTORES.POSITION.ENDYEAR, position.getEndYear());
    row.put(DATASTORES.POSITION.TITLE, position.getTitle());
    row.put(DATASTORES.POSITION.STARTYEAR, position.getStartYear());

    CommandResult cr = collection.save(row).getCachedLastError();
    if (cr.ok())
      return _id;
    else
      return null;
  }

  public void deletePosition(ObjectId id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    BasicDBObject query = new BasicDBObject();
    query.put("_id", id);
    collection.remove(query);
  }

}
