<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent">

	    <s:form action="password_updateUserPassword" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)" >
	     <div class="pageFormContent nowrap" layoutH="55">
	     
	     
		    	<dl>
		    	    <dt><s:text name="original_login_password" />：</dt>
		    	    <dd>
		    	    	<s:password name="password" maxlength="16" cssClass="required"></s:password>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_new_password" />：</dt>
		    	    <dd>
		    	    	<s:password id="newpassword" name="newPassword" maxlength="16" cssClass="required"></s:password>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="confirm_new_password" />：</dt>
		    	    <dd>
		    	    	<s:password name="confirmNewPassword" maxlength="16" cssClass="required" equalto="#newpassword"></s:password>
		    	    </dd>
		    	</dl>
		    		<dl>
		    	    <dt>&nbsp;</dt>
		    	    <dd>
		    	    <ul>
		    	    	<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_the_change"/></button></div></div></li>
					<li><div class="buttonActive" style="margin-left:5px"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
					</ul>
		    	    </dd>
		    	</dl>
		    </div>
	    	<div class="formBar">
				
					
			</div>
	    </s:form>
</div>
