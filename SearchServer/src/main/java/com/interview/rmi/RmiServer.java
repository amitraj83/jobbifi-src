package com.interview.rmi;


import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.common.ReceiveMessageInterface;
import com.interview.services.Services;

public class RmiServer extends java.rmi.server.UnicastRemoteObject
    implements ReceiveMessageInterface {

  /**
   * 
   */
  private static final long serialVersionUID = -7930656332830183067L;



  public RmiServer(String host, int port) throws RemoteException {
    String address = "";
    try {
      address = (InetAddress.getLocalHost()).toString();
    } catch (Exception e) {
      System.out.println("can't get inet address.");
    }
    System.out.println("Search Server Running at :" + address + ",port=" + port);
    try {
      Registry registry = LocateRegistry.createRegistry(port);
      registry.rebind(host, this);
    } catch (RemoteException e) {
      System.out.println("remote exception" + e);
    }
  }



  public Map<String, Object> executeRequest(Map<Object, Object> req, String reqType)
      throws RemoteException {
    Map<String, Object> map = new HashMap<String, Object>();
    String result = "default";
    if (reqType.equals(REQUEST_TYPES.SEARCH_INTERVIEWER)) {

      map.put("result", Services.getInstance().getInterviewerSearchHandler()
          .search((String) req.get("searchkey")));

    } else if (reqType.equals(REQUEST_TYPES.SEARCH_INTERVIEWS)) {
      map.put("result", Services.getInstance().getSolrService()
          .searchInterviews((String) req.get("searchkey"), 0, 50));
    } else if (reqType.equals(REQUEST_TYPES.DELETE_INTERVIEW_SOLR)) {
      result = Services.getInstance().getDeleteInterviewHandler()
          .deleteInterviewFromSolr(req.get("_id").toString());
      map.put("result", result);
    } else if (reqType.equals(REQUEST_TYPES.SEARCH_JOBS)) {
      String searchKey = (String) req.get("searchkey");
      int start = (Integer) req.get("start");
      int rows = (Integer) req.get("rows");
      map.put("result", Services.getInstance().getSolrService().searchJobs(searchKey, start, rows));
    }

    System.out.println("REceived at server : " + req);
    return map;
  }

}
