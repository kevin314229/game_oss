<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：任务管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：任务管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		新增任务：
		<s:form action="addBaseTask" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:600px;">
		<tr>
		<td>任务ID：</td>
		<td><s:textfield name="taskId" /></td>
		<td>任务名称：</td>
		<td><s:textfield name="name" /></td>
		</tr>
		<tr><td >任务类型：</td>
		<td><s:select list="#{'1':'主线','2':'支线','3':'跑环','4':'循环'}" name="type" listKey="key" listValue="value"/></td>
		<td>是否可以放弃：</td>
		<td>
		<s:select list="#{'0':'不可以','1':'可以'}" name="abdicable" listKey="key" listValue="value"/>
		</td>
		</tr>
		<tr><td >编号：</td>
		<td>
		<s:textfield name="loopId" />
		</td>
		<td></td>
		<td>
		</td>
		</tr>
		<tr><td colspan="4" style="background-color:#7dc4e6;"> 接任务条件：</td>
		</tr>
		<tr><td>等级：</td>
		<td><s:textfield name="revLevel" /></td>
		<td>称号：</td>
		<td><s:textfield name="recName" /></td>
		</tr>
        <tr> <td>前置ID：</td>
        <td><s:textfield name="triggerId" /></td>
         <td>门忠：</td>
       <td><s:textfield name="revLoyal" /></td>
        </tr>        
       <tr> <td>接受限制：</td>
      <td><s:textfield name="revStrict" /></td>
         <td>正邪值：</td>
       <td><s:textfield name="revJustice" /></td>
	  </tr> 
		<tr><td>时间段：</td>
		<td ><s:textfield name="revTime" /></td>
		<td></td>
		<td>
		
		</td>
		</tr>
		<tr>
		<td colspan="3">门派限制(0:无限制，1:华山，2:少林，3:血刀门，4:白驼山。多个用#隔开)</td>
		<td>
		<s:textfield name="school"></s:textfield>
		</td>
		</tr>
		<tr>
		<td>接任务时间限制：</td>
		<td colspan="3"><s:textfield name="recvTime" /></td>
		</tr>
		<tr>
		 <td style="background-color:#7dc4e6;">接任务方式:</td>
		 <td colspan="3"><s:select list="#{'0':'NPC','1':'UI','2':'使用物品'}" name="revType" listKey="key" listValue="value"></s:select>
		 关联ID：<s:textfield name="recvId" />
		 </td>
		</tr>
		<tr><td >接任务消耗：</td>
		<td colspan="3">银两:<s:textfield name="silver"/>物品:<s:textfield name="item" /></td>
		</tr>
		<tr>
			<td>接任务获得物品：</td>
			<td><s:textfield name="receive"/></td>
			<td></td>
			<td></td>
		</tr>
		
		<tr><td colspan="4">任务描述：</td></tr>
		<tr><td style="background-color:#7dc4e6;">任务步骤：</td><td colspan="3"><s:textarea name="step" cols="50" rows="5"/></td></tr>
		<tr><td style="background-color:#7dc4e6;">任务步骤描述：</td><td colspan="3"><s:textarea name="stepDesc" cols="50" rows="5"/></td></tr>
		<tr><td style="background-color:#7dc4e6;">任务介绍：</td><td colspan="3"><s:textarea name="description" cols="50" rows="5"/></td></tr>
		<tr><td style="background-color:#7dc4e6;">任务过程</td>
		<td colspan="3">目标ID：<s:textfield name="objectId"/>数量：<s:textfield name="objectNum" /></td>
		</tr>
		<tr><td style="background-color:#7dc4e6;">交任务方式：</td>
		<td colspan="3"><s:select list="#{'0':'NPC','1':'UI','2':'使用物品'}" name="rewType" listKey="key" listValue="value"></s:select>
		 关联ID：<s:textfield name="deliverId" />
		 </td>
		</tr>
		<tr>
		<td>完成方式：</td><td><s:select list="#{'0':'后台完成','1':'前台完成'}" name="finishType" listKey="key" listValue="value"></s:select></td>
		<td>完成类型：</td><td><s:textfield name="taskType"/></td>
		</tr>
		<tr>
		<td >接任务对白：</td>
		<td colspan="3"><s:textarea name="recvDialog" cols="50" rows="5"/></td>
		</tr>
		<tr>
		<td >交任务对白：</td>
		<td colspan="3"><s:textarea name="deliverDialog" cols="50" rows="5"/></td>
		</tr>
		<tr><td colspan="4" style="background-color:#7dc4e6;">任务奖励：</td></tr>
		<tr><td>金票：</td>
		<td><s:textfield name="rewGoldTicket" /></td>
		<td>银两：</td>
		<td><s:textfield name="rewSilver" /></td>
		</tr>
        <tr> <td>经验：</td>
        <td><s:textfield name="rewExp" /></td>
         <td>门忠：</td>
       <td><s:textfield name="rewLoyal" /></td>
        </tr>        
       <tr> <td>称号：</td>
      <td><s:textfield name="rewName" /></td>
         <td>正邪值：</td>
       <td><s:textfield name="rewJustice" /></td>
	  </tr> 
		<tr><td>物品：</td>
		<td ><s:textfield name="rewItem" /></td>
		<td>功勋：</td>
		<td><s:textfield name="rewRep" /></td>
		</tr>
		<tr><td>回收物品：</td>
		<td ><s:textfield name="recycle"/></td>
		<td>道具奖励：</td><td><s:textfield name="propReward"/></td>
		</tr>
		
		<tr><td colspan="4"><s:submit value="新增" />
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseTask.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
