<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：副本系统管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath%>/media/default/js/jquery-1.5.1.min.js"  type="text/javascript"></script>
    <script type="text/javascript" language="javascript">
    	var j = 2;
    	var j2 = 2;
    	var j3 = 2;
    	var j4 = 2;
    	var flag = true;
    	function addRow(type){
    		var obj;
    		var sHtml;
    		obj = document.getElementById("tb"+type);
    		obj.insertRow(obj.rows.length);
    		obj.rows.item(obj.rows.length-1).insertCell(0);
    		obj.rows.item(obj.rows.length-1).cells.item(0).colSpan="2";
    		if(type==1){
    		sHtml=(j++)+'、前置ID：<input type="text" size=\"10\" id=\"pre'+(j-1)+'\" name=\"pre'+(j-1)+'\">'+'等级：<input type="text" size=\"10\" id=\"lv'+(j-1)+'\" name=\"lv'+(j-1)+'\">';
    		if(obj.rows.length>1){
    			$('#a1').show();
    		}
    		}else if(type==2){
    		sHtml=(j2++)+'、体力：<input type="text" size=\"10\" id=\"st'+(j2-1)+'\" name=\"st'+(j2-1)+'\">'+'银两：<input type="text" size=\"10\" id=\"sv'+(j2-1)+'\" name=\"sv'+(j2-1)+'\">';
    		if(obj.rows.length>1){
    			$('#a2').show();
    		}
    		}else if(type==3){
    			sHtml=(j3++)+'、事件ID：<input type="text" size=\"10\" id=\"ev'+(j3-1)+'\" name=\"ev'+(j3-1)+'\">'+'事件目标ID：<input type="text" size=\"10\" id=\"tg'+(j3-1)+'\" name=\"tg'+(j3-1)+'\">';
    		if(obj.rows.length>1){
    			$('#a3').show();
    		}
    		}else if(type==4){
    			sHtml=(j4++)+'、怪物ID：<input type="text" size=\"10\" id=\"mt'+(j4-1)+'\" name=\"mt'+(j4-1)+'\">'+'数量：<input type="text" size=\"10\" id=\"num'+(j4-1)+'\" name=\"num'+(j4-1)+'\">';
    		if(obj.rows.length>1){
    			$('#a4').show();
    		}
    		}
    		obj.rows.item(obj.rows.length-1).cells.item(0).innerHTML=sHtml;
    	}
    	function deleteRow(type){
    	var obj;
    	 obj=document.getElementById("tb"+type);
    	 obj.deleteRow(obj.rows.length-1);
    	if(type==1){
    	  j--;
    	  if(obj.rows.length<=1){
    	   $('#a1').hide();
    	  }
    	  }else if(type==2){
    	   j2--;
    	   if(obj.rows.length<=1){
    	   $('#a2').hide();
    	  }
    	  }else if(type==3){
    	  	j3--;
    	  	if(obj.rows.length<=1){
    	   $('#a3').hide();
    	  }
    	  }else if(type==4){
    	  	j4--;
    	  	if(obj.rows.length<=1){
    	   $('#a4').hide();
    	  }
    	  }
    	}
    	
    	function addJson(){
    		var equipJson;
    		var tb = document.getElementById("row");
    		var temp = '[';
  
    		for(var k=1;k<j;k++){
    		 if(k!=j-1){
    			temp=temp+'{preId:"'+document.getElementById("pre"+k).value
    			+'",level:'+document.getElementById("lv"+k).value+'},';
    			}else{
    			 temp=temp+'{preId:"'+document.getElementById("pre"+k).value
    			+'",level:'+document.getElementById("lv"+k).value+'}]';
    			}
    		}
    		addCopyForm.conditionJson.value=temp;
 
    		temp='[';
    		
    		for(var k=1;k<j2;k++){
    		 if(k!=j2-1){
    			temp=temp+'{body:"'+document.getElementById("st"+k).value
    			+'",silver:'+document.getElementById("sv"+k).value+'},';
    			}else{
    			 temp=temp+'{body:"'+document.getElementById("st"+k).value
    			+'",silver:'+document.getElementById("sv"+k).value+'}]';
    			}
    		}
    		addCopyForm.consumeJson.value=temp;
    		
    		temp='[';
    		
    		for(var k=1;k<j3;k++){
    		 if(k!=j3-1){
    			temp=temp+'{eventId:"'+document.getElementById("ev"+k).value
    			+'",targetId:'+document.getElementById("tg"+k).value+'},';
    			}else{
    			 temp=temp+'{eventId:"'+document.getElementById("ev"+k).value
    			+'",targetId:'+document.getElementById("tg"+k).value+'}]';
    			}
    		}
    		addCopyForm.eventJson.value=temp;
    		
    		temp='[';
    		
    		for(var k=1;k<j4;k++){
    		 if(k!=j4-1){
    			temp=temp+'{monsterId:"'+document.getElementById("mt"+k).value
    			+'",number:'+document.getElementById("num"+k).value+'},';
    			}else{
    			 temp=temp+'{monsterId:"'+document.getElementById("mt"+k).value
    			+'",number:'+document.getElementById("num"+k).value+'}]';
    			}
    		}
    		addCopyForm.finishJson.value=temp;
    		addCopyForm.submit();
    	}
    	$(document).ready(function(){
    		$('#a1').hide();
    		$('#a2').hide();
    		$('#a3').hide();
    		$('#a4').hide();
    	});
    </script>
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：副本系统管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		新增副本：
		<s:form action="addGameCopy" namespace="/admin/skill" method="post" theme="simple" id="addCopyForm">
		<s:hidden name="conditionJson" id="conditionJson"></s:hidden>
		<s:hidden name="consumeJson" id="consumeJson"></s:hidden>
		<s:hidden name="eventJson" id="eventJson"></s:hidden>
		<s:hidden name="finishJson" id="finishJson"></s:hidden>
		<table id="row" style="width:850px;">
		<tr>
			<td style="background-color:#7dc4e6;" width="100">副本ID</td><td><s:textfield name="copyId" /></td>
		</tr>
		<tr>
			<td style="background-color:#7dc4e6;" >副本地图ID</td><td><s:textfield name="mapId" /></td>
		</tr>
		<tr>
			<td style="background-color:#7dc4e6;" >最大次数</td>
			<td><s:textfield name="maxNumber" /></td>
		</tr>
		<tr>
		<td style="background-color:#7dc4e6;" width="100">开启条件</td><td>
			<table id="tb1">
			<tr>
			<td>
			1、前置ID：<s:textfield name="pre1" size="10" id="pre1"/>等级：<s:textfield name="lv1" size="10" id="lv1"/><a href="javascript:addRow(1);">增加</a>
		<a href="javascript:deleteRow(1);" id="a1">删除</a>
			</td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
		<td style="background-color:#7dc4e6;" width="100">消耗</td><td>
		<table id="tb2">
			<tr>
			<td>
			1、体力：
		<s:textfield name="st1" size="10" id="st1"/>银两：<s:textfield name="sv1" size="10" id="sv1"/><a href="javascript:addRow(2);">增加</a>
		<a href="javascript:deleteRow(2);" id="a2">删除</a>
			</td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
		<td style="background-color:#7dc4e6;" width="100">事件</td><td>
		<table id="tb3">
			<tr>
			<td>
			1、事件ID：
		<s:textfield name="ev1" size="10" id="ev1"/>事件目标ID：<s:textfield name="tg1" size="10" id="tg1"/><a href="javascript:addRow(3);">增加</a>
		<a href="javascript:deleteRow(3);" id="a3">删除</a>
			</td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
		<td style="background-color:#7dc4e6;" width="100">完成条件</td><td>
		<table id="tb4">
			<tr>
			<td>
			1、怪物ID：
		<s:textfield name="mt1" size="10" id="mt1"/>数量：<s:textfield name="num1" size="10" id="num1"/><a href="javascript:addRow(4);">增加</a>
		<a href="javascript:deleteRow(4);" id="a4">删除</a>
			</td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
			<td style="background-color:#7dc4e6;" width="100">后置剧情ID</td><td><s:textfield name="suffixPlotId" /></td>
		</tr>
		<tr>
			<td style="background-color:#7dc4e6;" width="100">前置剧情ID</td><td><s:textfield name="prefixPlotId" /></td>
		</tr>
		<tr><td colspan="4"><!--<s:submit value="新增" />-->
		<input type="button" value="新增" onClick="addJson();">
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryGameCopy.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
