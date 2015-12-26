package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Job;
import com.interview.framework.pojo.JobApplication;
import com.interview.rmi.DataStoreRegistry;

@Service
public class JobHandler extends RequestHandler {
  public JobHandler() {
    addHandler(this, REQUEST_TYPES.JOB);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    String SUB_REQ = data.get(REQUEST_TYPES.SUB_REQ).toString();
    if (null != SUB_REQ && REQUEST_TYPES.POST_JOB.equals(SUB_REQ)) {
      Job job = (Job) data.get("job");
      try {
        DataStoreRegistry.getInstance().getJobStore().saveJob(job);
        resMap.put("status", 1);
      } catch (RemoteException e) {
        e.printStackTrace();
        resMap.put("status", -1);
      }
    } else if (null != SUB_REQ && REQUEST_TYPES.GET_JOBS_OFFERED.equals(SUB_REQ)) {
      try {
        String interviewer = (String) data.get(DATASTORES.JOB.INTERVIEWER);
        List<Job> jobs = DataStoreRegistry.getInstance().getJobStore().getJobsOffered(interviewer);
        for (Job job : jobs) {
          List<JobApplication> jobApplications = DataStoreRegistry.getInstance()
              .getJobApplicationStore().getJobApplicationsByJobId(job.getId());
          for (JobApplication jobApplication : jobApplications) {
            if (null != jobApplication.getCvFileId() && !"".equals(jobApplication.getCvFileId())) {
              jobApplication
                  .setUploadedFile(DataStoreRegistry.getInstance().getUploadedFileDataStore()
                      .getUploadedFile(new ObjectId(jobApplication.getCvFileId())));
            }
            jobApplication.setRating(DataStoreRegistry.getInstance().getRatingStore()
                .getAvgRating(jobApplication.getApplicantId()));
            jobApplication.setReviewCount(DataStoreRegistry.getInstance().getRatingStore()
                .getReviewsCount(jobApplication.getApplicantId()));
            Map<String, Object> userMap = DataStoreRegistry.getInstance().getInterviewerDataStore()
                .getUserExternalInfo(jobApplication.getApplicantId());
            jobApplication.setProfilePic((String) userMap.get(USER.PROFILE_PIC));
          }
          job.setJobApplications(jobApplications);
        }
        resMap.put("jobs", jobs);
      } catch (RemoteException e) {
        e.printStackTrace();
        resMap.put("jobs", null);
      }
    } else if (null != SUB_REQ && REQUEST_TYPES.SEARCH_JOB_INFO.equals(SUB_REQ)) {
    	Map<String, Object> result = (Map<String, Object>) data.get("result");
    	try {
        Iterator<String> it = result.keySet().iterator();
        try{
          while (it.hasNext()) {
        	
	          Map<String, Object> obj = (Map<String, Object>) result.get(it.next());
	          Job job = DataStoreRegistry.getInstance().getJobStore().getJob((String) obj.get("id"));
	          obj.put("salary", null == job ? "" : job.getSalary());
	          obj.put("experience", null == job ? "" : job.getExperience());
	          obj.put("location", null == job ? "" : job.getLocation());
	          Map<String, Object> idata = DataStoreRegistry.getInstance().getInterviewerDataStore()
	              .getUserInfo((String) obj.get("interviewer"));
	          obj.put("rating", idata.get(USER.RATING));
	          obj.put("profilepic", idata.get(USER.PROFILE_PIC));
         }
        }
        catch(Exception ex){}
        resMap.put("result", result);
      } catch (Exception e) {
        e.printStackTrace();
        resMap.put("result", result);
      }
    } else if (null != SUB_REQ && REQUEST_TYPES.GET_JOB.equals(SUB_REQ)) {
      try {
        String jobid = (String) data.get(DATASTORES.JOB.ID);
        Job job = DataStoreRegistry.getInstance().getJobStore().getJob(jobid);
        if (null != job) {
          resMap.put("job", job);
          Map<String, Object> idata = DataStoreRegistry.getInstance().getInterviewerDataStore()
              .getUserInfo(job.getInterviewer());
          resMap.put("rating", idata.get(USER.RATING));
          resMap.put("profilepic", idata.get(USER.PROFILE_PIC));
        } else {
          resMap.put("job", null);
        }
      } catch (Exception e) {
        e.printStackTrace();
        resMap.put("job", null);
      }
    } else if (null != SUB_REQ && REQUEST_TYPES.UPDATE_JOB.equals(SUB_REQ)) {
      try {
        Job job = (Job) data.get("job");
        String jobid = (String) job.getId();
        try {
          DataStoreRegistry.getInstance().getJobStore().updateJob(jobid, job);;
          resMap.put("status", 1);
        } catch (RemoteException e) {
          resMap.put("status", -1);
        }
      } catch (Exception e) {
        e.printStackTrace();
        resMap.put("job", null);
      }
    }
    return resMap;
  }
}
