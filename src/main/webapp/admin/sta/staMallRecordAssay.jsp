<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script type="text/javascript">
		function optionChange(obj){
				$("#ossServerIdLogin").empty();
				$.ajax({
		        type: "post",
		        url: "<%=basePath%>/admin/sta/playerBehavior_ajax.action",
		        cache: false,
		        global:false,
		        data: {
		        	"operationId":obj
		        } ,
		        success: function(msg){
		        	var myobj=eval(msg);
		        	$("#ossServerIdLogin").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
		        	for(var i=0;i<myobj.length;i++){
		        		$("#ossServerIdLogin").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
		        	}
		        }
		   });
		}


</script>


<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="staMallRecord_index" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   
			<div class="searchBar">
		   		<div class="searchDiv_left">
					<s:text name="platform" />：<select id="operationId" name="operationId" onchange="optionChange(this.value)">
							<option value="-1">--全部平台--</option>
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
						<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="ossServerIdLogin"
			 multiple="true" size="5" headerKey="-1" headerValue="全部大区"      ></s:select> 
					新登录时间：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
	    	</div>
	    	<div class="searchButton">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
</div>

<div class="pageContent sortDrag" layoutH="80" selector="h1">
		<div class="panel collapse">
			<h1><s:text name="mall_data" /></h1>
			<div>
		    <table class="list" width="100%"  >
		    <thead>
				<tr class="even" >
				<td><s:text name="item_name" /></td>
				<td style="color: "><s:text name="average_unit_price" /></td>
				<td>VIP<s:text name="the_unit_price" /></td>
				<td><s:text name="the_number_of" /></td>
				<td>vip<s:text name="the_number_of" /></td>
				<td>普通售卖总金额 </td>
				<td>vip售卖总金额</td>
				<td><s:text name="the_total_amount" /></td>
				<td><s:text name="general_stores_accounted_for" /></td>
				<td>vip<s:text name="sales_accounted_for" /></td>
				<td><s:text name="the_total_percentage" /></td>
				</tr>
				</thead>
				<tbody>
			<s:iterator value="ossMallRecordList" status="offsets" var="mallRecord">
				<tr>
					<td><s:property value="goodName" /> </td>
					<td><s:property value="price" /></td>
					<td><s:property value="vipPrice" /></td>
					<td><s:property value="number" /> </td>
					<td><s:property value="vipNumber" /> </td>
					<td><s:property value="normalSum" /> </td>
					<td><s:property value="vipSum" /> </td>
					<td><s:property value="goldSum" /> </td>
					<td><s:property value="scale" />% </td>
					<td><s:property value="vipScale" />% </td>
					<td><s:property value="totalScale" />% </td>
				</tr>
			</s:iterator>
				<tr class="even">
					<td colspan="3"> <s:text name="a_total_of" /> </td>
					<td><s:property value="sumMallRecord.number" /> </td>
					<td><s:property value="sumMallRecord.vipNumber" /> </td>
					<td><s:property value="sumMallRecord.normalSum" /> </td>
					<td><s:property value="sumMallRecord.vipSum" /> </td>
					<td><s:property value="sumMallRecord.goldSum" /> </td>
					<td>100%</td>
					<td>100%</td>
					<td>100%</td>
				</tr>
				</tbody>
			</table>
			</div>
		</div>
	</div>
