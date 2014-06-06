package net.nuttle.sandbox.corona.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is meant to provide a way to test/experiment with the other classes in the package.
 */
public class ActionConfigDriver {

  public static void main(String[] args) throws JsonMappingException, JsonParseException, IOException {
    String j = "{\"actionConfigs\":[" +
        "{\"name\":\"javaTest\", \"description\":\"A JavaActionConfig\",\"actionType\":\"JavaActionConfig\",\"className\":\"someclass\"}," +
        "{\"name\":\"otherTest\", \"description\":\"An OtherActionConfig\",\"actionType\":\"OtherActionConfig\",\"other\":\"other value\"}" +
        "]}";
    ObjectMapper mapper = new ObjectMapper();
    ActionConfigList configList = mapper.readValue(j, ActionConfigList.class);
    System.out.println(configList.getActionConfigs().get(1).getName());
    OtherActionConfig c = (OtherActionConfig) configList.getActionConfigs().get(1);
    System.out.println(c.getOther());
    System.out.println(mapper.writeValueAsString(configList));
  }
}
