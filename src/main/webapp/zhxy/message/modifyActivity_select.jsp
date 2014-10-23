<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<select class="combox" name="${param.inputName}">
	<option value="0"><s:text name="all" /></option>
	<option value="1"><s:text name="monkey" /></option>
	<option value="2"><s:text name="gods" /></option>
	<option value="3"><s:text name="xuannv" /></option>
</select>