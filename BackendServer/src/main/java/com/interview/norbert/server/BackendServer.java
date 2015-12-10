package com.interview.norbert.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;

import com.interview.framework.VARIABLES;
import com.interview.framework.util.TCPPortUtils;
import com.interview.services.Services;
import com.linkedin.norbert.javacompat.cluster.ClusterClient;
import com.linkedin.norbert.javacompat.cluster.ClusterListener;
import com.linkedin.norbert.javacompat.cluster.Node;
import com.linkedin.norbert.javacompat.cluster.ZooKeeperClusterClient;

public class BackendServer implements DisposableBean {
  private Logger log = Logger.getLogger(BackendServer.class);
  private String norbertBackendServiceName = VARIABLES.NORBERT_BACKEND_SERVICE_NAME;
  private String norbertZkConnectionString = VARIABLES.NORBERT_ZK_CONNECTION_STRING;
  private int nodeId = 0;
  private ClusterClient cc = null;

  // final ClusterClient cc = new
  // ZooKeeperClusterClient(norbertBackendServiceName,
  // norbertZkConnectionString, 30000);
  public BackendServer() {
    this.cc =
        new ZooKeeperClusterClient(norbertBackendServiceName, norbertZkConnectionString, 30000);
    this.cc.awaitConnectionUninterruptibly();
    cleanup();
    /*
     * NetworkServerConfig config = new NetworkServerConfig();
     * config.setServiceName(norbertBackendServiceName);
     * config.setZooKeeperConnectString(norbertZkConnectionString);
     * config.setZooKeeperSessionTimeoutMillis(30000); config.setRequestThreadCorePoolSize(5);
     * config.setRequestThreadMaxPoolSize(10); config.setRequestThreadKeepAliveTimeSecs(300);
     */

    configCluster(norbertBackendServiceName, norbertZkConnectionString);
    startServer(norbertBackendServiceName, norbertZkConnectionString);

  }

  private void cleanup() {
    Set<Node> nodes = cc.getNodes();
    for (Node node : nodes) {
      cc.markNodeUnavailable(node.getId());
      cc.removeNode(node.getId());
    }
  }

  private void startServer(String serviceName, String zkConnectStr) {
    Node me = cc.getNodeWithId(nodeId);
    System.out.println("node: id: " + me.getId() + " url: " + me.getUrl());

    String url = me.getUrl();
    Integer port = new Integer(url.split(":")[1]);

    try {
      Registry registry = LocateRegistry.createRegistry(port);
      Services.getInstance().getRMIRegistry().setRegistry(registry);
      // Services.getInstance().getRMIRegistry().getRegistry().bind("localhost",
      // arg1)
      log.debug("Datastore running at port: " + registry.toString());
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  private void configCluster(String serviceName, String zkConnectStr) {

    cc.awaitConnectionUninterruptibly();
    cc.addListener(new ClusterListener() {
      public void handleClusterConnected(Set<Node> nodes) {
        log.debug("connected to cluster: " + nodes);
      }

      public void handleClusterNodesChanged(Set<Node> nodes) {
        System.out.println("nodes changed: ");
        for (Node node : nodes) {
          log.debug("node: " + node);
        }
      }

      public void handleClusterDisconnected() {
        // final Set<Node> nodes = cc.getNodes();
        log.debug("dis-connected from cluster: ");
      }

      public void handleClusterShutdown() {
        // final Set<Node> nodes = cc.getNodes();
        log.debug("cluster shutdown: ");
      }
    });

    Set<Node> nodes = cc.getNodes();
    int maxAvailableId = 0;
    for (Node node : nodes) {
      if ((node.getId() > maxAvailableId)) {
        maxAvailableId = node.getId();
      }
    }

    nodeId = cc.getNodes().size() + 1;
    log.debug("Seeting node id: " + nodeId);
    int port = TCPPortUtils.getFreePort();
    log.debug("Got free port: " + port);

    cc.addNode(nodeId, "localhost:" + port);
    cc.markNodeAvailable(nodeId);

    for (Node node : nodes) {
      log.debug("BackendServer node: " + node.toString());
    }

  }

  @Override
  public void destroy() {
    try {
      log.debug("Removing Backend nodes from zookeeper...");
      this.cc.awaitConnectionUninterruptibly();
      this.cc.markNodeUnavailable(nodeId);
      this.cc.removeNode(nodeId);
      this.cc.shutdown();
      log.debug("Backend nodes removed from zookeeper successfully...");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
