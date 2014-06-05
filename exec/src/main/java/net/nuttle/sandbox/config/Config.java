package net.nuttle.sandbox.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 *
 */
public class Config {
  
  private static final Logger LOG = Logger.getLogger(Config.class);
  private static final Properties prop = new Properties();
  private static final Config INSTANCE = new Config();
  
  public Config() {
    try {
      //InputStream is = new FileInputStream("config.properties");
      InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
      prop.load(is);
    } catch (IOException ioe) {
      LOG.error("* * * Error loading config.properties", ioe);
    }
    
  }
  
  public static Config getInstance() {
    return INSTANCE;
  }

  public String getProperty(String key) {
    return prop.getProperty(key);
  }
  
}
