<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script src="<%=basePath%>/media/default/js/jquery.jmpopups-0.5.1.js"></script>
	<script type="text/javascript" language="javascript">
    	
		function deleteModifyActivity(id) {
			if (confirm("<s:text name="are_you_sure_you_want_to_delete_this_activity" />？")){
				self.location ="<%=basePath %>/zhxy/message/activityNotice_deleteActivityNotice.action?ossActivityNotice.activityNoticeId="+id;
			}
		}
		
		function reflash(){
			$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            url: "<%=basePath%>/zhxy/message/activityNotice_reflash.action",
	            cache: false,
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	alert("<s:text name="activities_to_refresh_the_success" />!");
	            	window.location="<%=basePath %>/zhxy/message/activityNotice_index.action";
	            }
		   });
		} 
		
		
	</script>
	<s:include value="/admin/template/scriptMessage.jsp"/>
	<style>
	#myHiddenDiv {display:none;}
	.popup { border:1px solid #ccc; text-align:center; background:url(images/b1-bg06.gif) repeat-x left top #fff;padding-bottom:20px;}
	/* .popup a:hover{ text-decoration:none; color:#fff;} */
	.popup-header {height:24px; padding-top:20px; height:38px; line-height:32px;}
	.popup-header h2 {font-size:14px; width:100%; text-align:center;}
	.popup-body { width:100%; padding-top:8px; }
	.popup-body strong{ display:block; text-align:center; font-size:14px; font-weight:normal; margin-bottom:5px;}
	.con{ padding:10px; width:692px;  margin:0 auto 20px auto; overflow:auto; border:1px solid #a4c9e3;}
	.con p{ text-indent:2em; line-height:18px; margin-bottom:10px;}
	.con b{ text-indent:2em;}
	a.close{ color:#fff;font-size:12px;font-weight:700;  width:156px; height:24px; line-height:24px; text-align:center; margin:0 auto; }
	</style>
  </head>
  

	<div class="pageContent">
	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" width="740" height="580" href="<%=basePath%>/cjwz/notice/systemGift_toAdd.action" target="dialog"><span><s:text name="new" /> <s:text name="increase" /></span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:confirmRefresh({'url':'<%=basePath%>/cjwz/notice/activityNotice_reflash.action'})" ><span><s:text name="the_refresh" /></span></a></li>
			
			<%-- <li><a class="icon" width="770" height="360"    href="javascript:queryServerList3(this);"><span><s:text name="synchronous" /></span></a></li> --%>
		</ul>
	</div>

	<table class="list" width="100%" layoutH="42" style="table-layout: fixed;">
		<thead>
	    	<tr>
	    		
	    		<th>ID </th>
	    		<th>标题</th>
	    	    <th><s:text name="content" /></th>
	    	    <th>领奖用户ID</th>
	    		<th><s:text name="the_end_of_time" /></th>
	    	    <th><s:text name="creation_time" /></th>
	    	    <th><s:text name="the_last_modification_time" /></th>
	    	    <th><s:text name="modify_the_operating" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="jsonArrayList" var="oneRow"  status="offset">
	    		<tr  >
	    		 	<td>
		    	    	<s:property value="id"/> 
		    	    </td>
		    	    <td  class="tdwarp">
		    	    	<s:property value="title"/>
		    	    </td>
		    	    <td  class="tdwarp">
		    	    	<s:property value="content"/>
		    	    </td>
		    	    <td  class="tdwarp">
		    	    	<s:property value="playerIdList"/>
		    	    </td>
		    	     <td>
		    	    	<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss" />
		    	    </td>
		    	    
		    	    <td>
		    	    	<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" />
		    	    </td>
		    	    <td>
		    	    	<s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss" />
		    	    </td>
		    	    <td> 
		    	    	<a class="btnEdit" target="dialog" width="740" height="580" href="<%=basePath%>/cjwz/notice/systemGift_toUpdate.action?systemGift.id=${id}" ><s:text name="modify_the" /></a>
		    	    	
		    	      <a class="btnDel" href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_this_activity" />？',
		    	      'url':'<%=basePath %>/cjwz/notice/systemGift_doDel.action','data':{'systemGift.id': ${id}}});"><s:text name="delete" /></a>
		    	    </td>
		    	    
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
