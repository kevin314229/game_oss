package com.jcwx.game.admin.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.Question;


/**
 * 裁决王座客服系统 action
 * 
 * @author tanfl 2014-06-19
 */
@ParentPackage("base")
@Namespace("/cjwz/base")
@ResultPath("/")
public class CjwzQuestionNewAction extends BasalAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;

    private Date beginDate;

    /** 当前页数 */
    private Integer currPageNO;

    private Date endDate;
    // 帐号名
    private String loginName;
    // 角色名称
    private String nickName;
    /** 每页记录数 */
    private Integer onePageNum;
    // 平台标识
    private Integer operationId;

    private Question ossQuestion;

    private List<Question> ossQuestionList;

    private Integer ossServerId;

    /** 服务区列表 */
    private List<OssServer> ossServerList;

    /** 总页数 */
    private Integer pages;

    // 问题状态
    private String questionStatus;

    // 问题类型
    private String questionType;

    public Integer getAllNum() {
	return allNum;
    }

    public Date getBeginDate() {
	return beginDate;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public Date getEndDate() {
	return endDate;
    }

    // 查看当个问题以及回复
    @Action(value = "question_queryQuestion")
    public String getEquipProperty() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	String playerBaseId = ServletActionContext.getRequest().getParameter(
		"ossQuestion.playerBaseId");
	int ossServerId = Integer.valueOf(ServletActionContext.getRequest()
		.getParameter("ossServerId"));
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "QuestionHandler");
	object.put("methodName", "queryQuestion");
	List<Question> questionNewList = null;
	try {
	    object.put("playerBaseId", Long.valueOf(playerBaseId));
	    object = CONNECTION.sendMsg(object);
	    questionNewList = (List<Question>) object.get("ossQuestionList");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    for (Question ossQuestion : questionNewList) {
		if (ossQuestion.getQuestionDate() != null) {
		    ossQuestion.setNickName(ossQuestion.getNickName()
			    + "&nbsp;&nbsp;"
			    + sdf.format(ossQuestion.getQuestionDate()));
		}
		if (ossQuestion.getReplyDate() != null) {
		    ossQuestion.setReplyUserName(ossQuestion.getReplyUserName()
			    + "&nbsp;&nbsp;"
			    + sdf.format(ossQuestion.getReplyDate()));
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	out.print(JSON.toJSON(questionNewList).toString());
	out.close();
	return null;
    }

    @Action(value = "question_getOssServerListByOperationId")
    public String getEquipPropertyList() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	int operationId = Integer.valueOf(ServletActionContext.getRequest()
		.getParameter("ossServerId"));
	ossServerList = new ArrayList<OssServer>();
	List<OssServer> list = getBaseAdminContext().getOssServers();
	for (OssServer ossServer : list) {
	    if (ossServer.getServerId() == operationId) {
		ossServerList.add(ossServer);
	    }
	}
	out.print(JSON.toJSON(ossServerList).toString());
	out.close();
	return null;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public Question getOssQuestion() {
	return ossQuestion;
    }

    public List<Question> getOssQuestionList() {
	return ossQuestionList;
    }

    public Integer getOssServerId() {
	return ossServerId;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    public Integer getPages() {
	return pages;
    }

    public Question getQuestion() {
	return ossQuestion;
    }

    public List<Question> getQuestionList() {
	return ossQuestionList;
    }

    public String getQuestionStatus() {
	return questionStatus;
    }

    public String getQuestionType() {
	return questionType;
    }

    /**
     * 多服下未回复的客服问题
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "question_index", results = { @Result(name = "success", location = "../../cjwz/message/question.jsp") })
    public String index() throws Exception {
	try {
	    if (beginDate == null) {
		beginDate = DateService.getCurrentDayFirstUtilDate();
	    } else {
		beginDate = DateService.getDateFirstTime(beginDate);
	    }
	    if (endDate == null) {
		endDate = DateService.getCurrentDayLastUtilDate();
	    } else {
		endDate = DateService.getDateLastTime(endDate);
	    }
	    this.allNum = 0;
	    this.pages = 0;

	    if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
		setOnePageNum(20);
	    }
	    if (getCurrPageNO() == null || getCurrPageNO() == 0) {
		setCurrPageNO(1);
	    }

	    if (operationId == null) {

		return SUCCESS;
	    }
	    // 设置要查询的服务器
	    List<OssServer> ossServersList = getBaseAdminContext()
		    .getOssServers();
	    String serverIds = "";
	    this.setActionMsg("");
	    for (OssServer ossServer : ossServersList) {
		if (operationId == null || operationId == -1) {
		    serverIds += ossServer.getId() + ",";
		}
		if (operationId != null
			&& operationId.intValue() == ossServer.getServerId()
				.intValue() && ossServerId == -1) {
		    serverIds += ossServer.getId() + ",";
		}
	    }
	    if (serverIds == null || serverIds.equals("")) {
		this.setActionMsg("server is error");
		return SUCCESS;
	    }
	    // 给服务器赋值
	    if (operationId != -1) {
		ossServerList = new ArrayList<OssServer>();
		List<OssServer> list = getBaseAdminContext().getOssServers();
		for (OssServer ossServer : list) {
		    if (ossServer.getServerId().intValue() == operationId
			    .intValue()) {
			ossServerList.add(ossServer);
		    }
		}
	    }
	    serverIds = serverIds.substring(0, serverIds.length() - 1);
	    ossQuestionList = new ArrayList<Question>();
	    for (String serverId : serverIds.split(",")) {
		int id = Integer.valueOf(serverId);
		OssServer ossServer = getBaseAdminContext()
			.getOssServerById(id);
		if (ossServer == null) {
		    continue;
		}
		try {
		    Map<String, Object> object = new HashMap<String, Object>();
		    object.put("handlerName", "QuestionHandler");
		    object.put("methodName", "query");
		    object.put("beginTime", beginDate);
		    object.put("endTime", endDate);
		    object = CONNECTION.sendMsg(object);
		    List<Question> questionNewList = (List<Question>) object
			    .get("ossQuestionList");
		    for (Question ossQuestion : questionNewList) {
			ossQuestion.setServerName(ossServer.getName());
			ossQuestion.setServerCode(ossServer.getServerCode());
			ossQuestion.setServerId(id);
			ossQuestionList.add(ossQuestion);
		    }
		} catch (Exception e) {
		    this.setActionMsg(this.getActionMsg() + ossServer.getName() + "服务器出错，请检查...");
		    e.printStackTrace();
		}
	    }
	    this.allNum = ossQuestionList.size();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    /**
     * 单服分页版本
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "question_query", results = { @Result(name = "success", location = "../../cjwz/message/question.jsp") })
    public String query() throws Exception {
	if (beginDate == null) {
	    beginDate = DateService.getCurrentDayFirstUtilDate();
	} else {
	    beginDate = DateService.getDateFirstTime(beginDate);
	}
	if (endDate == null) {
	    endDate = DateService.getCurrentDayLastUtilDate();
	} else {
	    endDate = DateService.getDateLastTime(endDate);
	}
	if (ossServerId == null || ossServerId.equals("-1")) {
	    this.setActionMsg("server is error");
	    return SUCCESS;
	}

	// 给服务器赋值
	if (operationId != -1) {
	    ossServerList = new ArrayList<OssServer>();
	    List<OssServer> list = getBaseAdminContext().getOssServers();
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId().intValue() == operationId
			.intValue()) {
		    ossServerList.add(ossServer);
		}
	    }
	}
	if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
	    setOnePageNum(20);
	}
	if (getCurrPageNO() == null || getCurrPageNO() == 0) {
	    setCurrPageNO(1);
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;

	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "QuestionHandler");
	    object.put("methodName", "index");
	    object.put("beginTime", beginDate);
	    object.put("endTime", endDate);
	    object.put("questionType", questionType);
	    object.put("questionStatus", questionStatus);
	    object.put("loginName", loginName);
	    object.put("nickName", nickName);
	    object.put("beginNum", beginNum);
	    object.put("onePageNum", onePageNum);
	    object = CONNECTION.sendMsg(object);
	    ossQuestionList = (List<Question>) object.get("ossQuestionList");
	    OssServer ossServer = getBaseAdminContext().getOssServerById(
		    ossServerId);
	    for (Question ossQuestion : ossQuestionList) {
		ossQuestion.setServerName(ossServer.getName());
		ossQuestion.setServerCode(ossServer.getServerCode());
		ossQuestion.setServerId(ossServerId);
	    }
	    allNum = (Integer) object.get("allNum");
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		    / onePageNum;
	    // 当前页设置
	    if (currPageNO.intValue() > pages) {
		currPageNO = pages;
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return SUCCESS;
    }

    /**
     * 回复玩家问题
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "question_update")
    public String replyQuestion() throws Exception {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	String code = "ok";
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "QuestionHandler");
	object.put("methodName", "updateQuestion");
	ossQuestion.setReplyUserName(getBaseAdminContext().getOssUser()
		.getUsername());
	object.put("ossQuestion", ossQuestion);
	try {
	    object = CONNECTION.sendMsg(object);
	    code = CodeUtil.getActionMsgByMap(object);
	} catch (Exception e) {
	    code = "no";
	    e.printStackTrace();
	}
	out.print(JSON.toJSON(code).toString());
	out.close();
	return null;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginDate(Date beginDate) {
	this.beginDate = beginDate;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public void setOssQuestion(Question ossQuestion) {
	this.ossQuestion = ossQuestion;
    }

    public void setOssQuestionList(List<Question> ossQuestionList) {
	this.ossQuestionList = ossQuestionList;
    }

    public void setOssServerId(Integer ossServerId) {
	this.ossServerId = ossServerId;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setQuestion(Question ossQuestion) {
	this.ossQuestion = ossQuestion;
    }

    public void setQuestionList(List<Question> ossQuestionList) {
	this.ossQuestionList = ossQuestionList;
    }

    public void setQuestionStatus(String questionStatus) {
	this.questionStatus = questionStatus;
    }

    public void setQuestionType(String questionType) {
	this.questionType = questionType;
    }

}
