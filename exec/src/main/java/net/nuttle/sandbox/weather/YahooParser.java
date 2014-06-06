package net.nuttle.sandbox.weather;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

/**
 *
 */
public class YahooParser {

  @SuppressWarnings("unused")
  private static final Logger LOG = Logger.getLogger(YahooParser.class);
  
  public Weather parse(InputStream stream) throws DocumentException {
    SAXReader xmlReader = createXmlReader();
    Weather weather = new Weather();
    Document doc = xmlReader.read(stream);
    weather.setChill(doc.valueOf("/rss/channel/y:wind/@chill"));
    weather.setCity(doc.valueOf("/rss/channel/y:location/@city"));
    weather.setCondition(doc.valueOf("/rss/channel/item/y:condition/@text"));
    weather.setCountry(doc.valueOf("/rss/channel/y:location/@country"));
    weather.setHumidity(doc.valueOf("/rss/channel/y:atmosphere/@humidity"));
    weather.setRegion(doc.valueOf("/rss/channel/y:location/@region"));
    weather.setTemp(doc.valueOf("/rss/channel/item/y:condition/@temp"));
    return weather;
  }
  
  private SAXReader createXmlReader() {
    Map<String,String> uris = new HashMap<String,String>();
    uris.put("y", "http://xml.weather.yahoo.com/ns/rss/1.0");
    DocumentFactory factory = new DocumentFactory();
    factory.setXPathNamespaceURIs(uris);
    SAXReader xmlReader = new SAXReader();
    xmlReader.setDocumentFactory(factory);
    return xmlReader;
    
  }
}
