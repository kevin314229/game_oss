<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 1); 
%>

<html> 
  <head>
    <base href="<%=basePath%>">
    
    <title>分时段分析内存记录</title>
    
    
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>

	
	<script src="<%=basePath%>/media/default/js/jquery.ui.core.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/jquery.ui.widget.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/jquery.ui.datepicker.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/jquery.pager.js"  type="text/javascript"></script>
	
	<script src="<%=basePath%>/media/default/js/highcharts/highcharts.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/highcharts/modules/exporting.js"  type="text/javascript"></script>
	
	
	
	
	
	
	
	
	
 
	
	
	<script type="text/javascript" language="javascript">
	
		$(function() {
			$("#beginDate").datepicker({dateFormat:"yy-mm-dd",prevText:'上个月',nextText:'下个月',dayNames:['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],dayNamesMin:['日', '一', '二', '三', '四', '五', '六'],monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']});
			$("#beginDate").datepicker('setDate', '<s:property value="beginDate"/>' );
			$("#endDate").datepicker({dateFormat:"yy-mm-dd",prevText:'上个月',nextText:'下个月',dayNames:['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],dayNamesMin:['日', '一', '二', '三', '四', '五', '六'],monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']});
			$("#endDate").datepicker('setDate', '<s:property value="endDate"/>' );
		});
		
		
		
        
        function queryAllPlayer(){
        	$("#currPageNO").val(1);
            $("#baseFrom1").submit();
        }
	</script>
	
<script language="javascript" type="text/javascript">
Highcharts.theme = {
   colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
   chart: {
      backgroundColor: {
         linearGradient: [0, 0, 500, 500],
         stops: [
            [0, 'rgb(255, 255, 255)'],
            [1, 'rgb(240, 240, 255)']
         ]
      }
,
      borderWidth: 2,
      plotBackgroundColor: 'rgba(255, 255, 255, .9)',
      plotShadow: true,
      plotBorderWidth: 1
   },
   title: {
      style: { 
         color: '#000',
         font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
      }
   },
   subtitle: {
      style: { 
         color: '#666666',
         font: 'bold 12px "Trebuchet MS", Verdana, sans-serif'
      }
   },
   xAxis: {
      gridLineWidth: 1,
      lineColor: '#000',
      tickColor: '#000',
      labels: {
         style: {
            color: '#000',
            font: '11px Trebuchet MS, Verdana, sans-serif'
         }
      },
      title: {
         style: {
            color: '#333',
            fontWeight: 'bold',
            fontSize: '12px',
            fontFamily: 'Trebuchet MS, Verdana, sans-serif'

         }            
      }
   },
   yAxis: {
      minorTickInterval: 'auto',
      lineColor: '#000',
      lineWidth: 1,
      tickWidth: 1,
      tickColor: '#000',
      labels: {
         style: {
            color: '#000',
            font: '11px Trebuchet MS, Verdana, sans-serif'
         }
      },
      title: {
         style: {
            color: '#333',
            fontWeight: 'bold',
            fontSize: '12px',
            fontFamily: 'Trebuchet MS, Verdana, sans-serif'
         }            
      }
   },
   legend: {
      itemStyle: {         
         font: '9pt Trebuchet MS, Verdana, sans-serif',
         color: 'black'

      },
      itemHoverStyle: {
         color: '#039'
      },
      itemHiddenStyle: {
         color: 'gray'
      }
   },
   labels: {
      style: {
         color: '#99b'
      }
   }
};

// Apply the theme
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);

//.............
//日期格式
Highcharts.setOptions({
   global: {
      useUTC: false
   }
});
var chart;
var chart2;
var chart3;

$(document).ready(function(){



var p1 = [];

var p2 = [];

var p3 = [];
var p4 = [];
var count = 0;
<s:set name="tmp" value="@com.jjl.rzjh.common.JSONService@JSONListToString(jsonList)"></s:set>
var json = <s:property value="#tmp" escape="false"/>;

if(json==null || json==""){
	
}else{
	
	var str = "";
	
	for (var one in json){
	        var date = json[one]["d"];
	        //Tomcat已使用
	        //var tem1 = (json[one]["totalMemory"] - json[one]["freeMemory"])/1000;
	       p1.push([date,(json[one]["tm"])/1000]);;
	        //Tomcat已分配
	        p2.push([date,(json[one]["osm"])/1000]);
	        p3.push([date,(json[one]["c"])]);
	        count++;
	}
	
	 
	//alert(count);
	//....................................................
	
	chart = new Highcharts.Chart({
      chart: {
         renderTo: 'container',
         zoomType: 'x',
         spacingRight: 20
      },
       title: {
         text: 'Tomcat内存记录分析'
      },
       subtitle: {
         text: document.ontouchstart === undefined ?
            'Click and drag in the plot area to zoom in' :
            'Drag your finger over the plot to zoom in'
      },
      xAxis: {
         type: 'datetime',
         maxZoom: 14 * 24 * 3600000, // fourteen days
         title: {
            text: 'Time'
         },
          formatter: function() {
                return Highcharts.dateFormat('%H:%M:%S', this.x); 
            }
         
         
         
      },
      yAxis: {
         title: {
            text: 'Memory (M)'
         },
         min: 0,
         startOnTick: false,
         showFirstLabel: false
      },
      tooltip: {
        // shared: true,
          formatter: function() {
                    return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+ Highcharts.numberFormat(this.y, 2);
         }             
      },
      legend: {
         enabled: false
      },
      plotOptions: {
         area: {
            fillColor: {
               linearGradient: [0, 0, 0, 300],
               stops: [
                  [0, Highcharts.theme.colors[0]],
                  [1, 'rgba(2,0,0,0)']
               ]
            },
            lineWidth: 1,
            marker: {
               enabled: false,
               states: {
                  hover: {
                     enabled: true,
                     radius: 5
                  }
               }
            },
            shadow: false,
            states: {
               hover: {
                  lineWidth: 1                  
               }
            }
         }
      },
   
      series: [{
         type: 'area',
         name: 'USD to EUR',
         pointInterval: 24 * 3600 * 1000,
         //pointStart: Date.UTC(2006, 0, 01),
         data: p1
      }]
   });
	
	//........OS............................................
	chart2 = new Highcharts.Chart({
      chart: {
         renderTo: 'container2',
         zoomType: 'x',
         spacingRight: 20
      },
       title: {
         text: 'OS内存记录分析'
      },
       subtitle: {
         text: document.ontouchstart === undefined ?
            'Click and drag in the plot area to zoom in' :
            'Drag your finger over the plot to zoom in'
      },
      xAxis: {
         type: 'datetime',
         maxZoom: 14 * 24 * 3600000, // fourteen days
         title: {
            text: 'Time'
         },
          formatter: function() {
                return Highcharts.dateFormat('%H:%M:%S', this.x); 
            }
         
         
         
      },
      yAxis: {
         title: {
            text: 'Memory (M)'
         },
         min: 0,
         
         startOnTick: false,
         showFirstLabel: false
      },
      tooltip: {
        // shared: true,
          formatter: function() {
                    return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+ Highcharts.numberFormat(this.y, 2);
         }             
      },
      legend: {
         enabled: false
      },
      plotOptions: {
         area: {
            fillColor: {
               linearGradient: [0, 0, 0, 300],
               stops: [
                  [0, Highcharts.theme.colors[0]],
                  [1, 'rgba(2,0,0,0)']
               ]
            },
            lineWidth: 1,
            marker: {
               enabled: false,
               states: {
                  hover: {
                     enabled: true,
                     radius: 5
                  }
               }
            },
            shadow: false,
            states: {
               hover: {
                  lineWidth: 1                  
               }
            }
         }
      },
   
      series: [{
         type: 'area',
         name: 'USD to EUR',
         pointInterval: 24 * 3600 * 1000,
         //pointStart: Date.UTC(2006, 0, 01),
         data: p2
      }]
   });
   
   //-----------cpu-------------------
   	chart3 = new Highcharts.Chart({
      chart: {
         renderTo: 'container3',
         zoomType: 'x',
         spacingRight: 20
      },
       title: {
         text: 'CPU记录分析'
      },
       subtitle: {
         text: document.ontouchstart === undefined ?
            'Click and drag in the plot area to zoom in' :
            'Drag your finger over the plot to zoom in'
      },
      xAxis: {
         type: 'datetime',
         maxZoom: 14 * 24 * 3600000, // fourteen days
         title: {
            text: 'Time'
         },
          formatter: function() {
                return Highcharts.dateFormat('%H:%M:%S', this.x); 
            }
         
         
         
      },
      yAxis: {
         title: {
            text: 'percentage (%)'
         },
         min: 0,
         max:100,
         startOnTick: false,
         showFirstLabel: false
      },
      tooltip: {
        // shared: true,
          formatter: function() {
                    return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+ Highcharts.numberFormat(this.y, 2);
         }             
      },
      legend: {
         enabled: false
      },
      plotOptions: {
         area: {
            fillColor: {
               linearGradient: [0, 0, 0, 300],
               stops: [
                  [0, Highcharts.theme.colors[0]],
                  [1, 'rgba(2,0,0,0)']
               ]
            },
            lineWidth: 1,
            marker: {
               enabled: false,
               states: {
                  hover: {
                     enabled: true,
                     radius: 5
                  }
               }
            },
            shadow: false,
            states: {
               hover: {
                  lineWidth: 1                  
               }
            }
         }
      },
   
      series: [{
         type: 'area',
         name: 'USD to EUR',
         pointInterval: 24 * 3600 * 1000,
         //pointStart: Date.UTC(2006, 0, 01),
         data: p3
      }]
   });




} 
    
});
</script>
	
	
	
  </head>
  
  <body > 

	


  	<div id="divLeft">
		<b>分时段分析内存记录</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
		
	</div>
	<div id="divLeft"> 
		<s:form action="memoryMonitor_browseMemoryMonitorByTime" namespace="/admin/base" method="post" theme="simple" id="baseFrom1">
		<s:hidden name="currPageNO" id="currPageNO"></s:hidden>
		查询方式：<s:select list="#{'byMin':'每分钟'}" label="" listKey="key" listValue="value" name="queryFlag"></s:select>
		统计时间：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12"></s:textfield>
	    	    	至 <s:textfield id="endDate" name="endDate" maxlength="10" size="12"></s:textfield>
		<s:submit value="查询"></s:submit>
		 </s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList"> 
		<div id="ms">
			<s:if test="hasActionMessages()">
			<span class="color-red"> <img src="<%=basePath %>/media/default/images/sorry.gif"/> <s:actionmessage /> </span>
			</s:if>
		</div>
	    <div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div> 
	    <br/>
	    <div id="container2" style="width: 800px; height: 400px; margin: 0 auto"></div> 
	     <br/>
	    <div id="container3" style="width: 800px; height: 400px; margin: 0 auto"></div> 
	 </div>
   <div id="bottomPager"></div>
 
  </body>
</html>