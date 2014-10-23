package com.jcwx.game.admin.message;

import java.util.HashMap;
import java.util.Map;

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
 * @Description: 消息与通知：发送系统公告
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class SendSysMsgAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private IOssLogService ossLogService;

    /** 系统消息 */
    private String systemMessage;

    @Override
    @Action(value = "sendSysMsg_sendSysMsg", results = { @Result(name = "success", location = "../../admin/message/sendSysMsg.jsp") })
    public String execute() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("systemMessage", systemMessage);
	object.put("handlerName", "SendSysMsgHandler");
	object = CONNECTION.sendMsg(object);
	Object code = object.get("code");
	resetInfos();
	if (code.toString().equals("0")) {
	    setSuccessInfo("发送成功");
	} else {
	    setErrorInfo("发送失败");
	}

	// 日志
	StringBuffer buf = new StringBuffer();
	buf.append("msg:" + systemMessage).append(
		"#result:" + (code.toString().equals("0") ? "成功" : "失败"));
	ossLogService.createOssLog(OssLogConstant.SEND_MSG, systemMessage);

	return "success";
    }

    public String getSystemMessage() {
	return systemMessage;
    }

    @Action(value = "sendSysMsg_index", results = { @Result(name = "success", location = "../../admin/message/sendSysMsg.jsp") })
    public String sendMsgIndex() {
	return "success";
    }

    public void setSystemMessage(String systemMessage) {
	this.systemMessage = systemMessage;
    }
}