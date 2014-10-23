<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
  	<div class="pageContent">
	     <s:form action="systemConfig_doAdd" namespace="/admin/base" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return checkForm();" >
	     	<div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="system" />key：</dt>
		    	    <dd>
		    	    	<s:textfield size="40" id="systemKey" cssClass="required" name="ossSystemParam.systemKey" />
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="system_parameter_values" />：</dt>
		    	    <dd>
		    	    	<s:textfield size="40" id="systemValue" cssClass="required" name="ossSystemParam.systemValue" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	
		    	
		    	<dl>
		    	    <dt><s:text name="describe" />：</dt>
		    	    <dd>
		    	    	<s:textarea id="systemDesc" cols="32" rows="6" cssClass="required" name="ossSystemParam.systemDesc" >
		    	    	</s:textarea>
		    	    </dd>
		    	</dl>
		    </div>
		    <div class="formBar">
		    	<ul>
		    	    <li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_to_add"/></button></div></div></li>								
		    	    <li><div class="button"><div class="buttonContent"><button type="reset"><s:text name="reset" /></button></div></div></li>
		    	</ul>
		    </div>
	    </s:form>
    </div>
