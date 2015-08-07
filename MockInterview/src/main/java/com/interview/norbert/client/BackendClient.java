package com.interview.norbert.client;

import org.apache.log4j.Logger;

import com.interview.framework.proto.Backend.Request;
import com.interview.framework.proto.Backend.Response;
import com.linkedin.norbert.javacompat.network.NetworkClient;

public class BackendClient {
  private NetworkClient nc = null;
  private Logger log = Logger.getLogger(BackendClient.class);

  public BackendClient() {}

  public Response executeRequest(Request request) {



    /*
     * // TODO: Find a less resource consuming way to do this. NetworkClientConfig config = new
     * NetworkClientConfig();
     * 
     * config.setServiceName(VARIABLES.NORBERT_BACKEND_SERVICE_NAME);
     * config.setZooKeeperConnectString(VARIABLES.NORBERT_ZK_CONNECTION_STRING);
     * config.setZooKeeperSessionTimeoutMillis(30000); config.setConnectTimeoutMillis(1000);
     * config.setWriteTimeoutMillis(150); config.setMaxConnectionsPerNode(5);
     * config.setStaleRequestTimeoutMins(10); config.setStaleRequestCleanupFrequencyMins(10);
     * 
     * nc = new NettyNetworkClient(config, new RoundRobinLoadBalancerFactory());
     * 
     * Future<Response> responseFuture = this.nc.sendRequest(request, new ResponseSerializer());
     * 
     * Response appendResp = null; try { appendResp = responseFuture.get();
     * log.debug("got search response: " + appendResp); return appendResp; } catch
     * (InterruptedException e) { e.printStackTrace(); } catch (ExecutionException e) {
     * e.printStackTrace(); }
     */
    return Response.newBuilder().setData("ERROR!").build();
  }
}
