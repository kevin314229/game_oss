<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ include file="select.jsp" %>
<s:include value="/admin/template/changeCSS.jsp"/>
<script type="text/javascript">

    	$(function () {
    	
		    var dataObject = '<s:property value="jsonString" escape="false" />';
		    var categories = '<s:property value="categories" escape="false" />';
		   	var days=<s:property value="days" escape="false" />;
		    dataObject =(new Function("","return "+dataObject))();
		    categories=(new Function("","return "+categories))();
		    var width=days*100;
		    if(width<1000){
		    	width=1000;
		    }
		    
	        $('#pay_charContainer').highcharts({
            chart: {
                type: 'line',
                width: width,
            },
            title: {
                text: ''
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                min: 0,
                title: {
                    text: '<s:text name="recharge_amount" />'
                },
                stackLabels: {
                    enabled: false,
                    style: {
                        fontWeight: 'bold',
                        color: '#808080'
                    }
                }
            },
           
            legend: {
                align: 'right',
                x: 1,
                verticalAlign: 'top',
                y: 1,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
                
            },
            tooltip: {
                formatter: function() {
                    return this.y;
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true,
                        style: {
                            textShadow: '0 0 3px white, 0 0 3px white'
                        }
                    },
                    enableMouseTracking: false
                }
            },
            series: dataObject
        });
	        
	    });
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="payReport_queryAreaRecordIndex" rel="pagerForm" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar" >
		   <div  class="searchDiv_left">
		    		<s:hidden name="gameId" id="paySta_gameId"/>
		  		<%--  <s:text name="item" /> <s:text name="eye" />：
			<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="paySta_gameId"  onchange="changePtCodeOption(this.value,'paySta_ptId','paySta_areaId');"></s:select> --%>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptId" headerKey="" 
				headerValue="%{getText('all_platforms')}"   
				id="paySta_ptId"  onchange="initPtCodeParam('paySta_gameId',this.value,'paySta_areaId');"></s:select> 
					<s:text name="server" />：
				 
				<!--	<select id="areaId" name="areaId" onchange="checkStutKPI()"  multiple="true" size="5" >
						    <option value="0" >--%{getText('all_servers')}--</option>
							<s:iterator value="ossServerList" var="oneRow"  status="offset">
										<option value="<s:property value="#oneRow.id" />"  > 	
						    				<s:property value="#oneRow.name"/>
						    			</option>
					    	</s:iterator>
					</select>   -->
						  <s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="paySta_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> 
			 <s:text name="start_time" />：	<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date" value="%{beginDate}"></s:textfield>
				<s:text name="end_time" />： <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
		  </div>
	   <div class="searchButton">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div>
			</div>
	 </s:form>
	</div>

	<div class="pageContent sortDrag"  layoutH="110" selector="h1">
		<div class="panel  ">
			<h1><s:text name="recharge_statistics" /></h1>
			<div id="pay_charContainer" style="min-width: 400px; height: 600px; margin: 0 auto"></div>
		</div>
	 </div>
