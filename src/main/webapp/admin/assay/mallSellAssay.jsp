<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="/admin/assay/mallSellAssay.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
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
	
<div class="pageContent" selector="h1" layoutH="50">
	 <table class="list" width="100%">
	    	<thead>
	    	<tr>
	    	    <th width="300"><s:text name="sales" />ID</th>
	    	    <th><s:text name="name_of_commodity" /></th>
	    	    <th><s:text name="sales_of_the_total_number" /></th> 
	    	    <th><s:text name="pricing" /></th> 
	    	    <th>vip<s:text name="the_price" /></th> 
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="list" var="oneRow"  status="offset">
	    		<tr>
		    	    <td>
		    	    	<s:property value="#oneRow.mallId"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.name"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.c"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.price"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.vipPrice"/>
		    	    </td>
		    	    
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	</table>
</div>
	
