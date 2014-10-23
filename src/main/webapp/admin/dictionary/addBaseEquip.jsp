<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：装备管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：装备管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		新增装备：
		<s:form action="addBaseEquip" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:600px;">
		<tr>
		<td>装备ID：</td>
		<td><s:textfield name="baseEquipId" /></td>
		<td>装备名称：</td>
		<td><s:textfield name="name" /></td>
		</tr>
		<tr><td>模型路径：</td>
		<td><s:textfield name="moduleName" /></td>
		<td>装备类型：</td>
		<td><s:textfield name="equipType" /></td>
		</tr>
		<tr><td>是否可交易：</td>
		<td>
		<s:select list="#{'0':'可以','1':'不可以'}" name="tradeFlag" listKey="key" listValue="value"/>
		</td>
		<td>是否可出售：</td>
		<td><s:select list="#{'0':'可以','1':'不可以'}" name="sellFlag" listKey="key" listValue="value"/>
		</td>
		</tr>
		<tr><td> 是否可丢弃：</td>
		<td>
		<s:select list="#{'0':'可以','1':'不可以'}" name="discardFlag" listKey="key" listValue="value"/>
		</td>
		<td>装备部位：</td>
		<td><s:textfield name="equipPos" /></td>
		</tr>
		<tr><td>男路径：</td>
		<td><s:textfield name="malePath" /></td>
		<td>女路径：</td>
		<td><s:textfield name="femalePath" /></td>
		</tr>
        <tr> <td>图片路径：</td>
        <td><s:textfield name="imgPath" /></td>
         <td>装备品质：</td>
       <td><s:textfield name="quality" /></td>
        </tr>        
       <tr> <td>门派限制：</td>
      <td><s:textfield name="schoolFlag" /></td>
         <td>性别限制：</td>
       <td><s:textfield name="sexFlag" /></td>
	  </tr> 
		<tr><td>等级限制：</td>
		<td ><s:textfield name="levelFlag" /></td>
		<td>臂力限制：</td>
		<td><s:textfield name="muscle" /></td>
		</tr>
		<tr><td>悟性限制：</td>
		<td ><s:textfield name="clever" /></td>
		<td>灵敏限制：</td>
		<td><s:textfield name="nimble" /></td>
		</tr>
		<tr><td>根骨限制：</td>
		<td ><s:textfield name="basis" /></td>
		<td>福缘限制：</td>
		<td><s:textfield name="fortune" /></td>
		</tr>
		<tr><td>主属性标识：</td>
		<td ><s:textfield name="mainProp" /></td>
		<td>主属性值：</td>
		<td><s:textfield name="mainPropValue" /></td>
		</tr>
		<tr>
		 <td>商城价格:</td>
		 <td><s:textfield name="gold"/></td>
		 <td>类别</td>
		 <td>
		 <s:select list="#{'0':'非商城装备','1':'元宝购买','2':'金票购买'}" name="shopFlag" listKey="key" listValue="value"/>
		 </td>
		</tr>
		<tr><td>装备描述：</td>
		<td><s:textfield name="description" /></td>
		<td></td>
		<td></td>
		</tr>
		<tr><td colspan="4"><s:submit value="新增" />
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseEquip.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
