<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String error ="timeout"+System.currentTimeMillis();
%>
 <script>
        var flag<%=error%> = true;
		function showTimeoutException<%=error%>(){
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
			<p style="margin:0px;height:30px">服务器连接超时，请重新连接服务器！<img class="tip" src="<%=basePath %>/media/default/themes/default/images/layout/right.png" onclick="showTimeoutException<%=error%>()"></p>
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
	
 