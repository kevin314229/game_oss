<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
  	<div class="pageContent">
  	
	    <s:form action="ossOperation_addOssOperation" namespace="/admin/base" method="post" theme="simple"
	    cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this);">
		    <div class="pageFormContent nowrap" layoutH="60">
			        <dl>
			    	    <dt><s:text name="name_of_the_operator" />：</dt>
			    	    <dd>
			    	    	<s:textfield name="ossOperation.operationName" cssClass="required"/>
			    	    </dd>
			    	</dl>
			        <dl>
			    	    <dt><s:text name="operators_shorthand" />：</dt>
			    	    <dd>
			    	    	<s:textfield name="ossOperation.carrierOperator"/>
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="operator_instructions" />：</dt>
			    	    <dd>
			    	    	<s:textfield name="ossOperation.operationDetail" cssClass="required"/>
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="divided_into_the_proportion" />：</dt>
			    	    <dd>
			    	    	<s:textfield name="ossOperation.dividendRate" cssClass="required number"/>
			    	    </dd>
			    	</dl>
			    </div>
			    
			    <div class="formBar">
			    	<ul>
			    		<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="add" /></button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="reset"><s:text name="reset" /></button></div></div></li>
		    		</ul>
		    	</div>
	    </s:form>
	    
    </div>
    

