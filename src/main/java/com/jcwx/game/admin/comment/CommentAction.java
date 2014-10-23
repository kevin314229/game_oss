package com.jcwx.game.admin.comment;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.PlayerBaseQuestion;
import com.jcwx.game.service.oss.IAnswerService;
import com.jcwx.game.service.oss.ICommentService;


@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class CommentAction extends BasalAction {

    private static final long serialVersionUID = 7320938239187192557L;
    /** 总记录数 */
    private Integer allNum;

    public int answerID;

    @Autowired
    private IAnswerService answerService;

    /** 开始时间 */
    private String beginDate;

    @Autowired
    public ICommentService commentService;

    public String content;

    /** 当前页数 */
    private Integer currPageNO;

    /** 结束时间 */
    private String endDate;

    private String nickName;
    /** 每页记录数 */
    private Integer onePageNum;
    /** 排序标记 */
    private String orderFlag;

    /** 总页数 */
    private Integer pages;
    public List<PlayerBaseQuestion> playerBaseQuestionList;
    private String questionStatus;// 状态

    private String questionType; // 类型

    @SuppressWarnings("unchecked")
    @Action(value = "comment_browseComment", results = { @Result(name = "success", location = "../../admin/comment/browseComment.jsp") })
    public String browseComment() throws Exception {

	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}
	if (questionType == null || "".equals(questionType))
	    questionType = null; // 类型
	if (questionStatus == null || "".equals(questionStatus))
	    questionStatus = null;// 状态
	if (nickName == null || "".equals(nickName))
	    nickName = null;// 状态

	// 总记录数
	onePageNum = 20; // 每页20条记录
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "DESC";
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("questionType", questionType);
	object.put("questionStatus", questionStatus);
	object.put("orderFlag", orderFlag);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("nickName", nickName);
	object.put("handlerName", "CommentHandler");
	object = CONNECTION.sendMsg(object);
	allNum = (Integer) object.get("allNum");
	playerBaseQuestionList = (List<PlayerBaseQuestion>) object
		.get("playerBaseQuestionList");
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return "success";
    }

    public Integer getAllNum() {
	return allNum;
    }

    public int getAnswerID() {
	return answerID;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public ICommentService getCommentService() {
	return commentService;
    }

    public String getContent() {
	return content;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public String getEndDate() {
	return endDate;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public String getOrderFlag() {
	return orderFlag;
    }

    public Integer getPages() {
	return pages;
    }

    public List<PlayerBaseQuestion> getPlayerBaseQuestionList() {
	return playerBaseQuestionList;
    }

    public String getQuestionStatus() {
	return questionStatus;
    }

    public String getQuestionType() {
	return questionType;
    }

    @Action(value = "comment_replyAnswer", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "comment_browseComment", "namespace", "/admin/base/" }) })
    public String replyAnswer() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("id", answerID);
	object.put("questionReplay", content);
	object.put("methodName", "replyAction");
	object.put("handlerName", "CommentHandler");
	object = CONNECTION.sendMsg(object);
	return "success";
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setAnswerID(int answerID) {
	this.answerID = answerID;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCommentService(ICommentService commentService) {
	this.commentService = commentService;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOrderFlag(String orderFlag) {
	this.orderFlag = orderFlag;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPlayerBaseQuestionList(
	    List<PlayerBaseQuestion> playerBaseQuestionList) {
	this.playerBaseQuestionList = playerBaseQuestionList;
    }

    public void setQuestionStatus(String questionStatus) {
	this.questionStatus = questionStatus;
    }

    public void setQuestionType(String questionType) {
	this.questionType = questionType;
    }

}
