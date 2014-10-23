package com.jcwx.game.web;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONException;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.exception.ActionValidateException;
import com.jcwx.game.exception.GameException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ActionValidateInterceptor extends AbstractInterceptor {
    /**
     * 
     */
    private static final long serialVersionUID = 7726389281310256216L;

    Logger logger = Logger.getLogger(ActionValidateInterceptor.class);

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
	try {
	    return invocation.invoke();
	} catch (ConnectException e) {
	    logger.warn("ConnectException:" + e.getMessage(), e);
	    throw e;
	} catch (ActionValidateException e) {
	    logger.warn("ActionValidateException:" + e.getMessage(), e);
	    Object obj = invocation.getAction();
	    if(obj instanceof BasalAction){
		BasalAction ba = (BasalAction)obj;
		ba.setErrorInfo(e.getMessage());
	    }
	    return e.getActionMessage();
	} catch (NullPointerException e) {
	    logger.warn("NullPointerException:" + e.getMessage(), e);
	    throw new InvocationTargetException(e);
	} catch (GameException e) {
	    logger.warn("GameException:" + e.getMessage(), e);
	    throw e;
	} catch (JSONException e) {
	    logger.warn("JSONException:" + e.getMessage(), e);
	    return Action.SUCCESS;
	} catch (IllegalArgumentException e) {
	    logger.warn("IllegalArgumentException:" + e.getMessage(), e);
	    throw e;
	} catch (Exception e) {
	    logger.warn("Exception:" + e.getMessage(), e);
	    throw e;
	}
    }

}
