<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<div class="panelBar" >
			<div class="pages">
				<span><s:text name="show"/></span>
				<select name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value,pageNum:1})">
					<option value="20" <s:if test="#request.onePageNum==20">selected</s:if>>20</option>
					<option value="50" <s:if test="#request.onePageNum==50">selected</s:if>>50</option>
					<option value="100" <s:if test="#request.onePageNum==100">selected</s:if>>100</option>
					<option value="200" <s:if test="#request.onePageNum==200">selected</s:if>>200</option>
				</select>
				<span><s:text name="article"/>,<s:text name="total"/><s:property value="allNum"/><s:text name="article"/></span>
			</div>
			<div class="pagination" targetType="dialog" totalCount="${allNum}" numPerPage="${onePageNum}" pageNumShown="10" currentPage="${currPageNO}"></div>
</div>