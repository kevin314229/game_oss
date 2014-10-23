<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

    <SCRIPT type="text/javascript">
       function verify(){
	       var playerIP = document.banIP_addBanIP.playerIP.value;
	       var re = /((?:(?:25[0-5]|2[0-4]\d|[01]?\d?\d)\.){3}(?:25[0-5]|2[0-4]\d|[01]?\d?\d))/;
	       if(!re.test(playerIP)){
	           alert("IP<s:text name="the_address_is_not_correct" />！");
	           return false;
	       }else{
	       	 return true;
	       }
       }
    </SCRIPT>

  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="banIP_addBanIP" namespace="/admin/base" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return  $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		    <table class="searchContent">
			<tr>
				<td>
					IP: <s:textfield name="playerIP"></s:textfield>
				</td>
				<td>
					<s:text name="due_to_the_time" />: <s:textfield name="endDate" id="endDate"  maxlength="10" size="12" cssClass="required date"></s:textfield>(23：59：59)
				</td>
			</tr>
			</table>
	    	<div class="subBar">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="add"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
	</div>
