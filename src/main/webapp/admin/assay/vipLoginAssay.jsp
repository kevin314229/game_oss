<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="/admin/assay/newVipPlayerAssay.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		      <div class="searchDiv_left">
							<s:text name="statistical_time" />：
							<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
			 </div>
		    	<div class="searchButton2">
					<ul>
						<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
					</ul>
				</div>
			</div>
	 </s:form>
</div>
	<div style="height:20px">
	<s:text name="by_the_end_of_all_specified_time" />vip<s:text name="the_total_number" />
	<span class="color-red"><s:text name="vipNum"><s:param name="value" value="vipNum"/></s:text></span>
	<s:text name="within_the_specified_date" />vip<s:text name="the_player_login_number" />
	<span class="color-red"><s:text name="loginNum"><s:param name="value" value="loginNum"/></s:text></span>
	VIP<s:text name="gamers_to_total" />VIP<s:text name="the_percentage_of_the_population" />
	<span class="color-red"><s:text name="percent"><s:param name="value" value="percent"/></s:text></span>
	vip<s:text name="the_average_daily_online_time_by_the_user" /> (<s:text name="seconds" />)
	<span class="color-red"><s:text name="avg"><s:param name="value" value="avg"/></s:text></span>
	<b>vip<s:text name="landing_time_distribution" /> </b>
	</div>
	<div class="pageContent" selector="h1" layoutH="70">
	    <table class="list" width="100%">
	    	<thead>
	    	<tr>
	    	    <th width="300"><s:text name="hours" /></th>
	    	    <th>vip<s:text name="the_login_number" /></th> 
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="loginHourAssayList" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
		    	    	<s:property value="#oneRow.h"/><s:text name="when" />
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.c"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	</div>
