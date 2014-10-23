/**
 * 
 */
package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 
 */
public class OssActivityDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 活动id */
    private Integer activityId;
    /**  */
    private Integer id;
    /** 奖励道具 */
    private String item;
    /** 道具数量 */
    private Integer number;
    /** 职业类型 */
    private Integer occupation;
    /** 提示 */
    private String point;
    /** 活动条件 */
    private String value;
    
    private List<Map<String, Object>> itemList;

    public OssActivityDetail() {
    }

    public OssActivityDetail(Integer id, Integer activityId, String value,
	    Integer number, String item, Integer occupation) {
	this.id = id;
	this.activityId = activityId;
	this.value = value;
	this.number = number;
	this.item = item;
	this.occupation = occupation;
    }

    public Integer getActivityId() {
	return activityId;
    }

    public Integer getId() {
	return id;
    }

    public String getItem() {
	return item;
    }

    public Integer getNumber() {
	return number;
    }

    public Integer getOccupation() {
	return occupation;
    }

    public String getPoint() {
	return point;
    }

    public String getValue() {
	return value;
    }

    public void setActivityId(Integer activityId) {
	this.activityId = activityId;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setItem(String item) {
	this.item = item;
    }

    public void setNumber(Integer number) {
	this.number = number;
    }

    public void setOccupation(Integer occupation) {
	this.occupation = occupation;
    }

    public void setPoint(String point) {
	this.point = point;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public List<Map<String, Object>> getItemList() {
	itemList= new ArrayList<Map<String,Object>>();
	if(item!=null&&!"".equals(item)){
	    String[] itemArr = item.split("#");
	    for(String itemStr:itemArr){
		Map<String,Object> paraMap=new HashMap<String, Object>();
		String[] temp = itemStr.split(",");
		paraMap.put("itemId", temp[0]);
		paraMap.put("num", temp[1]);
		itemList.add(paraMap);
	    }
	}
        return itemList;
    }

    public void setItemList(List<Map<String, Object>> itemList) {
        this.itemList = itemList;
    }
    
}
