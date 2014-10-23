<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/changeCSS.jsp"/>
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
  		$("#ossServerIdLogin").empty();
  		
  		/* if(obj==-1){
  			$("#ossServerIdLogin").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
  			//checkStut();
  			return;
  		} */
 		$.ajax({
            type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
     /*        dataType: "json",//<s:text name="return" />json<s:text name="data_format" /> */
            url: "<%=basePath%>/admin/base/queryLoginReport_ajax.action",
            cache: false,
            global:false,
            data: {
            	"operationId":obj
            } ,//<s:text name="data_to_be_sent" />
            success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
            	//alert("<s:text name="pass_back" />operationId="+obj);
            	var myobj=eval(msg);
            	$("#ossServerIdLogin").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
            	for(var i=0;i<myobj.length;i++){
            		$("#ossServerIdLogin").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
            	}
            }
	   });
	}
</script>
  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="queryLoginReport_report" namespace="/admin/base" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
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
						<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="ossServerIdLogin"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all')}"      ></s:select> 
					<s:text name="platform_logo" />：<select name="ptCode">
								<option value="-1">--<s:text name="all_platform_logo" />--</option>
									<s:iterator value="ossOperationList" var="oneRows">
										<option value='<s:property value='#oneRows.carrierOperator'/>'
										 <s:if test='ptCode==#oneRows.carrierOperator'> selected </s:if> >
										 <s:property value="#oneRows.operationName"/>
										 </option>
									</s:iterator>
							  </select>
					<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
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
	    <table class="list" width="100%" layoutH="90" >
	    <thead>
	    	<tr>
	    	    <th colspan="8" style="text-align:center">
	    	    	<s:text name="statistical_time_range" />：<s:property value="beginDate"/> 00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
	    	    </th>
	    	</tr>
	    	<tr>
	    	    <th><s:text name="date" /></th>
	    	    <th><s:text name="account_number" /></th>
	    	    <th><s:text name="number_of_roles" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="loginReport" var="oneRow">
	    		<tr >
		    	    <td>
						  	<s:date name="#oneRow.loginTime" format="yyyy-MM-dd"/>&nbsp;
		    	    </td>
		    	    <td>
						  	<s:property value="#oneRow.loginNum"/>&nbsp;
		    	    </td>
		    	    
		    	    <td>
		    	    	<s:property value="#oneRow.playerNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	<tr>
				<td><s:text name="total" /></td>	  
				<td> <s:property value="loginSum.sumLoginName"/></td>
				<td> <s:property value="loginSum.sumPlayerBaseId"/></td>  		
	    	</tr>
	    	</tbody>
	    </table>
	 </div>
   
