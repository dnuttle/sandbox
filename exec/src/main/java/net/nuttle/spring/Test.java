package net.nuttle.spring;

/**
 *
 */
public class Test {
  
  private String msg = "Default message";
  
  public Test() {
    
  }
  
  public Test(String msg) {
    this.msg = msg;
  }
  
  public String getMessage() {
    return msg;
  }

}
