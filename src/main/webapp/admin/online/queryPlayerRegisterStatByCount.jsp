
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
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
  		$("#ossServerIdRegister").empty();
  		
  		if(obj==-1){
  			//$("#ossServerIdRegister").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
  			//checkStut();
  			//return;
  		}
 		$.ajax({
            type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
            dataType: "json",//<s:text name="return" />json<s:text name="data_format" />
            url: "<%=basePath%>/admin/base/ossLog_queryOssServerList.action",
            cache: false,
            data: {
            	"operationId":obj
            } ,//<s:text name="data_to_be_sent" />
            success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
            	//alert("<s:text name="pass_back" />operationId="+obj);
            	var myobj=eval(msg);
            	$("#ossServerIdRegister").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
            	for(var i=0;i<myobj.length;i++){
            		$("#ossServerIdRegister").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
            	}
            }
	   });
	}
 
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
        $('#highcharServer').highcharts({
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
                text: '<s:text name="enrollment" />'
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
    
$(function () {
		
	    var dataObject = '<s:property value="jsonStringPt" escape="false" />';
	    var categories = '<s:property value="categories" escape="false" />';
	   	var days=<s:property value="days" escape="false" />;
	    dataObject =(new Function("","return "+dataObject))();
	    categories=(new Function("","return "+categories))();
	    var width=days*100;
	    if(width<1000){
	    	width=1000;
	    }
        $('#highcharPt').highcharts({
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
                text: '<s:text name="enrollment" />'
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
<s:include value="/admin/template/changeCSS.jsp"/>
  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="queryPlayerRegisterStatByCount" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
			<table class="searchContent">
				   	<tr>
				   	<td>
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
					<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="ossServerIdRegister"
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
					<s:text name="all_the_total_number_of_registered_users" />：<s:property value="allRegisterNum"/>
				</td> 
				<td>
					<ul>
						<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
					</ul>
				</td> 
				</tr>				
				</table>
			</div>
	 </s:form>
	</div>

	<div class="pageContent sortDrag" layoutH="120" selector="h1">
		<div class="panel collapse">
		<h1><s:text name="registration_statistics" /></h1>
		<div>
		    <table class="list" width="100%" layoutH="90" >
		    <thead> 
		    	<tr>
		    	    <th colspan="8" style="text-align:center">
		    	    	<s:text name="statistical_time_range" />：<s:property value="beginDate"/> 00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
		    	    </th>
		    	</tr>
		    	<tr>
		    	    <th><s:text name="date" /></th>
		    	    <th><s:text name="the_number_of_registered" /></th>
		    	</tr>
		    	</thead>
		    	<tbody>
		    	<s:iterator value="statList" var="oneStat">
		    		<tr >
			    	    <td>
							  	<s:property value="#oneStat.year"/><%-- -<s:property value="#oneStat.month"/>-<s:property value="#oneStat.day"/> --%>&nbsp;
			    	    </td>
			    	    <td>
			    	    	<s:property value="#oneStat.countNum"/>
			    	    </td>
			    	</tr>
		    	</s:iterator>
		    	<tr>
		    		<td><s:text name="total" /></td>
		    		<td><s:property value="registerCount"/></td>
		    	</tr>
		    	</tbody>
		    </table>
	    </div>
	    </div>
	    
	    <div class="panel collapse">
			<h1><s:text name="server_statistics_graph" /></h1>
			<div id="highcharServer" style="min-width: 400px; height: 600px; margin: 0 auto"></div>
	 </div>
	    
	    <div class="panel collapse">
	    	<h1><s:text name="platform_statistics_graph" /></h1>
	    	<div id="highcharPt" style="min-width: 400px; height: 600px; margin: 0 auto"></div>
	    </div>
	 </div>
   
	