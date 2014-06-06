package net.nuttle.sandbox.corona.endpoint;

import java.io.IOException;
import java.net.URLEncoder;


import org.apache.log4j.Logger;

import net.nuttle.sandbox.httpclient.HttpUtils;

/**
 *
 */
public class EndpointRegistration {

  private static final Logger LOG = Logger.getLogger(EndpointRegistration.class);

  /**
   * This no longer seems to work, I get SSL errors
   * @param central
   * @param endpoint
   * @throws IOException
   */
  public void unregister(String central, String endpoint) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append("https://")
      .append(central)
      .append("/admin/api?operation=endpoint&type=profile&address=")
      .append(URLEncoder.encode(endpoint, "UTF-8"));
    String response = HttpUtils.doGet(sb.toString());
    LOG.debug("Response from central: " + response);
  }
}
