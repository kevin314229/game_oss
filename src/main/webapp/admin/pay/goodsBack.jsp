<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
	
   	
</form>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="goodsBack_index" rel="pagerForm" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
				<div class="searchDiv_left">
					<s:text name="statistical_time" />：
					<s:textfield id="beginTime" name="beginTime" maxlength="10" size="12" cssClass="required date">
						 <s:param name="value" ><s:date name="beginTime" format="yyyy-MM-dd" /></s:param> 
					</s:textfield>
	
					<s:text name="to" />
					 <s:textfield id="endTime" name="endTime" maxlength="10" size="12" cssClass="required date">
					 	<s:param name="value" ><s:date name="endTime" format="yyyy-MM-dd" /></s:param> 
					</s:textfield>
		
					类型：<s:select name="type"  headerKey="0" headerValue="--选择类型--" list="#{'1':getText('magic_crystal'),'2':getText('gift_certificates'),'3':getText('gold'),'4':getText('star_stone'),'5':'vip'+getText('grade'),'6':'vip'+getText('experience')}"></s:select>
		
					<s:text name="login_name" />：<s:textfield name="loginName" size="16" id="loginName"></s:textfield>
		
					<s:text name="or_the_role_name" />：<s:textfield name="nickName" size="12" id="nickName"></s:textfield>
				</div>
		 <div class="searchButton2">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div>
		 
		</div>
	</s:form>
</div>

<div class="pageContent">
	    <table class="list" width="100%" layoutH="90">
	    	<thead>
	    	<tr>
	    	    <th><s:text name="players" />ID</th>
	    	    <th><s:text name="role_players" />ID</th>
	    	    <th><s:text name="type_of_operation" /></th>
	    	    <th><s:text name="quantity" /></th>
	    	    <th><s:text name="created" /></th>
	    	    <th><s:text name="create_time" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossGoodsBackList" var="ossGoodsBack" status="offset">
	    		<tr >
		    	  	<td>
		    	    	<s:property value="#ossGoodsBack.playerId"/>
		    	    </td>
		    	    <td>
						<s:property value="#ossGoodsBack.playerBaseId"/>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#ossGoodsBack.type==1">
		    	    		魔晶
		    	    	</s:if>
		    	    	<s:elseif test="#ossGoodsBack.type==2">
		    	    		礼券
		    	    	</s:elseif>
		    	    	<s:elseif test="#ossGoodsBack.type==3">
		    	    		金币
		    	    	</s:elseif>
		    	    	<s:elseif test="#ossGoodsBack.type==4">
		    	    		星石
		    	    	</s:elseif>
		    	    	<s:elseif test="#ossGoodsBack.type==5">
		    	    		vip等级
		    	    	</s:elseif>
		    	    	<s:elseif test="#ossGoodsBack.type==6">
		    	    		vip经验
		    	    	</s:elseif>
		    	    	<s:else>
		    	    		<s:text name="other" />
		    	    	</s:else>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#ossGoodsBack.itemNub"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#ossGoodsBack.createUserId"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#ossGoodsBack.createTime"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
