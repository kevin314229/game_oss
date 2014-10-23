<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>设置用户服务器映射</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>
	
	<script src="<%=basePath%>/media/default/js/jquery.pager.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/public.js"  type="text/javascript"></script>
  </head>
  
  <body>

  	<div id="divLeft">
		<b>设置用户服务器映射</b>
		<span class="color-red"></span>
		<span class="color-gr">当前被操作操作的管理员:<s:property value="username"/> </span>
	</div>
	<div id="divLeft">
	
	</div>


	<div id="divList">
		<s:form action="ossUser_updateOssUserserver" namespace="/admin/base" method="post" theme="simple">
	      <table id="row">
	    	<tr>
	    	    <th>服务器列表</th>
	    	</tr>
	    	<tr>
		    	    <td>
		    	    	<s:checkboxlist 
				           name="selectServerArray"          
				           list="ossServerList"  
				           listKey="ossServerID"
				           listValue="name"
				           value="selectServerArray"
				           /> 
		    	    </td>
		    </tr>
	    	
	    	
	    </table>
	    <s:hidden name="username"/>
	    <s:submit value="确认修改"></s:submit>
	    <input type="button" value="返回" onclick="self.location='<%=basePath%>/admin/base/ossUser_browseOssUser.action'"/>
	    </s:form>
	 </div>
   <div id="bottomPager"></div>
<s:if test='#request.actionMsg !=null && #request.actionMsg !="" '>
	<script language="javascript">
		alert("<s:property value="actionMsg" escape="false"/>");
	</script>
</s:if>	
  </body>
</html>
