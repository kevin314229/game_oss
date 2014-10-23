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
	    <s:form action="ossUser_updateOssUser" namespace="/admin/base" method="post" theme="simple" 
	    cssClass="pageForm required-validate"  onsubmit="return $(this).valid()&&navTabSearch(this)">
	   	 <div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="account" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="username" readonly="true"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="real-name" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="realnames" cssClass="required" />
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_administrator_is_operators" />：</dt>
		    	    <dd>
		    	    	<s:select name ="isOperator" id="isOperator" list="#{ '0':getText('no'),'1':getText('is')}" onchange="isSelected(this.value)" />
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="operating_platform_identity" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="carrierOperator" id="carrierOperator" /><span class="info">（<s:text name="operator_user" />）</span>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="state" />：</dt>
		    	    <dd>
		    	    	<s:select list="#{'1': getText('normal'),'0': getText('suspended')}" name="status" listKey="key" listValue="value"></s:select>
		    	    </dd>
		    	</dl>
		    	 </div>
		    	 
		    	<div class="formBar">
			    	<ul>
			    		<s:hidden name="id"></s:hidden>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_the_change" /></button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="reset"><s:text name="reset" /></button></div></div></li>
			    	</ul>
		    	</div>
		    	
	    </s:form>
	    </div>
