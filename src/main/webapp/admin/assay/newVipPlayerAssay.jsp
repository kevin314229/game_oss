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
	<s:form action="/admin/assay/newVipPlayerAssay.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		      <div class="searchDiv_left">
							<s:text name="statistical_time" />：
							<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
							<s:text name="to" /> 
							<s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
		    	</div>
		    	<div class="searchButton2">
					<ul>
						<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
					</ul>
				</div>
			</div>
	 </s:form>
</div>
	
<div class="pageContent sortDrag" selector="h1" layoutH="70">
		<s:text name="new_query_time_frame" />VIP<s:text name="the_number" /><span class="color-red"> <s:property value="count"/></span>
		<div class="panel collapse">
			<h1><s:text name="the_number_of_new_period" /></h1>
			<div>
			    <table class="list" width="100%">
			    	<thead>
			    	<tr>
						<th width="300"><s:text name="time" /></th>
						<th><s:text name="the_number_of" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="hourList" var="oneRow"  status="offset">
			    		<tr  >
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
		</div>
		<div class="panel collapse">
			<h1><s:text name="for_the_first_time" />vip<s:text name="the_user's" />(vip)<s:text name="grade_distribution" /></h1>
			<div>
			    <table class="list" width="100%">
				    <thead>
			    	<tr>
						<th width="300">vip<s:text name="level" /></th>
						<th><s:text name="the_number_of" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="levelList" var="oneRow"  status="offset">
			    		<tr  >
				    	    <td>
								<s:property value="#oneRow.level"/>
							</td>
							<td>
								<s:property value="#oneRow.c"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
			    </table>
			</div>
		</div>
		
		<div class="panel collapse">
			 <h1><s:text name="daily_new" />vip<s:text name="the_number_of_distribution" /></h1>
			 <div>
			     <table class="list" width="100%">
			     	<thead>
			    	<tr>
						<th width="300"><s:text name="the_date_of" /></th>
						<th><s:text name="the_number_of" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="everyDayNewCountList" var="oneRow"  status="offset">
			    		<tr  >
				    	    <td>
				    	    	<s:property value="#oneRow.d"/>
				    	    </td>
							<td>
								<s:property value="#oneRow.c"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
			    </table>
		    </div>
		</div>
</div>
