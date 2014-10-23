<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
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
			var items =document.getElementsByName("itemIds");
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
	            url: "<%=basePath%>/admin/message/zhfunctionAdjust_syn.action",
	            cache: false,
	            data: {types:sign,serverArray:servers},//<s:text name="to_send_data" />
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	alert(msg);
	            }
		   });
			
		}
		
		function queryServerList1(obj){
			var sign = "";
			var items =document.getElementsByName("itemIds");
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
			$.pdialog.open("<%=basePath%>/admin/message/zhfunctionAdjust_server.action?syncType=1&types="
						+ sign, "1", "<s:text name="synchronous" />", {
					width : 760,
					height : 720
				});
	}
</script>
<s:include value="/admin/template/scriptMessage.jsp" />
<style>
#myHiddenDiv {
	display: none;
}

.popup {
	border: 1px solid #ccc;
	text-align: center;
	background: url(images/b1-bg06.gif) repeat-x left top #fff;
	padding-bottom: 20px;
}
/* .popup a:hover{ text-decoration:none; color:#fff;} */
.popup-header {
	height: 24px;
	padding-top: 20px;
	height: 38px;
	line-height: 32px;
}

.popup-header h2 {
	font-size: 14px;
	width: 100%;
	text-align: center;
}

.popup-body {
	width: 100%;
	padding-top: 8px;
}

.popup-body strong {
	display: block;
	text-align: center;
	font-size: 14px;
	font-weight: normal;
	margin-bottom: 5px;
}

.con {
	padding: 10px;
	width: 692px;
	margin: 0 auto 20px auto;
	overflow: auto;
	border: 1px solid #a4c9e3;
}

.con p {
	text-indent: 2em;
	line-height: 18px;
	margin-bottom: 10px;
}

.con b {
	text-indent: 2em;
}

a.close {
	color: #fff;
	font-size: 12px;
	font-weight: 700;
	width: 156px;
	height: 24px;
	line-height: 24px;
	text-align: center;
	margin: 0 auto;
}

#tab_inline td{

	word-break:break-all;word-wrap:break-word
}

</style>
	
	<div class="pageContent">
	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a width="770" height="550" class="add" rel="newincrease" href="<%=basePath%>/admin/message/zhfunctionAdjust_add.action" target="dialog"><span><s:text name="new" /> <s:text name="increase" /></span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:confirmRefresh({'url':'<%=basePath%>/admin/message/zhfunctionAdjust_reflash.action'});"><span><s:text name="the_refresh_function_points" /></span></a></li>
			<li><a class="icon" href="javascript:queryServerList1(this);"><span><s:text name="synchronous" /></span></a></li>
						<li><a target="selectedTodo"
				title="<s:text name="do_you_really_want_to_delete_these_records" />?" rel="itemIds"
				href="<%=basePath%>/admin/message/zhfunctionAdjust_delMulti.action"
				class="delete"><span><s:text name="batch_delete" /></span></a></li>
		</ul>
	</div>

	<table class="list" width="100%" layoutH="42" id="tab_inline">
		<thead>
	    	<tr>
	    		<th style="width:13px"><input type="checkbox" group="itemIds" class="checkboxCtrl"></th>
	    		<th  style="width:85px"><s:text name="function_adjustment_type" /> </th>
	    		<th style="width:70px"><s:text name="function_point_name" /> </th>
	    		<th style="width:450px"><s:text name="function_changes_the_value_adjustment" /></th>
	    		<th style="width:60px"><s:text name="the_start_time" /></th>
	    	    <th style="width:60px"><s:text name="the_end_of_time" /></th>
	    	    <th style="width:200px"><s:text name="describe" /></th>
	    	    <th style="width:60px"><s:text name="creation_time" /></th>
	    	    <th style="width:60px"><s:text name="modify_the_time" /></th>
	    	    <th style="width:60px"><s:text name="modify_the_operating" /></th>
	    	    <th style="width:60px"><s:text name="delete_operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossFunctionAdjustList" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
		    	    	<input type="checkbox" name="itemIds" value="${oneRow.functionType}"> 
		    	    </td>
		    	    <td>
		    	    <s:text name="#oneRow.functionType" />-<s:property value="typeMap[#oneRow.functionType]"/>
		    <%-- 
		    	    <s:if test="#oneRow.functionType==1">
		    	    	<s:text name="refresh_the_mystery_shop" />
		    	    </s:if>
		    	    <s:elseif test="#oneRow.functionType==2">
		    	    		<s:text name="rune_master_invited" />
		    	    </s:elseif>
		    	    <s:elseif test="#oneRow.functionType==3">
		    	    		<s:text name="gold_multiples" />
		    	    </s:elseif>
		    	    <s:elseif test="#oneRow.functionType==4">
		    	    		<s:text name="runic_writing_discount" />
		    	    </s:elseif>
		    	    <s:elseif test="#oneRow.functionType==5">
		    	    		<s:text name="main_invite" />
		    	    </s:elseif>
		    	    <s:elseif test="#oneRow.functionType==6">
		    	    		<s:text name="are_invited_to_join" />
		    	    </s:elseif>
		    	    <s:elseif test="#oneRow.functionType==7">
		    	    		<s:text name="invited_rating" /> 
		    	    </s:elseif>
		    	    <s:elseif test="#oneRow.functionType==8">
		    	    		<s:text name="number_of_invited" /> 
		    	    </s:elseif>
	    	        <s:elseif test="#oneRow.functionType==9">
	    	    		<s:text name="probability_of_cure_park_seed" /> 
	    	    	</s:elseif>
	    	        <s:elseif test="#oneRow.functionType==10">
	    	    		<s:text name="elixir_garden_seed_prices" />
	    	    	</s:elseif>
	    	        <s:elseif test="#oneRow.functionType==11">
	    	    		<s:text name="refresh_consumption_elixir_park" />
	    	    	</s:elseif>
	    	        <s:elseif test="#oneRow.functionType==12">
	    	    		<s:text name="online_awards" />
	    	    	</s:elseif>
	    	        <s:elseif test="#oneRow.functionType==13">
	    	    		<s:text name="the_first_charge_active_configuration" />
	    	    	</s:elseif>
	    	        <s:elseif test="#oneRow.functionType==14">
	    	    		14-<s:text name="the_first_charge_reward" />
	    	    	</s:elseif>
	    	        <s:elseif test="#oneRow.functionType==15">
	    	    		15-<s:text name="daily_recharge_awards" />
	    	    	</s:elseif>
	    	        <s:elseif test="#oneRow.functionType==16">
	    	    		16-vip<s:text name="packs" />
	    	    	</s:elseif>
	    	        <s:elseif test="#oneRow.functionType==17">
	    	    		17-<s:text name="integral_wall_configuration" /> (<s:text name="hong_kong,_macao_and_taiwan" />)
	    	    	</s:elseif>
	    	    	
	    	    	
	    	    	
		    	    <s:else>
		    	    	<s:text name="other" />
		    	    </s:else> --%>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.functionName"/>
		    	    </td>
		    	     <td width="25">
		    	    	<s:property value="#oneRow.functionNub"/>
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
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.moldfyTime"/>
		    	     
		    	    <td> 
		    	    	<a class="btnEdit" width="770" height="550" target="dialog"
		    	    	 rel="editincrease"
		    	    	 href="<%=basePath%>/admin/message/zhfunctionAdjust_query.action?ossFunctionAdjust.functionType=${oneRow.functionType}" 
		    	    	 style="color:green"><s:text name="modify_the" /></a>
		    	    </td>
		    	    <td> 
			 		  <a class="btnDel" href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_this" />？',
		    	      'url':'<%=basePath %>/admin/message/zhfunctionAdjust_del.action',
		    	      'data':{'ossFunctionAdjust.functionType': ${oneRow.functionType}}});"><s:text name="delete" /></a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
