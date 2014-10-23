<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：怪物管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：怪物管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		修改怪物：
		<s:form action="updateBaseMonster" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:600px;">
		<tr>
		<td>怪物ID：</td>
		<td><s:textfield name="monsterId" /></td>
		<td>怪物名称：</td>
		<td><s:textfield name="name" /></td>
		</tr>
		<tr><td>怪物称号：</td>
		<td><s:textfield name="title" /></td>
		<td>模型路径：</td>
		<td><s:textfield name="module" /></td>
		</tr>
		<tr><td>武学境界：</td>
		<td><s:textfield name="level" /></td>
		<td>怪物类型：</td>
		<td>
		<s:select list="#{'1':'人形','2':'猛兽','3':'机械'}" name="monsterType" listKey="key" listValue="value"/>
		</td>
		</tr>
		<tr><td> 技能ID：</td>
		<td><s:textfield name="skillId" /></td>
		<td>品阶：</td>
		<td><s:textfield name="monsterRank" /></td>
		</tr>
		<tr><td>攻击：</td>
		<td><s:textfield name="acttack" /></td>
		<td>防御：</td>
		<td><s:textfield name="defend" /></td>
		</tr>
        <tr> <td>暴击率：</td>
        <td><s:textfield name="strikeRate" /></td>
         <td>闪避率：</td>
       <td><s:textfield name="dodgeRate" /></td>
        </tr>        
       <tr> <td>格挡率：</td>
      <td><s:textfield name="blockRate" /></td>
         <td>生命血量：</td>
       <td><s:textfield name="blood" /></td>
	  </tr> 
		<tr><td>怪物内力：</td>
		<td ><s:textfield name="innerForce" /></td>
		<td>AI：</td>
		<td><s:textfield name="ai" /></td>
		</tr>
		<tr><td>掉落ID：</td>
		<td><s:textfield name="dropId" /></td>
		<td>经验奖励：</td>
		<td><s:textfield name="experience" /></td>
		</tr>
		<tr><td>潜能奖励：</td>
		<td><s:textfield name="potential" /></td>
		<td></td>
		<td></td>
		</tr>
		<tr><td colspan="4"><s:submit value="确认修改"></s:submit>
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseMonster.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
