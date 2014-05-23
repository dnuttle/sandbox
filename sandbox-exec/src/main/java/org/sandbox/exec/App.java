package org.sandbox.exec;

import net.nuttle.json.JsonUtil;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class App 
{
	//private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	public static void main( String[] args )
	{
    String json = "{\"abc\":\"def\"}";
    try {
    	JSONObject j = JsonUtil.parse(json);
    	System.out.println(j.toJSONString());
    } catch (ParseException e) {
    		
    }
	}
}