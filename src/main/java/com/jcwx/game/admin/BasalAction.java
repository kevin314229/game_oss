package com.jcwx.game.admin;

import java.util.List;
import java.util.Map;

import com.jcwx.game.admin.vo.PageMessage;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.interceptor.ValidationWorkflowAware;

/** 基本Action2 */
@SuppressWarnings("serial")
public class BasalAction extends BaseInfoAction implements Result,
ValidationWorkflowAware {

    protected static final String VERIFY_ERROR = "verify_error";

    /** action对象之间通信 warning */
    private String actionMsg;

    /** 远程数据长度 */
    private Integer contentLength;

    /** 错误信息 */
    private String errorInfo;

    /** 本地远行时间 */
    private Long localRunTime;

    /** 远程远行时间 */
    private Long remoteRunTime;

    /** 正确信息 */
    private String successInfo;

    private PageMessage pageMessage = PageMessage.getOkMessage();

    public String getActionMsg() {
	return actionMsg;
    }

    public Integer getContentLength() {
	return contentLength;
    }

    public String getErrorInfo() {
	return errorInfo;
    }

    public Long getLocalRunTime() {
	return localRunTime;
    }

    public Long getRemoteRunTime() {
	return remoteRunTime;
    }

    public String getSuccessInfo() {
	return this.successInfo;
    }

    /** 初始化数据 */
    protected void handleKryoMap(Map<String, Object> object) {
	if (object == null)
	    return;
	// 外网更新，值不存在，临时做一下处理
	Long rtime = (Long) object.get("remoteRunTime");
	Integer lenght = (Integer) object.get("contentLength");
	if (rtime == null || lenght == null)
	    return;

	if (getRemoteRunTime() == null)
	    setRemoteRunTime(0L);
	setRemoteRunTime(getRemoteRunTime() + rtime);

	//
	if (getContentLength() == null)
	    setContentLength(0);
	setContentLength(getContentLength() + lenght);

    }

    protected void resetInfos() {
	this.successInfo = "";
	this.setErrorInfo("");
    }
    
    public void setActionMsg(String actionMsg) {
	this.actionMsg = actionMsg;
    }

    public void setContentLength(Integer contentLength) {
	this.contentLength = contentLength;
    }

    public void setErrorInfo(String errorInfo) {
	this.errorInfo = errorInfo;
    }

    public void setLocalRunTime(Long localRunTime) {
	this.localRunTime = localRunTime;
    }

    public void setRemoteRunTime(Long remoteRunTime) {
	this.remoteRunTime = remoteRunTime;
    }

    public void setSuccessInfo(String successInfo) {
	this.successInfo = successInfo;
    }

    public PageMessage getPageMessage() {
	return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
	this.pageMessage = pageMessage;
    }

    @Override
    public String getInputResultName() {
	if(getFieldErrors().size()>0){
	    StringBuilder builder = new StringBuilder();
	    for(List<String> wai : getFieldErrors().values()){
		for(String str:wai){
		    builder.append("<p>").append(str).append("</p>");   
		}
	    }
	    setActionMsg(builder.toString());
	    clearFieldErrors();
	}
	return VERIFY_ERROR;
    }

    @Override
    public void execute(ActionInvocation invocation) throws Exception {
	invocation.invoke();
    }

}
