package net.nuttle.sandbox.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 */
@XmlRootElement(name="campaignAlgorithms")
public class CampaignAlgorithms {
  
  public static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
    "<campaignAlgorithms>" +
      "<campaignAlgorithm>" +
        "<campaignId value=\"3812\"/>" +
        "<clientCode value=\"corona\"/>" +
        "<keyMetric value=\"Conversion Rate\"/>" +
        "<modelingGoal value=\"32767\"/>" +
        "<algorithmId value=\"1\"/>" +
        "<modellables>" +
        "<modellable value=\"6752\" key=\"0\" group=\"2\" deleted=\"false\"/>" +
        "<modellable value=\"6751\" key=\"0\" group=\"3\" deleted=\"false\"/>" +
        "<modellable value=\"6754\" key=\"1\" group=\"0\" deleted=\"false\"/>" +
        "<modellable value=\"6753\" key=\"1\" group=\"1\" deleted=\"false\"/>" +
      "</modellables>" +
    "</campaignAlgorithm>" +
    "<campaignAlgorithm>" +
    "<campaignId value=\"3813\"/>" +
    "<clientCode value=\"other\"/>" +
    "<keyMetric value=\"Conversion Rate\"/>" +
    "<modelingGoal value=\"32768\"/>" +
    "<algorithmId value=\"1\"/>" +
    "<modellables>" +
    "<modellable value=\"6763\" key=\"0\" group=\"2\" deleted=\"false\"/>" +
    "<modellable value=\"6764\" key=\"0\" group=\"3\" deleted=\"false\"/>" +
    "<modellable value=\"6765\" key=\"1\" group=\"0\" deleted=\"false\"/>" +
    "<modellable value=\"6766\" key=\"1\" group=\"1\" deleted=\"false\"/>" +
  "</modellables>" +
"</campaignAlgorithm>" +
  "</campaignAlgorithms>";
  
  public static CampaignAlgorithms bindCampaignAlgorithms(String xml) throws JAXBException, UnsupportedEncodingException {
    JAXBContext jc = JAXBContext.newInstance(CampaignAlgorithms.class);
    Unmarshaller unmarshaller = jc.createUnmarshaller();
    InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
    CampaignAlgorithms algorithms = (CampaignAlgorithms)unmarshaller.unmarshal(is);
    return algorithms;
  }

  /**
   * Returns list of all client codes from XML
   * @param xml
   * @return
   * @throws Exception
   * TODO: Should throw EndpointRegistrationException instead
   */
  public static Set<String> getClientCodes(String xml) throws Exception {
    Set<String> clientCodes = new HashSet<String>();
    try {
      CampaignAlgorithms algorithms = bindCampaignAlgorithms(xml);
      Iterator<CampaignAlgorithm> iterator = algorithms.getCampaignAlgorithms().iterator();
      while(iterator.hasNext()) {
        CampaignAlgorithm algorithm = iterator.next();
        clientCodes.add(algorithm.getClientCode().getValue());
      }
    } catch (UnsupportedEncodingException e) {
      throw new Exception(e.getMessage(), e);
    }
    return clientCodes;
  }

  
  @XmlElement(name="campaignAlgorithm")
  private ArrayList<CampaignAlgorithm> campaignAlgorithms;
  
  public List<CampaignAlgorithm> getCampaignAlgorithms() {
    return campaignAlgorithms;
  }

  @XmlRootElement(name="campaignAlgorithm")
  public static class CampaignAlgorithm {
    private String campaignId;
    private Bean clientCode;
    private String keyMetric;
    private String modelingGoal;
    private String algorithmId;
    
    @XmlElement(name="campaignId[@value]")
    public String getCampaignId() {
      return campaignId;
    }
    
    public void setCampaignId(String campaignId) {
      this.campaignId = campaignId;
    }
    
    @XmlElement(name="clientCode") 
    public Bean getClientCode() {
      return clientCode;
    }
    
    public void setClientCode(Bean clientCode) {
      this.clientCode = clientCode;
    }
    
    @XmlElement(name="keyMetric")
    public String getKeyMetric() {
      return keyMetric;
    }
    
    @XmlElement(name="modelingGoal")
    public String getModelingGoal() {
      return modelingGoal;
    }
    
    @XmlElement(name="algorithmId")
    public String getAlgorithmId() {
      return algorithmId;
    }
    
  }
  
  @XmlRootElement
  public static class Bean {
    private String value;
    @XmlAttribute(name="value")
    public String getValue() {
      return value;
    }
    public void setValue(String value) {
      this.value = value;
    }
  }

}


