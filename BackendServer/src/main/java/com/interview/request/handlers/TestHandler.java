package com.interview.request.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.Test;
import com.interview.rmi.DataStoreRegistry;

@Service
public class TestHandler extends RequestHandler {

  private static final Logger logger = Logger.getLogger(TestHandler.class);

  public TestHandler() {
    addHandler(this, REQUEST_TYPES.TEST_REQ);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    String subReq = (String) data.get(REQUEST_TYPES.SUB_REQ);

    if (null != subReq && REQUEST_TYPES.TEST_SUB_REQ.SAVE_TEST.equals(subReq)) {
      try {
        Test test = (Test) data.get("test");
        ObjectId id = DataStoreRegistry.getInstance().getTestStore().saveTest(test);
        if (id != null) {
          resMap.put("result", 1);
        } else {
          resMap.put("result", 0);
        }
      } catch (Exception e) {
        logger.error("Exception occured while saving the test.", e);
        resMap.put("result", 0);
      }
    } else if (null != subReq && REQUEST_TYPES.TEST_SUB_REQ.GET_ALL_TESTS.equals(subReq)) {
      try {
        List<Test> testList = DataStoreRegistry.getInstance().getTestStore().getAllTests();
        resMap.put("result", testList);
      } catch (Exception e) {
        logger.error("Exception occured while get all the test.", e);
        resMap.put("result", null);
      }

    } else if (null != subReq && REQUEST_TYPES.TEST_SUB_REQ.GET_ALL_PUBLISH_TESTS.equals(subReq)) {
      try {
        List<Test> testList = DataStoreRegistry.getInstance().getTestStore().getAllPublishTests();
        resMap.put("result", testList);
      } catch (Exception e) {
        logger.error("Exception occured while get all publish test.", e);
        resMap.put("result", null);
      }

    } else if (null != subReq && REQUEST_TYPES.TEST_SUB_REQ.GET_TEST_BY_ID.equals(subReq)) {
      String id = null;
      try {
        id = (String) data.get("id");
        Test test = DataStoreRegistry.getInstance().getTestStore().getTest(id);
        resMap.put("result", test);
      } catch (Exception e) {
        logger.error("Exception occured while getting test. Test Id : " + id, e);
        resMap.put("result", null);
      }
    } else if (null != subReq && REQUEST_TYPES.TEST_SUB_REQ.DELETE_TEST.equals(subReq)) {
      String id = null;
      try {
        id = (String) data.get("id");
        int result = DataStoreRegistry.getInstance().getTestStore().deleteTest(id);
        resMap.put("result", result);
      } catch (Exception e) {
        logger.error("Exception occured while deleting test. Test Id : " + id, e);
        resMap.put("result", null);
      }
    } else if (null != subReq && REQUEST_TYPES.TEST_SUB_REQ.PUBLISH_TEST.equals(subReq)) {
      String id = null;
      try {
        id = (String) data.get("id");
        int result = DataStoreRegistry.getInstance().getTestStore().publishTest(id);
        resMap.put("result", result);
      } catch (Exception e) {
        logger.error("Exception occured while publishing test. Test Id : " + id, e);
        resMap.put("result", null);
      }
    }
    return resMap;
  }
}
