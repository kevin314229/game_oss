<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script>
function detail(baseid,loginName,nickName){
	var url = "<%=basePath%>/admin/message/playermoney_detail.action?playerMoneyDay.playerBaseId="+baseid+"&loginName="+loginName+"&nickName="+nickName;
		navTab.openTab('<s:text name="detailed" />', url, { title:"<s:text name="detailed" />"});
}
function modifyBaseProperty(url,itemId){
	var item = $("input",$("#"+itemId));
	
	item.attr("action",url);
	
	 var tabId = $("ul.navTab-tab li.selected").attr("tabid");
	 
	 navTabSearch(item,tabId);
}

</script>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="playermoney_index" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		
		 <div class="searchDiv_left"> 
					<s:text name="login_name" />：<s:textfield id="loginName" name="loginName" ></s:textfield>
				 
					<s:text name="nickname" /> <s:textfield id="nickName" name="nickName"></s:textfield>
				      <s:hidden name="firstFlag" value="true"></s:hidden>
		</div>
		 <div class="searchButton2">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div>
		 
		</div>
	</s:form>
</div>


<div class="pageContent sortDrag" selector="h1" layoutH="50">
		<div class="panel">
		    <table class="list" width="100%" >
		    	<thead>
				<tr>
				<td><s:text name="login_name" /> </td>
				<td><s:text name="nickname" /></td>
				<td><s:text name="magic_crystal" /> </td>
				<td><s:text name="gold" /></td>
				<td><s:text name="gift_certificates" /></td>
				<td><s:text name="star_stone" /></td>
				<!-- <td>VIP<s:text name="grade" /></td> -->
				<td>VIP<s:text name="total_points" /></td>
				<s:if test="ossPlayerMoneys.size==1">
				<td><s:text name="change" /></td>
				</s:if>
				<s:if test="ossPlayerMoneys.size>1">
					<td><s:text name="detailed" /></td>
				</s:if>
				</tr>
				</thead>
				<tbody>
			<s:iterator value="ossPlayerMoneys" status="offsets" var="item">
				<s:form action="playermoney_modify" method="post" theme="simple" onsubmit="return $(this).valid()&&navTabSearch(this)">
				<tr id="item<s:property value="#offsets.index"/>">
					<td>
					<s:property value="%{#item.loginName}" />
					<s:hidden name="loginName" value="%{#item.loginName}" />
					<s:hidden name="nickName" value="%{#item.nickName}" />
					<s:hidden name="playerMoneyDay.playerBaseId" value="%{#item.playerBaseId}" />
					</td>
					<td><s:property value="%{#item.nickName}" /> </td>
					<td><s:textfield name="playerMoneyDay.gold" value="%{#item.gold}"/> </td>
					<td><s:textfield name="playerMoneyDay.silver" value="%{#item.silver}" /> </td>
					<td><s:textfield name="playerMoneyDay.bindGold" value="%{#item.bindGold}" /> </td>
					<td><s:textfield name="playerMoneyDay.starStone" value="%{#item.starStone}" /> </td>
					<%-- <td><s:textfield name="playerMoneyDay.vipLevel" value="%{#item.vipLevel}" /> </td> --%>
					<td><s:textfield name="playerMoneyDay.vipScore" value="%{#item.vipScore}" /> </td>
					<s:if test="ossPlayerMoneys.size==1">
						<td>
							<a class="button" href="javascript:modifyBaseProperty('<%=basePath%>/admin/message/playermoney_modify.action?','item<s:property value="#offsets.index"/>');"><span><s:text name="modification" /></span></a>
						</td>
					</s:if>
					<s:if test="ossPlayerMoneys.size>1">
						<td>
							<a class="button" href="javascript:detail('<s:property value="#item.playerBaseId"/>','<s:property value="#item.loginName" />','<s:property value="#item.nickName" />');"><span><s:text name="detailed" /></span></a>
						</td>
					</s:if>
				</tr>
				</s:form>
				</tbody>
			</s:iterator>
			</table>
		</div>
		<div class="panel">
			<table class="list" width="100%" >
		    	<thead>
				<tr class="even" >
					<td><s:text name="name" /></td>
					<td><s:text name="quantity" /></td>
					<td><s:text name="change" /> </td>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="ossItemNumbers" status="offsets" var="item">
					<tr id="itemNumber<s:property value="#offsets.index"/>">
					<s:form action="playermoney_itemmodify" method="post" theme="simple" onsubmit="return $(this).valid()&&navTabSearch(this)">
						<td>
						<s:hidden name="loginName"  value="%{#request.loginName}" />
						<s:hidden name="nickName"  value="%{#request.nickName}" />
						
						<s:hidden name="itemNumber.itemId"  value="%{#item.itemId}" />
						<s:hidden name="oldNumber" value="%{#item.itemNumber}" />
						<s:hidden name="itemNumber.playerBaseId" type="hidden" value="%{#item.playerBaseId}" />
						<s:property value="%{#item.itemName}"/>
						</td>
						<td><s:textfield name="itemNumber.itemNumber" value="%{#item.itemNumber}" /> </td>
						<td>
							<a class="button" href="javascript:modifyBaseProperty('<%=basePath%>/admin/message/playermoney_itemmodify.action?','itemNumber<s:property value="#offsets.index"/>');"><span><s:text name="modification" /></span></a>
						</td>
					</s:form>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
		<div class="panel">
			<table class="list" width="100%" >
		    	<thead>
				<tr class="even" >
					<td><s:text name="name" /> </td>
					<td><s:text name="enhanced_level" /> </td>
					<td><s:text name="property" /> </td>
					<td><s:text name="inlaid_stones" />1</td>
					<td><s:text name="inlaid_stones" />2 </td>
					<td><s:text name="inlaid_stones" />3</td>
					<td><s:text name="inlaid_stones" />4 </td>
					<td><s:text name="change" /> </td>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="ossEquips" status="offsets" var="item">
					<tr>
						<td>
						<s:property value="%{#item.name}" /></td>
						<td><s:property value="%{#item.strongLevel}" /></td>
						<td><s:property value="%{#item.baseEquipId}"/></td>
	
						<td><s:property value="%{#item.equipStringA}" /> </td>
						<td><s:property value="%{#item.equipStringB}" /> </td>
						<td><s:property value="%{#item.equipStringC}" /> </td>
						<td><s:property value="%{#item.equipStringD}" /> </td>
						
						<td>
							<s:form action="playermoney_equipmodify" method="post" theme="simple" onsubmit="return $(this).valid()&&navTabSearch(this)">
								<s:hidden name="zequipDay.baseEquipId" value="%{#item.playerItemId}" />
								<s:hidden name="zequipDay.playerItemId" value="%{#item.playerItemId}" />
								<s:hidden name="zequipDay.playerBaseId" value="%{#item.playerBaseId}" />
								<s:hidden name="loginName" value="%{#request.loginName}" />
								<s:hidden name="oldNumber" value="1" />
								<s:hidden name="nickName" value="%{#request.nickName}" />
						
							<div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="delete" /></button></div></div>
							</s:form>
						</td>
					
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
		<div class="panel">
			<h1><s:text name="modify_receive_state" /></h1>
			<div>
			<table class="list" width="100%" >
		    	<thead>
				<tr>
					<td><s:text name="project" /> </td>
					<td><s:text name="the_first_punch" /> </td>
					<td>500<s:text name="magic_crystal" /> </td>
					<td>1000<s:text name="magic_crystal" /> </td>
					<td>3000<s:text name="magic_crystal" /> </td>
					<td>6000<s:text name="magic_crystal" /> </td>
					<td>10000<s:text name="magic_crystal" /> </td>
					<td>30000<s:text name="magic_crystal" /> </td>
					<td>60000<s:text name="magic_crystal" /> </td>
					<td>100000<s:text name="magic_crystal" /> </td>
				</tr>
				</thead>
				<tbody>
					<tr>
						<td><s:text name="<s:text name='status' />" /> </td>
						<td>
							<s:if test="ossPlayerSdataVo.firstType != null">
							   		<s:text name="receive" />
					   		</s:if>
					   		<s:else>
							   		<s:text name="unclaimed" />
					   		</s:else>
						</td>
						<s:bean name="org.apache.struts2.util.Counter" id="counter">
						   <s:param name="first" value="1" />
						   <s:param name="last" value="8" /> 
						   <s:iterator value="ossPlayerSdataVo.receive" status="offsets" var="item">
						   	<td>
						   		<s:if test="#item == 1">
						   			<s:text name="receive" />
						   		</s:if>
						   		<s:elseif test="#item == 0">
						   			<s:text name="unclaimed" />
						   		</s:elseif>
						   		<s:elseif test="#item == null">
						   			<s:text name="not_reached" />
						   		</s:elseif>
						   	</td>
						   </s:iterator>
						</s:bean>
					</tr>
					<tr>
						<td><s:text name="change_status" /> </td>
						
						<td>
							<s:form action="playermoney_sdatamodify" method="post" theme="simple" onsubmit="return $(this).valid()&&navTabSearch(this)">
							
								<s:hidden name="loginName" value="%{#request.loginName}" />
									
								<s:hidden name="nickName" value="%{#request.nickName}" />
								
								<s:hidden name="ossPlayerSdata.val" type="hidden" value="0" />
								
								<s:if test="request.ossPlayerSdataVo.firstId!=null">
								
									<s:hidden name="ossPlayerSdata.id" type="hidden" value="%{#request.ossPlayerSdataVo.firstId}" />
								
								</s:if>
								
								<s:hidden name="ossPlayerSdata.playerId" type="hidden" value="%{#request.ossPlayerSdataVo.playerId}" />
									
								<s:hidden name="ossPlayerSdata.type" type="hidden" value="944" />
								
								<s:if test="ossPlayerSdataVo.firstType != null">
									<font style="color:red"><s:text name="have_received" /></font>
								</s:if>
								<s:else>
									<div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="receive" /></button></div></div>
								</s:else>
						   		
					   		</s:form>
						</td>
						
						
						
						<s:bean name="org.apache.struts2.util.Counter" id="counter">
						   <s:param name="first" value="1" />
						   <s:param name="last" value="8" /> 
						   <s:iterator value="ossPlayerSdataVo.receive" status="offsets" var="item">
						   	<td>
						   		<s:form action="playermoney_sdatamodify" method="post" theme="simple" onsubmit="return $(this).valid()&&navTabSearch(this)">
						   		
						   			<s:hidden name="ossPlayerSdata.val" value="%{(#request.ossPlayerSdataVo.val==null?0:#request.ossPlayerSdataVo.val)^(1<<#offsets.index)}" />
	
									<s:hidden name="ossPlayerSdata.id" value="%{#request.ossPlayerSdataVo.id}" />
									
									<s:hidden name="ossPlayerSdata.playerId" value="%{#request.ossPlayerSdataVo.playerId}" />
									
									<s:hidden name="ossPlayerSdata.type" value="945" />
									
									<s:hidden name="loginName" value="%{#request.loginName}" />
									
									<s:hidden name="nickName" value="%{#request.nickName}" />
									
							   		<s:if test="#item == 1">
							   			<font style="color:red"><s:text name="have_received" /></font>
							   		</s:if>
							   		<s:else>
							   			<div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="receive" /></button></div></div>
							   			<!-- <input type="submit" style="cursor: hand"  value="<s:text name="receive" />" />-->
							   		</s:else>
						   		</s:form>
						   	</td>
						   </s:iterator>
						</s:bean>
					</tr>
					</tbody>
			</table>
			</div>
	</div>
</div>
