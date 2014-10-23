package com.jcwx.game.admin.event;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.event.Hook;
import com.jcwx.game.exception.ActionValidateException;

public class LoginVerifyEvent implements Hook {
    private HttpSession session;
    private String checkcode;
    private String errorActionMsg;
    private BasalAction basalAction;
    
    
    public LoginVerifyEvent(HttpSession session, String checkcode,
	    String errorActionMsg, BasalAction basalAction) {
	super();
	this.session = session;
	this.checkcode = checkcode;
	this.errorActionMsg = errorActionMsg;
	this.basalAction = basalAction;
    }


    @Override
    public void trigger()throws Exception {
	// 验证码
	    try {
		String kaptchaExpected = (String) session
			.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (StringUtils.isBlank(kaptchaExpected)
			|| !kaptchaExpected.equalsIgnoreCase(checkcode)) {
		    basalAction.addActionMessage(errorActionMsg);
		    throw ActionValidateException.INPUT;
		}
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		throw e;
	    }
	
    }

}
