package net.nuttle.sandbox.exec;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.nuttle.sandbox.spring.Test;

import net.nuttle.sandbox.jackson.JacksonMapperDemo;
import net.nuttle.sandbox.json.gson.GsonDemo;



public class App 
{
	private static final Logger LOG = Logger.getLogger(App.class);
	
	public static void main( String[] args ) throws Exception
	{
		//HttpClientLab.getExample1();
	  //SpringWeatherDemo.printWeather("60118");
	  //SpringWeatherDemo.printWeather("90210");
	  //JsoupDemo.printHealth("localhost");
	  JacksonMapperDemo.mapObject();
	  GsonDemo.gsonDemo();
	  
	}
	
	
	
	public static void testSpring() {
    AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("net/nuttle/beans.xml");
    Test t = (Test)ctx.getBean("test1");
    System.out.println(t.getMessage());
    ctx.registerShutdownHook();
	}
}