package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.Question;
import com.interview.framework.pojo.Test;
import com.interview.services.Services;

@Controller
public class QuestionController {

  @RequestMapping(value = "/admin/testquestion.do", method = {RequestMethod.GET})
  public String getTestQuestion(@RequestParam("tid") String testId, Model map) {
    map.addAttribute("tid", testId);
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("id", testId);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.TEST_SUB_REQ.GET_TEST_BY_ID);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.TEST_REQ);
    Test test = (Test) resMap.get("result");
    map.addAttribute("test", test);
    return "forward:/testquestion.jsp";
  }

  @RequestMapping(value = "/admin/testquestion.do", method = {RequestMethod.POST})
  public ModelAndView saveTestQuestion(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> resMap = new HashMap<String, Object>();

    Question question = new Question();
    question.setQuestion(request.getParameter("question"));
    question.setQuestionType(request.getParameter("questionType"));
    question.setAnswer1(request.getParameter("answer1"));
    question.setAnswer2(request.getParameter("answer2"));
    question.setAnswer3(request.getParameter("answer3"));
    question.setAnswer4(request.getParameter("answer4"));
    String correctAnswers[] = request.getParameter("correctAnswers").split(",");
    question.setCorrectAnswers(correctAnswers);
    question.setTestId(request.getParameter("testId"));

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("question", question);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.QUESTION_SUB_REQ.SAVE_QUESTION);

    resMap = Services.getInstance().getRequestHandlerService().handleRequest(reqMap,
        REQUEST_TYPES.QUESTION_REQ);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/admin/gettestquestion.do", method = {RequestMethod.GET})
  public ModelAndView getTestQuestions(@RequestParam("tid") String testId,
      @RequestParam("pagesize") int pagesize, @RequestParam("pageno") int pageno) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.QUESTION.TEST_ID, testId);
    reqMap.put(DATASTORES.PAGE_SIZE, pagesize);
    reqMap.put(DATASTORES.PAGE_NO, pageno);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.QUESTION_SUB_REQ.GET_QUESTIONS_BY_TEST_ID);

    Map<String, Object> resMap = new HashMap<String, Object>();
    resMap = Services.getInstance().getRequestHandlerService().handleRequest(reqMap,
        REQUEST_TYPES.QUESTION_REQ);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }


  @RequestMapping(value = "/admin/deletequestion.do", method = {RequestMethod.GET})
  public ModelAndView deleteTestQuestion(@RequestParam("qid") String questionId) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.QUESTION.ID, questionId);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.QUESTION_SUB_REQ.DELETE_QUESTION);
    Map<String, Object> resMap = new HashMap<String, Object>();
    resMap = Services.getInstance().getRequestHandlerService().handleRequest(reqMap,
        REQUEST_TYPES.QUESTION_REQ);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

}
