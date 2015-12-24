package com.interview.framework.pojo;

import java.io.Serializable;

public class ContactList implements Serializable {

  /**
  * 
  */
  private static final long serialVersionUID = 569578033532112285L;
  private String user;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  private String contact;

}
