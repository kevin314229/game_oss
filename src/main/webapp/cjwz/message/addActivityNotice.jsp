<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
  <div class="pageContent"  >
		
	     <s:form action="activityNotice_addActivityNotice" namespace="/cjwz/message" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)" >
	     	<div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt width="30%"><s:text name="activity_description" />：</dt>
		    	    <dd>
		    	  	 (提示不能超过2048个字符,1024个汉字!)
		    	    <br>
		    	    	<s:textarea cols="80" rows="20" id="content" name="activityNotice.content" cssClass="required" maxlength="2048"></s:textarea>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt width="30%">url：</dt>
		    	    <dd>
		    	    	<s:textfield id="url"  name="activityNotice.url" cssClass="required">
		    	    	</s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt width="30%"><s:text name="activities_start_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="startTime"  name="activityNotice.startTime" cssClass="required date">
		    	    	</s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt width="30%"><s:text name="activity_over_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="endTime"  name="activityNotice.endTime" cssClass="required date" >
		    	    	</s:textfield>
		    	    </dd>
		    	</dl>
		    </div>
		    	<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_to_add"/></button></div></div></li>
					</ul>
				</div>
			
	    </s:form>
</div>
