package com.interview.solr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.interview.framework.DATASTORES;

public class SolrService {

  private SolrServer server;

  public SolrService(String host, String port) {
    this.server = new HttpSolrServer("http://" + host + ":" + port + "/solr/");
  }

  public SolrServer getServer() {
    return this.server;
  }

  public Map<String, Object> searchInterviews(String key, int start, int rows) {
    Map<String, Object> results = new HashMap<String, Object>();
    try {
      SolrQuery query = new SolrQuery();
      if (key == null || key.isEmpty()) {
        query.setQuery("{!df=doctype}=INTERVIEW");
      } else {
        query.setQuery("{!q.op=AND}" + key + " AND {!df=doctype}=INTERVIEW");
      }
      query.addFilterQuery("status:0"); // open interview
      query.setSort(SortClause.desc(DATASTORES.INTERVIEW.DATE));
      query.setFacet(true).setFacetMinCount(1).setFacetLimit(8);

      query.setStart(start);
      query.setRows(rows);

      QueryResponse rsp = this.server.query(query);
      SolrDocumentList outdocs = rsp.getResults();
      Iterator<SolrDocument> it = outdocs.iterator();
      int count = 0;
      Map<String, Object> jsonDocList = new HashMap<String, Object>();
      while (it.hasNext()) {
        SolrDocument doc = it.next();
        Map<String, Object> jsonDoc = new HashMap<String, Object>();
        Iterator<Entry<String, Object>> iit = doc.iterator();
        while (iit.hasNext()) {
          Entry<String, Object> entry = iit.next();
          jsonDoc.put(entry.getKey(), entry.getValue());
        }

        jsonDocList.put("" + count, jsonDoc);
        count++;
      }

      results.put("JSON_DOC_LIST", jsonDocList);
      results.put("NUM_OF_RESULTS", rsp.getResults().getNumFound());

    } catch (SolrServerException e) {
      e.printStackTrace();
    }
    return results;
  }

  public Map<String, Object> search(String key, int start, int rows) {
    Map<String, Object> results = new HashMap<String, Object>();
    try {
      SolrQuery query = new SolrQuery();

      // query.setQuery("{!q.op=AND} *" + key + "* AND {!df=doctype}=INTERVIEWER");
      query.setQuery("(cv:*" + key + "* " + "OR country:*" + key + "* " + "OR skills:*" + key + "* "
          + "OR position:*" + key + "*) " + "AND {!df=doctype}=INTERVIEWER");
      query.setFacet(true).setFacetMinCount(1).setFacetLimit(8);



      query.setStart(start);
      query.setRows(rows);

      QueryResponse rsp = this.server.query(query);
      SolrDocumentList outdocs = rsp.getResults();
      Iterator<SolrDocument> it = outdocs.iterator();
      int count = 0;
      Map<String, Object> jsonDocList = new HashMap<String, Object>();
      while (it.hasNext()) {
        SolrDocument doc = it.next();
        Map<String, Object> jsonDoc = new HashMap<String, Object>();
        Iterator<Entry<String, Object>> iit = doc.iterator();
        while (iit.hasNext()) {
          Entry<String, Object> entry = iit.next();
          jsonDoc.put(entry.getKey().toString(), entry.getValue().toString());
          System.out.println(entry.getKey().toString() + "  --   " + entry.getValue().toString());
        }
        jsonDocList.put("" + count, jsonDoc);
        count++;
      }


      // String[] keys = key.split("\\s+");
      // String ORQuery = "";
      // if(keys.length > 1){
      // for(String aKey : keys){
      // ORQuery += " cv:*"+aKey+"* "
      // + "OR country:*"+aKey+"* "
      // + "OR skills:*"+aKey+"* "
      // + "OR position:*"+aKey+"* ";
      // }
      //
      // }


      results.put("JSON_DOC_LIST", jsonDocList);
      results.put("NUM_OF_RESULTS", rsp.getResults().getNumFound());

    } catch (SolrServerException e) {
      e.printStackTrace();
    }
    return results;
  }

  public Map<String, Object> searchInterviewee(String key, int start, int rows) {
    Map<String, Object> results = new HashMap<String, Object>();
    try {
      SolrQuery query = new SolrQuery();
      query.setQuery(" (cv:*" + key + "* " + "OR country:*" + key + "* " + "OR skills:*" + key
          + "* " + "OR position:*" + key + "*) " + "AND {!df=doctype}=INTERVIEWEE");
      query.setFacet(true).setFacetMinCount(1).setFacetLimit(8);

      query.setStart(start);
      query.setRows(rows);

      QueryResponse rsp = this.server.query(query);
      SolrDocumentList outdocs = rsp.getResults();
      Iterator<SolrDocument> it = outdocs.iterator();
      int count = 0;
      Map<String, Object> jsonDocList = new HashMap<String, Object>();
      while (it.hasNext()) {
        SolrDocument doc = it.next();
        Map<String, Object> jsonDoc = new HashMap<String, Object>();
        Iterator<Entry<String, Object>> iit = doc.iterator();
        while (iit.hasNext()) {
          Entry<String, Object> entry = iit.next();
          jsonDoc.put(entry.getKey().toString(), entry.getValue().toString());

          System.out.println(entry.getKey().toString() + "  --  " + entry.getValue().toString());

        }
        jsonDocList.put("" + count, jsonDoc);
        count++;
      }



      results.put("JSON_DOC_LIST", jsonDocList);
      results.put("NUM_OF_RESULTS", rsp.getResults().getNumFound());

    } catch (SolrServerException e) {
      e.printStackTrace();
    }
    return results;
  }

  /**
   * Search jobs according to the search string Search by default date
   * 
   * @param key - Search String
   * @param start - Start record for result
   * @param rows - No of search results
   */
  public Map<String, Object> searchJobs(String key, int start, int rows) {


    // if(!key.equals("''")){
    // key="*"+key+"*";
    // }
    Map<String, Object> results = new HashMap<String, Object>();
    try {
      Map<String, Object> jsonDocList = new HashMap<String, Object>();
      SolrQuery query = new SolrQuery();
      // query.setQuery("{!q.op=AND}" + key + " AND {!df=doctype}=JOB");
      query.setQuery("(title:*" + key + "* " + "OR skills:*" + key + "* " + "OR description:*" + key
          + "* " + "OR location:*" + key + "* " + "OR companyname:*" + key + "*) "
          + "AND {!df=doctype}=JOB");

      query.setSort(SortClause.desc(DATASTORES.JOB.DATE));
      query.setFacet(true).setFacetMinCount(1).setFacetLimit(8);
      query.setStart(start);
      query.setRows(rows);

      QueryResponse rsp = this.server.query(query);
      SolrDocumentList outdocs = rsp.getResults();
      Iterator<SolrDocument> it = outdocs.iterator();
      int count = 0;

      while (it.hasNext()) {
        SolrDocument doc = it.next();
        Map<String, Object> jsonDoc = new HashMap<String, Object>();
        Iterator<Entry<String, Object>> iit = doc.iterator();
        while (iit.hasNext()) {
          Entry<String, Object> entry = iit.next();
          jsonDoc.put(entry.getKey(), entry.getValue());
          System.out.println(entry.getKey() + " -- " + entry.getValue());
        }

        jsonDocList.put("" + count, jsonDoc);
        count++;
      }


      String[] keys = key.split("\\s+");
      String ORQuery = "";
      if (keys.length > 1) {
        for (String aKey : keys) {
          ORQuery += " title:*" + aKey + "* " + "OR skills:*" + aKey + "* " + "OR description:*"
              + aKey + "* " + "OR location:*" + aKey + "* " + "OR companyname:*" + aKey + "* ";
        }



        query.setQuery("( " + ORQuery + " ) " + "AND {!df=doctype}=JOB");

        query.setSort(SortClause.desc(DATASTORES.JOB.DATE));
        query.setFacet(true).setFacetMinCount(1).setFacetLimit(8);

        rsp = this.server.query(query);
        outdocs = rsp.getResults();
        it = outdocs.iterator();

        while (it.hasNext()) {
          SolrDocument doc = it.next();
          Map<String, Object> jsonDoc = new HashMap<String, Object>();
          Iterator<Entry<String, Object>> iit = doc.iterator();
          while (iit.hasNext()) {
            Entry<String, Object> entry = iit.next();
            jsonDoc.put(entry.getKey(), entry.getValue());
            System.out.println(entry.getKey() + " -- " + entry.getValue());
          }

          jsonDocList.put("" + count, jsonDoc);
          count++;
        }

      }

      results.put("JSON_DOC_LIST", jsonDocList);
      results.put("NUM_OF_RESULTS", rsp.getResults().getNumFound());

    } catch (SolrServerException e) {
      e.printStackTrace();
    }
    return results;
  }
}

