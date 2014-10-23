<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script>
function refreshList(json){
	  if (json.statusCode == DWZ.statusCode.ok){  
		  alertMsg.correct(json.message);
	  }else{
		  alertMsg.error(json.message); 
	  }
}
</script>
	<s:include value="/admin/template/scriptMessage.jsp"/>
 <div class="pageHeader">
	<s:form action="dataModify_recover"  method="post"  
		id="queryPayForm" theme="simple" cssClass="pageForm required-validate"  onsubmit="return iframeCallback(this,refreshList)">
	   <div class="searchButton2">
			<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="数据修复" /></button></div></div></li>
			</ul>
		 </div>
	 </s:form>
	</div>
	
