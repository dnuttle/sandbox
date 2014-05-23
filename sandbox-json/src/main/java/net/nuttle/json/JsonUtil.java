package net.nuttle.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
public class JsonUtil 
{
	public static JSONObject parse(String json) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jo = (JSONObject)parser.parse(json);
		return jo;
	}
	

}
