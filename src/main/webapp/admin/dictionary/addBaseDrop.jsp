<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：掉落系统管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath%>/media/default/js/jquery-1.5.1.min.js"  type="text/javascript"></script>
    <script type="text/javascript" language="javascript">
    	var i = 4;
    	var j = 2;
    	var i2 = 6;
    	var j2 = 2;
    	var flag = true;
    	function addRow(type){
    		var obj;
    		var sHtml;
    		if(type==1){
    		obj = document.getElementById("tb1");
    		obj.insertRow(obj.rows.length);
    		obj.rows.item(obj.rows.length-1).insertCell(0);
    		obj.rows.item(obj.rows.length-1).cells.item(0).colSpan="2";
    		sHtml=(j++)+'、装备ID：<input type="text" id=\"eq'+(j-1)+'\" name=\"eq'+(j-1)+'\">'+'几率：<input type="text" id=\"rt'+(j-1)+'\" name=\"rt'+(j-1)+'\">';
    		obj.rows.item(obj.rows.length-1).cells.item(0).innerHTML=sHtml;
    		if(obj.rows.length>1){
    			$('#a1').show();
    		}
    		}else if(type==2){
    		obj = document.getElementById("tb2");
    		obj.insertRow(obj.rows.length);
    		obj.rows.item(obj.rows.length-1).insertCell(0);
    		obj.rows.item(obj.rows.length-1).cells.item(0).colSpan="2";
    		sHtml=(j2++)+'、道具ID：<input type="text" id=\"pp'+(j2-1)+'\" name=\"pp'+(j2-1)+'\" size=\"10\">'+'数量：<input type="text" id=\"num'+(j2-1)+'\" name=\"num'+(j2-1)+'\" size=\"10\">'+'几率：<input type="text" id=\"prt'+(j2-1)+'\" name=\"prt'+(j2-1)+'\" size=\"10\">';
    		obj.rows.item(obj.rows.length-1).cells.item(0).innerHTML=sHtml;
    		if(obj.rows.length>1){
    			$('#a2').show();
    		}
    		}
    	}
    	function deleteRow(type){
    	var obj;
    	if(type==1){
    	  obj=document.getElementById("tb1");
    	  obj.deleteRow(obj.rows.length-1);
    	  j--;
    	  if(obj.rows.length<=1){
    	   $('#a1').hide();
    	  }
    	  }else if(type==2){
    	   obj=document.getElementById("tb2");
    	  obj.deleteRow(obj.rows.length-1);
    	   j2--;
    	   if(obj.rows.length<=1){
    	   $('#a2').hide();
    	  }
    	  }
    	}
    	
    	function addJson(){
    		var equipJson;
    		var tb = document.getElementById("row");
    		var temp = '[';
    		var list1 = document.getElementById("l1").value;
    		var list2 = document.getElementById("l2").value;
    		if(list1==0){
    		for(var k=1;k<j;k++){
    		 if(k!=j-1){
    			temp=temp+'{equipId:"'+document.getElementById("eq"+k).value
    			+'",rate:'+document.getElementById("rt"+k).value+'},';
    			}else{
    			 temp=temp+'{equipId:"'+document.getElementById("eq"+k).value
    			+'",rate:'+document.getElementById("rt"+k).value+'}]';
    			}
    		}
    		addDropForm.equipJson.value=temp;
    		}
    		temp='[';
    		if(list2==0){
    		for(var k=1;k<j2;k++){
    			if(k!=j2-1){
    			temp=temp+'{propId:"'+document.getElementById("pp"+k).value
    			+'",number:'+document.getElementById("num"+k).value+',rate:'+document.getElementById("prt"+k).value+'},';
    			}else{
    			 temp=temp+'{propId:"'+document.getElementById("pp"+k).value
    			+'",number:'+document.getElementById("num"+k).value+',rate:'+document.getElementById("prt"+k).value+'}]';
    			}
    		}
    		addDropForm.propJson.value=temp;
    		}
    		addDropForm.submit();
    	}
    	$(document).ready(function(){
    		$('#a1').hide();
    		$('#a2').hide();
    	});
    </script>
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：掉落系统管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		新增掉落：
		<s:form action="addBaseDrop" namespace="/admin/skill" method="post" theme="simple" id="addDropForm">
		<s:hidden name="equipJson" id="equipJson"></s:hidden>
		<s:hidden name="propJson" id="propJson"></s:hidden>
		<table id="row" style="width:850px;">
		<tr>
			<td style="background-color:#7dc4e6;" width="100">掉落ID</td><td><s:textfield name="dropId" /></td>
		</tr>
		<tr>
			<td style="background-color:#7dc4e6;" >掉落银两</td><td><s:textfield name="silver" /></td>
		</tr>
		<tr>
			<td style="background-color:#7dc4e6;" >掉落金票</td>
			<td><s:textfield name="goldTicket" />几率：<s:textfield name="goldTicketRate" /></td>
		</tr>
		<tr>
			<td style="background-color:#7dc4e6;">掉落装备</td>
			<td><s:select list="#{'0':'掉落','1':'不掉'}" name="equipFlag" listKey="key" listValue="value" id="l1"></s:select>
			数量：<s:textfield name="minEquip" size="10"/>-<s:textfield name="maxEquip" size="10"/>
		</td>
		</tr>
		<tr>
		<td style="background-color:#7dc4e6;" width="100">掉落详情</td><td>
			<table id="tb1">
			<tr>
			<td>
			1、装备ID：<s:textfield name="eq1" id="eq1"/>几率：<s:textfield name="rt1" id="rt1"/><a href="javascript:addRow(1);">增加</a>
		<a href="javascript:deleteRow(1);" id="a1">删除</a>
			</td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
		<td  style="background-color:#7dc4e6;">掉落道具</td><td>
			<s:select list="#{'0':'掉','1':'不掉'}" name="propFlag" listKey="key" listValue="value" id="l2"></s:select>
			数量：<s:textfield name="minProp" size="10"/>-<s:textfield name="maxProp" size="10"/>
		</td>
		</tr>
		<tr>
		<td style="background-color:#7dc4e6;" width="100">掉落详情</td><td>
		<table id="tb2">
			<tr>
			<td>
			1、道具ID：
		<s:textfield name="pp1" size="10" id="pp1"/>数量：<s:textfield name="num1" size="10" id="num1"/>几率：<s:textfield name="prt1" size="10" id="prt1"/><a href="javascript:addRow(2);">增加</a>
		<a href="javascript:deleteRow(2);" id="a2">删除</a>
			</td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
		<td  style="background-color:#7dc4e6;">掉落描述</td><td><s:textarea name="description" cols="50" rows="5"/></td>
		</tr>
		<tr><td colspan="4"><!--<s:submit value="新增" />-->
		<input type="button" value="新增" onClick="addJson();">
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseDrop.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
