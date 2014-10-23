<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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

		function queryServerList1(obj){
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
				alertMsg.error('<s:text name="did_not_choose_to_synchronize" />！');
			    return;
			}else{
				sign=sign.substring(0,sign.length-1);
			}
			$.pdialog.open("<%=basePath%>/admin/message/mallNew_server.action?syncType=7&types="
						+ sign, "1", "<s:text name="synchronous" />", {
					width : 760,
					height : 720
			});
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
			<li><a width="800" height="500" class="add" href="<%=basePath%>/admin/message/mallNew_add.action" target="dialog"><span><s:text name="new" /> <s:text name="increase" /></span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:confirmRefresh({'url':'<%=basePath%>/admin/message/mallNew_reflash.action'});" ><span><s:text name="refresh_the_goods" /></span></a></li>
			<li><a class="icon" href="javascript:queryServerList1(this);"><span><s:text name="synchronous" /></span></a></li>
		</ul>
	</div>

	<table class="list" width="100%" layoutH="42">
		<thead>
	    	<tr>
	    		<th><input type="checkbox" group="itemId" class="checkboxCtrl"></th>
	    		<th><s:text name="name_of_commodity" /> </th>
	    		<th><s:text name="items" />ID </th>
	    		<th title="<s:text name="is_displayed_in_the_mall" />"><s:text name="condition" /></th>
	    		<th title="<s:text name="sort_mall" />"><s:text name="product_sort" /></th>
	    		<th><s:text name="commodity_prices" /></th>
	    		<th>vip<s:text name="the_price" /></th>
	    	    <th><s:text name="activity_price" /></th>
	    	    <th><s:text name="the_label"/></th>
	    	    <th><s:text name="mall_columns" /></th>
	    	    <th><s:text name="promotional_type" />  </th>
	    	    <th><s:text name="the_total_amount_of_the_sale" /></th>
	    	    <th><s:text name="the_remaining_amount" /></th>
	    	    <th><s:text name="online_time" /></th>
	    	    <th><s:text name="rolled_off_the_production_time" /></th>
	    	    <th><s:text name="created" /> </th>
	    	    <th><s:text name="modified" /></th>
	    	    <th><s:text name="modify_the" /></th>
	    	    <th><s:text name="delete_operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossMallNewList" var="oneRow"  status="offset">
	    		<tr >
		    	     <td>
		    	    	<input type="checkbox" name="itemId" value="${oneRow.mallNewId}"> 
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.itemName"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.itemId"/>
		    	    </td>
		    	     <td>
		    	   
		    	    	<s:if test="#oneRow.isShow==0">
		    	    	<s:text name="off_the_shelf" />
		    	    	</s:if>
		    	    	<s:elseif test="#oneRow.isShow==1">
		    	    	<s:text name="added" />
		    	    	</s:elseif>
		    	    	<s:elseif test="#oneRow.isShow==2">
		    	    	<s:text name="moratorium_on_the_sale" />
		    	    	</s:elseif>
		    	    	
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.mallOrder"/>
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
			    	    <s:if test="#oneRow.state==0">
			    	    	<s:text name="ordinary" />
			    	    </s:if>
			    	     <s:elseif test="#oneRow.state==1">
			    	    	<s:text name="new" />
			    	    </s:elseif >
			    	  	<s:elseif test="#oneRow.state==2">
			    	  		<s:text name="hot" />
			    	  	</s:elseif>
			    	  	<s:elseif test="#oneRow.state==3"><s:text name="recommend" />
			    	  	</s:elseif>
			    	  	<s:elseif test="#oneRow.state==4"><s:text name="promotions" />
			    	  	</s:elseif>
			    	  	<s:elseif test="#oneRow.state==5">
			    	  		<s:text name="promotional_recommended" />
			    	  	</s:elseif>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#oneRow.mallType==1"><s:text name="props" /></s:if>
			    	    <s:elseif test="#oneRow.mallType==2"><s:text name="gem" /></s:elseif>
			    	    <s:elseif test="#oneRow.mallType==3"><s:text name="gift_certificates" /></s:elseif>
			    	    <s:elseif test="#oneRow.mallType==4"><s:text name="fashion_clothes" /></s:elseif>
			    	    <s:elseif test="#oneRow.mallType==5"><s:text name="fashion_weapons" /></s:elseif>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#oneRow.sellType==0"><s:text name="no" /></s:if>
			    	    <s:elseif test="#oneRow.sellType==1"><s:text name="limited_time" /></s:elseif>
			    	    <s:elseif test="#oneRow.sellType==2"><s:text name="limit" /></s:elseif>
			    	    <s:elseif test="#oneRow.sellType==3"><s:text name="limited_limited" /></s:elseif>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.maxNubDay"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.surplusNubDay"/>
		    	    </td>
		    	     
		    	     <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.startTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.endTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.createTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.moldfyTime"/>
		    	    </td>
		    	   
		    	    <td> 
		    	    	<a width="800" height="500" href="<%=basePath%>/admin/message/mallNew_query.action?ossMallNew.mallNewId=${oneRow.mallNewId}"  class="btnEdit" target="dialog" ><s:text name="modify_the" /></a>
		    	    </td>
		    	    <td> 
		    	     <a href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_the_goods" />？',
		    	     		 'url':'<%=basePath %>/admin/message/mallNew_del.action','data':{'ossMallNew.mallNewId':${oneRow.mallNewId}}});" class="btnDel" >
				    	      	<span><s:text name="delete" /></span></a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>    
    
