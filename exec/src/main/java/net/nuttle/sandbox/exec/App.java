package net.nuttle.sandbox.exec;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.nuttle.sandbox.jsoup.JsoupDemo;
import net.nuttle.sandbox.weather.SpringWeatherDemo;
import net.nuttle.spring.Test;



public class App 
{
	private static final Logger LOG = Logger.getLogger(App.class);
	
	public static void main( String[] args ) throws Exception
	{
		//HttpClientLab.getExample1();
	  //SpringWeatherDemo.printWeather("60118");
	  //SpringWeatherDemo.printWeather("90210");
	  JsoupDemo.printHealth("localhost");
	  
	}
	
	
	
	public static void testSpring() {
    AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("net/nuttle/beans.xml");
    Test t = (Test)ctx.getBean("test1");
    System.out.println(t.getMessage());
    ctx.registerShutdownHook();
	}
}