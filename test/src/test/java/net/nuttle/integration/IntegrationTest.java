package net.nuttle.integration;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

import com.jayway.restassured.RestAssured;

//import static org.hamcrest.Matchers.*;
//import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.path.json.JsonPath.*;
//import com.jayway.restassured.path.json.JsonPath;
/**
 *
 */
public class IntegrationTest {

  @Test
  public void testConnection() {
    given()
      .port(80).
    expect().
      statusCode(200).
      when().
      get("http://localhost");
  }

  @Test
  public void testLinksPage() {
    RestAssured.basePath = "/html";
    given().
      port(80).
    expect().
      statusCode(200).
    when().
      get("/links-adobe2.html");
    Object o = given();
  }
  
  @Test
  public void testJsonPage() {
    RestAssured.basePath = "/html/json";
    RestAssured.port = 80;
    String json = get("test.json").asString();
    //System.out.println(json);
    //JsonPath p = from(json);
    //System.out.println(p.toString());
    String property1 = from(json).get("test.inner.property1");
    assertEquals(property1, "test1");
    
    List<String> vals = with(json).get("test.inner.collection");
    assertEquals(vals.get(0), "first");
    assertEquals(vals.get(1), "second");
    assertEquals(vals.size(), 2);
    
    List<Map<String,?>> vals2 = with(json).get("test.inner.map");
    Map m1 = vals2.get(0);
    Object o = m1.get("one");
    Map m2 = (Map)m1.get("one");
    assertEquals(m2.get("name"), "mapfirst");
  }
  
}
