<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：玩家技能管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：玩家技能管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		新增招式：
		<s:form action="addBaseAction" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:600px;">
		<tr>
		<td>招式ID：</td>
		<td><s:textfield name="baseActionId" /></td>
		<td>招式名称：</td>
		<td><s:textfield name="name" /></td>
		</tr>
		<tr><td>招式来源ID：</td>
		<td><s:textfield name="skillId" /></td>
		<td>技能等级：</td>
		<td><s:textfield name="skillLevel" /></td>
		</tr>
		<tr><td>目标数量：</td>
		<td><s:textfield name="goalNum" /></td>
		<td>基础伤害：</td>
		<td><s:textfield name="baseHurt" /></td>
		</tr>
		<tr><td> 内力消耗：</td>
		<td><s:textfield name="forceConsume" /></td>
		<td>内力加成消耗：</td>
		<td><s:textfield name="forceAddConsume" /></td>
		</tr>
		<tr><td>活力消耗：</td>
		<td><s:textfield name="vigourConsume" /></td>
		<td>释放频率：</td>
		<td><s:textfield name="freeTime" /></td>
		</tr>
        <tr> <td>招式类型：</td>
        <td><select name="actionType">
                 <option value="1">被动</option>
                 <option value="2">主动</option>
                </select></td>
         <td>释放方式：</td>
       <td><select name="freeType">
                 <option value="0">无效</option>
                 <option value="1">自动</option>
                 <option value="2">手动</option>
                </select>
                </td>
        </tr>        
       <tr> <td>招式目标：</td>
      <td> <select name="goalType">
                 <option value="0">自己</option>
                 <option value="1">敌方</option>
                </select></td>
         <td>是否默认招式：</td>
       <td><select name="isDefault">
                 <option value="0">否</option>
                 <option value="1">是</option>
                </select>
                </td>
	  </tr> 
		<tr><td colspan="1">招式描述：</td>
		<td colspan="3"><s:textarea name="description" cols="50" rows="5"></s:textarea></td>
		</tr>
		<tr><td colspan="4"><s:submit value="新增"></s:submit>
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseAction.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
