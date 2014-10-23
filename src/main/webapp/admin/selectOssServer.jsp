<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title><s:text name="list_view_game_area" /></title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>
	
	<script src="<%=basePath%>/media/default/js/jquery.pager.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/public.js"  type="text/javascript"></script>
	<script type="text/javascript" language="javascript">
		function insertOssServer(id) {
			if (confirm("<s:text name="are_you_sure_you_want_to_enter_the_game_zone" />？")){
				self.location ="<%=basePath %>/admin/selectOssServer.action?id="+id;
			}
		}
	</script>
  </head>
  
  <body>
  <div>
		<font size="15" color="red"><s:text name="please_choose_the_game_to_enter_the_service_area" /> </font>
		<span class="color-red"><s:property value="actionMsg" escape="false"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	    <table id="row" style="width:800px;height:25px;margin:0 auto;">
	    	
 	    	<tr>
	    		<th>ID<s:text name="no." /></th>
	    		<th><s:text name="the_server" />ID</th>
	    		<th><s:text name="subordinate_to_the_operator" /></th>
	    	    <th><s:text name="the_game_zone_name" /></th>
	    	    <th><s:text name="operation" /></th>
	    	</tr>
	    	<s:iterator value="ossServers" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
		    	    	<s:property value="#offset.index+1"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.id"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.serverProvider"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.name"/>
		    	    </td>
		    	    <td> 
		    	    	<a href="javascript:insertOssServer('${oneRow.id}')" style="color:green"><s:text name="enter_the" /></a>
		    	    </td>
		    	</tr>
	    	</s:iterator> 
	    	
	    </table>	
  </body>
</html>
