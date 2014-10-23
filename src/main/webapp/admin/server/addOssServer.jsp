<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
  	<div class="pageContent">
	    <s:form action="ossServer_addOssServer" namespace="/admin/base" method="post" theme="simple" 
	    cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this);">
	    <div class="pageFormContent nowrap" layoutH="60" >
	   		 <dl>
		    	    <dt>大区ID：</dt>
		    	    <dd>
					<s:textfield name="ossServer.id"   size="25"/>
		    	   </dd>
		    	</dl>
	    		 <dl>
		    	    <dt><s:text name="subordinate_to_the_operator" />：</dt>
		    	    <dd>
					<s:select name="ossServer.serverId" list="ossOperationList" listKey="id" listValue="operationName"/>  
		    	   </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_game_zone_name" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="ossServer.name" cssClass="required textInput valid" size="25"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_address_of_the_server" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="ossServer.url" cssClass="required textinput valid" size="25"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="server_communication"  />Key：</dd>
		    	    <dd>
		    	    	<s:textfield name="ossServer.communicateKey" cssClass="required textinput valid" size="25"/>
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
    
