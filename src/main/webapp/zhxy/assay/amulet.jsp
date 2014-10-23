<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
	<div class="pageHeader">
		<s:form action="/zhxy/assay/amulet_index.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				  <div class="searchDiv_left">
									<s:text name="player_names" />：
									<s:textfield id="beginDate" name="playerName" cssClass="required "></s:textfield>
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
					<li class=""><a href="javascript:;"><span><s:text name="rune_equipment" /></span></a></li>
					<li class=""><a href="javascript:;"><span><s:text name="rune_backpack" /></span></a></li>
					<li class=""><a href="javascript:;"><span>符文增强</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" layoutH="80">
			<div id="container1" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="rune_name" /></th>
						<th><s:text name="rune_level" /></th>
						<th><s:text name="rune_quality" /></th>
						<th><s:text name="rune_experience" /> </th>
						<th><s:text name="equipment_location" /></th>
						<th><s:text name="is_locked" /> </th>
					</tr>
					</thead>
					<tbody>
					
			    	<s:iterator value="amuletEquip" var="oneRow"  status="offset">
			    		<tr>
							<td>
								<s:property value="#oneRow.amuletName"/>
								
								<s:hidden  value="#oneRow.amuletId"/>
							</td>
							<td>
								<s:property value="#oneRow.amuletId%100"/>
							</td>
							<td>
								<s:property value="#oneRow.qualityLevel"/>
							</td>
							<td>
								<s:property value="#oneRow.currExp"/>
							</td>
							<td>
								<s:property value="#oneRow.posId"/>
							</td>
							<td>
								<s:property value="#oneRow.isLock"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
			 </div>
			 		<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="rune_name" /></th>
						<th><s:text name="rune_level" /></th>
						<th><s:text name="rune_quality" /></th>
						<th><s:text name="rune_experience" /> </th>
						<th><s:text name="backpack_position" /></th>
						<th><s:text name="is_locked" /> </th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="amuletList" var="oneRow"  status="offset">
			    		<tr>
							<td>
								<s:property value="#oneRow.amuletName"/>
								<s:hidden  value="#oneRow.amuletId"/>
							</td>
							<td>
							<s:property value="#oneRow.amuletId%100"/>
							</td>
							<td>
								<s:property value="#oneRow.qualityLevel"/>
							</td>
							<td>
								<s:property value="#oneRow.currExp"/>
							</td>
							<td>
								<s:property value="#oneRow.posId"/>
							</td>
							<td>
								<s:property value="#oneRow.isLock"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
			 </div>
			 <div id="container3" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="rune_name" /></th>
						<th><s:text name="rune_level" /></th>
						<th><s:text name="rune_quality" /></th>
						<th><s:text name="rune_experience" /> </th>
				 		<th>符文属性 </th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="amuletList" var="oneRow"  status="offset">
			    		<tr>
							<td>
								<s:property value="#oneRow.amuletName"/> 
							</td>
							<td>
								<s:property value="#oneRow.amuletId%100"/>
							</td>
							<td>
								<s:property value="#oneRow.qualityLevel"/>
							</td>
							<td>
								<s:property value="#oneRow.currExp"/>
							</td>
							<td style="word-wrap: break-word;">
							<s:iterator value="#oneRow.prop" var="oneRow2"  status="offset">
							增加<s:if test="#oneRow2.propertyType==1">力量</s:if>
							<s:elseif test="#oneRow2.propertyType==2">体质</s:elseif>
							<s:elseif test="#oneRow2.propertyType==3">智力</s:elseif>
							<s:elseif test="#oneRow2.propertyType==4">敏捷</s:elseif>
							<s:elseif test="#oneRow2.propertyType==5">血量</s:elseif>
							<s:elseif test="#oneRow2.propertyType==6">附加伤害</s:elseif>
							<s:elseif test="#oneRow2.propertyType==7">物理攻击</s:elseif>
							<s:elseif test="#oneRow2.propertyType==8">物理攻击</s:elseif>
							<s:elseif test="#oneRow2.propertyType==9">物理防御</s:elseif>
							<s:elseif test="#oneRow2.propertyType==10">魔法防御</s:elseif>
							<s:elseif test="#oneRow2.propertyType==11">暴击</s:elseif>
							<s:elseif test="#oneRow2.propertyType==12">韧劲</s:elseif>
							<s:elseif test="#oneRow2.propertyType==13">普通攻击免伤值</s:elseif>
							<s:elseif test="#oneRow2.propertyType==14">法术攻击免伤值</s:elseif>
							<s:elseif test="#oneRow2.propertyType==15">命中</s:elseif>
							<s:elseif test="#oneRow2.propertyType==16">闪避</s:elseif>
							<s:property value="#oneRow2.propertyValue"/><br>
							</s:iterator>
							</td>
							 
						</tr>
			    	</s:iterator>
			    	<tr>
			    		<td><font><b>当前灵气</b></font></td>
			    		<td><s:property value="totalExp"/></td>
			    		<td></td>
			    		<td></td>
			    		<td></td>
			    	</tr>
			    	</tbody>
	    		</table>
			 </div>
		</div>
	</div>
</div>
