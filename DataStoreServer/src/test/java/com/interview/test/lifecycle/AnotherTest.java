package com.interview.test.lifecycle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AnotherTest {


  @Test
  public void testing() {
    System.out.println("Testing in another");
  }

  @Test
  public void testing2() {
    System.out.println("Testing in another 2");
  }

}
