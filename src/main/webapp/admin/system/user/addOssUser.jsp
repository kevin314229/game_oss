<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script type="text/javascript">

	function isSelected(value) { 
		var isOperator = document.getElementById("isOperator"); 
		var status=isOperator.options[isOperator.selectedIndex].value;
			if(status=="1"){
				$("input[name=carrierOperator]").addClass("required");
			}else{
				$("input[name=carrierOperator]").removeClass("required");
			}
	} 
	
	$(document).ready(function(){
		if($("#isOperator").val()=="1"){
			$("#carrierOperator").addClass("required");
		}else{
			$("#carrierOperator").removeClass("required");
		}
	});
	
</script>

<s:include value="/admin/template/scriptMessage.jsp"/>
  	<div class="pageContent">

	    <s:form action="ossUser_addOssUser" namespace="/admin/base" method="post" theme="simple"
	    cssClass="pageForm required-validate"  onsubmit="return $(this).valid()&&navTabSearch(this);">
	    	<div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="account" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="username" cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="password" />：</dt>
		    	    <dd>
		    	    	<s:password name="password" id="password" maxlength="16" cssClass="required"></s:password>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="confirm_password" />：</dt>
		    	    <dd>
		    	    	
		    	    	<s:password name="confirm_password" maxlength="16" cssClass="required" equalto="#password"></s:password>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="real-name" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="realnames" cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_administrator_is_operators" />：</dt>
		    	    <dd>
		    	    	<s:select name ="isOperator" id="isOperator" headerKey="0" list="#{ '0':getText('no'),'1':getText('is')}" onchange="isSelected(this.value)"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="operating_platform_identity" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="carrierOperator" id="carrierOperator"/><span class="info">（<s:text name="operator_user" />）</span>
		    	    </dd>
		    	</dl>
		    	</div>
		    	
		    	<div class="formBar">
			    	<ul>
			    		<s:hidden name="id"></s:hidden>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="add" /></button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="reset"><s:text name="reset" /></button></div></div></li>
			    	</ul>
		    	</div>
	    </s:form>
    </div>
    
