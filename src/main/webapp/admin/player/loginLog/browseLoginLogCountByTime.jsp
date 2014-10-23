<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>

  <s:include value="/admin/template/scriptMessage.jsp"/>
  <script type="text/javascript" language="javascript">
    	
  $(function () {
		
	  $(function () {
			
		    var dataObject = '<s:property value="data" escape="false" />';
		    var dataDay = '<s:property value="data" escape="false" />';
		    var queryFlag ='${queryFlag}';
		    dataObject =(new Function("","return "+dataObject))();
		    dataDay=(new Function("","return "+dataDay))();
		    var text="";
		    if(queryFlag=="byMonth"){
		    	 dataObject[0].name='<s:text name="in_section" />';
		    	 text='<s:text name="in_section" />';
		    }else if(queryFlag=="byDay"){
		    	 initDayChat(dataDay);
		    	 dataObject[0].name='<s:text name="day_section" />';
		    	 text='<s:text name="day_section" />';
		    	
		    }else if(queryFlag=="byYear"){
		    	 dataObject[0].name='<s:text name="in_section_rewrite_key" />';
		    	 text='<s:text name="in_section_rewrite_key" />';
		    }
		    
	        $('#loginLogContainer').highcharts({
          chart: {
              type: 'column'
          },
          title: {
              text: text+"(${beginDate}"+"--"+"${endDate})"
          },
          xAxis: {
              categories: []
          },
          yAxis: {
              min: 0,
              title: {
                  text: '<s:text name="the_number_of" />'
              },
              stackLabels: {
                  enabled: false,
                  style: {
                      fontWeight: 'bold',
                      color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                  }
              }
          },
          legend: {
              align: 'right',
              x: -100,
              verticalAlign: 'top',
              y: 20,
              floating: true,
              backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
              borderColor: '#CCC',
              borderWidth: 1,
              shadow: false
          },
          tooltip: {
              formatter: function() {
                  return     '<b></b> '+
                  this.series.name +'  '+ this.y +'<s:text name="people" /><br/>';
              }
          },
          plotOptions: {
              column: {
                  dataLabels: {
                      enabled: true
                  }
              }
          },
          series: dataObject
      });
	        
	    });
      
  });
  
  function initDayChat(data){
	  $("#dayContainer")[0].style.display="block";
	  data[0].name='precentage';
	  data[0].type='pie';
	  $('#loginLogDayContainer').highcharts({
          chart: {
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false
          },
          title: {
              text: ' <s:text name="day_section" />  '
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
          series: data
      });
  }
	</script>
<div class="pageHeader">
	<s:form rel="pagerForm" action="/admin/base/loginStatistics_browseLoginLogCountByTime.action" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
				<div class="searchDiv_left">
						<s:text name="a_query" />：<s:select list="#{'byMonth': getText('in_section'),'byDay': getText('day_section'),'byYear': getText('in_section_rewrite_key')}" label="" listKey="key" listValue="value" name="queryFlag"></s:select>
	
						<s:text name="statistical_time" />：
						<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
			
						<s:text name="to" /> 
						<s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
				</div>
	    	<div class="searchButton2">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
	</div>
	<div class="pageContent" style="height:440px"> 
		  <%--   <img src="<%=basePath %>/<s:property value="imgSrc"/>" width="900" height="450"/>
		    <s:if test="imgSrc2!=null && imgSrc2!=''">
		    	<br/>
		    	<img src="<%=basePath %>/<s:property value="imgSrc2"/>" />
		    </s:if> --%>
		    <div>
			<div id="loginLogContainer" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		</div>
	 </div>
	 
	 <div class="pageContent" layoutH="442" id="dayContainer" style="display:none"> 
		  <%--   <img src="<%=basePath %>/<s:property value="imgSrc"/>" width="900" height="450"/>
		    <s:if test="imgSrc2!=null && imgSrc2!=''">
		    	<br/>
		    	<img src="<%=basePath %>/<s:property value="imgSrc2"/>" />
		    </s:if> --%>
		    <div>
			<div id="loginLogDayContainer" style="min-width: 400px; height: 360px; margin: 0 auto"></div>
		</div>
	 </div>
	 
	<div class="panel">
	 
	 
	</div>
