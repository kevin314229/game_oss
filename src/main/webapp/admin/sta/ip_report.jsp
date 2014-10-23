<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script  type="text/javascript">

	
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
  		$("#ossServerIdIp").empty();
  		
/*   		if(obj==-1){
  			$("#ossServerIdIp").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
  			//checkStut();
  			return;
  		}
 */ 		$.ajax({
            type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
            dataType: "json",//<s:text name="return" />json<s:text name="data_format" />
            url: "<%=basePath%>/admin/base/ipReport_queryOssServerList.action",
            cache: false,
            data: {
            	"operationId":obj
            } ,//<s:text name="data_to_be_sent" />
            success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
            	var myobj=eval(msg);
            	$("#ossServerIdIp").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
            	for(var i=0;i<myobj.length;i++){
            		$("#ossServerIdIp").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
            	}
            }
	   });
	} 
</script>
  <s:include value="/admin/template/scriptMessage.jsp"/>
  <s:include value="/admin/template/changeCSS.jsp"/>
<div class="pageHeader">
	<s:form action="ipReport_index" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
	
		   <div class="searchBar">
		   		<div class="searchDiv_left">
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
					<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="ossServerIdIp"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all')}"      ></s:select> 
					<s:text name="platform_logo" />：<select name="ptId">
								<option value="">--<s:text name="all_platform_logo" />--</option>
									<s:iterator value="ossOperationList" var="oneRows">
										<option value='<s:property value='#oneRows.carrierOperator'/>'
										 <s:if test='ptId==#oneRows.carrierOperator'> selected </s:if> >
										 <s:property value="#oneRows.operationName"/>
										 </option>
									</s:iterator>
							  </select>
					<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="20" cssClass="required date"  value="%{beginDate}"></s:textfield>
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="20" cssClass="required date"></s:textfield>
				</div>
				
	    	<div class="searchButton">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form> 
	</div>

	<div class="pageContent">
	    <table class="list" width="100%" layoutH="120" >
	    <thead>
	    	<tr>
	    	    <th colspan="3" style="text-align:center">
	    	    	<s:text name="statistical_time_range" />：<s:property value="beginDate"/> 00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
	    	    </th>
	    	</tr>
	    	<tr>
	    		<th><s:text name="platform" /></th>
	    	    <th><s:text name="the_total_number_of_logins" /> </th>
	    	    <th>IP<s:text name="number" />（<s:text name="to_re-" />） </th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ipReport" var="oneRow">
	    	<tr>
	    		<td>
	    		 <dict:itemvalue gameId="${session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId}" type="4"  itemValue="${oneRow.ptCode }"></dict:itemvalue>
	    	<%-- 	<s:property value="@com.jcwx.game.common.constants.PtServerConstant@ptTypeMap[#oneRow.ptCode]"/>/<s:property value="#oneRow.ptCode"/> --%></td>
	    	    <td><s:property value="#oneRow.loginNum"/> </td>
	    	    <td><s:property value="#oneRow.ipNum"/> </td>
	    	</tr>
	    	</s:iterator>
	    	<tr>
				<td><s:text name="total" /></td>
				<td><s:property value="loginCount"/></td>
				<td><s:property value="ipCount"/></td>	    	
	    	</tr>
	    	</tbody>
	    </table>
	 </div>
   
