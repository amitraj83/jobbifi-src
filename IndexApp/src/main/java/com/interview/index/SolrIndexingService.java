package com.interview.index;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.params.CoreAdminParams.CoreAdminAction;

public class SolrIndexingService {
  private SolrServer server;
  private static SolrIndexingService instance;
  private static String host;
  private static int port;

  public static void init(String h, int p) {
    host = h;
    port = p;
  }

  private SolrIndexingService(String host, int port) {
    this.server = new HttpSolrServer("http://" + host + ":" + port + "/solr/");
  }

  public static SolrIndexingService getInstance() {
    if (instance == null)
      instance = new SolrIndexingService(host, port);
    return instance;
  }

  public UpdateResponse addPojo(Object pojo) {
    try {
      return this.server.addBean(pojo);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SolrServerException e) {
      e.printStackTrace();
    }
    return null;
  }

  public UpdateResponse deletePojo(String id) {
    try {
      return this.server.deleteById(id);
    } catch (SolrServerException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void reload() {
    try {
      // this.server.commit();
      CoreAdminRequest adminRequest = new CoreAdminRequest();
      adminRequest.setAction(CoreAdminAction.RELOAD);
      CoreAdminResponse adminResponse = adminRequest.process(this.server);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
