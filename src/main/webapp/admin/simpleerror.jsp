<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<div class="accountInfo">
							<div class="alertInfo">
								<h2>消息:<s:property value="exception.message"/></h2>
								<a>错误类别:<s:property value="exception.cause"/></a>
							</div>
							<p>堆栈信息:<s:property value="exception.cause.stackTrace"/></p>
	</div>
	<div class="pageContent" >
		
		<table class="list" width="100%" layoutH="90" >
		<thead>
			<tr>
				<td>详细内容</td>
			</tr>
		</thead>
		<tbody>
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
