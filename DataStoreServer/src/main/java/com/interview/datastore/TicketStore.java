package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Ticket;
import com.interview.framework.rmi.common.ITicketStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class TicketStore extends UnicastRemoteObject implements ITicketStore {

  protected TicketStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public String save(Ticket ticket) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    ObjectId _id = new ObjectId();

    DBObject row = new BasicDBObject();
    row.put(DATASTORES.TICKET.AGENT, ticket.getAgent());
    row.put(DATASTORES.TICKET.CREATEDBY, ticket.getCreatedBy());
    row.put(DATASTORES.TICKET.DESCRIPTION, ticket.getDescription());
    row.put(DATASTORES.TICKET.ID, _id);
    row.put(DATASTORES.TICKET.SERVICE_TYPE, ticket.getServicetype());
    row.put(DATASTORES.TICKET.STATUS, ticket.getStatus());
    row.put(DATASTORES.TICKET.TIME, ticket.getTime());

    CommandResult cr = collection.save(row).getCachedLastError();
    if (cr.ok())
      return _id.toString();
    else
      return null;
  }

}
