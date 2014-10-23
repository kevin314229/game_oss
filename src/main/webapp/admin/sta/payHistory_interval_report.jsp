<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%-- <script  type="text/javascript">

	function selectServer(id) {
		window.parent.location.href = "<%=basePath %>/admin/selectOssServer.action?id="+id; 
	}
	
	function selectServerPt(id) {
		window.parent.location.href = "<%=basePath %>/admin/selectOssServerPt.action?serverCode="+id; 
	}
	
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
  			$("#ossServerId").append("<option value='-1'>--%{getText('all_servers')}--</option>");
  			//checkStut();
  			return;
  		}
 		$.ajax({
            type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
            dataType: "json",//<s:text name="return" />json<s:text name="data_format" />
            url: "<%=basePath%>/admin/base/payHistory_queryOssServerList.action",
            cache: false,
            data: {
            	"operationId":obj,
            	"gameId":$("#gameId").val()
            } ,//<s:text name="data_to_be_sent" />
            success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
            	var myobj=eval(msg);
            	for(var i=0;i<myobj.length;i++){
            		$("#ossServerId").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
            	}
            }
	   });
	} 
</script> --%>
  <s:include value="select.jsp"/>
  <s:include value="/admin/template/changeCSS.jsp"/>
  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="payHistory_interval" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
	
		   <div class="searchBar">
		   	 <div class="searchDiv_left">
		   <s:hidden name="gameId" id="pay_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptCode" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="pay_operationId"  onchange="initPtCodeParam('pay_gameId',this.value,'pay_ossServerId');"></s:select> 
					<s:text name="server" />：
			<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="pay_ossServerId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> 
		   	 <s:text name="from_the_platform" />：<select name="ptId">
								<option value="">--<s:text name="all_platforms" />--</option>
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
	    <table class="list" width="100%" layoutH="90" >
	    <thead>
	    	<tr>
	    	    <th colspan="5" style="text-align:center">
	    	    	<s:text name="statistical_time_range" />：<s:property value="beginDate"/> 00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
	    	    </th>
	    	</tr>
	    	 <tr>
	    	    <th><s:text name="top-up_interval" /> </th>
	    	    <th><s:text name="prepaid_phone_number" /> </th>
	    	    <th><s:text name="prepaid_phone_users" /> </th>
	    	    <th>ARPU</th>
	    	    <th><s:text name="top-up_amount" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="payHistoryStaList" var="oneStat"  status="offset">
	    		<tr>
	    		<td>
	    		<s:property value="interval"/>
	    		</td>
	    	    <td><s:property value="payNum"/> </td>
	    	    <td><s:property value="payPlayerNum"/></td>
	    	    <td><s:property value="arpu"/></td>
	    	    <td><s:property value="amountSum"/></td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    	</tbody>
	    </table>
	 </div>
   
