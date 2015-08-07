package com.interview.helper;

import com.mongodb.DBObject;

public class MongoDataHelper {

	public static final String getStringValue(final DBObject row, final String key, final String defaultValue){
		String value = "";
		try {
			return row.get(key).toString();
		} catch (Exception e) {
			if(null != defaultValue){
				value =defaultValue;
			}			
		}
		return value;
	}
	
	public static final String getStringValue(final DBObject row, final String key){
		return getStringValue(row, key, null);
	}
	
	public static final long getLong(final DBObject row, final String key, final long defaultValue){		
		try {
			return Long.parseLong(row.get(key).toString());
		} catch (Exception e) {
			return defaultValue;			
		}		
	}
	
	public static final long getLong(final DBObject row, final String key){		
		return getLong(row, key, 0);		
	}
	
	public static final int getInt(final DBObject row, final String key, final int defaultValue){		
		try {
			return Integer.parseInt(row.get(key).toString());
		} catch (Exception e) {
			return defaultValue;			
		}		
	}
	
	public static final int getInt(final DBObject row, final String key){		
		return getInt(row, key, 0);		
	}
}
