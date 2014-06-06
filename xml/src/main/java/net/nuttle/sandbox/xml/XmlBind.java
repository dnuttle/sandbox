package net.nuttle.sandbox.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 */
public class XmlBind {

  private static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
    "<ns2:test_object xmlns:ns2=\"aza\">" +
    "<component>" +
        "<type>sub2</type>" +
    "</component>" +
    "<component>" +
        "<type>sub2</type>" +
    "</component>" +
    "<id>ABC</id>" +
    "<something>A</something>" +
    "</ns2:test_object>";
  public void testBindFromString() throws JAXBException, UnsupportedEncodingException {
    JAXBContext jc = JAXBContext.newInstance(TestObject.class);
    Unmarshaller unmarshaller = jc.createUnmarshaller();
    InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
    TestObject to = (TestObject)unmarshaller.unmarshal(is);
    System.out.println("The id is: " + to.getId());
  }
  
  public void testBind() throws JAXBException {
    TestObject o = new TestObject();
    o.setId("ABC");
    Writer writer = new StringWriter();
    JAXBContext jc = JAXBContext.newInstance(TestObject.class);
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(o, writer);
    System.out.println(writer.toString());
    
    //JAXBContext jc2 = JAXBContext.newInstance(TestObject.class);
    Unmarshaller um = jc.createUnmarshaller();
    ByteArrayInputStream is = new ByteArrayInputStream(writer.toString().getBytes());
    TestObject o2 = (TestObject)um.unmarshal(is);
    System.out.println(o2.getId());
  }
  
  @XmlRootElement(name="test_object",namespace="aza")
  public static class TestObject {
    private String id;
    private String name;
    public TestObject() {
      c = new SubComponent2();
      c2 = new SubComponent2();
    }
    public void setId(String id) {
      this.id = id;
    }
    @XmlElement(name="id")
    public String getId() {
      return id;
    }
    
    //This indicates that c can be SubComponent1 or SubComponent2.
    //If it is instantiated as something else with name="component", then there will just be an empty <component/> element.
    //Not sure why this is.  I would think there would be an error.  Instead, the internals of SubComponent3 (i.e., type)
    //are just ignored.
    @XmlElementRefs({
      @XmlElementRef(name = "sub1", type = SubComponent1.class),
      @XmlElementRef(name = "sub2", type = SubComponent2.class)
    })
    private TestComponent c;
    
    //Yet if there is only one XmlElementRef, it does not have to be instantiated as the indicated type.
    //If it's SubComponent2.class, the "type" value still appears in the XML.
    @XmlElementRef(name="sub1only", type=SubComponent1.class)
    private TestComponent c2;
    @XmlElement
    private String getSomething() {
      return "A";
    }
    public String getName() {
      return name;
    }
  }
  
  @XmlRootElement(name="component")
  public static class TestComponent {
    @XmlElement(name="type")
    private String type = "parent";
    @SuppressWarnings("unused")
    private String something="something";
  }
  
  @XmlRootElement(name="component")
  public static class SubComponent1 extends TestComponent {
    @XmlElement(name="type")
    private String type = "sub1";
    
  }
  
  @XmlRootElement(name="component")
  @XmlType(propOrder="type")
  public static class SubComponent2 extends TestComponent {
    @XmlElement
    private String type = "sub2";
    //@XmlTransient //Still not sure how this works
    //@XmlElement
    @SuppressWarnings("unused")
    private String something="somethingelse";
  }
  
  @XmlRootElement(name="component")
  public static class SubComponent3 extends TestComponent {
    @XmlElement(name="type")
    private String type="sub3";
  }
}
