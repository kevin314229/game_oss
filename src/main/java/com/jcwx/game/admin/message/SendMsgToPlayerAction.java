package com.jcwx.game.admin.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.service.oss.IOssLogService;


/**
 * @Description: 给玩家发信
 */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class SendMsgToPlayerAction extends BasalAction {

    /** 玩家名称 */
    private String nickName;

    @Autowired
    private IOssLogService ossLogService;

    /** 邮件内容 */
    private String singleMessage;

    /** 邮件标题 */
    private String title;

    @Override
    @Action(value = "sendMsgToPlayer_sendMsgToPlayer", results = {
	    @Result(name = "success", location = "../../admin/message/sendMsgToPlayer.jsp"),
	    @Result(name = "error", location = "../../admin/message/sendMsgToPlayer.jsp") })
    public String execute() throws Exception {
	resetInfos();
	boolean isSend = true;
	if (isSend && (nickName == null || "".equals(nickName))) {
	    setErrorInfo("玩家名称不能为空");
	    isSend = false;
	    return "error";
	}
	if (isSend && (title == null || "".equals(title))) {
	    setErrorInfo("邮件标题不能为空");
	    isSend = false;
	    return "error";
	}
	if (isSend && (singleMessage == null || "".equals(singleMessage))) {
	    setErrorInfo("邮件内容不能为空");
	    isSend = false;
	    return "error";
	}
	boolean tmp = false;
	if (isSend == true) {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("nickName", nickName);
	    object.put("title", title);
	    object.put("singleMessage", singleMessage);
	    object.put("handlerName", "SendMsgToPlayerHandler");
	    object = CONNECTION.sendMsg(object);
	    Object code = object.get("code");
	    if (code.toString().equals("0") && isSend) {
		setSuccessInfo("发送成功");
		tmp = true;
	    } else {
		setErrorInfo("发送失败");
	    }
	}

	StringBuffer buf = new StringBuffer();
	buf.append("nickName:" + nickName).append("#title:" + title)
		.append("#content:" + singleMessage);
	buf.append("#result:" + (tmp ? "成功" : "失败"));
	ossLogService.createOssLog(ServletActionContext.getRequest(),
		OssLogConstant.SEND_MSG_TO_PLAYER, buf.toString(), null);

	return "success";
    }

    public String getNickName() {
	return nickName;
    }

    public String getSingleMessage() {
	return singleMessage;
    }

    public String getTitle() {
	return title;
    }

    @Action(value = "sendMsgToPlayer_index", results = { @Result(name = "success", location = "../../admin/message/sendMsgToPlayer.jsp") })
    public String SendMsgToPlayerIndex() {
	return "success";
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setSingleMessage(String singleMessage) {
	this.singleMessage = singleMessage;
    }

    public void setTitle(String title) {
	this.title = title;
    }
}