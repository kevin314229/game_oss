<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

 <div class="pageHeader">
		<s:form   action="/zhxy/player/zhplayerInfo_queryAllPlayerBase.action" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
			   <div class="searchDiv_left">
						 
							登录名：
							<s:textfield id="loginName" name="loginName"   size="12"  />
						 
					</div>
			    	<div class="searchButton2">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
						</ul>
					</div>
				</div>
		</s:form>
	</div>
	
	<div class="pageContent"  >
	
	<div class="tabs" currentIndex="0" eventType="click" layoutH="30">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li class=""><a href="javascript:;"><span>已删除角色</span></a></li>
					<li><a href="javascript:;"><span>现有角色</span></a></li>
					 
				</ul>
			</div>
		</div>
		<div class="tabsContent" layoutH="80">
			<div id="container1" style="min-width: 400px; height: 400px; margin: 0 auto">
			 <s:iterator value="playerInfors" var="oneRow"  >
			 <table class="list" width="100%">
	    	<thead>
	    	<tr>
	    	   <th><s:text name="the_player" />ID</th>
	    	   <th><s:text name="account_name" /></th>
	    	   <th><s:text name="platform" /></th>
	    	   <th><s:text name="registration_time" /></th>
	    	   <th>绑定信息</th>
	    	   <th><s:text name="recently_the_login" /> </th>
	    	   <th><s:text name="the_last_login_role" />ID</th>
	    	   <%-- <th><s:text name="monster_crystal_quantity" /></th>
	    	   <th><s:text name="whether_or_not" />VIP</th>
	    	   <th>VIP<s:text name="level" /></th> --%>
	    	   <th><s:text name="state" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<tr>
	    	   <td><s:property value="#oneRow.playerClass.playerId"/></td>
	    	   <td><s:property value="#oneRow.playerClass.loginName"/></td>
	    	    <td><s:property value="#oneRow.playerClass.carrierOperator"/></td>
	    	   <td><s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.playerClass.playerCreateTime"/></td>
	    	   <td><label id="guestKeyId">${#oneRow.playerClass.guestKey}</label> 
	    	    <s:if test="#oneRow.playerClass.guestKey==''">无</s:if><s:else>
	    	    有
	    	     <%-- <a href="javascript:clearEmail(${playerClass.playerId})">清除绑定</a> --%>
	    	    </s:else>
	    	   </td>
	    	   <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.playerClass.lastLoginTime"/>
		    	    	 
		    	</td>
		       <td><s:property value="#oneRow.playerClass.lastLoginId"/></td>
	    	   <%-- <td><s:property value="playerClass.gold"/></td>
	    	   <td>
						 <s:if test="playerVipInfor.vip==0"><span class="color-gr"><s:text name="no" /></span></s:if>
						 <s:else><span class="color-red"><s:text name="is" /></span></s:else>
		       </td>
	    	   <td><s:property value="playerVipInfor.vipLevel"/></td> --%>
	    	   <td>
		    	    <s:if test="#oneRow.playerClass.onlineStatus == 1"><s:text name="online" /></s:if>
					<s:else><s:text name="offline" /></s:else>
		    	</td>
	    	   
	    	</tr>
	    	</tbody>
	  </table>
	  <s:iterator value="%{#oneRow.playerBaseInfors}" var="oneRow2"  status="offset">
	  <s:if test="#oneRow2.status==100">
	   <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	   <th><s:text name="the_role_of" /></th>
	    	   <th><s:text name="professional" /></th>
	    	   <th><s:text name="level" /></th>
	    	   <th><s:text name="ingot" /> </th>
	    	   <th><s:text name="gold_coins" /></th>
	    	   <th><s:text name="backpack_grid" />(<s:text name="a" />)</th>
	    	   <th><s:text name="warehouse_check" />(<s:text name="a" />)</th>
	    	   <%-- <th><s:text name="skill_points" /></th>
	    	   <th><s:text name="rune_essence" /></th> --%>
	    	   <th><s:text name="talisman_plaid_number" /> </th>
	    	   <th><s:text name="whether_or_not" />VIP</th>
	    	   <th>VIP<s:text name="level" /></th> 
	    	   <th>VIP<s:text name="integration" /></th> 
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<tr>
	    	  <td>
	    		<a target="dialog" width="1080" height="540" href="<%=basePath%>/zhxy/player/zhplayerInfo_viewPlayBaseInfo.action?playerBaseId=${oneRow2.playerBaseId }" style="color:green"> <s:property value="#oneRow2.nickName"/></a>
	    	  </td>
	    	   <td>
		    	    	<s:if test="#oneRow2.occupation == 1"><s:text name="monkey" /> </s:if>
		    	    	<s:if test="#oneRow2.occupation == 2"><s:text name="gods" /></s:if>
		    	    	<s:if test="#oneRow2.occupation == 3"><s:text name="xuannv" /> </s:if>
		       </td>
	    	   <td><s:property value="#oneRow2.level"/></td>
	    	   <td><s:property value="#oneRow2.gold"/></td>
	    	   <td><s:property value="#oneRow2.silver"/></td>
	    	   <td><s:property value="#oneRow2.bagNumber"/></td>
	    	   <td><s:property value="#oneRow2.wareNumber"/></td>
	    	   <%--  <td><s:property value="#oneRow.techPoint"/></td>
	    	   <td><s:property value="#oneRow.amulet"/></td> --%>
	    	   <td><s:property value="#oneRow2.amuletNumber"/></td>
	    	   <td><s:if test="#oneRow2.vip==0"><s:text name="no" /></s:if><s:else><s:text name="be" /></s:else> </td>
	    	   <td> <s:if test="#oneRow2.vip==0">0</s:if><s:else><s:property value="#oneRow2.vipLevel"/> </s:else>  </td>
	    	   <td><s:property value="#oneRow2.vipScore"/></td>
	    	</tr>
	    	</tbody>
	  </table>
	  </s:if>
	 	</s:iterator>
	 	</s:iterator>
			 </div>
			<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto">
			 <s:iterator value="playerInfors" var="oneRow"  status="offset">
			 <table class="list" width="100%">
	    	<thead>
	    	<tr>
	    	   <th><s:text name="the_player" />ID</th>
	    	   <th><s:text name="account_name" /></th>
	    	   <th><s:text name="platform" /></th>
	    	   <th><s:text name="registration_time" /></th>
	    	   <th>绑定信息</th>
	    	   <th><s:text name="recently_the_login" /> </th>
	    	   <th><s:text name="the_last_login_role" />ID</th>
	    	   <%-- <th><s:text name="monster_crystal_quantity" /></th>
	    	   <th><s:text name="whether_or_not" />VIP</th>
	    	   <th>VIP<s:text name="level" /></th> --%>
	    	   <th><s:text name="state" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<tr>
	    	   <td><s:property value="#oneRow.playerClass.playerId"/></td>
	    	   <td><s:property value="#oneRow.playerClass.loginName"/></td>
	    	    <td><s:property value="#oneRow.playerClass.carrierOperator"/></td>
	    	   <td><s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.playerClass.playerCreateTime"/></td>
	    	   <td><label id="guestKeyId">${#oneRow.playerClass.guestKey}</label> 
	    	    <s:if test="#oneRow.playerClass.guestKey==''">无</s:if><s:else>
	    	    有
	    	     <%-- <a href="javascript:clearEmail(${playerClass.playerId})">清除绑定</a> --%>
	    	    </s:else>
	    	   </td>
	    	   <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.playerClass.lastLoginTime"/>
		    	    	 
		    	</td>
		       <td><s:property value="#oneRow.playerClass.lastLoginId"/></td>
	    	   <%-- <td><s:property value="playerClass.gold"/></td>
	    	   <td>
						 <s:if test="playerVipInfor.vip==0"><span class="color-gr"><s:text name="no" /></span></s:if>
						 <s:else><span class="color-red"><s:text name="is" /></span></s:else>
		       </td>
	    	   <td><s:property value="playerVipInfor.vipLevel"/></td> --%>
	    	   <td>
		    	    <s:if test="#oneRow.playerClass.onlineStatus == 1"><s:text name="online" /></s:if>
					<s:else><s:text name="offline" /></s:else>
		    	</td>
	    	   
	    	</tr>
	    	</tbody>
	  </table>
	  <s:iterator value="%{#oneRow.playerBaseInfors}" var="oneRow2"  status="offset">
	  <s:if test="#oneRow2.status!=100">
	   <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	   <th><s:text name="the_role_of" /></th>
	    	   <th><s:text name="professional" /></th>
	    	   <th><s:text name="level" /></th>
	    	   <th><s:text name="ingot" /> </th>
	    	   <th><s:text name="gold_coins" /></th>
	    	   <th><s:text name="backpack_grid" />(<s:text name="a" />)</th>
	    	   <th><s:text name="warehouse_check" />(<s:text name="a" />)</th>
	    	   <%-- <th><s:text name="skill_points" /></th>
	    	   <th><s:text name="rune_essence" /></th> --%>
	    	   <th><s:text name="talisman_plaid_number" /> </th>
	    	   <th><s:text name="whether_or_not" />VIP</th>
	    	   <th>VIP<s:text name="level" /></th> 
	    	   <th>VIP<s:text name="integration" /></th> 
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<tr>
	    	  <td>
	    		<a target="dialog" width="1080" height="540" href="<%=basePath%>/zhxy/player/zhplayerInfo_viewPlayBaseInfo.action?playerBaseId=${oneRow2.playerBaseId }" style="color:green"> <s:property value="#oneRow2.nickName"/></a>
	    	  </td>
	    	   <td>
		    	    	<s:if test="#oneRow2.occupation == 1"><s:text name="monkey" /> </s:if>
		    	    	<s:if test="#oneRow2.occupation == 2"><s:text name="gods" /></s:if>
		    	    	<s:if test="#oneRow2.occupation == 3"><s:text name="xuannv" /> </s:if>
		       </td>
	    	   <td><s:property value="#oneRow2.level"/></td>
	    	   <td><s:property value="#oneRow2.gold"/></td>
	    	   <td><s:property value="#oneRow2.silver"/></td>
	    	   <td><s:property value="#oneRow2.bagNumber"/></td>
	    	   <td><s:property value="#oneRow2.wareNumber"/></td>
	    	   <%--  <td><s:property value="#oneRow.techPoint"/></td>
	    	   <td><s:property value="#oneRow.amulet"/></td> --%>
	    	   <td><s:property value="#oneRow2.amuletNumber"/></td>
	    	   <td><s:if test="#oneRow2.vip==0"><s:text name="no" /></s:if><s:else><s:text name="be" /></s:else> </td>
	    	   <td> <s:if test="#oneRow2.vip==0">0</s:if><s:else><s:property value="#oneRow2.vipLevel"/> </s:else>  </td>
	    	   <td><s:property value="#oneRow2.vipScore"/></td>
	    	</tr>
	    	</tbody>
	  </table>
	  </s:if>
	 	</s:iterator>
	 	</s:iterator>
			</div>	

			
		</div>
	</div>
</div>
