<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title><s:text name="the_secondary_system_menu_settings" /></title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>
	
	<script src="<%=basePath%>/media/default/js/jquery.pager.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/public.js"  type="text/javascript"></script>
	 <script>
        function addRow(obj)
        {
       
     	   //<s:text name="add_a_line" />
        var newTr = document.getElementById("row2").insertRow(2);
        // %{getText('add')}3<s:text name="column" />
        var newTd0 = newTr.insertCell(0);
        var newTd1 = newTr.insertCell(1);
        var newTd2 = newTr.insertCell(2);
        //<s:text name="set_column_content_and_properties" />
        newTd0.innerHTML = '<input type="text" size="50" name="m"/>'; 
        newTd1.innerHTML= '<input type="text" size="50" name="d"/>';
        newTd2.innerHTML='<input type="button" value="deleteRow" onclick="deleteRow(this)">';
        
        }
        function deleteRow(r)
		{
		  var i=r.parentNode.parentNode.rowIndex
		  document.getElementById('row2').deleteRow(i)
 	}
    </script>
	
	<style>
		a{text-decoration:none;color:#60A70C;outline:none;hide-focus:expression(this.hideFocus=true)}
		a:visited{color:#60A70C;}
		#row1 {
			border:1px #3598E1 solid;
			border-collapse:collapse;
			font-size:12px;
			width:100%;
			text-align:left;
			margin-bottom:10px;
		}
		#row1 th {
			padding: 2px 0px 2px 0px;
			text-align: center;
			border:1px #3598E1 solid;
			background-color:#BEE092;
			color:#000;
			height:20px;
			font-weight:bold;
			white-space:nowrap;
		}
		#row1 tr.thead{
			background-color: #acdef6;
		}
		#row1 td {
			text-align: center;
			padding: 2px 4px 2px 4px;
			border:1px #3598E1 solid;
			background-color:#E0EFD1;
			height:20px;
		
		}
		#row1 th a,th a:visited {
			color: black;
		}
		#row1 th a:hover {
			text-decoration: underline;
			color: black;
		}
		#row1 tr.odd {
			background-color: #fff
		}
		#row1 tr.tableRowEven,#row tr.even {
			background-color: #e9f4f9
		}
		#row1 td.money {
			font-weight: bold;
			color: blue;
		}
	
		
		
		#row2 {
			border:1px #3598E1 solid;
			border-collapse:collapse;
			font-size:12px;
			width:100%;
			text-align:left;
			margin-bottom:10px;
		}
		#row2 th {
			padding: 2px 0px 2px 0px;
			text-align: center;
			border:1px #3598E1 solid;
			background-color:#D7E4F5;
			color:#000;
			height:20px;
			font-weight:bold;
			white-space:nowrap;
		}
		#row2 tr.thead{
			background-color: #acdef6;
		}
		#row2 td {
			text-align: center;
			padding: 2px 4px 2px 4px;
			border:1px #3598E1 solid;
			background-color:#EDF2F7;
			height:20px;
		
		}
		#row2 th a,th a:visited {
			color: black;
		}
		#row2 th a:hover {
			text-decoration: underline;
			color: black;
		}
		#row2 tr.odd {
			background-color: #fff
		}
		#row2 tr.tableRowEven,#row tr.even {
			background-color: #e9f4f9
		}
		#row2 td.money {
			font-weight: bold;
			color: blue;
		}
	
	</style>
	
  </head>
  
  <body>

  	<div id="divLeft">
		<b><s:text name="the_secondary_system_menu_settings" /></b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:text name="the_current_operation_of_the_menu" />:<s:property value="name"/> </span>
	</div>
	<div id="divLeft">
	
	</div>


	<div id="divList">
		
	     
	    <br/>
	    
		<s:form action="ossMenu_updateSecondOssMenuMapping" namespace="/admin/base" method="post" theme="simple">
	     <table id="row2" >
	    	<tr>
	    	    <th colspan="3"><s:text name="add_a_new_mapping" /></th>
	    	</tr>
	    	
	    	<tr>
	    		<th><s:text name="the_mapping_path" /></th>
	    		<th><s:text name="instructions" /></th>
	    		<th><s:text name="operation" /></th>
	    	</tr>
	    	<s:iterator value="jsonList" var="oneRow"  status="offset">
		    	<tr> 
		    		<td><input type="text" size="50"  name="m" value='<s:property value="@com.jjl.rzjh.common.JSONService@getJSONValueByKey(#oneRow,'url')"/>'/> </td>
		    		<td><input type="text" size="50" name="d" value='<s:property value="@com.jjl.rzjh.common.JSONService@getJSONValueByKey(#oneRow,'desc')"/>'/></td>
		    		<td><input type="button" value="deleteRow" onclick="deleteRow(this)"></td>
		    	</tr>
	    	</s:iterator>
	    	<tr>
	    		
	    		<td colspan="3">
	    			<input type="hidden" name="m" value=""/>
	    			<input type="hidden" name="d" value=""/>
	    			<s:submit value=" %{getText('confirm_to_add')}"></s:submit><input type="button" id="add" onclick="addRow();" value="Add Row" /> 
	    			<input type="button" value="<s:text name="return" />" onclick="self.location='<%=basePath%>/admin/base/ossMenu_browseOssMenu.action'" />
	    		</td>
	    	</tr>
	    </table>
	    <s:hidden name="ossMenuID"/>
	    </s:form>
	    
	 </div>
   <div id="bottomPager"></div>
<s:if test='#request.actionMsg !=null && #request.actionMsg !="" '>
	<script language="javascript">
		alert("<s:property value="actionMsg" escape="false"/>");
	</script>
</s:if>	
  </body>
</html>
