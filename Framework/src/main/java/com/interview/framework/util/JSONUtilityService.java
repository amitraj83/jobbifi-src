package com.interview.framework.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class JSONUtilityService extends ObjectMapper {

  public JSONUtilityService() {
    super();
    configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
  }

  public String convertObjectToJSON(Object object) {
    try {
      return writeValueAsString(object);
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getJSONStringOfMap(Map map) {
    try {    	
      return writeValueAsString(map);
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getJSONStringOfQueue(List q) {
    try {
      writeValueAsString(q);
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Use to 
   * */
  public Map<String, Object> getMapOfJsonString(String json){
	  
	Map<String, Object> map = null;
	TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
    try {
		map = readValue(json, typeRef);
	} catch (JsonMappingException j) {		
		j.printStackTrace();
	} catch (JsonParseException e) {		
		e.printStackTrace();
	} catch (IOException e) {		
		e.printStackTrace();
	}
	return map;
  }

}
