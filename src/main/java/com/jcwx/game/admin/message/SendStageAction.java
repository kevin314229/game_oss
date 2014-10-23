package com.jcwx.game.admin.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.SendBaseProperty;
import com.jcwx.game.service.oss.IOssLogService;
import com.jcwx.game.service.oss.IOssOperationService;

import com.jcwx.game.web.Global;

/**
 * @Description: 道具跟装备的调用
 */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class SendStageAction extends BasalAction {

    /** 发送给所有玩家的标记 */
    private Integer allFlag;

    /** 总记录数 */
    private Integer allNum;
    private String content;// 邮件内容

    /** 当前页数 */
    private Integer currPageNO;
    private List<SendBaseProperty> equipList = null; // 装备
    private String equipNum; // 装备赠送的数量
    private String equipStr;
    /** 按等级发送 最大等级等级 **/
    private String maxGrade;
    /** 按等级发送 最小等级 **/
    private String minGrade;
    private String msgTitle;// 标题

    private String nickName; // 玩家名称

    /** 每页记录数 */
    private Integer onePageNum;

    private List<SendBaseProperty> optList = null; // 装备,道具
    private Integer optType; // 道具装备类型

    @Autowired
    private IOssLogService ossLogService;
    @Autowired
    private IOssOperationService ossOperationService;
    /** 总页数 */
    private Integer pages;
    private List<SendBaseProperty> propertyList = null; // 道具

    private String propertyNum; // 道具赠送的数量

    /** 平台标识 */
    private String serverCode;

    /** 用于标识 **/
    private String serverValue;

    private String type;// 标题

    @Override
    @SuppressWarnings("unchecked")
    @Action(value = "sendStage_sendStage", results = { @Result(name = "success", location = "../../admin/message/sendStage.jsp") })
    public String execute() throws Exception {
	resetInfos();
	System.out.println("发送类型：" + allFlag);
	boolean isSend = true;
	if (isSend && (msgTitle == null || "".equals(msgTitle))) {
	    setErrorInfo("邮件标题不能为空");
	    isSend = false;
	}
	if (isSend && (content == null || "".equals(content))) {
	    setErrorInfo("邮件内容不能为空");
	    isSend = false;
	}
	List<SendBaseProperty> _equipList = Global
		.getEquipList(getBaseAdminContext().getCurrentOssServer()
			.getUrl());
	List<SendBaseProperty> _propertyList = Global
		.getPropertyList(getBaseAdminContext().getCurrentOssServer()
			.getUrl(),getBaseAdminContext().getProject().getProjectId());
	// JSONArray array = JSONArray.parseArray(equipStr);
	List<SendBaseProperty> sendBaseProperties = JSON.parseArray(equipStr,
		SendBaseProperty.class);
	for (SendBaseProperty sendBasePropert : sendBaseProperties) {
	    if (sendBasePropert.getValue() >= 2990001) {
		if (sendBasePropert.getNum() > 10000000) {
		    setErrorInfo("数量不能大于1千万");
		    isSend = false;
		    break;
		}
		continue;
	    }
	    String firstWorld = sendBasePropert.getValue().toString()
		    .substring(0, 1);
	    if ("1".equals(firstWorld)) {
		for (SendBaseProperty p : _equipList) {
		    if (p.getValue() == sendBasePropert.getValue().intValue()) {
			if (p.getMaxHeap() < sendBasePropert.getNum()
				.intValue()) {
			    setErrorInfo("发送的物品不能大于其最大堆叠数");
			    isSend = false;
			    break;
			}
		    } else {
			continue;
		    }
		}
	    }
	    if ("2".equals(firstWorld)) {
		for (SendBaseProperty p : _propertyList) {
		    if (p.getValue() == sendBasePropert.getValue().intValue()) {
			if (p.getMaxHeap() < sendBasePropert.getNum()
				.intValue()) {
			    setErrorInfo("发送的物品不能大于其最大堆叠数");
			    isSend = false;
			    break;
			}
		    } else {
			continue;
		    }
		}
	    }

	}

	if (!isSend) {
	    return com.opensymphony.xwork2.Action.SUCCESS;
	}

	OssServer ossServer = getBaseAdminContext().getCurrentOssServer();

	if (isSend && allFlag.intValue() == 1) { // 所有玩家
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("flag", 1);
	    object.put("title", msgTitle);
	    object.put("content", content);
	    object.put("minGrade", "0");
	    object.put("maxGrade", "0");
	    object.put("sendBasePropertyList", sendBaseProperties);
	    object.put("methodName", "sendToPlayer");
	    object.put("handlerName", "SendPropertyToPlayerHandler");
	    object = CONNECTION.sendMsg(object);
	    Object code = object.get("code");
	    if (code.toString().equals("0")) {
		setSuccessInfo("给所有玩家发送成功");
	    } else {
		setErrorInfo("给所有玩家发送失败");
	    }
	    StringBuffer buf = new StringBuffer();
	    buf.append("flag:" + 1)
		    .append("#title:" + msgTitle)
		    .append("#content:" + content)
		    .append("#equipStr:" + equipStr)
		    .append("#result:"
			    + (code.toString().equals("0") ? "给所有玩家发送成功"
				    : "给所有玩家发送失败"));
	    ossLogService.createOssLog(OssLogConstant.SEND_STAGE, buf.toString());

	} else if (isSend && allFlag.intValue() == 0) {
	    List<String> nickNameList = new ArrayList<String>(); // 玩家名称的集合
	    String[] nickNames = nickName.split(",");
	    for (int i = 0; i < nickNames.length; i++) {
		nickNameList.add(nickNames[i]);
	    }

	    if (isSend && (nickName == null || "".equals(nickName))) {
		setErrorInfo("玩家名不能为空");
		isSend = false;
	    }
	    if (isSend) { // 多个玩家
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("flag", 0);
		object.put("nickNameList", nickNameList);
		object.put("sendBasePropertyList", sendBaseProperties);
		object.put("title", msgTitle);
		object.put("content", content);
		object.put("minGrade", "0");
		object.put("maxGrade", "0");
		object.put("methodName", "sendToPlayer");
		object.put("handlerName", "SendPropertyToPlayerHandler");
		object = CONNECTION.sendMsg(ossServer.getUrl(),
			ossServer.getCommunicateKey(), object);
		Object code = object.get("code");
		StringBuffer msg = new StringBuffer();
		if (code.toString().equals("0")) {
		    setSuccessInfo("发送成功");
		} else {
		    List<Object> sendNickNames = (List<Object>) object
			    .get("sendNickNames");
		    if (sendNickNames != null) {
			for (int i = 0; i < sendNickNames.size(); i++) {
			    if (i == sendNickNames.size() - 1) {
				msg.append(sendNickNames.get(i));
			    } else {
				msg.append(sendNickNames.get(i) + ",");
			    }
			}
		    }
		    setErrorInfo("给玩家" + msg.toString() + "发送失败");
		}
		propertyList = Global.getPropertyList(ossServer.getUrl(),getBaseAdminContext().getProject().getProjectId());
		equipList = Global.getEquipList(ossServer.getUrl());
		StringBuffer buf = new StringBuffer();
		buf.append("flag:" + 0).append("#title:" + msgTitle)
			.append("#content:" + content)
			.append("#equipStr:" + equipStr)
			.append("#nickName:" + nickName)
			.append("#fail:" + msg.toString());
		ossLogService.createOssLog(OssLogConstant.SEND_STAGE, buf.toString());

	    }
	} else if (isSend && allFlag.intValue() == 2) {
	    // 分平台玩家
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("flag", 2);
	    object.put("title", msgTitle);
	    object.put("content", content);
	    object.put("serverCode", serverCode);
	    object.put("minGrade", "0");
	    object.put("maxGrade", "0");
	    object.put("sendBasePropertyList", sendBaseProperties);
	    object.put("methodName", "sendToPlayer");
	    object.put("handlerName", "SendPropertyToPlayerHandler");
	    object = CONNECTION.sendMsg(object);
	    Object code = object.get("code");
	    if (code.toString().equals("0")) {
		setSuccessInfo("给" + serverCode + "平台所有玩家发送成功");
	    } else {
		setErrorInfo("给" + serverCode + "平台所有玩家发送失败");
	    }
	    serverValue = serverCode;
	    StringBuffer buf = new StringBuffer();
	    buf.append("flag:" + 1)
		    .append("#title:" + msgTitle)
		    .append("#content:" + content)
		    .append("#equipStr:" + equipStr)
		    .append("#result:"
			    + (code.toString().equals("0") ? "给" + serverCode
				    + "平台所有玩家发送成功" : "给" + serverCode
				    + "所有玩家发送失败"));
	    ossLogService.createOssLog(OssLogConstant.SEND_STAGE, buf.toString());
	} else if (isSend && allFlag.intValue() == 3) { // 按等级发送

	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("flag", 3);
	    object.put("title", msgTitle);
	    object.put("content", content);
	    object.put("minGrade", minGrade);
	    object.put("maxGrade", maxGrade);
	    object.put("sendBasePropertyList", sendBaseProperties);
	    object.put("methodName", "sendToPlayer");
	    object.put("handlerName", "SendPropertyToPlayerHandler");
	    object = CONNECTION.sendMsg(object);
	    Object code = object.get("code");
	    if (code.toString().equals("0")) {
		setSuccessInfo("给" + minGrade + "至" + maxGrade + "所有玩家发送成功");
	    } else {
		setErrorInfo("给" + minGrade + "至" + maxGrade + "所有玩家发送失败");
	    }
	    StringBuffer buf = new StringBuffer();
	    buf.append("flag:" + 1)
		    .append("#title:" + msgTitle)
		    .append("#content:" + content)
		    .append("#equipStr:" + equipStr)
		    .append("#result:"
			    + (code.toString().equals("0") ? "给" + minGrade
				    + "至" + maxGrade + "所有玩家发送成功" : "给"
				    + serverCode + "所有玩家发送失败"));
	    ossLogService.createOssLog(OssLogConstant.SEND_STAGE, buf.toString());
	}

	return "success";
    }

    public Integer getAllFlag() {
	return allFlag;
    }

    public Integer getAllNum() {
	return this.allNum;
    }

    public String getContent() {
	return content;
    }

    public Integer getCurrPageNO() {
	return this.currPageNO;
    }

    public List<SendBaseProperty> getEquipList() {
	return equipList;
    }

    public String getEquipNum() {
	return equipNum;
    }

    @Action(value = "sendStage_getEquipPropertyList")
    public String getEquipPropertyList() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	switch (optType) {
	case 1:
	    optList = Global.getEquipList(getBaseAdminContext()
		    .getCurrentOssServer().getUrl());
	    break;
	case 2:
	    optList = Global.getPropertyList(getBaseAdminContext()
		    .getCurrentOssServer().getUrl(),getBaseAdminContext().getProject().getProjectId());
	    break;
	default:
	    break;
	}
	out.print(JSON.toJSON(optList).toString());
	out.close();
	return null;
    }

    public String getEquipStr() {
	return equipStr;
    }

    public String getMaxGrade() {
	return maxGrade;
    }

    public String getMinGrade() {
	return minGrade;
    }

    public String getMsgTitle() {
	return msgTitle;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOnePageNum() {
	return this.onePageNum;
    }

    public List<SendBaseProperty> getOptList() {
	return this.optList;
    }

    public Integer getOptType() {
	return optType;
    }

    public Integer getPages() {
	return this.pages;
    }

    public List<SendBaseProperty> getPropertyList() {
	return propertyList;
    }

    public String getPropertyNum() {
	return propertyNum;
    }

    public String getServerCode() {
	return serverCode;
    }

    public String getServerValue() {
	return serverValue;
    }

    public String getType() {
	return this.type;
    }

    @Action(value = "sendStage_index", results = { @Result(name = "success", location = "../../admin/message/sendStage.jsp") })
    public String index() {
	propertyList = Global.getPropertyList(getBaseAdminContext()
		.getCurrentOssServer().getUrl(),getBaseAdminContext().getProject().getProjectId());
	equipList = Global.getEquipList(getBaseAdminContext()
		.getCurrentOssServer().getUrl());
	return "success";
    }

    @Action(value = "sendStage_search", results = { @Result(name = "success", type = "chain", location = "sendStage_searchIndex") })
    public String search() {
	if (optType == null) {
	    return SUCCESS;
	}

	switch (optType) {
	case 1:
	    optList = Global.getEquipList(getBaseAdminContext()
		    .getCurrentOssServer().getUrl());
	    break;
	case 2:
	    optList = Global.getPropertyList(getBaseAdminContext()
		    .getCurrentOssServer().getUrl(),getBaseAdminContext().getProject().getProjectId());
	    break;
	}
	if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
	    setOnePageNum(20);
	}
	if (getCurrPageNO() == null || getCurrPageNO().intValue() == 0) {
	    setCurrPageNO(1);
	}
	if (optList == null) {
	    return SUCCESS;
	}

	if (StringUtils.isNotBlank(nickName)) {

	    optList = new ArrayList<SendBaseProperty>(Collections2.filter(
		    optList, new Predicate<SendBaseProperty>() {

			@Override
			public boolean apply(SendBaseProperty input) {
			    if (input != null
				    && StringUtils.isNotBlank(input.getName())) {
				return input.getName().contains(nickName);
			    }
			    return false;
			}
		    }));
	    // 总数量为筛选完成nickName之后的数据
	    setAllNum(optList.size());

	    List<List<SendBaseProperty>> result = Lists.partition(optList,
		    getOnePageNum());
	    if (result.size() > 0) {
		optList = result.get(getCurrPageNO() - 1);
	    }

	} else {
	    setAllNum(optList.size());
	    optList = Lists.partition(optList, getOnePageNum()).get(
		    getCurrPageNO() - 1);

	}
	return SUCCESS;
    }

    @Action(value = "sendStage_searchIndex", results = { @Result(name = "success", location = "../../admin/message/sendStageSearch.jsp") })
    public String searchIndex() {
	return search();
    }

    @Action(value = "sendStage_select", results = { @Result(name = "success", location = "../../admin/message/sendStageSelect.jsp") })
    public String searchSelect() {
	return SUCCESS;
    }

    public void setAllFlag(Integer allFlag) {
	this.allFlag = allFlag;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEquipNum(String equipNum) {
	this.equipNum = equipNum;
    }

    public void setEquipStr(String equipStr) {
	this.equipStr = equipStr;
    }

    public void setMaxGrade(String maxGrade) {
	this.maxGrade = maxGrade;
    }

    public void setMinGrade(String minGrade) {
	this.minGrade = minGrade;
    }

    public void setMsgTitle(String msgTitle) {
	this.msgTitle = msgTitle;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOptList(List<SendBaseProperty> optList) {
	this.optList = optList;
    }

    public void setOptType(Integer optType) {
	this.optType = optType;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPropertyNum(String propertyNum) {
	this.propertyNum = propertyNum;
    }

    public void setServerCode(String serverCode) {
	this.serverCode = serverCode;
    }

    public void setServerValue(String serverValue) {
	this.serverValue = serverValue;
    }

    public void setType(String type) {
	this.type = type;
    }

}
