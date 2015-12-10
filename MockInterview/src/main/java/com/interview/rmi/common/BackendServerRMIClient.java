package com.interview.rmi.common;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.interview.framework.VARIABLES;
import com.linkedin.norbert.javacompat.cluster.Node;
import com.linkedin.norbert.javacompat.cluster.ZooKeeperClusterClient;


public class BackendServerRMIClient {

  private ReceiveMessageInterface rmiServer;
  private Logger log = Logger.getLogger(BackendServerRMIClient.class);
  private Registry registry;
  private ZooKeeperClusterClient cc = null;
  private String rmiServerName = "";

  public BackendServerRMIClient(String serverAddress, String serverPort, String rmiServerName) {
    this.rmiServerName = rmiServerName;
    String norbertSearchServiceName = VARIABLES.NORBERT_BACKEND_SERVICE_NAME;
    String norbertZkConnectionString = VARIABLES.NORBERT_ZK_CONNECTION_STRING;


    this.cc =
        new ZooKeeperClusterClient(norbertSearchServiceName, norbertZkConnectionString, 30000);
    cc.awaitConnectionUninterruptibly();
    log.debug("String of cluster client: " + cc.toString());
    if (cc.isConnected()) {
      allocateRMIServer();
    } else {
      log.debug("not connected to cluster");
    }

    /*
     * Registry registry; try{ registry=LocateRegistry.getRegistry (serverAddress,(new
     * Integer(serverPort)).intValue());
     * rmiServer=(ReceiveMessageInterface)(registry.lookup(rmiServerName));
     * 
     * } catch(RemoteException e){ e.printStackTrace(); } catch(NotBoundException e){
     * System.err.println(e); }
     */
  }

  private void allocateRMIServer() {
    try {
      Set<Node> nodes = cc.getNodes();
      for (Node node : nodes) {
        log.debug("Tomcat connecting backend node: " + node.toString());
        int port = new Integer(node.getUrl().split(":")[1]);
        log.debug("Backend connecting datastore node at port : " + port);

        this.registry = LocateRegistry.getRegistry("127.0.0.1", port);
        System.out.println("Registry:" + this.registry);
        if (this.registry != null) {
          final String[] boundNames = this.registry.list();
          log.debug("Names bound to RMI registry at host localhost and port " + port + ":");
          for (final String name : boundNames) {
            log.debug("\t" + name);
          }

          log.debug("Backend Registry for datastore: " + this.registry.toString());

          this.rmiServer = (ReceiveMessageInterface) (registry.lookup(rmiServerName));
        } else {
          log.debug("Datastore not running at the moment. Please start datastore.");

          //
        }

      }

    } catch (AccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public Map<String, Object> executeRequest(Map<Object, Object> req, String type) {
    // JSONUtilityService jsonService = Services.getInstance().getJSONUtilityService();
    // Builder requestBuilder =
    // Request.newBuilder().setType(type).setData(jsonService.getJSONStringOfMap(req));
    //
    // Response response =
    // Services.getInstance().getBackendClient().executeRequest(requestBuilder.build());
    //
    // String data = response.getData();
    // try {
    // Map<String, Object> responseObject = jsonService.readValue(data, Map.class);
    // return responseObject;
    // } catch (JsonParseException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (JsonMappingException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // return (Map<String, Object>) new HashMap<String, Object>();

    Map<String, Object> map = null;
    try {
      allocateRMIServer();
      map = rmiServer.executeRequest(req, type);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return map;
  }

}
