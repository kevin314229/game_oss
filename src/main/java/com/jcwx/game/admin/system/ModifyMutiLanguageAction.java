package com.jcwx.game.admin.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.Validate;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONArray;
import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.admin.vo.PageMessage;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class ModifyMutiLanguageAction extends BaseInfoAction {

    JSONArray languageArray = new JSONArray();

    private String bundleName = "com.jcwx.game.admin.system.OssRoleAction";
    /**列表需要的 源语言，目标语言*/
    private String srcLocale = "zh_CN";
    
    private String descLocale = "en_US";;
    /**修改的时候需要的 key,value,locale*/
    private String key;
    
    private String value;

    private PageMessage pageMessage = PageMessage.getOkMessage();
    

    public JSONArray getLanguageArray() {
	return this.languageArray;
    }

    @Action(value = "modifyMutiLanguage_list", results = { @Result(name = SUCCESS, location = "/admin/system/language.jsp") })
    public String list() {
	try {
	    Validate.notNull(srcLocale);
	    Validate.notNull(descLocale);
	    
	    Locale srcLo = LocaleUtils.toLocale(srcLocale);
	    
	    Locale descLo = LocaleUtils.toLocale(descLocale);

	    ResourceBundle srcBundle = LocalizedTextUtil.findResourceBundle(
		    bundleName, srcLo);

	    ResourceBundle descBundle = LocalizedTextUtil.findResourceBundle(
		    bundleName, descLo);

	    Map<String, Object> result = new HashMap<String, Object>();
	    Enumeration<String> enumKeys = srcBundle.getKeys();
	    while (enumKeys.hasMoreElements()) {
		String key = enumKeys.nextElement();

		Map<String, Object> property = new HashMap<String, Object>();
		property.put("src", srcBundle.getObject(key));
		if (descBundle.containsKey(key)) {
		    property.put("desc", descBundle.getObject(key));
		}

		result.put(key, property);
	    }
	    languageArray.add(result);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    
    
    @Action(value = "modifyMutiLanguage_change", results = { @Result(name = SUCCESS,type="chain", location = "modifyMutiLanguage_list") })
    public String modifyKeyValue() {
	try {
	    
	    Validate.notBlank(getDescLocale(),"目标语言不能为空!");
	    
	    String actionPropertyPath= "F:\\dev\\workspace\\fengmoOss\\target\\classes\\com\\jcwx\\game\\admin\\system\\OssRoleAction_"+getDescLocale()+".properties";
	    
	    Properties p = new Properties();
	    
	    InputStream istream = new FileInputStream(actionPropertyPath);
	    
	    p.load(istream);
	    
	    if(p.containsKey(getKey())){
		p.setProperty(getKey(), getValue());
	    }else{
		p.put(getKey(), getValue());
	    }
	    FileUtils.write(new File(actionPropertyPath),propertyToList(p));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    
    private String propertyToList(Map<Object,Object> map){
	StringBuffer buffer= new StringBuffer();
	for(Object keyX:map.keySet()){
	    buffer.append(keyX).append("=").append(map.get(keyX)).append("\r\n");
	}
	return buffer.toString();
    }

    @Action(value = "modifyMutiLanguage_index", results = { @Result(name = SUCCESS, location = "/admin/system/language.jsp") })
    public String selectedAction() {
	return SUCCESS;
    }

    public void setLanguageArray(JSONArray languageArray) {
	this.languageArray = languageArray;
    }

    public String getBundleName() {
	return this.bundleName;
    }

    public void setBundleName(String bundleName) {
	this.bundleName = bundleName;
    }

    public String getSrcLocale() {
        return this.srcLocale;
    }

    public void setSrcLocale(String srcLocale) {
        this.srcLocale = srcLocale;
    }

    public String getDescLocale() {
        return this.descLocale;
    }

    public void setDescLocale(String descLocale) {
        this.descLocale = descLocale;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

    
    
    
    

}
