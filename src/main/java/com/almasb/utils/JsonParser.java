package com.almasb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
	
	private static String COMILLA = "\"";
	
	public static String objectToJson(Map<String, String> data){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (String key:data.keySet()){
			sb.append(COMILLA);
			sb.append(key);
			sb.append(COMILLA);
			sb.append(":");
			sb.append(COMILLA);
			sb.append(data.get(key));
			sb.append(COMILLA);
			sb.append(",");
		}
		
		String dev = sb.toString();
		dev = dev.substring(0, dev.length() - 1) + "}";
		return dev;
	}
	
	public static Map<String, String> jsonToMap(String json){
		if (json == null){
			return new HashMap<String, String>();
		}
		String treatedJson = json.replaceAll("\\{", "");
		treatedJson = treatedJson.replaceAll("\\}", "");
		treatedJson = treatedJson.replaceAll(COMILLA, "");
		
		Map<String, String> dev = new HashMap<String, String>();
		for (String jsonParam: treatedJson.split(",")){
			String[] params = jsonParam.split(":");
			dev.put(params[0], params[1]);
		}
		return dev;
	}
	
	public static List<Map<String, String>> jsonToMapList(String json){
		List<Map<String, String>> dev = new ArrayList<Map<String, String>>();
		if(json != null && json.length() > 4){
			String treatedJson = json.substring(2, json.length()-2);
			String[] splitedJson = treatedJson.split("\\},\\{");
			for (String jsonObject: splitedJson){
				dev.add(jsonToMap(jsonObject));
			}
		}
		return dev;
	}

}
