package com.jcwx.game.common;

import org.apache.commons.lang3.Validate;
import org.apache.struts2.ServletActionContext;

import com.jcwx.game.admin.constant.AdminSystemConstant;

/**
 * Oss上下文管理
 * 
 * @author liushang
 * 
 */
public class OssContext {
    /**
     * 得到SESSION上下文
     * 
     * 如果SESSION不存在的时候抛出异常。
     * 
     * 如果当SESSION中不存在BaseAdminContext时返回NULL
     * 
     * 请调用@see OssContext.getBaseAdminContext(boolean isValid)
     */
    public static BaseAdminContext getBaseAdminContext() {

	return getBaseAdminContext(true);

    }

    /**
     * 得到SESSION上下文
     * 
     * @param isValid
     *            是否验证得到的内容为null，如果验证，但结果为Null时，抛出异常。
     * @return
     */
    public static BaseAdminContext getBaseAdminContext(boolean isValid) {

	BaseAdminContext context = (BaseAdminContext) ServletActionContext
		.getRequest().getSession()
		.getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY);
	if (isValid) {
	    Validate.notNull(context,
		    "baseAdminContext is null!BaseInfoAction error");
	}

	return context;
    }
}
