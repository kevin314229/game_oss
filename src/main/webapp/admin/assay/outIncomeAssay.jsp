<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form action="/admin/assay/outIncomeAssay_index.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				   <div class="searchDiv_left">
							<s:text name="statistical_time" />：
							<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12"></s:textfield>
						 
							<s:text name="to" /> 
							<s:textfield id="endDate" name="endDate" maxlength="10" size="12"></s:textfield>
						 </div>
			    	<div class="searchButton2">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
						</ul>
					</div>
				</div>
		</s:form>
</div>

	<div class="pageContent sortDrag" selector="h1" layoutH="50">
		<div class="panel collapse">
		<h1></h1>
			<div>
			    <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="the_date_of" /></th>
						<th><s:text name="gold_output" /></th>
						<th><s:text name="gold_consumption" /></th>
						<th><s:text name="server_retained_number_of_gold_coins" /></th>
						<th><s:text name="the_single_largest" /></th>
						<th><s:text name="the_single_biggest_role_name" /></th>
						<th>PC<s:text name="gold_output" /></th>
						<th>PC<s:text name="gold_consumption_rewrite_key" /></th>
						<th><s:text name="mobile_phone_gold_output" /></th>
						<th><s:text name="mobile_phone_gold_consumption" /></th>
						<th><s:text name="temporary_data_identification" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="consumeOutPutList" var="oneRow"  status="offset">
			    		<tr  >
			    			<td>
								<s:date name="id" format="yyyy-MM-dd"/>
				    	    </td>
				    	    <td>
								<s:property value="#oneRow.outSilver"/>
							</td>
							<td>
								<s:property value="#oneRow.consumSilver"/>
							</td>
							<td>
								<s:property value="#oneRow.remainSilver"/>
							</td>
							<td>
								<s:property value="#oneRow.singleBig"/>
							</td>
							<td>
								<s:property value="#oneRow.nickName"/>
							</td>
							<td>
								<s:property value="#oneRow.pcOut"/>
							</td>
							<td>
								<s:property value="#oneRow.pcConsum"/>
							</td>
							<td>
								<s:property value="#oneRow.phoneOut"/>
							</td>
							<td>
								<s:property value="#oneRow.phoneConsum"/>
							</td>
							<td> 
								<s:if test="#oneRow.tmpFlag"><span class="color-red"><s:text name="temporary" /></span></s:if> 
								<s:else><s:text name="a_formal" /></s:else>
				    	    </td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	   	 	</table>
	   	 	</div>
	   	 </div>
	    <div class="panel collapse">
		<h1><s:text name="time_out_gold_coins" /></h1>
			<div>
			    <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="the_role_of" /></th>
						<th><s:text name="get_gold_coins" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="gainSilverList" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.nick_name"/>
							</td>
							<td>
								<s:property value="#oneRow.snum"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
	    		</div>
	    </div>
	    
	     <div class="panel collapse">
			<h1><s:text name="time_period_number_with_gold_coins" /></h1>
			<div>
			    <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="the_role_of" /></th>
						<th><s:text name="have_a_gold_coin" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="ownSilverList" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.name"/>
							</td>
							<td>
								<s:property value="#oneRow.silver"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
	    		</div>
	    </div>
	    
		<div class="panel collapse">
			<h1><s:text name="time_out_consumption_of_gold" /></h1>
			<div>
			    <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="the_role_of" /></th>
						<th><s:text name="consumption_of_gold" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="consumSilverList" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.nick_name"/>
							</td>
							<td>
								<s:property value="#oneRow.snum"/>
							</td>
						</tr>
	    			</s:iterator>
	    			</tbody>
	    		</table>
	    	</div>
	    </div>
</div>
