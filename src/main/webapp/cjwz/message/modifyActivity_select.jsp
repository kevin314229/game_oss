<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<select class="combox" name="${param.inputName}">
	<option value="0"><s:text name="all" /></option>
	<option value="1">灵猴</option>
	<option value="2">神君</option>
	<option value="3">玄女</option>
</select>