package com.interview.framework.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTest implements Serializable {

  private static final long serialVersionUID = -8879320806153331742L;

  private String id;
  private String testId;
  private String userId;
  private long startTime;
  private long endTime;

  /** minutes **/
  private int duration;
  private int totalQuestions;
  private double marksPerQuestion;
  private double marksObtained;

  /** specific to the User Interface **/
  /** question list */
  private List<Question> list;
  /** questionId, array of submitted answers */
  private Map<String, String[]> answersSubmitted;
  /** current test question no */
  private int currentQNo;
  /** remaining time in seconds **/
  private long remaingingTimeInSeconds;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTestId() {
    return testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public long getEndTime() {
    return endTime;
  }

  public void setEndTime(long endTime) {
    this.endTime = endTime;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getTotalQuestions() {
    return totalQuestions;
  }

  public void setTotalQuestions(int totalQuestions) {
    this.totalQuestions = totalQuestions;
  }

  public double getMarksPerQuestion() {
    return marksPerQuestion;
  }

  public void setMarksPerQuestion(double marksPerQuestion) {
    this.marksPerQuestion = marksPerQuestion;
  }

  public double getMarksObtained() {
    return marksObtained;
  }

  public void setMarksObtained(double marksObtained) {
    this.marksObtained = marksObtained;
  }

  public List<Question> getList() {
    return list;
  }

  public void setList(List<Question> list) {
    this.list = list;
  }

  public Map<String, String[]> getAnswersSubmitted() {
    return answersSubmitted;
  }

  public void setAnswersSubmitted(Map<String, String[]> answersSubmitted) {
    this.answersSubmitted = answersSubmitted;
  }

  public long getRemaingingTimeInSeconds() {
    return remaingingTimeInSeconds;
  }

  public void setRemaingingTimeInSeconds(long remaingingTimeInSeconds) {
    this.remaingingTimeInSeconds = remaingingTimeInSeconds;
  }

  public UserTest() {}

  public UserTest(String testId, String userId, long startTime, int duration, int totalQuestions,
      double marksPerQuestion, List<Question> list) {
    super();
    this.testId = testId;
    this.userId = userId;
    this.startTime = startTime;
    this.duration = duration;
    this.totalQuestions = totalQuestions;
    this.marksPerQuestion = marksPerQuestion;
    this.list = list;
    this.currentQNo = 1;
  }

  public int getCurrentQNo() {
    return currentQNo;
  }

  public void setCurrentQNo(int currentQNo) {
    this.currentQNo = currentQNo;
  }

  /* utility methods */
  public void addAnswerForQuestion(String questionId, String[] answers) {
    if (this.answersSubmitted == null) {
      this.answersSubmitted = new HashMap<String, String[]>();
    }
    this.answersSubmitted.put(questionId, answers);
  }

  public String[] getAnswerForQuestion(String questionId) {
    String[] answer = new String[0];
    if (this.answersSubmitted != null && this.answersSubmitted.get(questionId) != null) {
      answer = this.answersSubmitted.get(questionId);
    }
    return answer;
  }

  public Map<String, String> getAnswerForQuestionAsMap(String questionId) {
    String[] answer = new String[0];
    if (this.answersSubmitted != null && this.answersSubmitted.get(questionId) != null) {
      answer = this.answersSubmitted.get(questionId);
    }

    Map<String, String> ansMap = new HashMap<String, String>();
    for (String ans : answer) {
      ansMap.put(ans, ans);
    }
    return ansMap;
  }

  public void calculateScore() {
    double marks = 0.0d;
    for (Question question : this.list) {
      boolean correct = true;
      String[] correctAns = question.getCorrectAnswers();
      Map<String, String> subAns = getAnswerForQuestionAsMap(question.getId());
      for (String ans : correctAns) {
        if (subAns.get(ans) == null) {
          correct = false;
          break;
        }
      }

      if (correct) {
        marks += marksPerQuestion;
      }
    }

    this.marksObtained = marks;
  }
}
