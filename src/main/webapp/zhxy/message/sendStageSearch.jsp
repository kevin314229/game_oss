<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script type="text/javascript" language="javascript">
	function returnValue(value,name,mapHeap){
		var index ='${index}';
		$("input[name='btn_itemName"+index+"']").val(name);
		$("input[name='btn_itemId"+index+"']")[0].value=value;
		$.pdialog.closeCurrent();
	}
	</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<form rel="pagerForm" method="post" rel="pagerForm" action="<%=basePath%>/zhxy/message/modifyActivity_searchPropertyBaseList.action" onsubmit="return dialogSearch(this);">
	<div class="searchBar">
	<div class="searchDiv_left">
		<ul class="searchContent">
			<li style="width:200px">
				<s:hidden name="optType"/>
				<s:hidden name="index"/>
				<s:text name="name" />：<s:textfield name="nickName"/>
			</li>	  
		</ul>
		</div>
		<div class="searchButton2">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="inquiry" /></button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="list" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="value"><s:text name="goods" />ID</th>
				<th orderfield="name"><s:text name="name" /></th>
				<td>品质</td>
				<th orderfield="maxHeap"><s:text name="maximum_stack" /></th>
				<th width="80"><s:text name="find_back" /></th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="optList" var="oneRow"  status="offset">
			<tr>
				<td><s:property value="#oneRow.value"/></td>
				<td><s:property value="#oneRow.name"/></td>
				<td> 
				<s:if test="#oneRow.quality==1">白色</s:if>
				<s:elseif test="#oneRow.quality==2">绿色</s:elseif>
				<s:elseif test="#oneRow.quality==3">蓝色</s:elseif>
				<s:elseif test="#oneRow.quality==4">紫色</s:elseif>
				<s:elseif test="#oneRow.quality==5">金色</s:elseif>
				<s:else>
				-
				</s:else>
				
				</td>
				<td><s:property value="#oneRow.maxHeap"/></td>
				<td><a class="btnSelect" href="javascript:returnValue(
				'<s:property value="#oneRow.value"/>', 
						 '<s:property value="#oneRow.name"/>', 
						 '<s:property value="#oneRow.maxHeap"/>'
				)" 
				title="<s:text name="find_back" />"><s:text name="select" /></a></td>
			</tr>
		</s:iterator> 
		</tbody>
	</table>
	<s:include value="/admin/template/dialogPaging.jsp"/>
</div>
