package com.interview.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.Question;
import com.interview.framework.pojo.Test;
import com.interview.framework.pojo.UserTest;
import com.interview.services.Services;

@Controller
public class TestController {
	
	public static final String UserTest__ = "UserTest__";
	
	@RequestMapping(value="/admin/createtest.do", method={RequestMethod.POST})
	public ModelAndView postCreateTest(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<Object, Object> reqMap = new HashMap<Object, Object>();		
		Test test = new Test();		
		test.setTitle(request.getParameter("title"));
		test.setDescription(request.getParameter("description"));
		test.setCreatedDate(new Date().getTime());
		test.setDifficultyLevel(Integer.parseInt(request.getParameter("difficultyLevel")));
		test.setDuration(Integer.parseInt(request.getParameter("duration")));
		test.setNoOfQuestions(Integer.parseInt(request.getParameter("noOfQuestions")));
		test.setPublish(false); // default false for creation
		String skillsArray[] = request.getParameter("skills").split(",");		
		List<String> skills = new ArrayList<String>(); 
		for(int i=0; i < skillsArray.length; i++){
			skills.add(skillsArray[i]); 
		}		
		test.setSkills(skills);
		reqMap.put("test", test);		
		reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.TEST_SUB_REQ.SAVE_TEST);		
		resMap = Services.getInstance().getRequestHandlerService().handleRequest(reqMap, REQUEST_TYPES.TEST_REQ);		
		return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
			        .getJSONStringOfMap(resMap));
	}
	
	@RequestMapping(value="/admin/tests.do", method={RequestMethod.GET})
	public String getTests(Model map){			
		return "forward:/admintests.jsp";
	}
	
	@RequestMapping(value="/admin/gettests.do")
	public ModelAndView getTestList(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<Object, Object> reqMap = new HashMap<Object, Object>();
		reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.TEST_SUB_REQ.GET_ALL_TESTS);
		resMap = Services.getInstance().getRequestHandlerService()
					.handleRequest(reqMap, REQUEST_TYPES.TEST_REQ);			
		return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
		        .getJSONStringOfMap(resMap));
	}
	
	@RequestMapping(value="/admin/publishtest.do", method=RequestMethod.GET)
	public ModelAndView publishTest(HttpServletRequest request, HttpServletResponse response){
		
		// check if the questions are present in the test
		String testId = request.getParameter("tid");
		Map<Object, Object> reqMap = new HashMap<Object, Object>();
		reqMap.put(DATASTORES.QUESTION.TEST_ID, testId);		
		reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.QUESTION_SUB_REQ.GET_QUESTIONS_COUNT_BY_TEST_ID);		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap = Services.getInstance().getRequestHandlerService().handleRequest(reqMap, REQUEST_TYPES.QUESTION_REQ);
		long count = (Long) resMap.get("result");
		
		reqMap.put("id", testId);
		reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.TEST_SUB_REQ.GET_TEST_BY_ID);
		resMap = Services.getInstance().getRequestHandlerService()
					.handleRequest(reqMap, REQUEST_TYPES.TEST_REQ);
		Test test = (Test) resMap.get("result");
		
		resMap = new HashMap<String, Object>();		 	
		if(test.getNoOfQuestions() > count){
			int remain =  (int) (test.getNoOfQuestions() - count);
			resMap.put("error", "You have to add "+remain+" more questions to publish the test.");
			return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
			        .getJSONStringOfMap(resMap));
		}
		
		resMap = new HashMap<String, Object>();
		reqMap = new HashMap<Object, Object>();	
		reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.TEST_SUB_REQ.PUBLISH_TEST);
		reqMap.put("id", testId);
		resMap = Services.getInstance().getRequestHandlerService()
					.handleRequest(reqMap, REQUEST_TYPES.TEST_REQ);		
		return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
		        .getJSONStringOfMap(resMap));
	}
		
	// public pages
	@RequestMapping(value="/gettests.do")
	public ModelAndView getTests(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<Object, Object> reqMap = new HashMap<Object, Object>();	
		reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.TEST_SUB_REQ.GET_ALL_PUBLISH_TESTS);		
		resMap = Services.getInstance().getRequestHandlerService()
					.handleRequest(reqMap, REQUEST_TYPES.TEST_REQ);		
		return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
		        .getJSONStringOfMap(resMap));
	}
	
	@RequestMapping(value="/testdetails.do", method={RequestMethod.GET})
	public String getTestDetails(HttpServletRequest request, HttpServletResponse response, Model map) {		
		String id = request.getParameter("id");
		Map<Object, Object> reqMap = new HashMap<Object, Object>();
		reqMap.put("id", id);
		reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.TEST_SUB_REQ.GET_TEST_BY_ID);
		Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
					.handleRequest(reqMap, REQUEST_TYPES.TEST_REQ);
		Test test = (Test) resMap.get("result");
		map.addAttribute("test", test);
		return "forward:/testdetails.jsp";
	}
	
	@RequestMapping(value="/starttest.do", method={RequestMethod.GET})
	public String startTest(HttpServletRequest request, HttpServletResponse response){
							
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();		
		String testId = request.getParameter("tid");
		
		/*
		 if("anonymousUser".equals(userId)) {
			//request.getSession().setAttribute("loginmsg", "You need to login to give the test");
			return "redirect:/testdetails.do?id=" + testId;
		 }
		*/
		
		UserTest userTest = (UserTest) request.getSession().getAttribute(UserTest__);
		if(null != userTest){
			
			// validate the exam time and remove
			long time = userTest.getStartTime() + (userTest.getDuration()*60*1000);				
			int a = (int) ((time - new Date().getTime())/1000);			
			userTest.setRemaingingTimeInSeconds(a);
			if(0 == a){
				request.getSession().getAttribute(UserTest__);
				return "forward:/tests.html";
			}
			
		} else {
			
			Map<Object, Object> reqMap = new HashMap<Object, Object>();
			reqMap.put("id", testId);
			reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.TEST_SUB_REQ.GET_TEST_BY_ID);
			Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
						.handleRequest(reqMap, REQUEST_TYPES.TEST_REQ);
			Test test = (Test) resMap.get("result");
					
			reqMap = new HashMap<Object, Object>();
			reqMap.put(DATASTORES.QUESTION.TEST_ID, testId);		
			reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.QUESTION_SUB_REQ.GET_QUESTIONS_FOR_TEST);		
			resMap = new HashMap<String, Object>();
			resMap = Services.getInstance().getRequestHandlerService().handleRequest(reqMap, REQUEST_TYPES.QUESTION_REQ);				
			
			@SuppressWarnings("unchecked")
			List<Question> list = (List<Question>) resMap.get("result");
			
			int duration = test.getDuration();
			double marksPerQuestion = 1d;
			int totalQuestions = list.size();
			userTest = new UserTest(testId, userId, new Date().getTime(), duration, totalQuestions,
					marksPerQuestion, list);		
			
			// calculate remaining time 
			long time = userTest.getStartTime() + (userTest.getDuration()*60*1000);				
			int a = (int) ((time - new Date().getTime())/1000);			
			userTest.setRemaingingTimeInSeconds(a);			
			request.getSession().setAttribute(UserTest__, userTest);									
		}
		
		return "forward:/starttest.jsp";
	}
			
	@RequestMapping(value="/getTQuestion.do", method={RequestMethod.GET})
	public ModelAndView getTestQuestion(HttpServletRequest request, HttpServletResponse response){
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		UserTest userTest = (UserTest) request.getSession().getAttribute(UserTest__);
		Question question =	null;
		if(qno <= userTest.getTotalQuestions()){
			question = userTest.getList().get(qno-1);
		} else {
			question = userTest.getList().get(qno-2);
		}
		String[] subans = userTest.getAnswerForQuestion(question.getId());
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("question", getQuestion(question));
		resMap.put("subans", subans);		
		return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
		        .getJSONStringOfMap(resMap));
	}
	
	@RequestMapping(value="/submitanswer.do", method={RequestMethod.POST})
	public ModelAndView submitAnswer(HttpServletRequest request, HttpServletResponse response){
				
		UserTest userTest = (UserTest) request.getSession().getAttribute(UserTest__);
		if(null == userTest){
			return new ModelAndView("redirect:/");
		}
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		String qid = request.getParameter("qid");
		String[] answers = request.getParameter("sans").split(",");
				
		userTest.addAnswerForQuestion(qid, answers);
		userTest.setCurrentQNo(qno); // to restart test where left
		
		Question question =	null;		
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(qno <= userTest.getTotalQuestions()){
			question = userTest.getList().get(qno-1);
		} else {
			question = userTest.getList().get(qno-2);
		}
		
		String[] subans = userTest.getAnswerForQuestion(question.getId());
		resMap.put("question", getQuestion(question));
		resMap.put("subans", subans);
		
		return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
		        .getJSONStringOfMap(resMap));
	}
	
	@RequestMapping(value="/submittest.do", method={RequestMethod.POST})
	public ModelAndView submitTest(HttpServletRequest request, HttpServletResponse response){
			
		UserTest userTest = (UserTest) request.getSession().getAttribute(UserTest__);
		userTest.setEndTime(new Date().getTime()); 
		userTest.calculateScore();
		
		Map<String, Object> resMap = new HashMap<String, Object>();		
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		if(!"anonymousUser".equals(userId)) {
			Map<Object, Object> reqMap = new HashMap<Object, Object>();
			reqMap.put("usertest", userTest);		
			reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.USER_TEST_SUB_REQ.SAVE_USER_TEST);					
			resMap = Services.getInstance().getRequestHandlerService()
						.handleRequest(reqMap, REQUEST_TYPES.USER_TEST_REQ);
		}
						
		request.getSession().removeAttribute(UserTest__);
		resMap = new HashMap<String, Object>();
		resMap.put("score", userTest.getMarksObtained());
		
		return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
		        .getJSONStringOfMap(resMap));
	}
	
	/**
	 * Create the Proxy object to persist the correct answers in original object
	 * */
	private Question getQuestion(Question question){
		Question proxyQuestion = new Question();
		proxyQuestion.setId(question.getId());
		proxyQuestion.setQuestion(question.getQuestion());
		proxyQuestion.setAnswer1(question.getAnswer1());
		proxyQuestion.setAnswer2(question.getAnswer2());
		proxyQuestion.setAnswer3(question.getAnswer3());
		proxyQuestion.setAnswer4(question.getAnswer4());
		proxyQuestion.setQuestionType(question.getQuestionType());
		proxyQuestion.setTestId(question.getTestId());
		return proxyQuestion;
	}	
}