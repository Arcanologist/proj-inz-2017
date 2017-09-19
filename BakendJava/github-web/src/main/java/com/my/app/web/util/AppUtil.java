package com.my.app.web.util;

import java.util.HashMap;
import java.util.Map;

public class AppUtil {

	public final static String REST_PRODUCES_JSON = "application/json";
	
	private final static String ERROR_KEY= "error_code"; 
	private final static String ERROR_VAL = "error_message";
	private final static String EXCEPTION_MSG = "exception_message";
	
	
	/**
	 * function return error map with external exception message
	 * @param exception
	 * @return
	 */
	public static Map<String,String> getErrorMessage(String exception){
		Map<String,String> map = new HashMap<String,String>();
		map.put(ERROR_VAL, "Error occured in service.");
		map.put(ERROR_KEY, "error_occured");
		map.put(EXCEPTION_MSG, exception);
		return map;
		
	}
}
