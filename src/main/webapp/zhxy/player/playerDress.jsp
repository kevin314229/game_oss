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
		<s:form action="/zhxy/achieve/playerDress_index.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				  <div class="searchDiv_left">
									<s:text name="player_names" />：
									<s:textfield id="nickName" name="nickName" cssClass="required "></s:textfield>
					</div>
			    	 <div class="searchButton2">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
						</ul>
					</div>
				</div>
		 </s:form>
	</div>
	
	
	 <div class="pageContent sortDrag" layoutH="100" selector="h1">
		<div class="panel collapse">
			<h1><s:text name="statistics_server_by_day" /></h1>
			<div>
			 <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="fashion_name" /></th>
			    		<th><s:text name="fashion_psychic_level" /></th>
						<th><s:text name="valid" /> </th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="dress" var="oneRow"  status="offset">
			    		<tr>
							<td>
								<s:property value="#oneRow.name"/>
							</td>
							<td>
							
								<s:property value="#oneRow.tongLingLv"/>
							</td>
							<td>
								<s:if test="#isForever==1"><s:text name="permanent" /></s:if><s:else>  
								<s:if test="#oneRow.valid<0">0 <s:text name="day" />0<s:text name="hour" /> </s:if><s:else>
								  <s:property value="#oneRow.valid/86400"/> <s:text name="day" /> <s:property value="#oneRow.valid%86400/3600"/><s:text name="hour" />
								  </s:else>
								   </s:else>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
	    		
	    	</div>
	    </div>
	    
		<div class="panel collapse">
			<h1><s:text name="property" /></h1>
			<div>
			 <table class="list" width="100%"  >
	    	<thead>
	    	<tr>
	    		 <td width="80px"><s:text name="training_experience" /></td>
	    		 <td><s:property value="dressLv.exp" /></td>
	    		 <td width="80px"><s:text name="training_level" /></td>
	    		 <td><s:property value="dressLv.lv" /></td>
	    		 <td width="80px"><s:text name="the_current_training_class" /></td>
	    		 <td><s:property value="dressLv.quality" /></td>
	    		 <td width="80px"><s:text name="force" /></td>
	    		 <td><s:property value="dressLv.strength" /></td>
	    	</tr>
	    	<tr>
	    		 <td><s:text name="intelligence" /></td>
	    		 <td><s:property value="dressLv.clever" /></td>
	    		 <td><s:text name="physical_relief" /></td>
	    		 <td><s:property value="dressLv.physicalDerate" /></td>
	    		 <td><s:text name="additional_damage" /></td>
	    		 <td><s:property value="dressLv.extraHarm" /></td>
	    		 <td><s:text name="blood" /></td>
	    		 <td><s:property value="dressLv.hp" /></td>
	    	</tr>
	    	<tr>
	    		 <td> <s:text name="physical_attacks" /></td>
	    		 <td><s:property value="dressLv.physicalAttack" /></td>
	    		 <td><s:text name="physical_relief" /></td>
	    		 <td><s:property value="dressLv.magicAttack" /></td>
	    		 <td><s:text name="magic_attack" /></td>
	    		 <td><s:property value="dressLv.extraHarm" /></td>
	    		 <td><s:text name="magic_defense" /></td>
	    		 <td><s:property value="dressLv.magicDefend" /></td>
	    	</tr>
	    	<tr>
	    		 <td> <s:text name="physical_defense" /></td>
	    		 <td><s:property value="dressLv.physicalDefend" /></td>
	    		 <td><s:text name="crit_value" /></td>
	    		 <td><s:property value="dressLv.critical" /></td>
	    		 <td><s:text name="dodge" /></td>
	    		 <td><s:property value="dressLv.avoid" /></td>
	    		 <td> <s:text name="tenacity" /></td>
	    		 <td><s:property value="dressLv.tenacity" /></td>
	    	</tr>
	    	<tr>
	    		 <td><s:text name="magic_relief" /></td>
	    		 <td><s:property value="dressLv.magicDerate" /></td>
	    		 <td><s:text name="hit" /></td>
	    		 <td><s:property value="dressLv.hit" /></td>
	    		 <td><s:text name="constitution" /></td>
	    		 <td><s:property value="dressLv.body" /></td>
	    		 <td></td>
	    		 <td></td>
	    	</tr>
	    	</thead>
	    	
	    </table>
	     
	    	</div>
	    </div>
</div>
	
