package com.jcwx.game.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class PageTag extends ComponentTagSupport {

    private static final long serialVersionUID = 6464168398214506236L;

    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public Component getBean(ValueStack valueStack, HttpServletRequest servletRequest,
	    HttpServletResponse httpServletResponse) {
	ActionSupport actionObject = (ActionSupport)valueStack.getRoot().get(0);
	StringBuffer sb = new StringBuffer();
	sb.append("<div class='panelBar' >");
	sb.append("<div class='pages'>");
	sb.append("<span><s:text name='show'/></span>");
	sb.append("<select name='numPerPage' onchange=\"navTabPageBreak({'page.numPerPage':this.value,'page.pageNum':1})\">");
	sb.append("	<option value='20' "+(page.getOnePageNum()==20?"selected":"")+">20</option>");
	sb.append("	<option value='50' "+(page.getOnePageNum()==50?"selected":"")+">50</option>");
	sb.append("	<option value='100' "+(page.getOnePageNum()==100?"selected":"")+">100</option>");
	sb.append("	<option value='200' "+(page.getOnePageNum()==200?"selected":"")+">200</option>");
	sb.append("	<option value='500' "+(page.getOnePageNum()==500?"selected":"")+">500</option>");
	sb.append("</select>");
	sb.append("<span>"+actionObject.getText("article")+","+actionObject.getText("total")+page.getAllNum()+actionObject.getText("article")+"</span>");
	sb.append("</div>");
	sb.append("<div class='pagination' targetType='navTab' totalCount='"+page.getAllNum()+"' numPerPage='"+page.getOnePageNum()+"' pageNumShown='10' currentPage='"+page.getCurrPageNO()+"'></div>");
	sb.append("</div>");
	try {
	    pageContext.getOut().write(sb.toString());
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return new Component(valueStack);
    }

}
