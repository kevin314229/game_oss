<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ include file="select.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<s:include value="/admin/template/changeCSS.jsp"/>
<script type="text/javascript">


function optionChangeConsumeData(obj){
		$("#consume_areaId").empty();
		$.ajax({
        type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
        url: "<%=basePath%>/admin/kpiReport/query_OssServerListByServerCode.action",
        cache: false,
        global:false,
        data: "ptId="+obj,//<s:text name="data_to_be_sent" />
        success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
        	var myobj=eval(msg);
        	$("#consume_areaId").append("<option value='-1'>%{getText('all_servers')}</option>");
        	for(var i=0;i<myobj.length;i++){
        		$("#consume_areaId").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
        	}
        	checkStutConsumeData();
        },
        error:function(msg1,text){
        	alert(msg1+text);
        }
        
   });
}

function checkStutConsumeData(){
	if($("#consume_areaId").val()==-1){
		document.getElementById("nickName").disabled=true;
    	document.getElementById("loginName").disabled=true;
    	document.getElementById("questionStatus").disabled=true;
	}else{
		document.getElementById("nickName").disabled=false;
    	document.getElementById("loginName").disabled=false;
    	document.getElementById("questionStatus").disabled=false;
	}
}

function expCSV(){
	var url = "<%=basePath%>/admin/consume/query_consume_data.action";
	queryDataForm.action=url;
	document.getElementById("queryDataForm").submit();
  	queryDataForm.action= "<%=basePath%>/admin/consume/query_consume_data_list.action";

}
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="query_CycConsumeGoldList" rel="pagerForm" method="post" name="queryDataForm" id="queryDataForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		 <div class="searchDiv_left">
		<%-- 	 <s:text name="item" /> <s:text name="eye" />：
			<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="consume_gameId"  onchange="changePtCodeOption(this.value,'consume_ptId','consume_areaId');"></s:select> --%>
				
			<s:hidden name="gameId" id="consume_cycle_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptId" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="consume_cycle_ptId"  onchange="initPtCodeParam('consume_cycle_gameId',this.value,'consume_cycle_areaId');"></s:select> 
					<s:text name="server" />：
			<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="consume_cycle_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> 
			 <s:text name="start_time" />：	<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date" value="%{beginDate}"></s:textfield>
			<s:text name="end_time" />： <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
	 		查询周期:	<select name="cycle">
	 			<option value="1">第一周期</option>
	 			<option value="2">第二周期</option>
	 			<option value="3">第三周期</option>
	 			<option value="4">第四周期</option>
	 		</select>
	</div>
				 	
		 <div class="searchButton">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div>
		 
		</div>
	</s:form>
</div>

<div class="pageContent">
	    <table class="list" width="100%" layoutH="130">
	    	<thead>
	    	<tr>
	    	    <th width=70>消耗点</th>
	    	    <th width=50>消费元宝数</th>
	    	    <th width=50>消耗占比</th>
	    		 
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<tr>
	    		 <td style="font-weight:bold">
		    	   总消耗元宝：
		    	    </td>
		    	     <td style="font-weight:bold">
					  <s:property value="allConsumeGold"/> 
		    	    </td>
		    	    
		    	     <td>
		    	     
		    	    </td>
		    	</tr>
	    	<s:iterator value="resultList" var="oneRow" status="offset">
	    		<tr>
	    		 <td>
		    	    <s:property value="#oneRow.operation"/>	 
		    	    </td>
		    	     <td>
						<s:property value="#oneRow.consumeNum"/>
		    	    </td>
		    	    
		    	     <td>
		    	     <s:if test="allConsumeGold==0||allConsumeGold==null" >0.0</s:if>
		    	     <s:else>
		    	    <fmt:formatNumber value="${oneRow.consumeNum/allConsumeGold}" type="percent" pattern="#0.00%"  /> 
		    	     </s:else>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
</div>