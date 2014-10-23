<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
  	<div class="pageContent">
	    <s:form action="ossServer_doUpdateOssServer" namespace="/admin/base" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this);">
	     <div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="subordinate_to_the_operator" />：</dt>
		    	    <dd>
					<select name="ossServer.serverId" >
						<s:iterator value="ossOperationList" var="oneRow"  status="offset">
							<s:if test="ossServer.serverProvider==operationName">
							<option value="<s:property value="#oneRow.id" />" selected> 	
			    				<s:property value="#oneRow.operationName"/>
			    			</option>
							</s:if>
							<s:else>
							<option value="<s:property value="#oneRow.id" />"  > 	
			    				<s:property value="#oneRow.operationName"/>
			    			</option>
							</s:else>
			    		</s:iterator>
					</select>
		    	   </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_game_zone_name" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="ossServer.name" cssClass="required" size="30"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_address_of_the_server" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="ossServer.url" cssClass="required" size="30"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="server_communication" />Key：</dt>
		    	    <dd>
		    	    	<s:textfield name="ossServer.communicateKey" cssClass="required" size="30"/>
		    	    </dd>
		    	</dl>
		    	</div>
		    	
		    	<div class="formBar">
				<ul>
			    	<s:hidden name="ossServer.id"></s:hidden>
					<s:hidden name="ossServer.createTime"></s:hidden>
					<s:hidden name="ossServer.createUser"></s:hidden>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_the_change" /></button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="reset"><s:text name="reset" /></button></div></div></li>
			    </ul>
		    </div>
	    </s:form>
	   </div>
    
