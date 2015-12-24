package com.interview.norbert.server;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;

import com.interview.framework.VARIABLES;
import com.interview.framework.norbert.SearchResponseSerializer;
import com.interview.framework.proto.Search;
import com.interview.framework.proto.Search.Response.Builder;
import com.interview.services.Services;
import com.linkedin.norbert.javacompat.cluster.ClusterClient;
import com.linkedin.norbert.javacompat.cluster.ClusterListener;
import com.linkedin.norbert.javacompat.cluster.Node;
import com.linkedin.norbert.javacompat.cluster.ZooKeeperClusterClient;
import com.linkedin.norbert.javacompat.network.NettyNetworkServer;
import com.linkedin.norbert.javacompat.network.NetworkServer;
import com.linkedin.norbert.javacompat.network.NetworkServerConfig;
import com.linkedin.norbert.javacompat.network.RequestHandler;

public class NorbertSearchServer implements DisposableBean {
  private int nodeId = 0;
  private String url = "";
  private Logger log = Logger.getLogger(NorbertSearchServer.class);
  private String norbertSearchServiceName = VARIABLES.NORBERT_SEARCH_SERVICE_NAME;
  private String norbertZkConnectionString = VARIABLES.NORBERT_ZK_CONNECTION_STRING;

  public NorbertSearchServer() {
    configCluster(norbertSearchServiceName, norbertZkConnectionString);
    startServer(norbertSearchServiceName, norbertZkConnectionString);
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

    ns.registerHandler(new RequestHandler<Search.Query, Search.Response>() {
      @Override
      public Search.Response handleRequest(Search.Query request) throws Exception {

        // log.debug("node: " + nodeId + " / got request: " + request.getValue());
        Map reqMap =
            Services.getInstance().getJSONUtilityService().readValue(request.getValue(), Map.class);



        Builder responseBuilder = Search.Response.newBuilder();
        Map<String, Object> solrResponses = null;

        switch (request.getType()) {
          case INTERVIEW:
            solrResponses = Services.getInstance().getSolrService().searchInterviews(
                (String) reqMap.get("searchkey"), (Integer) reqMap.get("start"),
                (Integer) reqMap.get("rows"));
            break;
          case INTERVIEWER:
            solrResponses =
                Services.getInstance().getSolrService().search((String) reqMap.get("searchkey"),
                    (Integer) reqMap.get("start"), (Integer) reqMap.get("rows"));
            break;
          case INTERVIEWEE:
            solrResponses = Services.getInstance().getSolrService().searchInterviewee(
                (String) reqMap.get("searchkey"), (Integer) reqMap.get("start"),
                (Integer) reqMap.get("rows"));
            break;
          case JOB:

            String searchKey = (String) reqMap.get("searchkey");
            int start = (Integer) reqMap.get("start");
            int rows = (Integer) reqMap.get("rows");

            solrResponses =
                Services.getInstance().getSolrService().searchJobs(searchKey, start, rows);

            break;
          default:
            return responseBuilder.build();
        }

        // Map<String, Object> document = (Map<String, Object>) solrResponses.get("JSON_DOC_LIST");
        // Iterator<String> it = document.keySet().iterator();
        // while(it.hasNext()){
        // String key = it.next();
        // HashMap<String, Object> value = (HashMap<String, Object>)document.get(key);
        //
        // String documentId = (String) value.get("username");
        // log.debug("document username : " + documentId);
        // responseBuilder.addDocs(documentId);
        // }
        String response = (null != solrResponses) ? solrResponses.toString() : null;
        log.debug("Response : " + response);

        responseBuilder.addDocs(
            Services.getInstance().getJSONUtilityService().getJSONStringOfMap(solrResponses));
        System.out.println(responseBuilder);
        return responseBuilder.build();
      }
    }, new SearchResponseSerializer());

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
        log.debug("connected to cluster: " + nodes);
      }

      public void handleClusterNodesChanged(Set<Node> nodes) {
        System.out.println("nodes changed: ");
        for (Node node : nodes) {
          log.debug("node: " + node);
        }
      }

      public void handleClusterDisconnected() {
        final Set<Node> nodes = cc.getNodes();
        log.debug("dis-connected from cluster: " + nodes);
      }

      public void handleClusterShutdown() {
        final Set<Node> nodes = cc.getNodes();
        log.debug("cluster shutdown: " + nodes);
      }
    });
    nodeId = cc.getNodes().size() + 1;

    cc.addNode(nodeId, "localhost:" + (5000 + nodeId));
    cc.markNodeAvailable(nodeId);
    cc.shutdown();
  }

  @Override
  public void destroy() throws Exception {
    log.debug("Removing SearchServer nodes from zookeeper...");
    final ClusterClient cc =
        new ZooKeeperClusterClient(norbertSearchServiceName, norbertZkConnectionString, 30000);
    cc.awaitConnectionUninterruptibly();
    cc.markNodeUnavailable(nodeId);
    cc.removeNode(nodeId);
    cc.shutdown();
    log.debug("SearchServer nodes removed from zookeeper successfully...");
  }
}
