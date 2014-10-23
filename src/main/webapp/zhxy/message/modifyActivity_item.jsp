<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="date" uri="http://jcwx.oss.com/date"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script src="<%=basePath%>/media/default/js/jquery.jmpopups-0.5.1.js"></script>
	
	<script type="text/javascript" language="javascript">
    	
		function deleteModifyActivity(id) {
			if (confirm("<s:text name="are_you_sure_you_want_to_delete_this_activity" />？")){
				self.location ="<%=basePath %>/zhxy/message/modifyActivity_deleteModifyActivity.action?id="+id;
			}
		}
		
		function reflash(){
			$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            url: "<%=basePath%>/zhxy/message/modifyActivity_reflash.action",
	            cache: false,
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
				alert("<s:text name="system_activity_break_success" />!");
	            window.location="<%=basePath %>/zhxy/message/modifyActivity_index.action";
	            	
	           }
		   });
		}
		function openStaticPopup() {
			$.openPopupLayer({
				name: "myStaticPopup",
				width: 1000,
				height: 700,
				target: "myHiddenDiv"
			});
		}
		function checkItem(){
			var sign = "";
			var items =document.getElementsByName("id2");
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
	            url: "<%=basePath%>/zhxy/message/modifyActivity_syn.action",
	            cache: false,
	            data: {ids:sign,serverArray:servers},//<s:text name="to_send_data" />
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	alert(msg);
	            }
		   });
			
		} 
		
		function queryActivityServerList(obj){
			var sign = "";
			var items =document.getElementsByName("id2");
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
		    $.pdialog.open("<%=basePath%>/admin/message/zhfunctionAdjust_server.action?syncType=2&types="
					+ sign, "1", "<s:text name="synchronous" />", {
				width : 760,
				height : 720
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
	
<div class="pageContent">
	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a width="950" height="600" ref="dialog1" class="add" href="<%=basePath%>/zhxy/message/modifyActivity_addModifyActivityItem.action" target="dialog"><span><s:text name="new" /> <s:text name="increase" /></span></a> 
			<li class="line">line</li>
			
			<li><a class="icon" href="javascript:confirmRefresh({'url':'<%=basePath %>/zhxy/message/modifyActivity_reflash.action'})"><span><s:text name="refresh_the_activity" /></span></a></li>
 			<li><a class="icon" width="770" height="360"    href="javascript:queryActivityServerList(this);"><span><s:text name="synchronous" /></span></a></li> 
		</ul>
	</div>

	<table class="list" width="100%" layoutH="42" style="table-layout: fixed;">
		<thead>
	    	<tr>
	    		<th><input type="checkbox" group="id2" class="checkboxCtrl"></th>
	    		<th><s:text name="activity" />ID<s:text name="no." /></th>
	    		<th>平台</th>
	    		<th><s:text name="the_activity_type" /></th>
	    		<th><s:text name="get_the_way" /></th>
	    		<th>排序 </th>
	    		<th><s:text name="account_number_of_recipients" /></th>
	    	    <th><s:text name="role_of_recipients" /></th>
	    	    <th><s:text name="collection_type" /></th>
	    	    <th><s:text name="the_name_of_the_event" /></th>
	    	    <th><s:text name="activity_description" /></th>
	    	    <th><s:text name="activity_rules" /></th>
	    	    <th><s:text name="activities_on_time" /></th>
	    	    <th><s:text name="activity_over_time" /></th>
	    	      <th><s:text name="end_time_award" /></th>
	    	    <%--  <th><s:text name="platform_identity" /></th>--%>
	    	   <th><s:text name="modify_the_operating" /></th> 
	    	    <th><s:text name="delete_operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossActivities" var="oneRow"  status="offset">
	    		<tr >
	    			<td>
		    	    	<input type="checkbox" name="id2" value="${oneRow.id}"> 
		    	    </td>
		    	    <td  style="word-break:break-all; word-wrap:break-all;">
		    	    	<s:property value="#oneRow.id"/>
		    	    </td>
		    	    <td>
		    	   	 <s:property value="#oneRow.carrierOperator"/>
		    	    </td>
		    	    
		    	     <td>
		    	    <%-- 	<s:property value="#oneRow.type"/> --%><s:property value="typeMap[#oneRow.type]"/>
		    	    </td>
		    	     <td>
		    	     <s:if test="#oneRow.characteristic==1">
		    	     	<s:text name="click_and_receive_button_to_receive_awards_by_the_player" />
		    	     </s:if>
		    	     <s:elseif test="#oneRow.characteristic==2">
		    	     <s:text name="by_the_server_through_system_mail_sent" />
		    	     </s:elseif>
		    	 
		    	    </td>
		    	    <td>
		    	   	 <s:property value="#oneRow.rank"/>
		    	    </td>
		    	    
		    	    <td>
		    	      <s:if test="#oneRow.accountNum==null">
		    	    	0
		    	    	</s:if>
		    	    	 <s:else>
		    	   <s:property value="#oneRow.accountNum"/>
		    	     </s:else>
		    	    </td>
		    	   
		    	    <td>
		    	     <s:if test="#oneRow.roleNum==null">
		    	    	0
		    	    	</s:if>
		    	    	 <s:else>
		    				<s:property value="#oneRow.roleNum"/>
		    	     </s:else>
		    	    	
		    	    </td>
		    	    <td>
		    	    <s:if test="#oneRow.times==1">
		    	     	<s:text name="you_can_only_receive_a_reward_activity" />
		    	     </s:if>
		    	     <s:elseif test="#oneRow.times==2">
		    	     <s:text name="every_reward_can_be_obtained" />
		    	     </s:elseif>
		    	      <s:elseif test="#oneRow.times==3">
		    	     <s:text name="repeated_every_day_for_a_reward" />
		    	     </s:elseif>
		    	      <s:elseif test="#oneRow.times==4">
		    	     <s:text name="repeatable" />
		    	     </s:elseif>
		    	      <s:elseif test="#oneRow.times==5">
		    	     <s:text name="top-up_amount_can_receive" />
		    	     </s:elseif>
		    	    </td>
		    	    <td class="tdwarp" title="<s:property value='#oneRow.name'/>">
		    	    	<s:property value="#oneRow.name"/>
		    	    </td>
		    	    <td class="tdwarp" title="<s:property value='#oneRow.activityDesc' />">
		    	    	<s:property value="#oneRow.activityDesc"/>
		    	    </td>
		    	    <td class="tdwarp" title="<s:property value='#oneRow.activityDesc'/>">
		    	   		 <s:property value="#oneRow.rule"/>
		    	    </td>
		    	    <td class="tdwarp" title="<date:date value='${oneRow.openTime}'/> ">
		    	    <%-- 	<s:property value="openTime"/> --%>
		    	    		 <date:date value="${oneRow.openTime}"/>
		    	    	 <%-- <s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.openTime"/>   --%>
		    	    </td>
		    	    <td class="tdwarp" title="<date:date value='${oneRow.overTime}'/>">
		    	    	 <date:date value="${oneRow.overTime}" /> 
		    	    	 <%--<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.overTime"/>--%>
		    	    <%-- 	<s:property value="#oneRow.overTime"/> --%></td>
		    	     <td class="tdwarp" title='<date:date value='${oneRow.rewardOverTime}'/>'>
		    	    	 <%--  <s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.rewardOverTime"/>  --%>
		    	   	<date:date value="${oneRow.rewardOverTime}"/> 
		    	    	<%-- <s:property value="#oneRow.rewardOverTime"/> --%>
		    	    	</td>
		    	   <%--   <td>
		    	   		 <s:property value="#oneRow.carrierOperator"/>
		    	    </td> --%>
		    	    <td> 
		    	    		 <a width="1000" height="700" href="<%=basePath%>/zhxy/message/modifyActivity_updateModifyActivityItem.action?id=${oneRow.id}" style="color:green" class="btnEdit" target="dialog">
					    	      	<span><s:text name="modify_the" /></span>
					    	      </a>
					 </td>
		    	    <td> 
		    	     		 <a href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_this_activity" />？','url':'<%=basePath %>/zhxy/message/modifyActivity_deleteModifyActivity.action','data':{'id':'${oneRow.id}'}});" style="color:green" class="btnDel" >
				    	      	<span><s:text name="delete" /></span>
		    	      </a>
		    	    </td>
		    	    
			
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
