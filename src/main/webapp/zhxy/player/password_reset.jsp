<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script>

function initMessage(json){
	if(json.code==0)
		alertMsg.correct(json.msg+" <s:text name="your_new_password_is_reset" />"+json.newPw);
	else{
		alertMsg.error(json.msg);
	}
}
</script>
	<s:include value="/admin/template/scriptMessage.jsp"/>
 
 <div class="pageHeader" >
	<s:form action="password_query"  method="post"   
		id="queryPayForm" theme="simple" cssClass="pageForm required-validate"   onsubmit="return $(this).valid()&&navTabSearch(this)">
 	   <div class="searchBar" style="margin-bottom:30px" >
		   <div  class="searchDiv_left">
		  <s:text name="account" />：&nbsp;&nbsp;&nbsp;<s:textfield name="loginName" id="loginName"/>
		  </div>
	   <div class="searchButton2">
			<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="inquiry" /></button></div></div></li>
			</ul>
		 </div>
			</div>
	 </s:form>
	 	 <div  
	 <s:if test="player==null"> style="display:none" </s:if>
	 >
	<s:form action="password_reset"  method="post"   
		id="reset" theme="simple" cssClass="pageForm required-validate"  onsubmit="return $(this).valid()&&validateCallback(this,initMessage)">
		   <div  class="searchDiv_left">
		   <input type="hidden" name="uid" value="${loginName}"/>
		  <s:text name="account" />：&nbsp;&nbsp; ${loginName}  
		  </div>
		<div style="height:20px"></div> 
		 <div  class="searchDiv_left">
		 <s:text name="new_password" />：<input type="text" name="newPW" minlength="6" maxlength="12" class="textInput valid">
		  </div>
	   <div class="searchButton2">
			<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="reset_password" /></button></div></div></li>
			</ul>
		 </div>
	 </s:form>
	</div>
	</div>
	

	
	