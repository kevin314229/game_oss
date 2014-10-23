package com.jcwx.game.admin.dict;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.vo.PageMessage.CallbackType;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.domain.OssDictType;
import com.jcwx.game.service.oss.IOssDictService;

/** 统计查询KPI */
@ParentPackage("base")
@Namespace("/admin/dict")
@ResultPath("/")
public class DictAction extends BasalAction {

    private static final long serialVersionUID = -6472673020174408936L;
    /** 总记录数 */
    private Integer allNum;
    /** 起始页号 */
    private Integer beginNum;

    /** 当前页数 */
    private Integer currPageNO;

    private String dataName;

    private OssDictData dictData;

    private List<OssDictData> dictDatas;
    /** 查询消费魔晶参数对象 */

    private Integer dictId;

    private int[] dictIds;

    private String dictIdStr;

    @Autowired
    private IOssDictService dictService;

    private OssDictType dictType;

    private List<OssDictType> dictTypes;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 总页数 */
    private Integer pages;
    private Integer typeId;
    private String typeName;

    @Action(value = "dict_addData")
    public String addDictData() throws Exception {
	dictData.setGameId(getBaseAdminContext().getProject().getProjectId());

	getPageMessage().setStatusCode(200);
	// 判断字典值是否存在该字典类型中
	OssDictData temp = dictService.getOssDictDataByName(
		getBaseAdminContext().getProject().getProjectId(),
		dictData.getDictType(), dictData.getDictName(),
		dictData.getDictId());
	if (temp != null) {
	    getPageMessage().setMessage("name");
	    getJSONResponse().responseJson(getPageMessage());
	    return null;
	}
	// message.setNavTabId("w_字典管理");
	if (dictData.getDictId() == null
		|| dictData.getDictId().intValue() == 0) {
	    dictService.createOssDictData(dictData);
	    getPageMessage().setMessage(
		    "添加字典值" + dictData.getDictName() + "成功！");
	} else {
	    dictService.updateOssDictData(dictData);
	    getPageMessage().setMessage(
		    "修改字典值" + dictData.getDictName() + "成功！");
	}

	getPageMessage().setCallbackType(CallbackType.CLOSE_CURRENT);
	getJSONResponse().responseJson(getPageMessage());
	return null;
    }

    @Action(value = "dict_addType")
    public String addType() throws Exception {
	dictType.setGameId(getBaseAdminContext().getProject().getProjectId());

	getPageMessage().setStatusCode(200);
	// 判断是否存在相同的字典类型值
	OssDictType temp = dictService.getOssDictTypeByType(
		getBaseAdminContext().getProject().getProjectId(),
		dictType.getDictType(), dictType.getTypeId());
	if (temp != null) {
	    getPageMessage().setMessage("type");
	    getJSONResponse().responseJson(getPageMessage());
	    return null;
	}
	// 判断是否存在相同的字典名称
	temp = dictService.getOssDictTypeByName(getBaseAdminContext()
		.getProject().getProjectId(), dictType.getDictName(), dictType
		.getTypeId());
	if (temp != null) {
	    getPageMessage().setMessage("name");
	    getJSONResponse().responseJson(getPageMessage());
	    return null;
	}
	// message.setNavTabId("w_字典管理");
	if (dictType.getTypeId() == null
		|| dictType.getTypeId().intValue() == 0) {
	    dictService.createOssDictType(dictType);
	    getPageMessage().setMessage(
		    "添加字典类型" + dictType.getDictName() + "成功！");
	} else {
	    dictService.updateOssDictType(dictType);
	    getPageMessage().setMessage(
		    "修改字典类型" + dictType.getDictName() + "成功！");
	}
	// message.setStatusCode(200);
	getPageMessage().setCallbackType(CallbackType.CLOSE_CURRENT);
	getJSONResponse().responseJson(getPageMessage());
	return null;
    }

    @Action(value = "dict_deleteDictData", results = { @Result(name = "success", location = "../../admin/dictionary/ossdict_data_list.jsp") })
    public String deleteDictDictData() throws Exception {
	String[] ids = dictIdStr.split(",");
	for (String dict : ids) {
	    dictService.deleteOssDictDataByID(Integer.parseInt(dict));
	}
	return null;
    }

    @Action(value = "dict_deleteType", results = { @Result(name = "success", location = "../../admin/dictionary/ossdict_data_list.jsp") })
    public String deleteDictType() throws Exception {
	dictService.deleteOssDictTypeByID(typeId);
	return null;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public Integer getBeginNum() {
	return beginNum;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public String getDataName() {
	return dataName;
    }

    public OssDictData getDictData() {
	return dictData;
    }

    @Action(value = "dict_queryDictData", results = { @Result(name = "success", location = "../../admin/dictionary/ossdict_data_list.jsp") })
    public String getDictDataList() throws Exception {
	dictDatas = dictService.getOssDictDataList(getBaseAdminContext()
		.getProject().getProjectId(), typeId);
	return SUCCESS;
    }

    public List<OssDictData> getDictDatas() {
	return dictDatas;
    }

    public Integer getDictId() {
	return dictId;
    }

    public int[] getDictIds() {
	return dictIds;
    }

    public String getDictIdStr() {
	return dictIdStr;
    }

    public OssDictType getDictType() {
	return dictType;
    }

    /*
     * 查询消费记录
     */
    @Action(value = "dict_index", results = { @Result(name = "success", location = "../../admin/dictionary/ossdict_manage.jsp") })
    public String getDictTypeList() throws Exception {
	dictTypes = dictService.getOssDictTypeList(getBaseAdminContext()
		.getProject().getProjectId());
	return SUCCESS;
    }

    public List<OssDictType> getDictTypes() {
	return dictTypes;
    }

    @Action(value = "dict_typeTree", results = { @Result(name = "success", location = "../../admin/dictionary/ossdict_type_tree.jsp") })
    public String getDictTypeTree() throws Exception {
	dictTypes = dictService.getOssDictTypeList(getBaseAdminContext()
		.getProject().getProjectId());
	return SUCCESS;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public Integer getPages() {
	return pages;
    }

    public Integer getTypeId() {
	return typeId;
    }

    public String getTypeName() {
	return typeName;
    }

    @Action(value = "dict_initAddData", results = { @Result(name = "success", location = "../../admin/dictionary/ossdict_dataInfo.jsp") })
    public String initAddData() throws Exception {
	// dictTypes=dictService.getOssDictTypeList(baseAdminContext.getProject().getProjectId());
	if (dictId != null && dictId.intValue() > 0) {
	    dictData = dictService.getOssDictDataByID(dictId);
	}
	return SUCCESS;
    }

    @Action(value = "dict_initAddType", results = { @Result(name = "success", location = "../../admin/dictionary/ossdict_typeInfo.jsp") })
    public String initAddType() throws Exception {
	// dictTypes=dictService.getOssDictTypeList(baseAdminContext.getProject().getProjectId());
	if (typeId != null && typeId.intValue() > 0) {
	    dictType = dictService.getOssDictTypeByID(typeId);
	}
	return SUCCESS;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginNum(Integer beginNum) {
	this.beginNum = beginNum;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setDataName(String dataName) {
	this.dataName = dataName;
    }

    public void setDictData(OssDictData dictData) {
	this.dictData = dictData;
    }

    public void setDictDatas(List<OssDictData> dictDatas) {
	this.dictDatas = dictDatas;
    }

    public void setDictId(Integer dictId) {
	this.dictId = dictId;
    }

    public void setDictIds(int[] dictIds) {
	this.dictIds = dictIds;
    }

    public void setDictIdStr(String dictIdStr) {
	this.dictIdStr = dictIdStr;
    }

    public void setDictType(OssDictType dictType) {
	this.dictType = dictType;
    }

    public void setDictTypes(List<OssDictType> dictTypes) {
	this.dictTypes = dictTypes;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setTypeId(Integer typeId) {
	this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
	this.typeName = typeName;
    }

    @Action(value = "dict_validateDictData")
    public String validateDictData() throws Exception {
	dictData = dictService.getOssDictDataByName(getBaseAdminContext()
		.getProject().getProjectId(), typeId, dataName, dictData
		.getDictId());

	if (dictData != null) {
	    getJSONResponse().responseJson("name");
	} else {
	    getJSONResponse().responseJson("success");
	}
	return null;
    }

    @Action(value = "dict_validateDictType", results = { @Result(name = "success", location = "../../admin/dictionary/ossdict_data_list.jsp") })
    public String validateDictType() throws Exception {
	dictType = dictService.getOssDictTypeByType(getBaseAdminContext()
		.getProject().getProjectId(), typeId, dictType.getTypeId());
	if (typeId == null || typeName == null) {
	    getJSONResponse().responseJson("success");
	    return null;
	}
	if (dictType != null) {
	    getJSONResponse().responseJson("type");
	    return null;
	}
	dictType = dictService.getOssDictTypeByName(getBaseAdminContext()
		.getProject().getProjectId(), typeName, dictType.getTypeId());

	if (dictType != null) {
	    getJSONResponse().responseJson("name");
	    return null;
	} else {
	    getJSONResponse().responseJson("success");
	}
	return null;
    }

}
