<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script type="text/javascript" language="javascript">
    	
    	
		function deleteOssServer(id) {
			if (confirm("<s:text name="are_you_sure_you_want_to_delete_the_game_zone" />？")){
				self.location ="<%=basePath %>/admin/base/ossServer_deleteOssServer.action?id="+id;
			}
		}
		function reflash(){
			$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            //dataType: "json",//<s:text name="return" />json<s:text name="the_format_of_the_data" />
	            url: "<%=basePath %>/admin/base/ossServer_deleteOssServer.action",
	            cache: false,
	            data:null,
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	if(msg=="ok"){
	            		alert("<s:text name="refresh_the_success" />");
	            		window.location="<%=basePath %>/admin/base/ossServer_deleteOssServer.action";
	            	}
	            }
		   });
		} 
	</script>
	
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent">
    <div class="panelBar">
		<ul class="toolBar">
			<li>
		       <a class="add"  width="740" height="470" target="dialog"  href="<%=basePath%>/admin/base/ossServer_addOssServerIndex.action"  ><span><s:text name="new" /> <s:text name="increase" /></span></a>
			 <li class="line">line</li>
			
			<li><a class="icon" href="javascript:confirmRefresh({'url':'<%=basePath %>/admin/base/ossServer_reflash.action'})"><span><s:text name="synchronous" /></span></a></li>
		</ul>
	</div>
	


	    <table class="list" width="100%" layoutH="42" style="table-layout: fixed;">
	    	<thead>
		    	<tr>
		    		<th><s:text name="the_server" />ID</th>
		    		<th><s:text name="subordinate_to_the_operator" /></th>
		    	    <th><s:text name="the_game_zone_name" /></th>
		    	    <th width="165"><s:text name="the_address_of_the_server" /></th>
		    	    <th width="190"><s:text name="server_communication" />Key</th>
		    	    <th><s:text name="creation_time" /></th>
		    	    <th><s:text name="founder" /></th>
		    	    <th><s:text name="modify_the_time" /></th>
		    	    <th><s:text name="the_modifier" /></th>
		    	    <th><s:text name="operation" /></th>
		    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="servers" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
		    	    	<s:property value="#oneRow.id"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.serverProvider"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.name"/>
		    	    </td>
		    	    <td class="tdwarp">
		    	    	<s:property value="#oneRow.url"/>
		    	    </td>
		    	    <td class="tdwarp">
		    	    	<s:property value="#oneRow.communicateKey"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.createTime"/>
		    	    </td>
		    	    <td>
		    	   		 <s:property value="#oneRow.createUser"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.updateTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.updateUser"/>
		    	    </td>
		    	    <td> 
		    	    	<a class="btnAttach" title="<s:text name="set_up_management_personnel" />" width="740" height="470" target="dialog" href="<%=basePath%>/admin/base/ossServer_addOssServerManager.action?id=${oneRow.id}" style="color:green"><s:text name="set_up_management_personnel" /></a>
		    	    	<a class="btnEdit" width="740" height="470" target="dialog"  href="<%=basePath%>/admin/base/ossServer_updateOssServer.action?id=${oneRow.id}" style="color:green"><s:text name="modify_the" /></a>
		    	    	<a href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_the_game_zone" />？',
		    	     		 'url':'<%=basePath %>/admin/base/ossServer_deleteOssServer.action','data':{'id':${oneRow.id}}});" class="btnDel" >
				    	      	<span><s:text name="delete" /></span></a>
		    	    	
		    	    	
		    	    	
		    	    	
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
</div>
