<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="operationLog_index" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div class="searchDiv_left">
					<s:text name="statistical_time" />：
					<s:textfield id="beginTime" name="beginTime" maxlength="10" size="12" cssClass="required date" format="yyyy-MM-dd">
						 <s:param name="value" ><s:date name="beginTime" format="yyyy-MM-dd" /></s:param> 
					</s:textfield>

					<s:text name="to" />
					<s:textfield id="endTime" name="endTime" maxlength="10" size="12" cssClass="required date">
						<s:param name="value" ><s:date name="endTime" format="yyyy-MM-dd" /></s:param>
					</s:textfield>
	
					<s:text name="consumer_type" /> 
	    	    	<s:select name = "target" headerKey="1" list="#{
	    	    	'addGold':getText('increase_the_magic_crystal'),
	    	    	'addBindGold':getText('increase_vouchers'),
	    	    	'addSilver':getText('increase_in_gold'),
	    	    	'useGold':getText('consumer_magic_crystal'),
	    	    	'useBindGold':getText('consumer_vouchers'),
	    	    	'useSilver':getText('gold_consumption')}" />
			
				</div>

		 <div class="searchButton2">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
			</ul>
		 </div>
		 
		</div>
	</s:form>
</div>
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="90">
	    	<thead>
	    	<tr>
	    	    <th><s:text name="consumption_type" /></th>
	    	    <th><s:text name="abbreviation" /></th>
	    	    <th><s:text name="the_number_of_players_using" /></th>
	    	    <th><s:text name="the_total_consumption" /></th>
	    	    <th><s:text name="percentage_of_total" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossOperationLogAssayList" var="ossOperationLogAssay" status="offset">
	    		<tr>
		    	  	<td>
		    	  	<s:if test="@com.jcwx.game.common.constants.OperationLogConstant@maptype[#ossOperationLogAssay.operation]==null" >
		    	  	<dict:itemvalue gameId="${oneRow.gameId }" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
		    	  	</s:if>
		    	  	<s:property value="@com.jcwx.game.common.constants.OperationLogConstant@maptype[#ossOperationLogAssay.operation]"/>
		    	    </td>
		    	    <td>
						<s:property value="#ossOperationLogAssay.operation"/>
		    	    </td>
		    	    <td>
						<s:property value="#ossOperationLogAssay.nub"/>
		    	    </td>
		    	    <td>
						<s:property value="#ossOperationLogAssay.sum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#ossOperationLogAssay.ratio"/>%
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
