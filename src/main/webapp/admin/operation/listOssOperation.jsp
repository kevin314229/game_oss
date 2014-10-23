<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

	<script type="text/javascript" language="javascript">
		function deleteOssOperation(id) {
			if (confirm("<s:text name="are_you_sure_you_want_to_delete_the_carrier" />？")){
				self.location ="<%=basePath %>/admin/base/ossOperation_deleteOssOperation.action?id="+id;
			}
		}
	</script>
  

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent">
	
	 <div class="panelBar">
		<ul class="toolBar">
			<li>
		       <a class="add"  width="720" height="460" target="dialog"  href="<%=basePath%>/admin/base/ossOperation_addOssOperationIndex.action" ><span><s:text name="new" /> <s:text name="increase" /></span></a>
			</li>
		</ul>
	</div>


	    <table class="list" width="100%" layoutH="42" style="table-layout: fixed;">
	    	<thead>
		    	<tr>
		    		<th>ID<s:text name="no." /></th>
		    		<th><s:text name="name_of_the_operator" /></th>
		    		<th><s:text name="operators_shorthand" /></th>
		    		<th><s:text name="operator_instructions" /> </th>
		    	    <th><s:text name="divided_into_the_proportion" /></th>
		    	    <th><s:text name="operation" /></th>
		    	</tr>
	    	</thead>
	    	<tbody>
		    	<s:iterator value="ossOperationList" var="oneRow"  status="offset">
		    		<tr >
			    	    <td>
			    	    	<s:property value="#oneRow.id"/>
			    	    </td>
			    	     <td>
			    	    	<s:property value="#oneRow.operationName"/>
			    	    </td>
			    	     <td>
			    	    	<s:property value="#oneRow.carrierOperator"/>
			    	    </td>
			    	     <td>
			    	    	<s:property value="#oneRow.operationDetail"/>
			    	    </td>
			    	    <td>
			    	    	<s:property value="#oneRow.dividendRate"/>
			    	    </td>
			    	    <td> 
			    	    	<a class="btnEdit" width="720" height="460" target="dialog" href="<%=basePath%>/admin/base/ossOperation_updateOssOperationIndex.action?id=${oneRow.id}" style="color:green"><s:text name="modify_the" /></a>
			    	    	<a class="btnDel" href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_the_carrier" />？',
		    	      'url':'<%=basePath %>/admin/base/ossOperation_deleteOssOperation.action',
		    	      'data':{'id':${oneRow.id}}});" style="color:green"><s:text name="delete" /></a>
			    	    </td>
			    	</tr>
		    	</s:iterator>
	    	</tbody>
	    </table>
	    
</div>
