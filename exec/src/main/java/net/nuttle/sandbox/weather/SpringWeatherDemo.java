package net.nuttle.sandbox.weather;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Yes, the class name is a pun!
 */
public class SpringWeatherDemo {

  public static void printWeather(String zipCode) throws IOException, DocumentException {
    AbstractApplicationContext context = new ClassPathXmlApplicationContext("net/nuttle/beans.xml");
    YahooParser parser = context.getBean("yahoo-parser", YahooParser.class);
    YahooRetriever retriever = context.getBean("yahoo-retriever", YahooRetriever.class);
    WeatherFormatter formatter = context.getBean("weather-formatter", WeatherFormatter.class);
    System.out.println(formatter.format(parser.parse(retriever.retrieve(zipCode))));
    context.registerShutdownHook();
  }
}
