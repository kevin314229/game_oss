<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>






	
	<div class="pageContent">
		<table class="list" width="100%" layoutH="20">
			<thead>
				<tr class="even" >
					<td><s:text name="constellation_level" /></td>
					<td><s:text name="the_aries" />(<s:text name="life" />)</td>
					<td><s:text name="taurus" />(<s:text name="magic" />)</td>
					<td><s:text name="gemini" />(<s:text name="the_magic_of_attack" />)</td>
					<td><s:text name="the_cancer" />(<s:text name="content_of_attack" />)</td>
					<td><s:text name="the_lion" />(<s:text name="the_authors" />)</td>
					<td><s:text name="a_virgin" />(<s:text name="the_magic_against" />)</td>
					<td><s:text name="apparently" />(<s:text name="kill" />)</td>
					<td><s:text name="scorpio" />(<s:text name="crit" />)</td>
					<td><s:text name="striker" />(<s:text name="hit" />)</td>
					<td><s:text name="capricorn" />(<s:text name="toughness" />)</td>
					<td><s:text name="aquarius" />(<s:text name="guardian" />)</td>
					<td><s:text name="pisces" />(<s:text name="dodge" />)</td>
				</tr>
			</thead>
			<s:iterator value="ossConstellationList" status="offsets"  var="con">
				<tr>
				
					<td><s:property value="level" /> </td>
					<td><s:property value="type1" /> </td>
					<td><s:property value="type2" /> </td>
					<td><s:property value="type3" /> </td>
					<td><s:property value="type4" /> </td>
					<td><s:property value="type5" /> </td>
					<td><s:property value="type6" /> </td>
					<td><s:property value="type7" /> </td>
					<td><s:property value="type8" /> </td>
					<td><s:property value="type9" /> </td>
					<td><s:property value="type10" /> </td>
					<td><s:property value="type11" /> </td>
					<td><s:property value="type12" /> </td>
				</tr>
			</s:iterator>
	
		</table>
		
	</div>


