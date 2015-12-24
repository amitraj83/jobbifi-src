package com.interview.framework.pojo;

import java.io.Serializable;
import java.util.Arrays;

public class Question implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;

  private String testId;

  private String question;

  private String questionType;

  private String answer1;

  private String answer2;

  private String answer3;

  private String answer4;

  private String[] correctAnswers;

  public Question() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getQuestionType() {
    return questionType;
  }

  public void setQuestionType(String questionType) {
    this.questionType = questionType;
  }

  public String getAnswer1() {
    return answer1;
  }

  public void setAnswer1(String answer1) {
    this.answer1 = answer1;
  }

  public String getAnswer2() {
    return answer2;
  }

  public void setAnswer2(String answer2) {
    this.answer2 = answer2;
  }

  public String getAnswer3() {
    return answer3;
  }

  public void setAnswer3(String answer3) {
    this.answer3 = answer3;
  }

  public String getAnswer4() {
    return answer4;
  }

  public void setAnswer4(String answer4) {
    this.answer4 = answer4;
  }

  public String getTestId() {
    return testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  public String[] getCorrectAnswers() {
    return correctAnswers;
  }

  public void setCorrectAnswers(String[] correctAnswers) {
    this.correctAnswers = correctAnswers;
  }

  @Override
  public String toString() {
    return "Question [id=" + id + ", testId=" + testId + ", question=" + question
        + ", questionType=" + questionType + ", answer1=" + answer1 + ", answer2=" + answer2
        + ", answer3=" + answer3 + ", answer4=" + answer4 + ", correctAnswers="
        + Arrays.toString(correctAnswers) + "]";
  }
}
