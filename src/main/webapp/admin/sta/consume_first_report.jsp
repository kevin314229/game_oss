<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ include file="select.jsp" %>
<s:include value="/admin/template/changeCSS.jsp"/>
<script type="text/javascript">
	$(function() {
		var data1 = [];
		var data2 = [];
		var data3 = [];
		<s:set name="tmp" value="@com.jcwx.game.common.JSONService@JSONListToString(levelJsonList)"></s:set> 
		var json = <s:property value="#tmp" escape="false"/>;
		if(json!=null && json!=""){
			 $.each(json, function(i, n){
				 data1.push([n.level+"<s:text name="level" />", n.levelNum]);
			 });
			 $('#container1').highcharts({
		            chart: {
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: ' <s:text name="grade_distribution" />  '
		            },
		            tooltip: {
		        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
		            	percentageDecimals: 1
		            },
		            plotOptions: {
		                pie: {
		                    allowPointSelect: true,
		                    cursor: 'pointer',
		                    dataLabels: {
		                        enabled: true,
		                        color: '#000000',
		                        connectorColor: '#000000',
		                        formatter: function() {
		                        	return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage,2) +' %  ' + this.y+"<s:text name="people" />";  
		                        }
		                    }
		                }
		            },
		            series: [{
		                type: 'pie',
		                name: 'precentage',
		                data: data1
		            }]
		        });
		}
		
		<s:set name="tmp2" value="@com.jcwx.game.common.JSONService@JSONListToString(operationJsonList)"></s:set> 
		var json2 = <s:property value="#tmp2" escape="false"/>;
		if(json2!=null && json2!=""){
			 $.each(json2, function(i, n){
				 //var operation =<dict:itemvalue gameId="2" type="1"  itemValue="" />;
				 data2.push([n.operation+"", n.operationNum]);
			 });
						
			 $('#container2').highcharts({
		            chart: {
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: '<s:text name="consumer_point_of_distribution" />'
		            },
		            tooltip: {
		        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
		            	percentageDecimals: 1
		            },
		            plotOptions: {
		                pie: {
		                    allowPointSelect: true,
		                    cursor: 'pointer',
		                    dataLabels: {
		                        enabled: true,
		                        color: '#000000',
		                        connectorColor: '#000000',
		                        formatter: function() {
		                            return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage,2) +'% '+ this.y +"<s:text name="people" />";  
		                        }
		                    }
		                }
		            },
		            series: [{
		                type: 'pie',
		                name: 'num',
		                data: data2
		            }]
		        });
		} 
		
		<s:set name="tmp3" value="@com.jcwx.game.common.JSONService@JSONListToString(daysJsonList)"></s:set> 
		var json3 = <s:property value="#tmp3" escape="false"/>;
		if(json3!=null && json3!=""){
			 $.each(json3, function(i, n){
				 data3.push([n.fromFirstDays+"<s:text name="day" />", n.fromFirstDaysNum]);
			 });
						
			 $('#container3').highcharts({
		            chart: {
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: ' <s:text name="the_number_of_days_from_the_first_landing_maps" />'
		            },
		            tooltip: {
		        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
		            	percentageDecimals: 1
		            },
		            plotOptions: {
		                pie: {
		                    allowPointSelect: true,
		                    cursor: 'pointer',
		                    dataLabels: {
		                        enabled: true,
		                        color: '#000000',
		                        connectorColor: '#000000',
		                        formatter: function() {
		                            return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage,2) +'% '+ this.y +"<s:text name="people" />";  
		                        }
		                    }
		                }
		            },
		            series: [{
		                type: 'pie',
		                name: 'num',
		                data: data3
		            }]
		        });
		} 
		
	});
	
	function optionChangeConsumeFirst(obj){
		$("#first_areaId").empty();
		$.ajax({
        type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
        url: "<%=basePath%>/admin/kpiReport/query_OssServerListByServerCode.action",
        cache: false,
        global:false,
        data: "ptId="+obj,//<s:text name="data_to_be_sent" />
        success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
        	var myobj=eval(msg);
        	$("#first_areaId").append("<option value='-1'>%{getText('all_servers')}</option>");
        	for(var i=0;i<myobj.length;i++){
        		$("#first_areaId").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
        	}
        	checkStutConsumeData();
        },
        error:function(msg1,text){
        	alert(msg1+text);
        }
        
   });
}

function checkStutConsumeFirst(){
	if($("#first_areaId").val()==-1){
		document.getElementById("nickName").disabled=true;
    	document.getElementById("loginName").disabled=true;
    	document.getElementById("questionStatus").disabled=true;
	}else{
		document.getElementById("nickName").disabled=false;
    	document.getElementById("loginName").disabled=false;
    	document.getElementById("questionStatus").disabled=false;
	}
}

function expFirstCSV(){
	
	var url = "<%=basePath%>/admin/consume/query_consume_first.action";
	queryFirstForm.action=url;
	queryFirstForm.submit();
  	queryFirstForm.action= "<%=basePath%>/admin/consume/query_consume_first_data_list.action";

}
	
	
	
</script>



<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
 
<div class="pageHeader">
	<s:form action="query_consume_first_data_list"  rel="pagerForm" method="post" theme="simple" id="queryFirstForm" name="queryFirstForm" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   
		   <div class="searchBar">
		  	 <div class="searchDiv_left">
				 <%-- 
			 		 <s:text name="item" /> <s:text name="eye" />：
			<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="first_gameId"  onchange="changePtCodeOption(this.value,'first_ptId','first_areaId');"></s:select> --%>
					<s:hidden name="gameId" id="first_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptId" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="first_ptId"  onchange="initPtCodeParam('first_gameId',this.value,'first_areaId');"></s:select> 
					<s:text name="server" />：
			<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="first_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> 
			 	<s:text name="start_time" />：	<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date" value="%{beginDate}"></s:textfield>
				<s:text name="end_time" />： <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
	    		<s:text name="login_name" />：	<s:textfield id="loginName" name="loginName" size="12" ></s:textfield>
				<s:text name="role_name" />：	<s:textfield id="nickName" name="nickName"  size="12"></s:textfield>
	    	</div>
	    		 <div class="searchButton">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="inquiry" /></button></div></div></li>
					<li> <div class="buttonActive" style="margin-left:5px"><div class="buttonContent"><button type="button" onclick="expFirstCSV()"><s:text name="download" /></button></div></div></li>
				
				</ul>
			</div>
			</div>
	 </s:form>
</div>


<div layoutH="60">
<div class="pageContent  "  >
		<div class="panel  ">
			<h1><s:text name="players_for_the_first_time_consumption_data" /></h1>
	    <table class="list" width="100%"  >
	    <thead>
			<tr >
			<th>  <s:text name="date" /> </th>
			<th><s:text name="game" />ID</th>
			<th><s:text name="platform" /></th>
			<th style="color: "><s:text name="server" />ID</th>
			<th> <s:text name="user_account" />（<s:text name="login_name" />）</th>
			<th><s:text name="role" />ID</th>
			<th><s:text name="role_name" /></th>
			<th  ><s:text name="character_level" /></th>
			<th><s:text name="point_of_consumption" /></th>
			<th><s:text name="use" /></th>
			<th><s:text name="the_amount_of_consumption" /></th>
			<th><s:text name="date_of_first_landing" /></th>
			<th><s:text name="climb_from_the_first_few" /></th>
			</tr>
			</thead>
			<tbody>
		<s:iterator value="consumeFirstDatas" status="offsets" var="oneRow">
			<tr>
			<td>
					<s:date format="yyyy-MM-dd" name="#oneRow.consumeDate"/>
				</td>
				<td>
				 <dict:itemvalue gameId="${oneRow.gameId }" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
				</td>
				<td>
				 <dict:itemvalue gameId="${oneRow.gameId }" type="4"  itemValue="${oneRow.ptId }"></dict:itemvalue>
				</td>
				<td>
				 <dict:itemvalue gameId="${oneRow.gameId }" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				 </td>
				<td><s:property value="loginName" /> </td>
				<td><s:property value="playerBaseId" /> </td>
				<td><s:property value="nickName" /> </td>
				<td  ><s:property value="level" /> </td>
				<td> 
				<s:property value="operation" />
				<%-- <s:property value="@com.jcwx.game.common.constants.OperationLogConstant@maptype[#oneRow.operation]"/> --%>
				</td>
				  <td>
		    	    	<s:property value="#oneRow.operationDetail"/>
		    	    </td>
				<td><s:property value="moneyNum" /></td>
				<td><s:property value="firstLoginTime" /> </td>
				<td><s:property value="fromFirstDays" /> </td>
			</tr>
		</s:iterator>
			<tbody>
		</table>
		  <s:include value="/admin/template/paging.jsp"/>
		</div>
		</div>
	
	<div class="pageContent" style="height:490px">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li class=""><a href="javascript:;"><span><s:text name="grade_distribution" /></span></a></li>
					<li><a href="javascript:;"><span><s:text name="consumer_point_of_distribution" /></span></a></li>
					<li><a href="javascript:;" ><span><s:text name="time_distribution_from_the_first_ascent" /></span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:400px;">
			<div id="container1" style="min-width: 400px; height: 400px; margin: 0 auto">    </div>
			<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto"></div>	
			<div id="container3" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
	<p>&nbsp;</p>
	
</div>
</div>
