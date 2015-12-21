package com.interview.rmi.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.proto.Search.SearchType;
import com.interview.framework.util.JSONUtilityService;
import com.interview.services.Services;

public class SearchServerRMIClient {

  private ReceiveMessageInterface rmiServer;

  public SearchServerRMIClient(String serverAddress, String serverPort, String rmiServerName) {
    // Registry registry;
    // try {
    // registry=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort)).intValue());
    // rmiServer=(ReceiveMessageInterface)(registry.lookup(rmiServerName));
    //
    // } catch(RemoteException e){
    // e.printStackTrace();
    // } catch(NotBoundException e){
    // System.err.println(e);
    // }
  }

  public Map<String, Object> executeRequest(Map<Object, Object> req, String type) {
    JSONUtilityService jsonService = Services.getInstance().getJSONUtilityService();
    com.interview.framework.proto.Search.Response response = null;
    if (type.equals(REQUEST_TYPES.SEARCH_INTERVIEWER))
      response = Services.getInstance().getSearchServerClient()
          .runQuery(jsonService.getJSONStringOfMap(req), SearchType.INTERVIEWER);// executeRequest(requestBuilder.build());
    if (type.equals(REQUEST_TYPES.SEARCH_JOBS))
      response = Services.getInstance().getSearchServerClient()
          .runQuery(jsonService.getJSONStringOfMap(req), SearchType.JOB);// executeRequest(requestBuilder.build());

    if (type.equals(REQUEST_TYPES.SEARCH_INTERVIEWS))
      response = Services.getInstance().getSearchServerClient()
          .runQuery(jsonService.getJSONStringOfMap(req), SearchType.INTERVIEW);// executeRequest(requestBuilder.build());

    if (type.equals(REQUEST_TYPES.SEARCH_INTERVIEWEE))
      response = Services.getInstance().getSearchServerClient()
          .runQuery(jsonService.getJSONStringOfMap(req), SearchType.INTERVIEWEE);

    System.out.println(response);

    String jsonSearchResponse = response.getDocs(0);
    // response = response.replace("docs:", "").trim();
    // String[] array = response.split("\"");
    // Map<String, Object> responseObject = new HashMap<String, Object>();
    // for(int i=1;i<array.length;i=i+2){
    // responseObject.put(array[i], array[i]);
    // }
    // String data = response.getData();
    try {
      Map<String, Object> responseObject = jsonService.readValue(jsonSearchResponse, Map.class);
      return responseObject;
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return (Map<String, Object>) new HashMap<String, Object>();
    // System.out.println(responseObject);
    // return responseObject;
  }
}
