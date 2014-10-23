<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script type="text/javascript" language="javascript">
	$(function() {
		
		 var dataObject = '<s:property value="jobJsonStr" escape="false" />';
		    
		    dataObject =(new Function("","return "+dataObject))();
	        $('#jobAssaycontainer').highcharts({
	         chart: {
		             type: 'column'
		         },
		         title: {
		             text: '<s:text name="new_career_number_distribution" />'
		         },
		         xAxis: {
		             categories: []//<s:text name="and_this_can_make_all" />x<s:text name="coordinate_display" />
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
		             borderWidth: 0,
		             shadow: false
		             
		             
		         },
		         tooltip: {
		             formatter: function() {
		                 return this.series.name +'  '+ this.y +'<s:text name="people" /><br/>';
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
		<s:form action="/zhxy/assay/ZHplayerJobAssay.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
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
	
	
<div class="pageContent sortDrag" selector="h1"  layoutH="90" >
		<div class="panel collapse" >
			<h1></h1>
			<div>
			    <table class="list" width="100%">
			    	<thead>
				    	<tr>
				    	    <th width="300"><s:text name="professional_types" /></th>
				    	    <th><s:text name="the_total_number" /></th>
				    	</tr>
			    	</thead>
			    	<tbody>
				    	<s:iterator value="totalJobList" var="oneRow"  status="offset">
				    		<tr  >
					    	    <td>
					    	     <s:if test="#oneRow.jobs==1"><span class="color-gr"><s:text name="monkey" /></span></s:if>
					    	     <s:elseif test="#oneRow.jobs==2"><span class="color-gr"><s:text name="gods" /></span>
								 </s:elseif>
								 <s:else ><span class="color-gr"><s:text name="xuannv" /></span></s:else>
								 </td>
					    	     <td>
					    	    	<s:property value="#oneRow.jobNum"/>
					    	    </td>
					    	</tr>
					    	    
				    	</s:iterator>
			    	 </tbody>
			    </table>
		     </div>
	    </div>
	    
	    <div class="panel collapse" >
	    	  <h1></h1>
	    	  <div>
			      <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    	    <th width="300"><s:text name="the_date_of" /></th>
			    	    <th><s:text name="monkey" /></th>
			    	    <th><s:text name="gods" /></th>
			    	    <th><s:text name="xuannv" /></th>
			    	</tr>
			    	</thead>
			    	<tbody>
			    	<s:iterator value="playerJobsList" var="oneRow"  status="offset">
			    		<tr>
				    	    
				    	     <td>
				    	    	<s:property value="#oneRow.date"/>
				    	    </td>
				    	    <td>
				    	    	<s:property value="#oneRow.job1Num"/>
				    	    </td>
				    	    <td>
				    	    	<s:property value="#oneRow.job2Num"/>
				    	    </td>
				    	    <td>
				    	    	<s:property value="#oneRow.job3Num"/>
				    	    </td>
				    	</tr>
			    	</s:iterator>
			    	</tbody>
			    </table>
		     </div>
	    </div>
	    
	    <div class="panel collapse" >
	    	<h1><s:text name="new_occupational_distribution" /></h1>
	    	<div>
	   			<div id="jobAssaycontainer" style="min-width: 400px; height: 400px;"></div>
	   		</div>
	    </div>
</div>
	
		
	
	
	
   
