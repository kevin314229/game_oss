package com.jcwx.game.admin.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
 * @Description: 批量给玩家发信息
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class SendMsgToMorePlayerAction extends BasalAction {

    /** 发送给所有玩家的标记 */
    private Integer allFlag;

    /** 邮件内容 */
    private String msgContent;

    /** 邮件标题 */
    private String msgTitle;

    @Autowired
    private IOssLogService ossLogService;

    /** 玩家名(逗号分隔) */
    private String playerNames;

    @Override
    @SuppressWarnings("unchecked")
    @Action(value = "sendMsgToMorePlayer_sendMsgToMorePlayer", results = {
	    @Result(name = "success", location = "../../admin/message/sendMsgToMorePlayer.jsp"),
	    @Result(name = "error", location = "../../admin/message/sendMsgToMorePlayer.jsp") })
    public String execute() throws Exception {
	resetInfos();
	if (allFlag.intValue() == 1) { // 所有玩家
	    if (StringUtils.isBlank(msgTitle)) {
		setErrorInfo("邮件标题不能为空");
		return "error";
	    }
	    if (StringUtils.isBlank(msgContent)) {
		setErrorInfo("邮件内容不能为空");
		return "error";
	    }
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("flag", 1);
	    object.put("title", msgTitle);
	    object.put("content", msgContent);
	    object.put("handlerName", "SendMsgToMorePlayerHandler");
	    object = CONNECTION.sendMsg(object);
	    Object code = object.get("code");
	    if (code.toString().equals("0")) {
		setSuccessInfo("给所有玩家发送成功");
	    } else {
		setErrorInfo("给所有玩家发送失败");
	    }

	    StringBuffer buf = new StringBuffer();
	    buf.append("playerNames:" + playerNames)
		    .append("#allFlag:" + allFlag)
		    .append("#msgTitle:" + msgTitle)
		    .append("#msgContent:" + msgContent)
		    .append("#result:"
			    + (code.toString().equals("0") ? "给所有玩家发送成功"
				    : "给所有玩家发送失败"));
	    ossLogService.createOssLog(ServletActionContext.getRequest(),
		    OssLogConstant.SEND_MSG_TO_PLAYER, buf.toString(), null);

	} else {
	    if (StringUtils.isBlank(playerNames)) {
		setErrorInfo("玩家名不能为空");
		return "error";
	    }
	    if (StringUtils.isBlank(msgTitle)) {
		setErrorInfo("邮件标题不能为空");
		return "error";
	    }
	    if (StringUtils.isBlank(msgContent)) {
		setErrorInfo("邮件内容不能为空");
		return "error";
	    }
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("flag", 0);
	    String[] playerNamess = playerNames.split(",");
	    List<String> lits = new ArrayList<String>();
	    for (int i = 0; i < playerNamess.length; i++) {
		lits.add(playerNamess[i]);
	    }
	    object.put("nickNames", lits);
	    object.put("title", msgTitle);
	    object.put("content", msgContent);
	    object.put("handlerName", "SendMsgToMorePlayerHandler");
	    Map<String, Object> resultMap = CONNECTION.sendMsg(object);
	    Object code = resultMap.get("code");
	    StringBuffer msg = new StringBuffer();
	    if (code.toString().equals("0")) {
		setSuccessInfo("给多个玩家发送成功");
	    } else {
		List<Object> nickNameList = (List<Object>) object
			.get("nickNameList");
		if (nickNameList != null) {
		    for (int i = 0; i < nickNameList.size(); i++) {
			if (i == nickNameList.size() - 1) {
			    msg.append(nickNameList.get(i));
			} else {
			    msg.append(nickNameList.get(i) + ",");
			}
		    }
		}
		setErrorInfo("给玩家" + msg.toString() + "发送失败");
	    }
	    StringBuffer buf = new StringBuffer();
	    buf.append("playerNames:" + playerNames)
		    .append("#allFlag:" + allFlag)
		    .append("#msgTitle:" + msgTitle)
		    .append("#msgContent:" + msgContent)
		    .append("#fail:" + msg.toString());
	    ossLogService.createOssLog(ServletActionContext.getRequest(),
		    OssLogConstant.SEND_MSG_TO_MORE_PLAYER, buf.toString(),
		    null);

	}
	return "success";
    }

    public Integer getAllFlag() {
	return allFlag;
    }

    public String getMsgContent() {
	return msgContent;
    }

    public String getMsgTitle() {
	return msgTitle;
    }

    public String getPlayerNames() {
	return playerNames;
    }

    @Action(value = "sendMsgToMorePlayer_index", results = { @Result(name = "success", location = "../../admin/message/sendMsgToMorePlayer.jsp") })
    public String index() {
	return "success";
    }

    public void setAllFlag(Integer allFlag) {
	this.allFlag = allFlag;
    }

    public void setMsgContent(String msgContent) {
	this.msgContent = msgContent;
    }

    public void setMsgTitle(String msgTitle) {
	this.msgTitle = msgTitle;
    }

    public void setPlayerNames(String playerNames) {
	this.playerNames = playerNames;
    }
}