package net.nuttle.sandbox.weather;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import net.nuttle.sandbox.config.Config;

import static net.nuttle.sandbox.config.Constants.TEMPLATE_WEATHER;

/**
 * Formats a Weather instance with a Velocity template and outputs to stdout
 */
public class WeatherFormatter {

  private static final Logger LOG = Logger.getLogger(WeatherFormatter.class);
  private static final VelocityContext context = new VelocityContext();
  private static String template;
  
  static {
    StringWriter writer = new StringWriter();
    try {
      String templateFile = Config.getInstance().getProperty(TEMPLATE_WEATHER);
      IOUtils.copy(WeatherFormatter.class.getClassLoader().getResourceAsStream(templateFile), writer, "UTF-8");
      template = writer.toString();
    } catch (IOException e) {
      LOG.error("Could not load output.vm");
    }
  }
  
  public String format(Weather weather) {
    context.put("weather", weather);
    StringWriter writer = new StringWriter();
    Velocity.evaluate(context, writer, "", template);
    return writer.toString();
  }
}
