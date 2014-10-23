package com.jcwx.game.exception;

import com.jcwx.game.admin.BasalAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName: ActionValidateException
 * @Description: Action的Param合法,内容合法，等验证之后,抛出该异常,仍然会返回相应的内容 可以直接throw
 *               ActionValidateException.SUCCESS,如果要增加自定义message,
 *               可以调用newActionValidateException然后传入已经预定义好的SUCCESS
 *               ,INPUT,LOGIN三个参数。
 *               本类宗旨:自由在Action抛出验证异常，鼓励使用commons-lang3的Validate的相关验证。
 * @author liushang 364173778@qq.com
 * @date 2013年12月26日 下午6:20:20
 * 
 */
@SuppressWarnings("serial")
public class ActionValidateException extends RuntimeException {
    public static final ActionValidateException ERROR = new ActionValidateException(
	    ActionSupport.ERROR);
    public static final ActionValidateException INPUT = new ActionValidateException(
	    ActionSupport.INPUT);
    public static final ActionValidateException LOGIN = new ActionValidateException(
	    ActionSupport.LOGIN);
    public static final ActionValidateException SUCCESS = new ActionValidateException(
	    ActionSupport.SUCCESS);

    public static ActionValidateException newActionValidateException(
	    ActionValidateException e, String message) {
	return new ActionValidateException(e, message);
    }

    public static ActionValidateException newActionValidateException(Exception e) {
	return new ActionValidateException(e);
    }

    private String actionMessage;

    public ActionValidateException(ActionValidateException exception,
	    BasalAction action, String actionMsg) {
	super(action.getActionMsg());
	action.setActionMsg(actionMsg);
	setActionMessage(exception.getActionMessage());
    }

    public ActionValidateException(ActionValidateException exception,
	    String message) {
	super(message);
	setActionMessage(exception.getActionMessage());
    }

    private ActionValidateException(Exception actionMessage) {
	super(actionMessage.getMessage());
	setActionMessage(Action.SUCCESS);
    }

    private ActionValidateException(String actionMessage) {
	super();
	setActionMessage(actionMessage);
    }

    public String getActionMessage() {
	return this.actionMessage;
    }

    public void setActionMessage(String actionMessage) {
	this.actionMessage = actionMessage;
    }

}
