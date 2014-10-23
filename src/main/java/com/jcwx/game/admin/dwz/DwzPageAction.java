package com.jcwx.game.admin.dwz;

import org.apache.commons.lang3.StringUtils;

import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.admin.vo.PageMessage;

@SuppressWarnings("serial")
public class DwzPageAction extends BaseInfoAction {
    
    protected PageMessage pageMessage = PageMessage.getOkMessage();

    public PageMessage getPageMessage() {
	return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
	this.pageMessage = pageMessage;
    }

    public void ajaxResult() {

	if (StringUtils.isBlank(getPageMessage().getForwardUrl())) {
	    String url = getHttpServletRequest().getScheme() + "://"; // 请求协议
								      // http 或
								      // https
	    url += getHttpServletRequest().getHeader("host"); // 请求服务器
	    url += getHttpServletRequest().getRequestURI(); // 工程名
	    if (getHttpServletRequest().getQueryString() != null) // 判断请求参数是否为空
		url += "?" + getHttpServletRequest().getQueryString(); // 参数
	    getPageMessage().setForwardUrl(url);
	}

	getJSONResponse().responseJson(getPageMessage());
    }
}
