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
  <META HTTP-EQUIV="Pragma" CONTENT="no-cache">

<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">

<META HTTP-EQUIV="Expires" CONTENT="0"> 
    <base href="<%=basePath%>">
    
    <title>实习监控</title>
    
    <!--[if lt IE 9]><script language="javascript" type="text/javascript" src="<%=basePath%>/media/default/js/jqplot/excanvas.js"></script><![endif]-->
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>

	
	
	<script src="<%=basePath%>/media/default/js/highcharts/highcharts.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/highcharts/modules/exporting.js"  type="text/javascript"></script>


<script type="text/javascript">

//主题
Highcharts.theme = {
   colors: ["#DDDF0D", "#55BF3B", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee", 
      "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
   chart: {
      backgroundColor: {
         linearGradient: [0, 0, 250, 500],
         stops: [
            [0, 'rgb(48, 96, 48)'],
            [1, 'rgb(0, 0, 0)']
         ]
      },
      borderColor: '#000000',
      borderWidth: 2,
      className: 'dark-container',
      plotBackgroundColor: 'rgba(255, 255, 255, .1)',
      plotBorderColor: '#CCCCCC',
      plotBorderWidth: 1
   },
   title: {
      style: {
         color: '#C0C0C0',
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
      gridLineColor: '#333333',
      gridLineWidth: 1,
      labels: {
         style: {
            color: '#A0A0A0'
         }
      },
      lineColor: '#A0A0A0',
      tickColor: '#A0A0A0',
      title: {
         style: {
            color: '#CCC',
            fontWeight: 'bold',
            fontSize: '12px',
            fontFamily: 'Trebuchet MS, Verdana, sans-serif'

         }            
      }
   },
   yAxis: {
      gridLineColor: '#333333',
      labels: {
         style: {
            color: '#A0A0A0'
         }
      },
      lineColor: '#A0A0A0',
      minorTickInterval: null,
      tickColor: '#A0A0A0',
      tickWidth: 1,
      title: {
         style: {
            color: '#CCC',
            fontWeight: 'bold',
            fontSize: '12px',
            fontFamily: 'Trebuchet MS, Verdana, sans-serif'
         }            
      }
   },
   legend: {
      itemStyle: {
         font: '9pt Trebuchet MS, Verdana, sans-serif',
         color: '#A0A0A0'
      }
   },
   tooltip: {
      backgroundColor: 'rgba(0, 0, 0, 0.75)',
      style: {
         color: '#F0F0F0'
      }
   },
   toolbar: {
      itemStyle: { 
         color: 'silver'
      }
   },
   plotOptions: {
      line: {
         dataLabels: {
            color: '#CCC'
         },
         marker: {
            lineColor: '#333'
         }
      },
      spline: {
         marker: {
            lineColor: '#333'
         }
      },
      scatter: {
         marker: {
            lineColor: '#333'
         }
      }
   },      
   legend: {
      itemStyle: {
         color: '#CCC'
      },
      itemHoverStyle: {
         color: '#FFF'
      },
      itemHiddenStyle: {
         color: '#444'
      }
   },
   credits: {
      style: {
         color: '#666'
      }
   },
   labels: {
      style: {
         color: '#CCC'
      }
   },
   
   navigation: {
      buttonOptions: {
         backgroundColor: {
            linearGradient: [0, 0, 0, 20],
            stops: [
               [0.4, '#606060'],
               [0.6, '#333333']
            ]
         },
         borderColor: '#000000',
         symbolStroke: '#C0C0C0',
         hoverSymbolStroke: '#FFFFFF'
      }
   },
   
   exporting: {
      buttons: {
         exportButton: {
            symbolFill: '#55BE3B'
         },
         printButton: {
            symbolFill: '#7797BE'
         }
      }
   },
   
   // special colors for some of the
   legendBackgroundColor: 'rgba(0, 0, 0, 0.5)',
   legendBackgroundColorSolid: 'rgb(35, 35, 70)',
   dataLabelsColor: '#444',
   textColor: '#C0C0C0',
   maskColor: 'rgba(255,255,255,0.3)'
};

// Apply the theme
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);


//日期格式
Highcharts.setOptions({
   global: {
      useUTC: false
   }
});

	var chart;

	

	 
	 
$(document).ready(function() {
   chart = new Highcharts.Chart({
      chart: {
         renderTo: 'container',
         defaultSeriesType: 'spline',
         marginRight: 10,
         events: {
            load: function() {
   
               // set up the updating of the chart each second
               var series = this.series[0];
               setInterval(function() {
                  var x = (new Date()).getTime(); // current time
                      $.getJSON('<%=basePath%>/admin/base/json/realTimeMonitor.action?t='+Math.random(), function(data) {
		   			       var y = data.cpuRatio;
		   			       series.addPoint([x, y], true, true);
					  });
               }, 2000);
            }
         }
      },
      title: {
         text: 'CPU'
      },
      xAxis: {
      	 title:{text:'Time'},
         type: 'datetime',
         tickPixelInterval: 150
      },
      yAxis: {
         title: {
            text: 'Percentage (%)'
         },
         plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
         }]
      },
      tooltip: {
         formatter: function() {
                   return '<b>'+ this.series.name +'</b><br/>'+
               Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+ 
               Highcharts.numberFormat(this.y, 2);
         }
      },
      legend: {
         enabled: false
      },
      exporting: {
         enabled: false
      },
      series: [{
         name: 'Memory data',
         data: (function() {
            // generate an array of random data
            var data = [],
               time = (new Date()).getTime(),
               i;
            //初始化前加载一段无用数据，平缓曲线
            for (i = -20; i <= 0; i++) {
               data.push({
                  x: time + i * 1000,
                  y: 0
               });
            }
            return data;
         })()
      }]
   });

   
   
});

</script>

	


	
	
	
  </head>
  
  <body > 

	


  	<div id="divLeft">
		<b>实时监控</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
		
	</div>
	<div id="divLeft"> 
		
	</div>
	
	<div id="divList"> 
	  <div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>
	  
	 
	</div>
   <div id="bottomPager"></div>
 
  </body>
</html> 