package com.jcwx.game.admin.vo;

public class PageMessage {

    public enum CallbackType {
	/** 完成消息后关闭当前窗口 */
	CLOSE_CURRENT("closeCurrent"),
	/** 回调不做任何操作 */
	NULL(null);
	private String callBackType;

	private CallbackType(String type) {
	    callBackType = type;
	}

	public String getCallbackType() {
	    return callBackType;
	}
    }

    private static final String OPERATION_DONE = "ajaxOperationDone";

    /**
     * 默认完成操作后不做任何操作
     * 
     * */
    public static PageMessage getOkMessage() {
	PageMessage pageMessage = new PageMessage();
	pageMessage.setStatusCode(200);
	pageMessage.setMessage("操作成功");
	pageMessage.setCallbackType(CallbackType.NULL);
	return pageMessage;
    }

    private String callbackType;

    private String forwardUrl;

    private String message;

    private String navTabId;

    private String rel;

    private int statusCode;

    private PageMessage() {

    }

    private String ajaxForward(int statusCode) {

	this.statusCode = statusCode;

	return OPERATION_DONE;

    }

    public String ajaxForwardError(String message) {

	this.message = message;

	return ajaxForward(300);

    }

    public String ajaxForwardSuccess(String message) {

	this.message = message;

	return ajaxForward(200);

    }

    public String getCallbackType() {
	return this.callbackType;
    }

    public String getForwardUrl() {
	return this.forwardUrl;
    }

    public String getMessage() {
	return this.message;
    }

    public String getNavTabId() {
	return this.navTabId;
    }

    public String getRel() {
	return this.rel;
    }

    public int getStatusCode() {
	return this.statusCode;
    }

    public void setCallbackType(CallbackType callbackType) {
	this.callbackType = callbackType.getCallbackType();
    }

    public void setForwardUrl(String forwardUrl) {
	this.forwardUrl = forwardUrl;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public void setNavTabId(String navTabId) {
	this.navTabId = navTabId;
    }

    public void setRel(String rel) {
	this.rel = rel;
    }

    public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
    }

}
