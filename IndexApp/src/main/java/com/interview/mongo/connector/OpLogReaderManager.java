package com.interview.mongo.connector;

import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.interview.index.SolrIndexingService;
import com.interview.solr.pojo.SolrInterview;
import com.interview.solr.pojo.SolrInterviewer;
import com.interview.solr.pojo.SolrJob;
import com.interview.util.conversion.InterviewConversion;
import com.interview.util.conversion.InterviewerConversion;
import com.interview.util.conversion.JobConversion;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class OpLogReaderManager {

  private final Logger log = Logger.getLogger(OpLogReaderManager.class);

  @SuppressWarnings("deprecation")
  public void start(String host, int port) {

    try {
      Mongo mongo = new Mongo(host, port);
      BlockingQueue<OplogLine> queue = new LinkedBlockingQueue<OplogLine>();
      OpLogReader reader = new OpLogReader(mongo, queue);
      ExecutorService executor = Executors.newSingleThreadExecutor();
      executor.execute(reader);
      executor.shutdown();
      try {
        while (!executor.isTerminated()) {

          OplogLine line = queue.poll(10, TimeUnit.SECONDS);
          System.out.println("Line info: " + line);
          if (line != null) {
            System.out.println("Line info: " + " Op:" + line.getOperation() + " data:"
                + line.getData().toString());
            if (line.getOperation() != MongoOplogOperation.NoOp) {
              log.info("Line info: " + " Op:" + line.getOperation() + " data:"
                  + line.getData().toString());

              if (line.getNameSpace().equals(MongoCollections.INTERVIEW)) {
                processInterviewDocument(line);
              } else if (line.getNameSpace().equals(MongoCollections.INTERVIEWER)) {

                processInterviewerDocument(line);
              } else if (line.getNameSpace().equals(MongoCollections.JOB)) {
                processJobDocument(line);
              }
              SolrIndexingService.getInstance().reload();
            }
          }
        }
      } catch (InterruptedException e) {
        log.info("time to go.");
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (MongoException e) {
      e.printStackTrace();
    }

  }

  private void processJobDocument(OplogLine line) {
    if (line.getOperation() == MongoOplogOperation.Insert) {
      SolrJob solrjob = JobConversion.getJobForInsert(line.getData());
      SolrIndexingService.getInstance().addPojo(solrjob);
    } else if (line.getOperation() == MongoOplogOperation.Update) {
      SolrJob solrjob = JobConversion.getJobForUpdate(line.getData());
      SolrIndexingService.getInstance().addPojo(solrjob);
    } else if (line.getOperation() == MongoOplogOperation.Delete) {
      SolrIndexingService.getInstance().deletePojo(line.getData().getString("_id"));
    }
  }

  private void processInterviewerDocument(OplogLine line) {
    if (line.getOperation() == MongoOplogOperation.Insert) {
      SolrInterviewer solrInterviewer =
          InterviewerConversion.getInterviewerForInsert(line.getData());
      SolrIndexingService.getInstance().addPojo(solrInterviewer);
    } else if (line.getOperation() == MongoOplogOperation.Update) {
      SolrInterviewer solrInterviewer =
          InterviewerConversion.getInterviewerForUpdate(line.getData());
      SolrIndexingService.getInstance().addPojo(solrInterviewer);
    } else if (line.getOperation() == MongoOplogOperation.Delete) {
      SolrIndexingService.getInstance().deletePojo(line.getData().getString("_id"));
    }
  }

  private void processInterviewDocument(OplogLine line) {
    if (line.getOperation() == MongoOplogOperation.Insert) {
      SolrInterview solrInterview = InterviewConversion.getInterviewForInsert(line.getData());
      SolrIndexingService.getInstance().addPojo(solrInterview);
    } else if (line.getOperation() == MongoOplogOperation.Update) {
      SolrInterview solrInterview = InterviewConversion.getInterviewForUpdate(line.getData());
      SolrIndexingService.getInstance().addPojo(solrInterview);
    } else if (line.getOperation() == MongoOplogOperation.Delete) {
      SolrIndexingService.getInstance().deletePojo(line.getData().getString("_id"));
    }
  }

}
