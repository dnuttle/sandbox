package net.nuttle.sandbox.jackson;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 */
public class JacksonMapperDemo {

  private static final Logger LOG = Logger.getLogger(JacksonMapperDemo.class);

  public static void mapObject() {
    ObjectMapper mapper = new ObjectMapper();
    DemoBean bean = new DemoBean();
    try {
      System.out.println(mapper.writeValueAsString(bean));
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
    }
  }
  
  public static class DemoBean {
    private String name = "Smith";
    private String address = "101 Main";
    
    public void setName(String name) {
      this.name = name;
    }
    public void setAddress(String address) {
      this.address = address;
    }
    public String getName() {
      return name;
    }
    public String getAddress() {
      return address;
    }
  }
}
