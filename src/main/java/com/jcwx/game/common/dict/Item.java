package com.jcwx.game.common.dict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tanfl 改类用来封装专业数据中具体的数据项
 */
@SuppressWarnings("serial")
public class Item implements Serializable {
    public static final int DATAVALIDATE_FALSE = 0;
    public static final int DATAVALIDATE_TRUE = 1;
    /**
     * List<AttachField>
     */
    private List attachFields;
    private String dataId;
    /**
     * 数据项所属的机构
     */
    private String dataOrg;
    /**
     * 数据项是否有效 0 无效 1 有效
     */
    private int dataValidate = 1;
    private boolean flag;
    private String itemId;
    private String name;
    //
    private String oldParentId;
    private int order;
    private String orgId;

    /**
     * 父数据项索引
     */
    private String parentId;

    // 存放主键条件串 例如："  and a.org_id='179'"；不包含"值字段"与"名称字段"的主键条件串
    private String primaryCondition = "";

    /**
     * 存放字典主键值 Map<primarykeyName,primarykeyValue>
     */
    private Map primarykeys;

    protected List subItems;

    private String value;

    public void addSubItem(Item item) {
	if (this.subItems == null)
	    subItems = new ArrayList();
	this.subItems.add(item);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	try {
	    Item tep = (Item) obj;
	    return tep.name.equals(this.name) && tep.value.equals(this.value);
	} catch (Exception e) {
	    return false;
	}

    }

    public List getAttachFields() {

	return attachFields;
    }

    public String getDataId() {
	return dataId;
    }

    public String getDataOrg() {
	return dataOrg;
    }

    public int getDataValidate() {
	return dataValidate;
    }

    public String getItemId() {
	return itemId;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
	return name;
    }

    public String getOldParentId() {
	return oldParentId;
    }

    public int getOrder() {
	return order;
    }

    public String getOrgId() {
	return orgId;
    }

    public String getParentId() {
	return parentId;
    }

    public String getPrimaryCondition() {
	return primaryCondition;
    }

    public Map getPrimarykeys() {
	return primarykeys;
    }

    public List getSubItems() {
	return subItems;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
	return value;
    }

    public boolean isFlag() {
	return flag;
    }

    public void setAttachFields(List attachFields) {
	this.attachFields = attachFields;
    }

    public void setDataId(String dataId) {
	this.dataId = dataId;
    }

    public void setDataOrg(String dataOrg) {
	this.dataOrg = dataOrg;
    }

    public void setDataValidate(int dataValidate) {
	this.dataValidate = dataValidate;
    }

    public void setFlag(boolean flag) {
	this.flag = flag;
    }

    public void setItemId(String itemId) {
	this.itemId = itemId;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
	this.name = name;
    }

    public void setOldParentId(String oldParentId) {
	this.oldParentId = oldParentId;
    }

    public void setOrder(int order) {
	this.order = order;
    }

    public void setOrgId(String orgId) {
	this.orgId = orgId;
    }

    public void setParentId(String parentId) {
	this.parentId = parentId;
    }

    public void setPrimaryCondition(String primaryCondition) {
	this.primaryCondition = primaryCondition;
    }

    public void setPrimarykeys(Map primarykeys) {
	this.primarykeys = primarykeys;
    }

    public void setSubItems(List subItems) {
	this.subItems = subItems;
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return new StringBuffer().append(
		"item:[name=" + name + "][value=" + value + "]\r\n").toString();
    }
}
