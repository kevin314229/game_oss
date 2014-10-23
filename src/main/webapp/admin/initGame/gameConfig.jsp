<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

	<script type="text/javascript" language="javascript">
		function send(){
			if(confirm("<s:text name="are_you_sure_the_corps'_data_adjustment" />？")){
				return true;
			}
			return false;
		}
		
		$(function(){
			var data = '<s:property value="detailContent"/>';
			
		});
	</script>
 <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
  	<div>
		<s:form action="initGame_initArmy" method="post" theme="simple" cssClass="pageForm required-validate" namespace="/admin/initGame" onsubmit="return send()&&navTabSearch(this)">
		    <div class="subBar">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="bianconeri_interface_data_adjustment"/></button></div></div></li>
					<li>
						<s:text name="the_game_developers" /> <s:text name="do_not_operate" /> 
						<label style="color: red;"> <s:property value="%{result}"/> </label>
					</li>
				</ul>
		 </div>
		</s:form>
	</div>
</div>

<div class="pageContent">

	<table class="list" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="value">物品ID</th>
				<th orderfield="name">名称</th>
				<th orderfield="maxHeap">最大堆叠</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="optList" var="oneRow"  status="offset">
			<tr>
				<td><s:property value="#oneRow.value"/></td>
				<td><s:property value="#oneRow.name"/></td>
				<td><s:property value="#oneRow.maxHeap"/></td>
				<td><a class="btnSelect" href="javascript:$.bringBack(
					{
						value:'<s:property value="#oneRow.value"/>', 
						name:'<s:property value="#oneRow.name"/>', 
						maxHeap:'<s:property value="#oneRow.maxHeap"/>'
					}
				)" 
				title="查找带回">选择</a></td>
			</tr>
		</s:iterator> 
		</tbody>
	</table>

	<s:include value="/admin/template/paging.jsp"/>
</div>