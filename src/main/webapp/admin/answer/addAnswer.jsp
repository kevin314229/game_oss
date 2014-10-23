<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script>
	function closeAnswerDiglog(json){
				$.pdialog.closeCurrent();
				navTab.reload();
	}
	</script>

  	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<b><s:text name="problem_description" /></b>
		<s:property value="%{question}"/>
	</div>
	<div class="pageContent">
		<s:form action="comment_addAnswer" method="post" theme="simple"  
		cssClass="pageForm required-validate" namespace="/admin/base"  onsubmit="return $(this).valid()&&validateCallback(this,closeAnswerDiglog)">
		<br/><s:text name="reply_content" /><br/>
		<s:textarea name="questionReplay" cols="70" rows="7" maxlength="500"></s:textarea>
		<br/>
		<s:hidden name="cid"/>
		<s:hidden name="question"/>
		<div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="reply" /></button></div></div>
		</s:form>
	</div>
