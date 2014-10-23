<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<s:include value="/admin/template/scriptMessage.jsp"/>
  <div class="pageContent"> 
	     <s:form action="serverNotice_doUpdate" namespace="/admin/message" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
	     <input type="hidden" name="serverNotice.noticeId" value="${serverNotice.noticeId}"/>
		  	 <div class="pageFormContent" layoutH="60">	    	
		    	<dl>
		    	    <dt><s:text name="describe" />：</dt>
		    	    <dd>
		    	    	<s:textarea id="describe" cols="86" rows="25" maxlength="1000" name="serverNotice.notiveContent" >
		    	    	</s:textarea>
		    	    </dd>
		    	</dl>
		    	</div>
		    	<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_to_add"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" class="close"><s:text name="close" /></button></div></div></li>
					</ul>
					
				</div>
	    </s:form>
	   </div>  