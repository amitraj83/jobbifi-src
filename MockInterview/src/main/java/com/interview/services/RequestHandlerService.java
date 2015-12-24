package com.interview.services;


import java.util.HashMap;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.common.BackendServerRMIClient;
import com.interview.rmi.common.FileServerRMIClient;
import com.interview.rmi.common.SearchServerRMIClient;

public class RequestHandlerService {

  private BackendServerRMIClient client;
  private SearchServerRMIClient searchClient;
  private FileServerRMIClient fileServerClient;

  public FileServerRMIClient getFileServerClient() {
    return fileServerClient;
  }

  public void setFileServerClient(FileServerRMIClient fileServerClient) {
    this.fileServerClient = fileServerClient;
  }

  public SearchServerRMIClient getSearchClient() {
    return searchClient;
  }

  public void setSearchClient(SearchServerRMIClient searchClient) {
    this.searchClient = searchClient;
  }

  public BackendServerRMIClient getClient() {
    return client;
  }

  public void setClient(BackendServerRMIClient client) {
    this.client = client;
  }

  public Map<String, Object> handleRequest(Map<Object, Object> req, String type) {
    // server wise distribution.
    // only two server - one for search and other for all requests.
    if (type.equals(REQUEST_TYPES.SEARCH_INTERVIEWER)
        || type.equals(REQUEST_TYPES.SEARCH_INTERVIEWS)
        || type.equals(REQUEST_TYPES.SEARCH_INTERVIEWEE)
        || type.equals(REQUEST_TYPES.DELETE_INTERVIEW_SOLR)
        || type.equals(REQUEST_TYPES.SEARCH_JOBS)) {
      return searchClient.executeRequest(req, type);
    } else if (type.contains("FILESERVER")) {
      if (type.substring(0, "FILESERVER".length()).equals("FILESERVER"))
        return fileServerClient.executeRequest(req, type);
      else
        return new HashMap<String, Object>();
    } else {
      return client.executeRequest(req, type);
    }
  }

}
