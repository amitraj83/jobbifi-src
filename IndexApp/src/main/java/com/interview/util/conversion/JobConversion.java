package com.interview.util.conversion;

import java.util.ArrayList;
import java.util.List;

import com.interview.framework.DATASTORES;
import com.interview.solr.pojo.SolrJob;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class JobConversion {

	public static SolrJob getJobForUpdate(BasicDBObject mongoJob) {

		SolrJob solrJob = getJobPojo(mongoJob.getString("_id"),
				(BasicDBObject) mongoJob.get("$set"));
		return solrJob;
	}

	public static SolrJob getJobForInsert(BasicDBObject mongoJob) {

		SolrJob solrJob = getJobPojo(mongoJob.getString("_id"), mongoJob);
		return solrJob;
	}

	private static SolrJob getJobPojo(String _id, DBObject row) {
		SolrJob solrJob = new SolrJob();
		solrJob.setId(_id);
		solrJob.setDoctype(DOCTYPE.JOB);

		if (row.get(DATASTORES.JOB.DESCRIPTION) != null)
			solrJob.setDescription(row.get(DATASTORES.JOB.DESCRIPTION)
					.toString());

		if (row.get(DATASTORES.JOB.DATE) != null)
			solrJob.setDt(new Long(row.get(DATASTORES.JOB.DATE).toString()));

		if (row.get(DATASTORES.JOB.INTERVIEWER) != null)
			solrJob.setInterviewer(row.get(DATASTORES.JOB.INTERVIEWER)
					.toString());

		if (row.get(DATASTORES.JOB.SKILLS) != null) {
			BasicDBList list = (BasicDBList) row.get(DATASTORES.JOB.SKILLS);
			List<String> skills = new ArrayList<String>();
			for (Object object : list) {
				skills.add(object.toString());
			}
			solrJob.setSkills(skills);
		}

		if (row.get(DATASTORES.JOB.TITLE) != null) {
			solrJob.setTitle(row.get(DATASTORES.JOB.TITLE).toString());
		}

		if (row.get(DATASTORES.JOB.COMPANY_NAME) != null) {
			solrJob.setCompanyName(row.get(DATASTORES.JOB.COMPANY_NAME)
					.toString());
		}
		
		if (row.get(DATASTORES.JOB.LOCATION) != null) {
			solrJob.setLocation(row.get(DATASTORES.JOB.LOCATION)
					.toString());
		}

		return solrJob;
	}
}
