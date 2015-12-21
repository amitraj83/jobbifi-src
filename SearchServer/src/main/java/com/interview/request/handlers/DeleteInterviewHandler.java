package com.interview.request.handlers;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;

import com.interview.services.Services;

public class DeleteInterviewHandler {

  public String deleteInterviewFromSolr(String id) {
    try {

      UpdateResponse res =
          Services.getInstance().getSolrService().getServer().deleteByQuery("id:" + id);
      System.out.println(res.getResponse());
      return res.getResponse().toString();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SolrServerException e) {
      e.printStackTrace();
    }



    return null;
  }

}
