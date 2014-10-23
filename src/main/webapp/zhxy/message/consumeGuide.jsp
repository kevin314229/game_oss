<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
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

<script src="<%=basePath%>/media/default/js/jquery.jmpopups-0.5.1.js"></script>
<script type="text/javascript" language="javascript">
		function reflash(){
			$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            //dataType: "json",//<s:text name="return" />json<s:text name="the_format_of_the_data" />
	            url: "<%=basePath%>/admin/message/consumeGuide_reflash.action",
	            cache: false,
	            data:null,
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	if(msg=="ok"){
	            		alert("<s:text name="refresh_the_success" />");
	            		window.location="<%=basePath%>/admin/message/mallActivity_index.action";
	            	}
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
			var items =document.getElementsByName("itemId");
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
	            url: "<%=basePath%>/admin/message/consumeGuide_syn.action",
			cache : false,
			data : {
				malls : sign,
				serverArray : servers
			},//<s:text name="to_send_data" />
			success : function(msg) {//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
				alert(msg);
			}
		});

	}
		
		function queryGuideServerList(obj){
			var sign = "";
			var items =document.getElementsByName("consumeGuideId");
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
		    $.pdialog.open("<%=basePath%>/admin/message/zhfunctionAdjust_server.action?syncType=8&types="
					+ sign, "1", "<s:text name="synchronous" />", {
				width : 760,
				height : 720
			});
}
</script>
<s:include value="/admin/template/scriptMessage.jsp" />


<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<li><a width="500" height="350" class="add"
				href="<%=basePath%>/admin/message/consumeGuide_add.action"
				target="dialog"><span><s:text name="new" /> <s:text
							name="increase" /></span></a></li>
			<li class="line">line</li>
			<li><a class="icon"
				href="javascript:confirmRefresh({'url':'<%=basePath%>/admin/message/consumeGuide_reflash.action'});"><span><s:text
							name="refresh_the_goods" /></span></a></li>
		<li><a class="icon" href="javascript:queryGuideServerList();"><span><s:text name="synchronous" /></span></a></li>
				<%--<li><a title="<s:text name="do_you_really_want_to_delete_these_records" />?" href="javascript:deleteOssRole()" class="delete"><span><s:text name="batch_delete" /></span></a></li> --%>
		</ul>
	</div>

	<table class="list" width="100%" layoutH="42">
		<thead>
			<tr>
				<th width="50"><input type="checkbox" group="itemId"
					class="checkboxCtrl"></th>
				<th width="120"><s:text name="consumer_guide_names" /></th>
				<th width="120"><s:text name="go_interface" /></th>
				<th width="40" title="<s:text name="sort_guidelines" />"><s:text name="sort" /></th>
				<th><s:text name="description" /></th>
				<th width="120"><s:text name="are_open" /></th>
				<th width="120"><s:text name="created" /></th>
				<th width="120"><s:text name="modify_the" /></th>
				<th width="120"><s:text name="delete_operation" /></th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="OssConsumeGuideList" var="oneRow" status="offset">
				<tr>
					<td><input type="checkbox" name="consumeGuideId"
						value="${oneRow.consumeGuideId}"></td>
					<td><s:property value="#oneRow.name" /></td>
					<td><s:if test="#oneRow.path==0"><s:text name="no" /></s:if> <s:elseif
							test="#oneRow.path==1"><s:text name="mall" /></s:elseif> <s:elseif
							test="#oneRow.path==2"><s:text name="package_deals" /></s:elseif> <s:elseif
							test="#oneRow.path==3"><s:text name="recharge" /></s:elseif> <s:elseif
							test="#oneRow.path==4"><s:text name="elixir_park" /></s:elseif> <s:elseif
							test="#oneRow.path==5"><s:text name="alchemy" /></s:elseif> <s:elseif
							test="#oneRow.path==6">vip</s:elseif></td>
					<td><s:property value="#oneRow.sort" /></td>
					<td><s:property value="#oneRow.describe" /></td>
					</td>
					<td><s:if test="#oneRow.opened==1"><s:text name="be" /></s:if>
					<s:elseif test="#oneRow.opened==0"><s:text name="no" /></s:elseif>
					</td>					
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.createTime" /></td>
					<td><a width="500" height="350"
						href="<%=basePath%>/admin/message/consumeGuide_query.action?ossConsumeGuide.consumeGuideId=${oneRow.consumeGuideId}"
						class="btnEdit" target="dialog"><s:text name="modify_the" /></a>
					<td><a
						href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_the_goods" />？',
		    	     		 'url':'<%=basePath %>/admin/message/consumeGuide_del.action','data':{'ossConsumeGuide.consumeGuideId':${oneRow.consumeGuideId}}});"
						class="btnDel"> <span><s:text name="delete" /></span></a></td>

				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

