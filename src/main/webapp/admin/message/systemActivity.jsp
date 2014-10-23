<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
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
	<script src="<%=basePath%>/media/default/js/jquery.jmpopups-0.5.1.js"></script>
	
	<script type="text/javascript" language="javascript">
    	
		function openStaticPopup() {
			$.openPopupLayer({
				name: "myStaticPopup",
				width: 820,
				target: "myHiddenDiv"
			});
		}
		function checkItem(){
			var sign = "";
			var items =document.getElementsByName("id5");
			for(var i=0;i<items.length;i++){
			    var obj = items[i];
			   if(obj.type=='checkbox'){
			     if(obj.checked==true){
			      sign+=obj.value+",";
			      }
			    } 
			}
		    if(sign=="")//<s:text name="no_item_selected" />
			{
			    alert("<s:text name="did_not_choose_to_synchronize" />。");
			    return;
			}else{
				sign=sign.substring(0,sign.length-1);
			}
		    
		    var servers = "";
			var server =document.getElementsByName("server");
			for(var i=0;i<server.length;i++){
			   var obj = server[i];
			   if(obj.type=='checkbox'){
			     if(obj.checked==true){
			    	 servers+=obj.value+",";
			      }
			    } 
			}
		    if(servers=="")//<s:text name="no_item_selected" />
			{
			    alert("<s:text name="did_not_choose_to_synchronize_the_server" /> ");
			    return;
			}else{
				servers=servers.substring(0,servers.length-1);
			}
		    if(!confirm(" <s:text name="sure_to_synchronize_the_commodity" />？")){
		    	return;
		    }
		    
		    
		    $.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            url: "<%=basePath%>/admin/message/systemActivity_syn.action",
	            cache: false,
	            data: {ids:sign,serverArray:servers},//<s:text name="to_send_data" />
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	alert(msg);
	            }
		   });
			
		}
		function queryServerList5(obj){
			var sign = "";
			var items =document.getElementsByName("id5");
			for(var i=0;i<items.length;i++){
			    var obj = items[i];
			   if(obj.type=='checkbox'){
			     if(obj.checked==true){
			      sign+=obj.value+",";
			      }
			    } 
			}
		    if(sign=="")//<s:text name="no_item_selected" />
			{
				alertMsg.error('<s:text name="did_not_choose_to_synchronize" />！');
			    return;
			}else{
				sign=sign.substring(0,sign.length-1);
			}
			$.pdialog.open("<%=basePath%>/admin/message/functionAdjust_server.action?syncType=5&types="+sign, "5", "<s:text name="synchronous" />",  {width:760,height:720});
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
  
  <body>

	
	<div class="pageContent">
	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" width="770" height="360" href="<%=basePath%>/admin/message/systemActivity_add.action" target="dialog"><span><s:text name="new" /> <s:text name="increase" /></span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:confirmRefresh({'url':'<%=basePath%>/admin/message/systemActivity_reflash.action'});"><span><s:text name="refresh_the_goods" /></span></a></li>
			<li 
			<s:if test="#session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId==2"> style="display:none"</s:if>
			><a class="icon" width="770" height="360"    href="javascript:queryServerList5(this);"><span><s:text name="synchronous" /></span></a></li>
		</ul>
	</div>

	<table class="list" width="100%" layoutH="42">
		<thead>
	    	<tr>
	    		<th><input type="checkbox" group="id5" class="checkboxCtrl"></th>
	    		<th><s:text name="event_name" /> </th>
	    		<th><s:text name="active_configuration" /> </th>
	    		<th><s:text name="level_requirements" /></th>
	    		<th><s:text name="on_time" /></th>
	    	    <th><s:text name="end_time" /></th>
	    	    <th><s:text name="description" /></th>
	    	    <th><s:text name="created" /></th>
	    	    <th><s:text name="modification" /></th>
	    	    <th><s:text name="delete_operation" /></th>
	    	</tr>
	    </thead>
	    <tbody>
	    	<s:iterator value="ossSystemActivityList" var="oneRow"  status="offset">
	    		<tr >
	    			<td>
		    	    	<input type="checkbox" name="id5" value="${oneRow.systemActivityId}"> 
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.activityName"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.activityDeploy"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.grade"/>
		    	    </td>
		    	     <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.startTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.endTime"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.describe"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.createTime"/>
		    	    </td>
		    	    <td> 
		    	    	<a width="770" height="360" class="btnEdit" target="dialog" href="<%=basePath%>/admin/message/systemActivity_query.action?ossSystemActivity.systemActivityId=${oneRow.systemActivityId}"><s:text name="modification" /></a>
		    	    </td>
		    	    <td> 
		    	      <a class="btnDel" href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_remove_this_activity" />？',
		    	      'url':'<%=basePath %>/admin/message/systemActivity_del.action',
		    	      'data':{'ossSystemActivity.systemActivityId':${oneRow.systemActivityId}}});"><s:text name="delete" /></a>
		    	      
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
