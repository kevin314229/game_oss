<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<h2 class="contentTitle"><s:text name="system_management" />： <s:text name="modify_the_role" /> </h2>
<div class="pageContent">
	<s:form action="ossRole_updateOssRole"   namespace="/admin/base" method="post" theme="simple" 
	cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<input type="hidden" name="ossRoleID" value='<s:property value="ossRoleID"/>'> 
		<div class="pageFormContent nowrap" >

			<dl>
				<dt><s:text name="part_number" />：</dt>
				<dd style="width: 100px">
					 <s:textfield  type="text" name="roleCode" minlength="2"  maxlength="20" cssClass="required" />
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt><s:text name="character_name" />：</dt>
				<dd style="width: 100px">
					<s:textfield  type="text" name="roleName" minlength="2" maxlength="20" cssClass="required" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><s:text name="the_role_that" />：</dt>
				<dd style="width: 100px">
					<s:textfield name="roleDesc" rows="3" cols="17" cssClass="required" />
					<span class="info"></span>
				</dd>
			</dl>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" ><s:text name="confirm_the_change" /></button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close"><s:text name="close" /></button></div></div></li>
			</ul>
		</div>
	</s:form>
	
</div>


