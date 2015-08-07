package com.interview.norbert.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.interview.framework.VARIABLES;
import com.interview.framework.norbert.SearchResponseSerializer;
import com.interview.framework.proto.Search;
import com.interview.framework.proto.Search.Response;
import com.interview.framework.proto.Search.SearchType;
import com.linkedin.norbert.javacompat.network.NettyNetworkClient;
import com.linkedin.norbert.javacompat.network.NetworkClient;
import com.linkedin.norbert.javacompat.network.NetworkClientConfig;
import com.linkedin.norbert.javacompat.network.RoundRobinLoadBalancerFactory;

public class SearchClient {
  private NetworkClient nc = null;

  public SearchClient() {}

  public Response runQuery(String query, SearchType type) {

    // TODO: Find a less resource consuming way to do this.
    NetworkClientConfig config = new NetworkClientConfig();

    config.setServiceName(VARIABLES.NORBERT_SEARCH_SERVICE_NAME);
    config.setZooKeeperConnectString(VARIABLES.NORBERT_ZK_CONNECTION_STRING);
    config.setZooKeeperSessionTimeoutMillis(30000);
    config.setConnectTimeoutMillis(1000);
    config.setWriteTimeoutMillis(150);
    config.setMaxConnectionsPerNode(5);
    config.setStaleRequestTimeoutMins(10);
    config.setStaleRequestCleanupFrequencyMins(10);

    nc = new NettyNetworkClient(config, new RoundRobinLoadBalancerFactory());

    final Search.Query request = Search.Query.newBuilder().setValue(query).setType(type).build();
    Future<Search.Response> pingFuture =
        this.nc.sendRequest(request, new SearchResponseSerializer());

    Search.Response appendResp = null;
    try {
      appendResp = pingFuture.get();
      System.out.println("got search response: " + appendResp);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    return appendResp;
  }
}
