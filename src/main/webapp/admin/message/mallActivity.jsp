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
		
		function reflash(){
			$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            //dataType: "json",//<s:text name="return" />json<s:text name="the_format_of_the_data" />
	            url: "<%=basePath%>/admin/message/mallActivity_reflash.action",
	            cache: false,
	            data:null,
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	if(msg=="ok"){
	            		alert("<s:text name="refresh_the_success" />");
	            		window.location="<%=basePath %>/admin/message/mallActivity_index.action";
	            	}
	            }
		   });
		} 
		/* $.setupJMPopups({
			screenLockerBackground: "#003366",
			screenLockerOpacity: "0.5"
		}); */
		function openStaticPopup() {
			$.openPopupLayer({
				name: "myStaticPopup",
				width: 820,
				target: "myHiddenDiv"
			});
		}
		
		function checkItem(){
			var sign = "";
			var items =document.getElementsByName("itemId4");
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
	            url: "<%=basePath%>/admin/message/mallActivity_syn.action",
	            cache: false,
	            data: {malls:sign,serverArray:servers},//<s:text name="to_send_data" />
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	alert(msg);
	            }
		   });
		}
		
		function queryServerList4(obj){
			var sign = "";
			var items =document.getElementsByName("itemId4");
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
			$.pdialog.open("<%=basePath%>/admin/message/functionAdjust_server.action?syncType=4&types="+sign, "4", "<s:text name="synchronous" />",  {width:760,height:720});
		}
		
	</script>
<s:include value="/admin/template/scriptMessage.jsp"/>  
 
 <div id="myHiddenDiv">
 		
	<div class="popup">
		<div style="text-align: right;">
		<a href="javascript:;" style="text-align: center;" onclick="$.closePopupLayer('myStaticPopup')" title="<s:text name="close_the_window" />" ><s:text name="close_the_window" /></a>
		</div>
		<div class="popup-header">
			<h2><s:text name="please_select_a_server_to_synchronize" /></h2>
		</div>
		<div class="popup-body">
			<div class="con">
			<table style="width: 700px">
			<tr>
			<s:iterator value="ossServersList"  var="oneRow"  status="offset">
			<s:if test="#offset.index%4==0"><tr> </s:if>
			<td>
			<input type="checkbox" name="server" value="${id}"> <s:property value="name"/>
			</td>
			</s:iterator>
			</tr>
			</table>
			</div>
		</div>
		
		<input type="button" id="checkItem" onclick="checkItem()" value="<s:text name="synchronized_to_the_server" />"> 
	</div>
</div>
		
	<div class="pageContent">
	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a width="800" height="600" class="add" href="<%=basePath%>/admin/message/mallActivity_add.action" target="dialog"><span><s:text name="new" /> <s:text name="increase" /></span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:confirmRefresh({'url':'<%=basePath%>/admin/message/mallActivity_reflash.action'});" ><span><s:text name="refresh_the_goods" /></span></a></li>
			<li><a class="icon" width="770" height="360"    href="javascript:queryServerList4(this);"><span><s:text name="synchronous" /></span></a></li>
		</ul>
	</div>

	<table class="list" width="100%" layoutH="42">
		<thead>
	    	<tr>
	    		<th><input type="checkbox" group="itemId4" class="checkboxCtrl"></th>
	    		<th><s:text name="name_of_commodity" /> </th>
	    		<th><s:text name="items" />ID </th>
	    		<th><s:text name="commodity_prices" /></th>
	    		<th>vip<s:text name="the_price" /></th>
	    	    <th><s:text name="activity_price" /></th>
	    	    <th><s:text name="online_time" /></th>
	    	    <th><s:text name="rolled_off_the_production_time" /></th>
	    	    <th><s:text name="conditions_of_purchase" /></th>
	    	    <th><s:text name="the_label" /></th>
	    	    <th><s:text name="the_players_maximum_purchase_number" /></th>
	    	    <th><s:text name="maximum_number_of_sale_on_the_day" /></th>
	    	    <th><s:text name="on_the_day_of_the_sale_number" /></th>
	    	    <th><s:text name="additional_consumption" /></th>
	    	    <th><s:text name="modify_the" /></th>
	    	    <th><s:text name="delete_operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossMallActivityList" var="oneRow"  status="offset">
	    		<tr >
		    	     <td>
		    	    	<input type="checkbox" name="itemId4" value="${oneRow.mallActivityId}"> 
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.mallName"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.itemId"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.initPrice"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.vipPrice"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.rebatePrice"/>
		    	    </td>
		    	     
		    	     <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.startTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.endTime"/>
		    	    </td>
		    	    <td>
		    	     
		    	     <s:if test="#oneRow.vipGrade==0">
		    	    	<s:text name="there_is_no" />
		    	    </s:if>
		    	  	<s:else>
		    	  		'vip'<s:property value="#oneRow.vipGrade"/>
		    	  	</s:else>
		    	    </td>
		    	    <td>
		    	     <s:if test="#oneRow.state==0">
		    	    	<s:text name="ordinary" />
		    	    </s:if>
		    	     <s:elseif test="#oneRow.state==1">
		    	    	<s:text name="new" />
		    	    </s:elseif>
		    	  	<s:else>
		    	  		<s:text name="hot" />
		    	  	</s:else>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.maxNubPlayer"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.maxNubDay"/>
		    	    </td>
		    	    <td>
		    	    	${maxNubDay-surplusNubDay}
		    	    </td>
		    	    <td>
		    	    	<!-- /** <s:text name="additional_consumption" />  0-<s:text name="there_is_no" /> 1-<s:text name="gold_coins" /> 2-<s:text name="gift_certificates" /> 3-<s:text name="integral" /> */ -->
		    	    	
		    	    	<s:if test="#oneRow.costType==0">
		    	    		<s:text name="there_is_no" />
			    	    </s:if>
			    	    <s:elseif test="#oneRow.costType==1">
			    	    		<s:text name="gold_coins" />:
		    	    			<s:property value="#oneRow.costPrice"/>
			    	    </s:elseif>
			    	    <s:elseif test="#oneRow.costType==2">
			    	    		<s:text name="gift_certificates" />:
		    	    			<s:property value="#oneRow.costPrice"/>
			    	    </s:elseif>
			    	    <s:elseif test="#oneRow.functionType==3">
			    	    		<s:text name="integral" />:
		    	    			<s:property value="#oneRow.costPrice"/>
			    	    </s:elseif>
			    	    <s:else>
			    	    	<s:text name="other" />:
		    	    		<s:property value="#oneRow.costPrice"/>
			    	    </s:else>
		    	    </td>
		    	    
		    	    <td> 
		    	    	<a width="800" height="600" href="<%=basePath%>/admin/message/mallActivity_query.action?ossMallActivity.mallActivityId=${oneRow.mallActivityId}"  class="btnEdit" target="dialog" ><s:text name="modify_the" /></a>
		    	    </td>
		    	    <td> 
		    	     <a href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_the_goods" />？',
		    	     		 'url':'<%=basePath %>/admin/message/mallActivity_del.action','data':{'ossMallActivity.mallActivityId':${oneRow.mallActivityId}}});" class="btnDel" >
				    	      	<span><s:text name="delete" /></span></a>
				    	      	
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
