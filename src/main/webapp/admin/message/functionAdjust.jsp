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
				width: 1820,
				target: "myHiddenDiv"
			});
		}
		function checkItem(){
			var sign = "";
			var items =document.getElementsByName("itemId1");
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
	            url: "<%=basePath%>/admin/message/functionAdjust_syn.action",
	            cache: false,
	            data: {types:sign,serverArray:servers},//<s:text name="to_send_data" />
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	alert(msg);
	            }
		   });
			
		}
		
		function queryServerList1(obj){
			var sign = "";
			var items =document.getElementsByName("itemId1");
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
			$.pdialog.open("<%=basePath%>/admin/message/functionAdjust_server.action?syncType=1&types="
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
</style>



<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a width="770" height="360" class="add"
				href="<%=basePath%>/admin/message/functionAdjust_add.action"
				target="dialog"><span><s:text name="new" /> <s:text
							name="increase" /></span></a></li>
			<li class="line">line</li>
			<li><a class="icon"
				href="javascript:confirmRefresh({'url':'<%=basePath%>/admin/message/functionAdjust_reflash.action'});"><span><s:text
							name="the_refresh_function_points" /></span></a></li>
			<li><a class="icon" width="770" height="360"
				href="javascript:queryServerList1(this);"><span><s:text
							name="synchronous" /></span></a></li>
			<li><a target="selectedTodo"
				title="确实要删除这些记录吗?" rel="itemId1"
				href="<%=basePath%>/admin/message/functionAdjust_delMulti.action"
				class="delete"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="42">
		<thead>
			<tr>
				<th><input type="checkbox" group="itemId1" class="checkboxCtrl"></th>
				<th><s:text name="function_adjustment_type" /></th>
				<th><s:text name="function_changes_the_value_adjustment" /></th>
				<th><s:text name="the_start_time" /></th>
				<th><s:text name="the_end_of_time" /></th>
				<th><s:text name="describe" /></th>
				<th><s:text name="creation_time" /></th>
				<th><s:text name="modify_the_time" /></th>
				<th><s:text name="modify_the_operating" /></th>
				<th><s:text name="delete_operation" /></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="ossFunctionAdjustList" var="oneRow"
				status="offset">
				<tr>
					<td><input type="checkbox" name="itemId1"
						value="${oneRow.functionId}"></td>
					<td><s:if test="#oneRow.functionType==1">
		    	    	1-<s:text name="refresh_the_mystery_shop" />
						</s:if> <s:elseif test="#oneRow.functionType==2">
		    	    		2-<s:text name="rune_master_invited" />
						</s:elseif> <s:elseif test="#oneRow.functionType==3">
		    	    		3-<s:text name="gold_multiples" />
						</s:elseif> <s:elseif test="#oneRow.functionType==4">
		    	    		4-<s:text name="runic_writing_discount" />
						</s:elseif> <s:elseif test="#oneRow.functionType==5">5-普通洗炼金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==6">6-高级洗炼金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==7">7-超级洗炼金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==8">8-完美洗炼金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==9">9-普通洗炼魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==10">10-高级洗炼魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==11">11-超级洗炼魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==12">12-完美洗炼魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==13">13-金币培养金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==14">14-高级培养金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==15">15-至尊培养金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==16">16-天神培养金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==17">17-金币培养魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==18">18-高级培养魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==19">19-至尊培养魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==20">20-天神培养魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==21">21-议事厅金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==22">22-图书馆金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==23">23-军团捐献金币打折</s:elseif> <s:elseif
							test="#oneRow.functionType==24">24-议事厅魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==25">25-图书馆魔晶礼券打折</s:elseif> <s:elseif
							test="#oneRow.functionType==26">26-军团捐献魔晶礼券打折</s:elseif> <s:else>
							<s:text name="other" />
						</s:else></td>
					<td><s:property value="#oneRow.functionNub" /></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.startTime" /></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.endTime" /></td>
					<td><s:property value="#oneRow.describe" /></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.createTime" /></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.moldfyTime" />
					<td><a class="btnEdit" width="770" height="360"
						target="dialog"
						href="<%=basePath%>/admin/message/functionAdjust_query.action?ossFunctionAdjust.functionId=${oneRow.functionId}"
						style="color: green"><s:text name="modify_the" /></a></td>
					<td><a class="btnDel"
						href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_this" />？',
		    	      'url':'<%=basePath %>/admin/message/functionAdjust_del.action',
		    	      'data':{'ossFunctionAdjust.functionId': ${oneRow.functionId}}});"><s:text
								name="delete" /></a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>
		