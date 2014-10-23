package com.jcwx.game.common;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON服务
 * 
 * @author derek
 * 
 */
public class JSONService {

    public static String getJSONValueByKey(JSONObject json, String key) {
	String str = "";
	try {
	    str = json.getString(key).toString();
	} catch (JSONException e) {
	    e.printStackTrace();
	}
	return str;
    }

    /**
     * 将JSON列表转换为字符串
     * 
     * @param jsonList
     * @return
     */
    public static String JSONListToString(List<JSONObject> jsonList) {
	if (jsonList == null) {
	    return "[]";
	}
	StringBuffer stringBuffer = new StringBuffer();
	stringBuffer.append("[");
	for (JSONObject json : jsonList) {
	    stringBuffer.append(json.toString());
	    stringBuffer.append(",");
	}
	if (stringBuffer.length() != 1) {
	    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
	}
	stringBuffer.append("]");
	return stringBuffer.toString();
    }

    /**
     * 将JSON列表转换为字符串(支持num个字符)
     * 
     * @param jsonList
     * @return
     */
    public static String JSONListToString(List<JSONObject> jsonList, int num) {
	if (jsonList == null) {
	    return "[]";
	}
	StringBuffer stringBuffer = new StringBuffer();
	stringBuffer.append("[");
	for (JSONObject json : jsonList) {
	    if (stringBuffer.length() + json.toString().length() >= num)
		break;
	    stringBuffer.append(json.toString());
	    stringBuffer.append(",");
	}
	if (stringBuffer.length() != 1) {
	    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
	}
	stringBuffer.append("]");
	return stringBuffer.toString();
    }

    public static void putLast(JSONObject json, String key, String value) {
	try {
	    if (json.containsKey(key))
		json.put(key, json.get(key) + value);
	    else
		json.put(key, value);
	} catch (JSONException e) {
	    //
	}
    }

    /**
     * 将字符串转化为JSON列表
     * 
     * @param jsonString
     *            格式：[{"id":1,num:3},{"id":2,num:4}]
     * @return
     */
    public static List<JSONObject> stringToJSONList(String jsonListString) {
	List<JSONObject> jsonList = new ArrayList<JSONObject>();
	try {
	    if (jsonListString != null && jsonListString.length() > 2) {
		String jsonListStringTmp = jsonListString.substring(0,
			jsonListString.length() - 1);
		String[] jsonStrings = jsonListStringTmp.split("}");
		for (String jsonString : jsonStrings) {
		    // JSONObject jsonObject = new
		    // (jsonString.substring(1)+"}");
		    JSONObject jsonObject = (JSONObject) JSON
			    .toJSON(jsonString);
		    jsonList.add(jsonObject);
		}
	    }
	} catch (Exception e) {
	    return new ArrayList<JSONObject>();
	}
	return jsonList;
    }

}
