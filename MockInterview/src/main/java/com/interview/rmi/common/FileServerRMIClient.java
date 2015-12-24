package com.interview.rmi.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.interview.framework.proto.Backend.Request;
import com.interview.framework.proto.Backend.Request.Builder;
import com.interview.framework.proto.Backend.Response;
import com.interview.framework.util.JSONUtilityService;
import com.interview.services.Services;

public class FileServerRMIClient {


  // private ReceiveMessageInterface rmiServer;
  public FileServerRMIClient(String serverAddress, String serverPort, String rmiServerName) {

    // Registry registry;
    // try{
    // registry=LocateRegistry.getRegistry
    // (serverAddress,(new Integer(serverPort)).intValue());
    // rmiServer=(ReceiveMessageInterface)(registry.lookup(rmiServerName));
    //
    // }
    // catch(RemoteException e){
    // e.printStackTrace();
    // }
    // catch(NotBoundException e){
    // System.err.println(e);
    // }
  }

  public Map<String, Object> executeRequest(Map<Object, Object> req, String type) {
    JSONUtilityService jsonService = Services.getInstance().getJSONUtilityService();
    Builder requestBuilder =
        Request.newBuilder().setType(type).setData(jsonService.getJSONStringOfMap(req));

    Response response =
        Services.getInstance().getFileServerClient().executeRequest(requestBuilder.build());

    String data = response.getData();
    try {
      Map<String, Object> responseObject = jsonService.readValue(data, Map.class);
      return responseObject;
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return (Map<String, Object>) new HashMap<String, Object>();
  }

}
