package com.interview.search;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BootStrapper {


  public static void main(String[] args) {
    try {
      ClassPathXmlApplicationContext context =
          new ClassPathXmlApplicationContext("applicationContext.xml");
      context.registerShutdownHook();

      Runtime.getRuntime().addShutdownHook(new BootstrapperShutdownHook(context));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
