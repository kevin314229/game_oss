<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/changeCSS.jsp"/>
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
	<s:form action="playerBehavior_list" namespace="/admin/sta" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
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
					周期 :<select name="period">
					 <option value="1" ${period==1?"selected='selected'":""} > 1</option>
					 <option value="2" ${period==2?"selected='selected'":""} > 2</option>
					 <option value="3" ${period==3?"selected='selected'":""} > 3</option>
					 <option value="4" ${period==4?"selected='selected'":""} > 4</option>
					 </select> 周
					
					<%-- <s:text name="statistical_time" />：<s:textfield id="beginDate2" name="beginDate2" maxlength="10" size="12" cssClass="required date"></s:textfield>
					<s:text name="to" /> <s:textfield id="endDate2" name="endDate2" maxlength="10" size="12" cssClass="required date"></s:textfield> --%>
	    	</div>
	    	
	    	<div class="searchButton">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
	</div>

	<div class="pageContent">
	    <table class="list" width="100%" layoutH="90" >
	    <thead>
	    	<tr>
	    	    <th colspan="8" style="text-align:center">
	    	    	<s:text name="statistical_time_range" />：<s:property value="beginDate"/> 00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
	    	    </th>
	    	</tr>
	    	<tr>
	    	    <th>时间范围</th>
	    	    <th>登录人数</th>
	    	    <th>付费人数</th>
	    	    <th>付费率</th>
	    	    <th>充值ARPPU</th>
	    	    <th>充值总额</th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="playerBehaviorList" var="oneRow">
	    		<tr >
		    	    <td>
						 开始时间：${beginDate2} 结束时间：${endDate2 }
		    	    </td>
		    	    <td>
						  	<s:property value="#oneRow.longinNum"/>&nbsp;
		    	    </td>
		    	    
		    	    <td>
		    	    	<s:property value="#oneRow.payNum"/>
		    	    </td>
		    	    
		    	    <td>
		    	    	<s:property value="#oneRow.payRate"/>%
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.arppu"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.paySum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator> 
	    	
	    	</tbody>
	    </table>
	 </div>
   
