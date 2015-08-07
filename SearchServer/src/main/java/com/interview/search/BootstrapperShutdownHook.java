package com.interview.search;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootstrapperShutdownHook extends Thread {

  private Logger log = Logger.getLogger(BootStrapper.class);
  private ClassPathXmlApplicationContext context = null;

  public BootstrapperShutdownHook(ClassPathXmlApplicationContext context) {
    this.context = context;
  }

  @Override
  public void run() {
    log.debug("Shutting down SearchServer...");
    super.run();
    this.context.stop();
    log.debug("SearchServer shutdown successfully...");

  }

}
