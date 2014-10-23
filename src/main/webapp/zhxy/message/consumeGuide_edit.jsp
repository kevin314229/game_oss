<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
  <div class="pageContent"> 
	     <s:form action="consumeGuide_update" namespace="/admin/message" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)" >
		  	 <s:hidden id="consumeGuideId" name="ossConsumeGuide.consumeGuideId"></s:hidden>
		  	 <s:hidden id="createTime" name="ossConsumeGuide.createTime"></s:hidden>
		  	 <div class="pageFormContent" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="name" />：</dt>
		    	    <dd>
		    	     <s:textfield  id="name" name="ossConsumeGuide.name" minlength="2" class="required"></s:textfield>
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="go_interface" />：</dt>
		    	    <dd>
		    	    	<%-- <s:select onchange="onchangeSellType(this)" id="path" name = "ossConsumeGuide.path" headerKey="0" 
		    	    	list="#{'0':'<s:text name="no" />', '1':'<s:text name="mall" />','2':'<s:text name="package_deals" />',
		    	    	'3':'<s:text name="recharge" />','4':'<s:text name="elixir_park" />','5':'<s:text name="alchemy" /> ',
		    	    	'6':'vip'}" /> --%>
		    	    	<s:select onchange="onchangeSellType(this)" id="path" name = "ossConsumeGuide.path" headerKey="0" 
		        	list="#{'0':getText('no')  , '1':getText('mall'),
		        	'2':getText('package_deals'),'3':getText('recharge'),
		        	'4':getText('elixir_park'),'5':getText('alchemy'),'6':'vip'}" />
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="order" />：</dt>
		    	    <dd>
		    	    	<s:textfield  min="1" id="sort" name="ossConsumeGuide.sort" class="digits required" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="are_open" />：</dt>
		    	  
		    	    <dd>
	    	    		<s:text name="be" /><input type="radio" name="ossConsumeGuide.opened"  value="1"  <s:if test="ossConsumeGuide.opened==1">checked</s:if> id="openedY" />
		    	    	<s:text name="no" /><input type="radio" name="ossConsumeGuide.opened"  value="0"  <s:if test="ossConsumeGuide.opened==0">checked</s:if> id="openedN"  />
		    	    </dd>
		    	</dl>		    	
		    	<dl>
		    	    <dt><s:text name="describe" />：</dt>
		    	    <dd>
		    	    	<s:textarea id="describe" cols="30" rows="4"  name="ossConsumeGuide.describe" >
		    	    	</s:textarea>
		    	    </dd>
		    	</dl>
		    	</div>
		    	<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_the_changes" /></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" class="close"><s:text name="close" /></button></div></div></li>
					</ul>
					
				</div>
	    </s:form>
	   </div>  