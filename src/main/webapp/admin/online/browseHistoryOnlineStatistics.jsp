<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
 <script type="text/javascript" language="javascript">
    	
  $(function () {
	
		    var dataObject = '<s:property value="data" escape="false" />';
		  //  var dataDay = '<s:property value="data" escape="false" />';
		  
			   if(dataObject==''){
				  $("#onlineDiv")[0].style.display="none";
				   return;
			   }
		    var queryFlag ='${queryFlag}';
		    dataObject =(new Function("","return "+dataObject))();
		 //   dataDay=(new Function("","return "+dataDay))();
		    var text="";
		    if(queryFlag=="byDay"){
		    	 dataObject[0].name='<s:text name="day_section" />';  
		    	 text='<s:text name="day_section" />';
		    }else if(queryFlag=="byHour"){
		    	// initDayChat(dataDay);
		    	 dataObject[0].name='<s:text name="when_the_segmented" />';
		    	 text='<s:text name="when_the_segmented" />'+"-"+'<s:text name="columnar" />';
		    	
		    }else if(queryFlag=="byHour2"){
		    	
		    	 dataObject[0].name='<s:text name="when_the_segmented" />';
		    	 initDayLineChat(dataObject);
		    	 return;
		    }
		    
	        $('#onlineContainer').highcharts({
          chart: {
              type: 'column'
          },
          title: {
              text: text+"(${beginDate}"+"--"+"${endDate})"
          },
          xAxis: {
              categories: [],
              labels: {
              	rotation: -45,
                align: 'right',
                style: {
                	fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
             }
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
  
  function initDayLineChat(data){
	  text='<s:text name="when_the_segmented" />'+"-"+'<s:text name="curve" />';
	  $('#onlineContainer').highcharts({
	        chart: {
	            type: 'line'
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
	        tooltip: {
	        	 formatter: function() {
	                  return     '<b></b> '+
	                  this.series.name +'  '+ this.y +'<s:text name="people" /><br/>';
	              }
	        },
	        plotOptions: {
	            line: {
	                dataLabels: {
	                    enabled: true
	                },
	                enableMouseTracking: false
	            }
	        },
	        series:  data
	    });
  }
	</script>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>

  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form rel="pagerForm" action="/admin/base/historyOnlineStatistics_browseHistoryOnlineStatistics.action"
	method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
				<div class="searchDiv_left">
					<s:text name="a_query" />： 
					<%-- <s:select list="#{'byDay': getText('day_section')}" label="" listKey="key" listValue="value" name="queryFlag"></s:select>--%>
					<s:select list="#{'byDay': getText('day_section'),'byHour':getText('when_the_segmented')+'-'+ getText('columnar'),'byHour2':getText('when_the_segmented')+'-'+ getText('curve')}" label="" listKey="key" listValue="value" name="queryFlag"></s:select> 
	
					<s:text name="statistical_time" />：
					<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
			
		
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
				</div>
	    	<div class="searchButton2">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
	</div>
	<div  class="pageContent" id="onlineDiv">
	  <div>
			<div id="onlineContainer" style="min-width: 400px; height: 360px; margin: 0 auto"></div>
		</div>
	
	</div>
	<div  class="pageContent">
	   <%--  <img src="<%=basePath %>/<s:property value="imgSrc"/>" />
	    <s:if test="imgSrc2!=null && imgSrc2!=''">
	    	<br/>
	    	<img src="<%=basePath %>/<s:property value="imgSrc2"/>" />
	    </s:if> --%>
	 
	  <table class="list" width="60%" layoutH="490" >
	  <thead>
	  <tr>
	  	<td><s:text name="date" /></td>
	  	<td><s:text name="maximum_number_of_online" /></td>
	  </tr>
	  </thead>
	  <tbody>
	 <s:iterator value="listForTable" var="oneRow"  status="offset"> 
	 	<tr>
			<td>
				<s:property value="#oneRow.d"/>
			</td>
			<td>
			  <fmt:formatNumber value="${num}" type="number" pattern="#,###" />  
			<%--  <s:property value="#oneRow.num"/>   --%> 
			</td>	 	
	 	</tr>
	 </s:iterator>
	 </tbody>
	  </table>
	
	 </div>
