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
				self.location ="<%=basePath %>/admin/message/modifyActivity_deleteModifyActivity.action?id="+id;
			}
		}
		
		function reflash(){
			$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            url: "<%=basePath%>/admin/message/modifyActivity_reflash.action",
	            cache: false,
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	
	            	alert("<s:text name="system_activity_break_success" />!");
	            	window.location="<%=basePath %>/admin/message/modifyActivity_index.action";
	            	
	            }
		   });
		}
		function openStaticPopup() {
			$.openPopupLayer({
				name: "myStaticPopup",
				width: 820,
				target: "myHiddenDiv"
			});
		}
		function checkItem(){
			var sign = "";
			var items =document.getElementsByName("id6");
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
	            url: "<%=basePath%>/admin/message/modifyActivity_syn.action",
	            cache: false,
	            data: {ids:sign,serverArray:servers},//<s:text name="to_send_data" />
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	alert(msg);
	            }
		   });
			
		} 
		
		function queryServerList6(obj){
			var sign = "";
			var items =document.getElementsByName("id6");
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
			$.pdialog.open("<%=basePath%>/admin/message/functionAdjust_server.action?syncType=6&types="+sign, "6", "<s:text name="synchronous" />",  {width:760,height:720});
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


	
<div class="pageContent">
	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a width="950" height="670" class="add" href="<%=basePath%>/admin/message/rechargeActivity_toAdd.action" target="dialog"><span><s:text name="new" /> <s:text name="increase" /></span></a></li>
			<li class="line">line</li>
			
			<li><a class="icon" href="javascript:confirmRefresh({'url':'<%=basePath %>/admin/message/rechargeActivity_reflash.action'})"><span><s:text name="refresh_the_activity" /></span></a></li>
			 <li><a class="icon" width="770" height="360"    href="javascript:queryServerList6(this);"><span><s:text name="synchronous" /></span></a></li> 
		</ul>
	</div>

	<table class="list" width="100%" layoutH="42" style="table-layout: fixed;">
		<thead>
	    	<tr>
	    		<th width="30px"><input type="checkbox" group="id6" class="checkboxCtrl"></th>
	    		<th width="60px"><s:text name="activity" />ID</th>
	    		<th width="70px"><s:text name="the_activity_type" /></th>
	    		<th><s:text name="description_recharge" /></th>
	    		<th><s:text name="description_recharge" />2</th>
	    	    <th width="120px"><s:text name="activities_on_time" /></th>
	    	    <th width="120px"><s:text name="activity_over_time" /></th>
	    	    <th width="70px"><s:text name="modify_the_operating" /></th>
	    	    <th width="70px"><s:text name="delete_operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossRechargeActivityList" var="oneRow"  status="offset">
	    		<tr >
	    			<td>
		    	    	<input type="checkbox" name="id6" value="${oneRow.recharegeActivityId}"> 
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.recharegeActivityId"/>
		    	    </td>
		    	     <td>
			    	     <s:if test="#oneRow.type==1">
			    	     	<s:text name="once_a_day" />
			    	     </s:if>
			    	     <s:else>
			    	     	<s:text name="activity_cycle" />
			    	     </s:else>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.activityDesc"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.activityDescSecond"/>
		    	    </td>
		    	    
		    	    <td class="tdwarp" title="<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.openTime"/>">
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.openTime"/>
		    	    </td>
		    	    <td class="tdwarp" title="<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.openTime"/>">
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.overTime"/>
		    	    </td>
		    	    <td> 
		    	    	<a width="950" height="670" href="<%=basePath%>/admin/message/rechargeActivity_toUpdate.action?ossRechargeActivity.recharegeActivityId=${oneRow.recharegeActivityId}" style="color:green" class="btnEdit" target="dialog">
					    	   <span><s:text name="modify_the" /></span>
					    </a>
					 </td>
		    	    <td> 
		    	     <a href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_this_activity" />？','url':'<%=basePath %>/admin/message/rechargeActivity_delete.action','data':{'ossRechargeActivity.recharegeActivityId':${oneRow.recharegeActivityId}}});" style="color:green" class="btnDel" >
				    	      	<span><s:text name="delete" /></span>
		    	        </a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
