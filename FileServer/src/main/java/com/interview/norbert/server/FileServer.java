package com.interview.norbert.server;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import com.interview.framework.VARIABLES;
import com.interview.framework.norbert.ResponseSerializer;
import com.interview.framework.proto.Backend.Request;
import com.interview.framework.proto.Backend.Response;
import com.interview.framework.util.JSONUtilityService;
import com.interview.services.Services;
import com.linkedin.norbert.javacompat.cluster.ClusterClient;
import com.linkedin.norbert.javacompat.cluster.ClusterListener;
import com.linkedin.norbert.javacompat.cluster.Node;
import com.linkedin.norbert.javacompat.cluster.ZooKeeperClusterClient;
import com.linkedin.norbert.javacompat.network.NettyNetworkServer;
import com.linkedin.norbert.javacompat.network.NetworkServer;
import com.linkedin.norbert.javacompat.network.NetworkServerConfig;
import com.linkedin.norbert.javacompat.network.RequestHandler;

@Service
public class FileServer implements DisposableBean {
  private Logger log = Logger.getLogger(FileServer.class);
  private String norbertFileServiceName = VARIABLES.NORBERT_FILESERVER_SERVICE_NAME;
  private String norbertZkConnectionString = VARIABLES.NORBERT_ZK_CONNECTION_STRING;
  private int nodeId = 0;

  public FileServer() {
    configCluster(norbertFileServiceName, norbertZkConnectionString);
    startServer(norbertFileServiceName, norbertZkConnectionString);
    // startServer(norbertSearchServiceName, 2, norbertZkConnectionString);
  }

  private void startServer(String serviceName, String zkConnectStr) {
    NetworkServerConfig config = new NetworkServerConfig();
    config.setServiceName(serviceName);
    config.setZooKeeperConnectString(zkConnectStr);
    config.setZooKeeperSessionTimeoutMillis(30000);
    config.setRequestThreadCorePoolSize(5);
    config.setRequestThreadMaxPoolSize(10);
    config.setRequestThreadKeepAliveTimeSecs(300);

    NetworkServer ns = new NettyNetworkServer(config);

    ns.registerHandler(new RequestHandler<Request, Response>() {
      @Override
      public Response handleRequest(Request request) throws Exception {
        String requestType = request.getType();

        com.interview.request.handlers.RequestHandler handler =
            Services.getInstance().getRequestHandler(requestType.toString());

        String protoMap = request.getData();
        JSONUtilityService jsonService = Services.getInstance().getJSONUtilityService();
        Map<Object, Object> mapObject = jsonService.readValue(protoMap, Map.class);
        Map<String, Object> response = handler.handleRequest(mapObject);

        return Response.newBuilder().setData(jsonService.getJSONStringOfMap(response)).build();
      }
    }, new ResponseSerializer());

    ns.bind(nodeId);
  }

  private void configCluster(String serviceName, String zkConnectStr) {
    final ClusterClient cc = new ZooKeeperClusterClient(serviceName, zkConnectStr, 30000);
    cc.awaitConnectionUninterruptibly();

    Set<Node> nodes = cc.getNodes();
    for (Node node : nodes) {
      cc.markNodeUnavailable(node.getId());
      cc.removeNode(node.getId());
    }

    cc.addListener(new ClusterListener() {
      public void handleClusterConnected(Set<Node> nodes) {
        log.debug("File server connected to cluster: " + nodes);
      }

      public void handleClusterNodesChanged(Set<Node> nodes) {
        System.out.println("nodes changed: ");
        for (Node node : nodes) {
          log.debug("node: " + node);
        }
      }

      public void handleClusterDisconnected() {
        final Set<Node> nodes = cc.getNodes();
        log.debug("File server dis-connected from cluster: " + nodes);
      }

      public void handleClusterShutdown() {
        final Set<Node> nodes = cc.getNodes();
        log.debug("cluster shutdown: " + nodes);
      }
    });

    nodeId = cc.getNodes().size() + 1;

    cc.addNode(nodeId, "localhost:" + (5100 + nodeId));
    cc.markNodeAvailable(nodeId);

    // Set<Node> nodes = cc.getNodes();
    // for (Node node : nodes) {
    // log.debug("File server node: " + node.toString());
    // }

    cc.shutdown();
  }

  @Override
  public void destroy() throws Exception {
    log.debug("Removing Fileserver nodes from zookeeper...");
    final ClusterClient cc =
        new ZooKeeperClusterClient(norbertFileServiceName, norbertZkConnectionString, 30000);
    cc.awaitConnectionUninterruptibly();
    cc.markNodeUnavailable(nodeId);
    cc.removeNode(nodeId);
    cc.shutdown();
    log.debug("Fileserver nodes removed from zookeeper successfully...");
  }
}
