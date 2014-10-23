<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<style>
.pageFormContent label{
float: none;
}
</style>
<div class="pageContent">
	<s:form action="systemset_update"   namespace="/admin/system" method="post" theme="simple" 
	cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
	
		<div class="pageFormContent nowrap" layoutH="50" >
			<dl>
				<dt>验证码：</dt>
				<dd>
				<s:radio name="system.verify" list="%{#{'true':'是','false':'否'}}" theme="simple" cssClass="required"></s:radio>
				</dd>
			</dl>
			
			<dl>
				<dt>自动登录：</dt>
				<dd>
				<s:radio name="system.autoLogin" list="%{#{'true':'是','false':'否'}}" theme="simple" cssClass="required"></s:radio>
				</dd>
			</dl>
			
			<dl>
				<dt>提示模式：</dt>
				<dd>
				<s:radio name="system.tip" list="%{#{'true':'是','false':'否'}}" theme="simple" cssClass="required"></s:radio>
				</dd>
			</dl>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >提交</button></div></div></li>
			</ul>
		</div>
	</s:form>
	
</div>

