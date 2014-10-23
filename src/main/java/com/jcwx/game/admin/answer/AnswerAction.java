package com.jcwx.game.admin.answer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.Answer;


/** 回复意见 */
@SuppressWarnings("unused")
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class AnswerAction extends BasalAction {

    private static final long serialVersionUID = 826129094932369128L;

    public int answerId;

    private int cid; // 意见id
    private Answer model = new Answer();

    private String question;

    private String questionReplay;// 回复

    @Action(value = "comment_addAnswer", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "comment_browseComment", "namespace", "/admin/base",
	    "actionMsg", "${actionMsg}" }) })
    public String addAnswer() throws Exception {
	// commentService =
	// (ICommentService)SpringService.getBean("commentService");
	// answerService =
	// (IAnswerService)SpringService.getBean("answerService");
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("cid", cid);
	object.put("questionReplay", questionReplay);
	object.put("handlerName", "CommentHandler");
	object.put("methodName", "reply");
	try {
	    object = CONNECTION.sendMsg(object);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	this.clearMessages();
	return null;
    }

    public int getAnswerId() {
	return answerId;
    }

    public int getCid() {
	return cid;
    }

    // 采用模型驱动
    public Answer getModel() {
	return model;
    }

    public String getQuestion() {
	return question;
    }

    public String getQuestionReplay() {
	return questionReplay;
    }

    public void setAnswerId(int answerId) {
	this.answerId = answerId;
    }

    public void setCid(int cid) {
	this.cid = cid;
    }

    public void setQuestion(String question) {
	this.question = question;
    }

    public void setQuestionReplay(String questionReplay) {
	this.questionReplay = questionReplay;
    }

    /** 进入添加页面 */
    @Action(value = "comment_toaddAnswer", results = { @Result(name = "success", location = "../../admin/answer/addAnswer.jsp") })
    public String toaddAnswer() throws Exception {
	if (question != null && !"".equals(question)) {
	    addActionMessage(question);
	}
	return SUCCESS;
    }
}
