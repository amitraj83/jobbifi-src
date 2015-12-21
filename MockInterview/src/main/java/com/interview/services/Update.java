package com.interview.services;

import java.util.List;

public class Update {

  private String type;
  private String data;
  private String author;
  private List<String> recipients;

  public Update(String author, String type, String data, List<String> recipients) {
    super();
    this.type = type;
    this.data = data;
    this.author = author;
    this.recipients = recipients;
    // Services.getInstance().getLiveQueueService().insertUpdate(author, this);
  }



  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getUsername() {
    return author;
  }

  public void setUsername(String username) {
    this.author = username;
  }



  public String getAuthor() {
    return author;
  }



  public void setAuthor(String author) {
    this.author = author;
  }



  public List<String> getRecipients() {
    return recipients;
  }



  public void setRecipients(List<String> recipients) {
    this.recipients = recipients;
  }


}
