package com.jcwx.game.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.jcwx.game.common.logicobject.Key;
import com.jcwx.game.common.logicobject.KeyList;
import com.thoughtworks.xstream.XStream;

public class JSONValidate {
    private static final String DEFAULT_VALIDATION_NAME = "/functionAdjustType.xml";

    public static Map<Integer, List<Key>> jsonValidateCache = new HashMap<Integer, List<Key>>();

    @SuppressWarnings("unchecked")
    private static List<String> getFunctionAdjustTypes() {
	List<String> files = new ArrayList<String>();
	try {
	    SAXReader reader = new SAXReader();

	    Document document = reader.read(JSONValidate.class
		    .getResource(DEFAULT_VALIDATION_NAME));

	    List<Node> nodes = document.selectNodes("/files/include/@file");

	    for (Node node : nodes) {
		files.add(node.getText());
	    }

	    return files;

	} catch (DocumentException e) {
	    e.printStackTrace();
	}
	return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    public static void initialFile() {
	jsonValidateCache.clear();

	for (String path : getFunctionAdjustTypes()) {

	    try {
		SAXReader reader = new SAXReader();

		Document document = reader.read(JSONValidate.class
			.getResource(path));

		List<Node> nodes = document.selectNodes("/bean/KeyList");

		for (Node node : nodes) {
		    KeyList keyList = readValidateFile(node.asXML());

		    jsonValidateCache.put(Integer.valueOf(keyList.getValue()),
			    keyList.getKeys());
		}
	    } catch (NumberFormatException e) {
		e.printStackTrace();
	    } catch (DocumentException e) {
		e.printStackTrace();
	    }

	}

    }

    private static KeyList readValidateFile(String xml) {

	XStream xstream = new XStream();

	xstream.alias("KeyList", KeyList.class);

	xstream.alias("Key", Key.class);

	xstream.addImplicitCollection(KeyList.class, "keys");

	xstream.useAttributeFor(KeyList.class, "value");

	return (KeyList) xstream.fromXML(xml);
    }

    /**
     * 验证
     * 
     * @param id
     *            functionType id
     * @param json
     *            funcitonType Nub
     * @return
     */
    public static List<String> validateJson(Integer id, String json) {
	return Collections.emptyList();
	/*
	 * Validate.isTrue(jsonValidateCache.containsKey(id),
	 * "jsonValidateCache not contains key id:"+id); List<String> errorField
	 * = new ArrayList<String>(); JSONObject jsonObj =
	 * JSONObject.parseObject(json); for (Key key :
	 * jsonValidateCache.get(id)) { if (key.isRequired()) { boolean flag =
	 * false; for(String onekey:jsonObj.keySet()){
	 * if(!onekey.contains(key.getValue())){ flag = true; break; } }
	 * if(!flag){ errorField.add(key.getValue()); } if
	 * (!jsonObj.containsKey(key.getValue())) {
	 * errorField.add(key.getValue()); } }
	 * 
	 * } return errorField;
	 */
    }
}
