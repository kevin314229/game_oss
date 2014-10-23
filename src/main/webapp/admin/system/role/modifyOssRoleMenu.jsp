<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<s:include value="/admin/template/scriptMessage.jsp"/>
<s:include value="/admin/template/checkbox.jsp"/>
<div class="pageContent">

	<div class="panelBar">
			&nbsp; <s:text name="current_jobs" /> ： <s:property value="roleName"/>
	</div>
	<s:form action="ossRole_updateOssRoleMenu" namespace="/admin/base" method="post" 
		cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<table width="100%" class="list" layoutH="60"  >
			<thead>
				<tr>
					<th><s:text name="level_1_menu" /></th>
		    	    <th><s:text name="the_secondary_menu_list" /></th>
		    	    <th style="width:35px"><s:text name="select" /></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="menuAllListTree" var="oneRow"  status="offset">
					<s:if test="childOssMenu.size()>0">
			    		<tr>
				    	    <td width="80">
				    	    	<s:property value="#oneRow.name"/>
				    	    </td>
				    	    <td id="check<s:property value='#offset.index'/>">
				    	    <s:iterator value="childOssMenu" var="oneRow2"  status="offset">
				    	    	<input  name="selectMenuArray" type="checkbox"  value="<s:property value='#oneRow2.ossMenuID'/>" <s:if test="#oneRow2.ossMenuID in selectMenuArray">checked</s:if> />
				    	    	<s:property value="#oneRow2.name"/> 
				    	    </s:iterator>
				    	    	<%-- <s:checkboxlist 
						           name="selectMenuArray"          
						           list="#oneRow.childOssMenu"  
						           listKey="ossMenuID"
						           listValue="name"
						           value="selectMenuArray"
						           />  --%>
						           
				    	    </td>
				    	     <td>
				    	     	<input name="" title="<s:text name="select" />/<s:text name="incomplete_election" />"  type="checkbox" onchange="isCheckedAll(this,'selectMenuArray','check<s:property value='#offset.index'/>')" />

							</td>
				    	</tr>
			    	</s:if>
		    	</s:iterator>
			</tbody>
		</table>
		<div class="formBar">
				<s:hidden name="ossRoleID"/>
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" ><s:text name="confirm_the_change" /></button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="button" class="close"><s:text name="close" /></button></div></div></li>
				</ul>
		</div> 
	</s:form>
</div>
