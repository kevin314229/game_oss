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
		<s:form action="addBaseSkill" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:500px;">
		<tr>
		<td>技能ID：</td>
		<td><s:textfield name="skillId" /></td>
		</tr>
		<tr>
		<td>技能名称：</td>
		<td><s:textfield name="name" /></td>
		</tr>
		<tr><td>释放速度：</td>
		<td><s:textfield name="speed" /></td>
		</tr>
		<tr>
		<td>成长值：</td>
		<td><s:textfield name="growup" /></td>
		</tr>
		<tr><td>技能类型：</td><td><select name="skillType">
                 <option value="1">基本武学</option>
                 <option value="2">门派武学</option>
                 <option value="3">江湖绝学</option>
                 <option value="4">江湖武学</option>
                 <option value="11">生活技能</option>
                </select></td>
                </tr>
         <tr>
		<td>武学类型：</td>
		<td><select name="martialType">
                 <option value="0">无效</option>
                 <option value="1">剑法</option>
                 <option value="2">刀法</option>
                 <option value="3">棍法</option>
                 <option value="4">杖法</option>
                 <option value="5">拳脚</option>
                 <option value="101">内功</option>
                 <option value="102">轻功</option>
                </select></td>
		</tr>
		<tr><td>  所属门派：</td>
		<td><select name="school">
                 <option value="0">无效</option>
                 <option value="1">华山派</option>
                 <option value="2">少林派</option>
                 <option value="3">血刀门</option>
                 <option value="4">白驼山</option>
                </select></td>
                </tr>
		<tr><td colspan="1">技能描述：</td>
		<td colspan="1"><s:textarea name="description" cols="50" rows="5"></s:textarea></td>
		</tr>
		<tr><td colspan="2"><s:submit value="新增"></s:submit>
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseSkill.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
