<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>GM<s:text name="modify_the_information" /></title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>

  	<div id="divLeft">
		<b><s:text name="modify_the_information_specified_players" /></b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		<s:text name="modify_the_information_specified_players" />：
		<s:form action="updatePlayer_updatePlayerData" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:600px;">
		<tr>
		<td><s:text name="the_player_name" />：</td>
		<td><s:textfield name="name" /></td>
		</tr>
		<tr>
		<td><s:text name="increase_the_number_of_silver" />：</td>
		<td><s:textfield name="silver" /></td>
		</tr>
		<tr><td><s:text name="add_gold_votes" />：</td>
		<td><s:textfield name="goldTicket" /></td>
		</tr>
		<tr>
		<td><s:text name="increase_the_wing_number" />：</td>
		<td><s:textfield name="gold" /></td>
		</tr>
		<tr>
		<td><s:text name="specify_the_player_rating" />：</td>
		<td><s:textfield name="level"></s:textfield>
		</td>
		</tr>
		<tr><td colspan="4"><s:submit value="<s:text name="more" /> <s:text name="change" />" />
		<input name="Submit2" type="reset" value="<s:text name="heavy" /> <s:text name="buy" />" >
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
