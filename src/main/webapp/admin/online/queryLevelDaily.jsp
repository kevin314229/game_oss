<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script type="text/javascript">
	$(function () {
		
		    var dataObject = '<s:property value="data" escape="false" />';
		    
		    dataObject =(new Function("","return "+dataObject))();
		    
	        $('#highcharContainer').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '<s:text name="the_daily_grade_distribution" />'
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
                    return '<b>'+ this.x +'<s:text name="level" /></b><br/>'+
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
	</script>
	
	
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form action="/admin/online/queryLevelDaily.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
						<div class="searchDiv_left">
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
	
	<div class="pageContent sortDrag"  layoutH="35" selector="h1">
		<div class="panel collapse">
			<h1><s:text name="daily_grade_distribution" /></h1>
			<div id="highcharContainer" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		</div>
		<div class="panel collapse">
			<h1><s:text name="daily_level_analysis" /></h1>
			<div>
			    <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    	    <th width="300">Id</th>
			    	    <th><s:text name="time" />(<s:text name="day" />)</th> 
			    	    <th><s:text name="players_level" /></th> 
			    	    <th><s:text name="the_number_of" /> </th> 
			    	</tr>
			    	</thead>
			    	<tbody>
			    	<s:iterator value="curAssayList" var="oneRow"  status="offset">
			    		<tr >
				    	    <td>
				    	    	<s:property value="#oneRow.id"/>
				    	    </td>
				    	     <td>
				    	    	<s:date format="yyyy-MM-dd" name="#oneRow.idDate"/>
				    	    </td>
				    	    <td>
				    	    	<s:property value="#oneRow.level"/>
				    	    </td>
				    	    <td>
				    	    	<s:property value="#oneRow.peopleNum"/>
				    	    </td>
				    	</tr>
			    	</s:iterator>
			    	</tbody>
			    </table>
		    </div>
	    </div>
	</div>
	
