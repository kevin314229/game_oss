<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<style type="text/css"> 
body{ 
text-align:center; 
} 
.graph{ 
width:150px; 
border:1px solid #CEC1C6; 
height:15px; 
} 
.bar{ 
display:block; 
background:#646062; 
float:left; 
height:100%; 
text-align:center; 
} 
#barNum{ 
position:absolute; 
} 
</style> 
<script type="text/javascript"> 
	 
	
 
</script> 
<script type="text/javascript">
 var barArr = new Array();

 function confirmSubmit(){
	    var sign = "${types}";
	    var syncType="${syncType}";
	    var servers = "";
	    var arr = new Array();
	    var num=0;
		var server =document.getElementsByName("server");
		for(var i=0;i<server.length;i++){
		   var obj = server[i];
		   if(obj.type=='checkbox'){
		     if(obj.checked==true){
		    	 servers+=obj.value+",";
		    	 arr[num++]=obj.value;
		      }
		    } 
		}
	    if(servers=="")//<s:text name="no_item_selected" />
		{
			alertMsg.error("<s:text name="did_not_choose_to_synchronize_the_server" /> ");
		    return;
		}else{
			servers=servers.substring(0,servers.length-1);
		}
	   //<s:text name="different_synchronization_methods,_depending_on_the_type_of_synchronization_requests" />
	   var url="";
	   if(syncType=="1"){//<s:text name="synchronization_point" />
		   url="<%=basePath%>/admin/message/functionAdjust_syn.action";
	   }else  if(syncType=="2"){//<s:text name="concurrent_modification_activities" />
		   url="<%=basePath%>/admin/message/modifyActivity_syn.action";
	   }else  if(syncType=="3"){//<s:text name="synchronization_system_event_announcements" />
		   url="<%=basePath%>/admin/message/activityNotice_syn.action";
	   }else  if(syncType=="4"){//<s:text name="synchronous_activity_mall" />
		   url="<%=basePath%>/admin/message/mallActivity_syn.action";
	   }else  if(syncType=="5"){//<s:text name="synchronization_system_activity" />
		   url="<%=basePath%>/admin/message/systemActivity_syn.action";
	   }else  if(syncType=="6"){//<s:text name="the_first_charge_and_sync_daily" />
		   url="<%=basePath%>/admin/message/rechargeActivity_syn.action";
	   }
	    alertMsg.confirm(" <s:text name="are_you_sure_you_synchronize_the_selected_server" />？", {
			okCall: function(){
				//<s:text name="single_synchronization_server_sends_a_request_for_each" />，<s:text name="real-time_feedback_and_information" />
				for(var i=0;i<num;i++){
					id=arr[i];
					$.ajax({
				            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
				            url: url ,
				            cache: false,
				            global:false,
				            data: {types:sign,serverArray:arr[i]},//<s:text name="to_send_data" />
				            dataType: "json",
				            success: function(result){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
				           		$("#td"+result.id)[0].innerHTML=result.msg;
				           		$("#bar"+result.id)[0].style.width = 100 + "%"; 
				    			$("#bar"+result.id)[0].innerHTML =  $("#bar"+result.id)[0].style.width; 
				            }
					});
					barArr[i]= setInterval("go("+id+","+i+")",500);
				}
			}
		});
 }
 //clearInterval(bar);  
 //<s:text name="loading_progress_bar" />
 function go(id,num){ 
	 var width= parseInt( $("#bar"+id)[0].style.width);
	 if( width ==100){
		 clearInterval(barArr[num]); 
	 }
	 if( width <99){ 
		 $("#bar"+id)[0].style.width = parseInt( $("#bar"+id)[0].style.width) + 1 + "%"; 
		 $("#bar"+id)[0].innerHTML =  $("#bar"+id)[0].style.width; 
	 }
} 

 //<s:text name="on_the_new_server" />、<s:text name="delete_server_operating_table" />
function changeRow(obj){
	 var tb1 = document.getElementById("tb1");
	 if(obj.checked){
		 var tr=document.createElement("tr");
		 tr.setAttribute("style","border-bottom:2px solid #F8F8F8");
		 tr.setAttribute("id","tr"+obj.value);
		 var td1 =document.createElement("td");
		 td1.innerHTML=obj.nextSibling.nodeValue;
		 var td2 =document.createElement("td");
		 //<s:text name="added_progress_bar" />
		 var div =document.createElement("div");
		 div.setAttribute("class","graph");
		 
		 var strong =document.createElement("strong");
		 strong.setAttribute("id","bar"+obj.value);
		 strong.setAttribute("style","width:1%;");
		 strong.setAttribute("class","bar");
		 div.appendChild(strong);
		 td2.appendChild(div);
	 
		 
		 var td3 =document.createElement("td");
		 td3.setAttribute("id","td"+obj.value);
		 tr.appendChild(td1);
		 tr.appendChild(td2);
		 tr.appendChild(td3);
		 tb1.appendChild(tr);
	 }else{
		 var tr = document.getElementById("tr"+obj.value);
		 tb1.removeChild(tr);
	 }
 }
</script>
<s:include value="/admin/template/scriptMessage.jsp"/>
    	
		<div class="pageContent">
	     <s:form action="functionAdjust_update" namespace="/admin/message" method="post" theme="simple"
	    	 cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
	    	 
	    	 	<div class="popup-header"  style="height:16px;margin-top:-10px">
			<h2><s:text name="please_select_a_server_to_synchronize" /></h2>
		</div>
		<div class="popup-body">
			<div class="con">
			<table style="width:700px"  ">
			<tr>
			<s:iterator value="ossServersList"  var="oneRow"  status="offset">
			<s:if test="#offset.index%4==0"><tr> </s:if>
				<s:if test="#oneRow.id!=#session.ADMIN_SYSTEM_USER_NAME.currentOssServer.id">
			<td>
			<input type="checkbox" name="server" value="${id}" onclick="changeRow(this)">
								<s:property value="name"/>
			</td>
			</s:if>
			</s:iterator>
			</tr>
			</table>
			</div>
			
			  <div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="confirmSubmit();"><s:text name="synchronized_to_the_server" /></button></div></div></li>
					</ul>
				</div>
		</div>
		   
		     <div class="popup-body" >
			<div class="con" >
			<table class="list" width="100%" layoutH="350">
		<thead>
	    	<tr>
	    		<th style="width:25%"><s:text name="server_name" /></th>
	    		<th style="width:25%"><s:text name="synchronization_progress_bar" /></th>
	    		<th><s:text name="synchronization_information" /></th>
	    	</tr>
	    	</thead>
	    	<tbody id="tb1">
	    	
		
	    	</tbody>
	    </table>
			</div>
			
		</div>
	    </s:form>
	    </div>
