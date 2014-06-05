package net.nuttle.sandbox.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

/**
 *
 */
public class YahooRetriever {
  private static final Logger LOG = Logger.getLogger(YahooRetriever.class);
  
  public InputStream retrieve(String zipCode) throws IOException {
    String url = "http://weather.yahooapis.com/forecastrss?p=" + zipCode;
    URLConnection conn = new URL(url).openConnection();
    return conn.getInputStream();
  }
}
