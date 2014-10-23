
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
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
 
</script>
<%@ include file="select.jsp" %>
<s:include value="/admin/template/changeCSS.jsp"/>
  <s:include value="/admin/template/scriptMessage.jsp"/>
  <form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
<div class="pageHeader">
	<s:form action="playerBaseRank_index" rel="pagerForm" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		   		<div class="searchDiv_left">
				   
			<s:hidden name="gameId" id="playerBase_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptCode" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="playerBase_ptId"  onchange="initPtCodeParam('playerBase_gameId',this.value,'playerBase_areaId');"></s:select> 
					<s:text name="server" />：
					<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="playerBase_areaId"
				 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all')}"      ></s:select> 
				
					<s:text name="platform_logo" />：<select name="ptId">
								<option value="">--<s:text name="all_platform_logo" />--</option>
									<s:iterator value="ossOperationList" var="oneRows">
										<option value='<s:property value='#oneRows.carrierOperator'/>'
										 <s:if test='ptId==#oneRows.carrierOperator'> selected </s:if> >
										 <s:property value="#oneRows.operationName"/>
										 </option>
									</s:iterator>
							  </select>
				
				</div>
	    	<div class="searchButton">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
	</div>

   <div class="pageContent">
	    <table class="list" width="100%" layoutH="130">
	    	<thead>
	    	<tr>
	    	  
	    	    <th>角色名</th>
	    	     <th><s:text name="server_region" /></th>
	    	  <%--   <th><s:text name="game_platform" /></th> --%>
	    	   <th>渠道</th>
	    	    <th>职业</th>
	    	    <th>存余元宝</th>
	    	     <th>首次消费金额</th>
	    	     <th>首次消费点</th>
	    	     <th>首次消费时间</th>
	    	      <th>最后消费点</th>
	    	       <th>最后消费时间</th>
	    	       <th>最后下线时间</th>
	    	       <th>充值总金额</th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="rankList" var="oneRow" status="offset">
	    		<tr>
		    	   	     <td>
		    	   	     <s:property value="#oneRow.nickName"/>
				</td>
				<%-- <td>
				<dict:itemvalue gameId="${oneRow.gameId }" type="4"  itemValue="${oneRow.ptId }"></dict:itemvalue>
				<s:property value="@com.jcwx.game.common.constants.PtServerConstant@ptTypeMap[#oneRow.ptId]"/>/<s:property value="#oneRow.ptId"/>
				</td> --%>
				<td>
				<dict:itemvalue gameId="${oneRow.gameId }" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				<%--  <s:property value="@com.jcwx.game.common.constants.OssServerConstant@ossServerMap[#oneRow.areaId]"/>  --%>
				 </td>
		    	    <td>
		    	   	<dict:itemvalue gameId="${oneRow.gameId }" type="4"  itemValue="${oneRow.ptId }"></dict:itemvalue>
		    	    </td>
		    	      <td>
		    	   		<s:if test="#oneRow.occupation==1">灵猴
		    	   		</s:if>
		    	   		<s:elseif  test="#oneRow.occupation==2">神君
		    	   		</s:elseif>
		    	   		<s:elseif  test="#oneRow.occupation==3">玄女
		    	   		</s:elseif>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.gold"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.firstConsume"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.firstOperation"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.firstConsumeTime"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.operation"/>
		    	    </td>
		    	      <td>
		    	   		<s:date format="yyyy-MM-dd hh:mm:ss" name="#oneRow.consumeTime"/>
		    	    </td>
		    	     <td>
		    	   		<s:date format="yyyy-MM-dd hh:mm:ss" name="#oneRow.logoutTime"/>
		    	    </td>
		    	     <td>
		    	  	<a href="<%=basePath%>/zhxy/base/playerBaseRank_info.action?playerBaseId=<s:property value='#oneRow.playerBaseId' />&gameId=<s:property value='#oneRow.gameId' />&ptId=<s:property value='#oneRow.ptId' />&areaId=<s:property value='#oneRow.areaId' />" target="navTab" width="700" height="600"><s:property value="#oneRow.allMoney"/></a>	
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	     <s:include value="/admin/template/paging.jsp"/>
  </div>
	