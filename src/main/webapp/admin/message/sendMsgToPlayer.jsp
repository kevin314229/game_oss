<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent" >
		
			<s:form action="sendMsgToPlayer_sendMsgToPlayer" method="post" theme="simple" 
			cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			<div class="pageFormContent nowrap" layoutH="55">
				<dl>
					<dt><s:text name="the_player_name" />：</dt>
					<dd><s:textfield name="nickName" cssClass="required"></s:textfield></dd>
				<dl>
				<dl>
					<dt><s:text name="email_title" />：</dt>
					<dd><s:textfield name="title" maxlength="32" cssClass="required"></s:textfield></dd>
				<dl>
				<dl>
					<dt><s:text name="the_message" />(<s:text name="mail" />)<s:text name="content" />(<s:text name="no_more_than" />256<s:text name="a_character" />)</dt>
					<dd><s:textarea name="singleMessage" cols="50" rows="5" maxLength="256" cssClass="required"></s:textarea></dd>
				<dl>
				
				<dl>
					<dt> &nbsp;</dt>
					<dd><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div></div>
					<div class="button" style="margin-left:10px"><div class="buttonContent"><button type="button" class="close"><s:text name="cancel" /></button></div></div>
					</dd>
				<dl>
			</div>
			
			<!-- <div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="button" class="close"><s:text name="cancel" /></button></div></div></li>
				</ul>
			</div> -->
			
			</s:form>
	
</div>
  