package com.jcwx.game.web;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.OssContext;
import com.jcwx.game.service.oss.impl.OssLogService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*@SuppressWarnings("serial")
public class HotMoudleInterceptor extends AbstractInterceptor {
    @Autowired
    OssLogService logService;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
	BaseAdminContext context = OssContext.getBaseAdminContext(false);
	if (context != null && context.getCurrentOssServer() != null
		&& StringUtils.isNotBlank(context.getOssUser().getUsername())) {
	    Map<String, Object> mapResult = Maps.newHashMap();
	    mapResult.put("className", invocation.getAction().getClass()
		    .getName());
	    mapResult.put("actionName", invocation.getProxy().getActionName());
	    mapResult.put("methodName", invocation.getProxy().getMethod());
	    mapResult.put("nameSpace", invocation.getProxy().getNamespace());
	    mapResult.put("createTime", DateService.getCurrentDateAsString());
	    mapResult.put("currentUser", context.getOssUser().getUsername());
	    logService.createOssLog(OssLogConstant.OSS_HOT_MOUDLE,
		    JSON.toJSONString(mapResult));
	}
	return invocation.invoke();

    }

}
*/