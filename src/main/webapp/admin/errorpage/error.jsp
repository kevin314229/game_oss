<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String error ="error"+System.currentTimeMillis();
%>
<script src="<%=basePath%>/media/default/js/jquery-1.8.3.js"></script>
 <script type="text/javascript">
        var flag<%=error%> = true;
		function showException<%=error%>(){
			if(flag<%=error%>){
				$("#<%=error%>")[0].style.display="block";
				flag<%=error%>=false;
			}else{
				$("#<%=error%>")[0].style.display="none";
				flag<%=error%> = true;
			}
			
		}
		
 </script>
		<style>
		body{
			background: #FFFFFF;
		}
		#mainNotFound{
			width: 100%;
			text-align: center;
			font-size: 40px;
			vertical-align: middle;
			
		}
	 .tip{
	cursor: pointer;
	}
		
		</style>	
		
	<div>
		<div id="mainNotFound">
			<img src="<%=basePath%>/media/default/images/404.jpg"/>
			<p style="margin:0px;"></p>
			<p style="margin:0px;height:30px">系统繁忙，请稍后再试！<img class="tip" src="<%=basePath %>/media/default/themes/default/images/layout/right.png" onclick="showException<%=error%>()"></p>
		</div>	
	</div>
	<div class="pageContent" style="display:none" id="<%=error%>">
		
		<table class="list" width="100%" layoutH="90" >
		<thead>
			<tr>
				<td>详细内容</td>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td>错误消息:<s:property value="exception.message"/></td>
		</tr>
		<tr>
			<td>错误类别:<s:property value="exception.cause"/></td>
		</tr>
				<s:iterator value="exception.cause.stackTrace">
				<tr>
					<td>
			    			<s:property/>
					</td>
				</tr>
		   		</s:iterator>
   		</tbody>
   		</table>
	</div>
	
 