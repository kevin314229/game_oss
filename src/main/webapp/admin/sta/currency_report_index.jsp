<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>


<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="select.jsp" %>
<script type="text/javascript">
	function changePt(obj){
		changePtCodeOption(obj.value,'operCurrency_ptId','operCurrency_areaId');
		 var options =obj.options;
		 for(var i=0;i<options.length;i++){
			// if(options[i].value!=obj.value){
				 $("#gameId"+options[i].value)[0].style.display="none";
			 //}
		 }
		 $("#gameId"+obj.value)[0].style.display="block";
	}
	function checkSelect(){
		var obj = document.getElementById("")
	}
</script>
 
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="operationReport_currencyIndex" rel="pagerForm" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		<div  class="searchDiv_left" style="width:595px;">
		<div class="searchDiv_left searchDiv_Hight"  >
					<s:text name="role_name" />：	<s:textfield id="nickName" name="nickName"  ></s:textfield>
					</div>
				<s:if test='gameId==1'>
						<div id="gameId1"   class="searchDiv_left searchDiv_Hight" >
					<s:text name="currency" />： 
					<input type="checkbox" value="-1" onchange="isChecked(this,'itemIds','gameId1')" /><s:text name="whole" />&nbsp;&nbsp;
				
					<input type="checkbox" name="itemIds" value='2990003' 
					 <s:iterator value="itemIds" var="id"> 
         			   <s:if test="#id=='2990003'">
               		 		checked="checked"
           				 </s:if>
        			</s:iterator>
					/><s:text name="magic_crystal" />&nbsp;&nbsp; 
					<input type="checkbox" name="itemIds" value='2990002'
					 <s:iterator value="itemIds" var="id"> 
         			   <s:if test="#id=='2990002'">
               		 		checked="checked"
           				 </s:if>
        			</s:iterator> /><s:text name="gift_certificates" />&nbsp;&nbsp;
					<input type="checkbox" name="itemIds" value='2990001'
					 <s:iterator value="itemIds" var="id"> 
         			   <s:if test="#id=='2990001'">
               		 		checked="checked"
           				 </s:if>
        			</s:iterator> /><s:text name="gold" />
        			</div>
        			</s:if>
        			
        			
        			<s:if test='gameId==2'>
        			<div  class="searchDiv_left searchDiv_Hight"   id="gameId2" >
        			<s:text name="currency" />： 
					<input type="checkbox" value="-1" onchange="isChecked(this,'itemIds','gameId2')" /><s:text name="whole" />&nbsp;&nbsp;
        			<input type="checkbox" name="itemIds" value='2990001' 
					 <s:iterator value="itemIds" var="id"> 
         			   <s:if test="#id=='2990001'">
               		 		checked="checked"
           				 </s:if>
        			</s:iterator>
					/><s:text name="coins" />&nbsp;&nbsp; 
					<input type="checkbox" name="itemIds" value='2990003'
					 <s:iterator value="itemIds" var="id"> 
         			   <s:if test="#id=='2990003'">
               		 		checked="checked"
           				 </s:if>
        			</s:iterator> /><s:text name="star_power" />&nbsp;&nbsp;
					<input type="checkbox" name="itemIds" value='2990002'
					 <s:iterator value="itemIds" var="id"> 
         			   <s:if test="#id=='2990002'">
               		 		checked="checked"
           				 </s:if>
        			</s:iterator> /><s:text name="ingot" />&nbsp;&nbsp;
					<input type="checkbox" name="itemIds" value='2990005'
					 <s:iterator value="itemIds" var="id"> 
         			   <s:if test="#id=='2990005'">
               		 		checked="checked"
           				 </s:if>
        			</s:iterator> /><s:text name="talisman_experience" />&nbsp;&nbsp;
					<input type="checkbox" name="baseEquip" value='1'
					 <s:if test="baseEquip==1">
               		 		checked="checked"
           				 </s:if> /><s:text name="equipment" />&nbsp;&nbsp;
					<input type="checkbox" name="baseProperty" value='1'
					 
         			   <s:if test="baseProperty==1">
               		 		checked="checked"
           				 </s:if>
        			  /><s:text name="props" />
        			</div>
        			
        			</s:if>
				<div class="searchDiv_left searchDiv_Hight"  >
					<s:text name="start_time" />：	<s:textfield id="beginDate" name="beginDate" maxlength="17" size="17" datefmt='yyyy-MM-dd HH' cssClass="required date" value="%{beginDate}"></s:textfield>
				<s:text name="end_time" />： <s:textfield id="endDate" name="endDate" maxlength="17" size="17" datefmt='yyyy-MM-dd HH' cssClass="required date"></s:textfield>
		  	<%-- 	 <s:text name="item" /> <s:text name="eye" />：<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="operCurrency_gameId"  onchange="changePt(this)"></s:select> --%>
			<s:hidden name="gameId" id="operCurrency_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptId" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="operCurrency_ptId"  onchange="initPtCodeParam('operCurrency_gameId',this.value,'operCurrency_areaId');"></s:select> 
					
					
					</div>
					</div>
				<div style="height:90px;float:left">
					<s:text name="server" />：  <s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="operCurrency_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> </div>
		
		 <div class="searchButton">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div>
		 
		</div>
	</s:form>
</div>

   
  
	<div class="pageContent sortDrag" layoutH="140" selector="h1">
		<div class="panel collapse">
			<h1><s:text name="total_server_statistics" /></h1>
			<div>
			   <table class="list" width="100%"  >
	    	<thead>
	    	<tr>
	    		<!-- <th width=70><s:text name="name_of_the_game" /></th>
	    		<th width=70><s:text name="server_name" /></th> -->
	    		<th width=50><s:text name="goods" />ID</th>
	    		<th width=70><s:text name="currency_name" /></th>
	    	    <th width=50><s:text name="output" /></th>
	    	    <th width=50><s:text name="consume" /></th>
	    	    <th width=50><s:text name="i_kept" /></th>
	    	    <th width=50><s:text name="detail" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="sumOperList" var="oneRow" status="offset">
	    		<tr>
		    	   <%--   <td>
				<s:property value="@com.jcwx.game.common.constants.GameConstant@gameMap[#oneRow.gameId]"/> 
				</td>
				<td>
				 <s:property value="@com.jcwx.game.common.constants.OssServerConstant@ossServerMap[#oneRow.areaId]"/> 
				 </td> --%>
		    	    <td>
		    	    	<s:property value="#oneRow.itemId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.itemName"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.produceNum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="consumeNum"/>
		    	    </td>
		    	      <td>
		    	      <s:if test='#oneRow.produceNum-consumeNum<0'>0</s:if>
		    	      <s:else>
		    	      <s:property value="#oneRow.produceNum-consumeNum"/> 
		    	      </s:else>
		    	    	 
		    	    </td>
		    	     <td> 
		    	    	<a width="770" height="560" href='<%=basePath%>/admin/operReport/operationReport_currencyOperList.action?itemId=<s:property value='#oneRow.itemId' />&beginDate=<s:property value='beginDate' />&endDate=<s:property value='endDate' />
		    	    	&nickName=<s:property value='nickName' />&gameId=<s:property value="gameCode"/>&ptId=<s:property value='ptId' />
		    	    	&areas=<s:property value='areas' />&produceSum=<s:property value='produceNum' />&consumeSum=<s:property value='consumeNum' />' target="dialog"><s:text name="detail" /></a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    		</div>
	    </div>
	    
		<div class="panel collapse">
			<h1><s:text name="server_statistics_by_day" /></h1>
			<div>
			    <table class="list" width="100%"  >
	    	<thead>
	    	<tr>
	    		<!-- <th width=70><s:text name="name_of_the_game" /></th>
	    		<th width=70><s:text name="server_name" /></th> -->
	    		<th width=70><s:text name="date" /></th>
	    		<th width=70><s:text name="currency_name" /></th>
	    	    <th width=50><s:text name="output" /></th>
	    	    <th width=50><s:text name="consume" /></th>
	    	    <th width=50><s:text name="i_kept" /></th>
	    	    <th width=50><s:text name="detail" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="detailOperList" var="oneRow" status="offset">
	    		<tr>
		    	   <%--   <td>
				<s:property value="@com.jcwx.game.common.constants.GameConstant@gameMap[#oneRow.gameId]"/> 
				</td>
				<td>
				 <s:property value="@com.jcwx.game.common.constants.OssServerConstant@ossServerMap[#oneRow.areaId]"/> 
				 </td> --%>
		    	    <td>
		    	    	<s:property value="#oneRow.operationDate"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.itemName"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.produceNum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="consumeNum"/>
		    	    </td>
		    	      <td>
		    	     <s:if test='#oneRow.produceNum-consumeNum<0'>0</s:if>
		    	      <s:else>
		    	      <s:property value="#oneRow.produceNum-consumeNum"/> 
		    	      </s:else>
		    	    </td>
		    	     <s:if test="#offset.first" >
		    	     <td> 
		    	    	<a width="770" height="560" href='<%=basePath%>/admin/operReport/operationReport_currencyOperList.action?itemId=<s:property value='#oneRow.itemId' />&beginDate=<s:property value='operationDate' />&endDate=<s:property value='endDate' />&nickName=<s:property value='nickName' />&gameId=<s:property value="gameCode"/>&ptId=<s:property value='ptId' />
		    	    	&areas=<s:property value='areas' />&produceSum=<s:property value='produceNum' />&consumeSum=<s:property value='consumeNum' />' target="dialog"><s:text name="detail" /></a>
		    	    </td>
		    	    </s:if>
		    	     <s:elseif test="#offset.last" >
		    	     <td> 
		    	    	<a width="770" height="560" href='<%=basePath%>/admin/operReport/operationReport_currencyOperList.action?itemId=<s:property value='#oneRow.itemId' />&beginDate=<s:property value='beginDate' />&endDate=<s:property value='operationDate' />&nickName=<s:property value='nickName' />&gameId=<s:property value="gameCode"/>&ptId=<s:property value='ptId' />
		    	    	&areas=<s:property value='areas' />&produceSum=<s:property value='produceNum' />&consumeSum=<s:property value='consumeNum' />' target="dialog"><s:text name="detail" /></a>
		    	    </td>
		    	    </s:elseif>
		    	    <s:else>
		    	     <td> 
		    	    	<a width="770" height="560" href='<%=basePath%>/admin/operReport/operationReport_currencyOperList.action?itemId=<s:property value='#oneRow.itemId' />&beginDate=<s:property value='operationDate' />&endDate=<s:property value='operationDate' />&nickName=<s:property value='nickName' />&gameId=<s:property value="gameCode"/>&ptId=<s:property value='ptId' />
		    	    	&areas=<s:property value='areas' />&produceSum=<s:property value='produceNum' />&consumeSum=<s:property value='consumeNum' />' target="dialog"><s:text name="detail" /></a>
		    	    </td>
		    	    </s:else>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    	</div>
	    </div>
</div>
  
