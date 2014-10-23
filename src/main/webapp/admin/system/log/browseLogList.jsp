<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script type="text/javascript">
   		var warnMsg = "<s:property value="errorInfo"/>";
    	var successMsg = "<s:property value="successInfo"/>";
    	$(function(){
    		
    		if(successMsg){
    			alertMsg.correct(successMsg);
    		}else if(warnMsg){
    			alertMsg.info(warnMsg);
    		}
    	});
    	
        function optionChange(obj){
	  		$("#ossServerId").empty();
	  		if(obj==-1){
	  			$("#ossServerId").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
	  			//checkStut();
	  			return;
	  		}
	  		//alert("<s:text name="transmitted_to_the_background" />operationId="+obj);
	 		$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
	            dataType: "json",//<s:text name="return" />json<s:text name="data_format" />
	            url: "<%=basePath%>/admin/base/ossLog_getOssServerListByOperationId.action",
	            cache: false,
	            data: "operationId="+obj,//<s:text name="data_to_be_sent" />
	            success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
	            	//alert("<s:text name="pass_back" />operationId="+obj);
	            	var myobj=eval(msg);
	            	//$("#ossServerId").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
	            	for(var i=0;i<myobj.length;i++){
	            		$("#ossServerId").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
	            	}
	            }
		   });
		}
    	
    	
    	
    </script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
	<input type="hidden" id="opId" name="operationId" value="${operationId}">
	<input type="hidden" id="osId" name="ossServerId" value="${ossServerId}">
</form>

  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form rel="pagerForm"  namespace="/admin/base" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		       <div  class="searchDiv_left">
					<s:text name="platform" />：<select id="operationId" name="operationId" onchange="optionChange(this.value)">
							<option value="-1">--<s:text name="all_platforms" />--</option>
								<s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossServersPt" var="oneRow"  status="offset">
									<s:if test="operationId==#oneRow.serverId">
									<option value="<s:property value="#oneRow.serverId" />" selected> 	
					    				<s:property value="#oneRow.serverProvider"/>
					    			</option>
					    			</s:if>
					    			<s:else>
									<option value="<s:property value="#oneRow.serverId" />"  > 	
					    				<s:property value="#oneRow.serverProvider"/>
					    			</option>
									</s:else>
					    		</s:iterator>
						</select> 
				 
					<s:text name="server" />：
					<select id="ossServerId" name="ossServerId">
						    <option value="-1" >--<s:text name="all_servers" />--</option>
								<s:iterator value="ossServerList" var="oneRow"  status="offset">
									<s:if test="ossServerId==#oneRow.id">
									<option value="<s:property value="#oneRow.id" />" selected> 	
					    				<s:property value="#oneRow.name"/>
					    			</option>
					    			</s:if>
					    			<s:else>
									<option value="<s:property value="#oneRow.id" />" selected > 	
					    				<s:property value="#oneRow.name"/>
					    			</option>
									</s:else>
					    		</s:iterator>
						    

					</select>
				 
					<s:text name="operation_type" />
					<s:select list="@com.jcwx.game.admin.constant.OssLogConstant@maptype" name="operationType" listKey="key" listValue="value"></s:select>
				 
					<s:text name="account_number" /><s:textfield  id="name" name="name" maxlength="10" size="12" />
			 
					<s:text name="statistical_time" />：
					<s:textfield id="beginTime" name="beginTime" maxlength="10" size="12" cssClass="required date">
						 <s:param name="value" ><s:date name="beginTime" format="yyyy-MM-dd" /></s:param> 
					</s:textfield>
				 
					<s:text name="to" /> 
					<s:textfield id="endTime" name="endTime" maxlength="10" size="12" cssClass="required date">
						<s:param name="value" ><s:date name="endTime" format="yyyy-MM-dd" /></s:param>
					</s:textfield>
				 </div>
	    	<div class="searchButton2">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
	</div>
	
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="64" style="table-layout: fixed;">
	    <thead>
	    	<tr>
	    	    <th>ID</th>
	    	    <th><s:text name="account" /></th>
	    	    <th><s:text name="record" />IP</th>
	    	    <th>ip<s:text name="region" /></th>
	    	    <th><s:text name="operation_type" /></th>
	    	    <th><s:text name="operating_content" /></th>
	    	    <th><s:text name="operation_date" /></th>
	    	    <th><s:text name="note" /> </th>
	    	    <th><s:text name="name_of_the_server_area" /> </th>
	    	    <th><s:text name="name_of_the_operator" /> </th>
	    	   
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossLogList" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
						 <s:property value="#oneRow.id"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.name"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.loginIp"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.ipAddress"/>
		    	    </td>
		    	     <td>
		    	     
						 <s:property 
						 value="@com.jcwx.game.admin.constant.OssLogConstant@maptype[#oneRow.operationType]" />
					
		    	    </td>
		    	     <td class="tdwarp" title="<s:property value="#oneRow.operationMsg"/>">
						 <s:property value="#oneRow.operationMsg"/>
		    	    </td>
		    	     <td>
						 <s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.createTime"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.remark"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.serverName"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.businessName"/>
		    	    </td>
		    	   
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <s:include value="/admin/template/paging.jsp"/>
	 </div>
