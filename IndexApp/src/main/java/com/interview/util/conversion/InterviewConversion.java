package com.interview.util.conversion;

import java.util.ArrayList;
import java.util.List;

import com.interview.framework.DATASTORES;
import com.interview.solr.pojo.SolrInterview;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class InterviewConversion {

  public static SolrInterview getInterviewForUpdate(BasicDBObject mongoInterview) {

    SolrInterview interview = getInterviewPojo(mongoInterview.getString("_id"),
        (BasicDBObject) mongoInterview.get("$set"));
    return interview;
  }

  public static SolrInterview getInterviewForInsert(BasicDBObject mongoInterview) {

    SolrInterview interview = getInterviewPojo(mongoInterview.getString("_id"), mongoInterview);
    return interview;
  }

  private static SolrInterview getInterviewPojo(String _id, DBObject row) {
    SolrInterview solrInterview = new SolrInterview();
    solrInterview.setId(_id);

    if (row.get(DATASTORES.INTERVIEW.DESCRIPTION) != null)
      solrInterview.setDescription(row.get(DATASTORES.INTERVIEW.DESCRIPTION).toString());
    solrInterview.setDoctype(DOCTYPE.INTERVIEW);
    if (row.get(DATASTORES.INTERVIEW.DATE) != null)
      solrInterview.setDt(new Long(row.get(DATASTORES.INTERVIEW.DATE).toString()));
    if (row.get(DATASTORES.INTERVIEW.INTERVIEWEE) != null)
      solrInterview.setInterviewee(row.get(DATASTORES.INTERVIEW.INTERVIEWEE).toString());

    if (row.get(DATASTORES.INTERVIEW.SKILLS) != null) {
      BasicDBList list = (BasicDBList) row.get(DATASTORES.INTERVIEW.SKILLS);
      List<String> skills = new ArrayList<String>();
      for (Object object : list) {
        skills.add(object.toString());
      }
      solrInterview.setSkills(skills);
    }

    if (row.get(DATASTORES.INTERVIEW.TITLE) != null)
      solrInterview.setTitle(row.get(DATASTORES.INTERVIEW.TITLE).toString());

    if (row.get(DATASTORES.INTERVIEW.STATUS) != null) {
      solrInterview.setStatus(Integer.parseInt(row.get(DATASTORES.INTERVIEW.STATUS).toString()));
    }

    return solrInterview;
  }
}
