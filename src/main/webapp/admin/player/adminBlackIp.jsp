<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title><s:text name="message_and_notify" />：<s:text name="system_message_management" /></title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" language="javascript">
		function deleteMsg(msgID) {
			if (confirm("<s:text name="are_you_sure_you_want_to_delete_the_system_message" />？")){
				deleteSysMsgForm.systemNoticeID.value = msgID;
				deleteSysMsgForm.submit();
			}
		}
	</script>
  </head>
  
  <body>

  	<div id="divLeft">
		<b><s:text name="message_and_notify" />：<s:text name="system_message_management" /></b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		<s:text name="add_a_system_message" /><br/>
		<s:form action="adminSysMsg" method="post" theme="simple" id="sendSysMsgForm">
		<s:textarea name="msgContent" cols="50" rows="5"></s:textarea>
		<br/>
		<s:submit value=" %{getText('new')}"></s:submit>
		</s:form>
		<s:form action="deleteSysMsg" method="post" theme="simple" id="deleteSysMsgForm">
		<s:hidden name="systemNoticeID" id="systemNoticeID"></s:hidden>
		</s:form>
	</div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th><s:text name="system_message" />ID</th>
	    	    <th><s:text name="system_message_content" /></th>
	    	    <th><s:text name="operation" /></th>
	    	</tr>
	    	<s:iterator value="systemNoticeList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
						 <s:property value="#oneRow.systemNoticeID"/>
		    	    </td>
		    	    <td>
						 <s:property value="#oneRow.content"/>
		    	    </td>
		    	    <td>
						 <A href='javascript:deleteMsg("<s:property value="#oneRow.systemNoticeID"/>")'><s:text name="delete" /></A>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
  </body>
</html>
