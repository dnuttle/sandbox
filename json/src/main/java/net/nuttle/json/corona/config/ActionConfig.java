package net.nuttle.json.corona.config;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *
 */
//The following removes any requirement for a type:
//@JsonTypeInfo(use = JsonTypeInfo.NONE)
//If you use JsonTypeInfo.Id.NAME, it means simple class name, so if it's in same path, you don't have to resolve(?)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "actionType")
@JsonSubTypes({
  @JsonSubTypes.Type(value = JavaActionConfig.class),
  @JsonSubTypes.Type(value = OtherActionConfig.class)
})

/*
 * It appears that you can't make a property required.  You can add this:
 * @JsonProperty(required = true)
 * But according to the javadcos for JsonProperty, it is still ignored as of 2.0.
 */

public class ActionConfig {
  private String description;
  private String name;
  private String actionType;
  
  public String getDescription() {
    return description;
  }
  public String getName() {
    return name;
  }
  
  public String getActionType() {
    return actionType;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setActionType(String actionType) {
    this.actionType = actionType;
  }
}
