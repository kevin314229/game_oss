<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

	<script type="text/javascript" language="javascript">
		function send(){
			if(confirm("<s:text name="are_you_sure_the_corps'_data_adjustment" />？")){
				return true;
			}
			return false;
		}
	</script>
 <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
  	<div>
		<s:form action="initGame_initArmy" method="post" theme="simple" cssClass="pageForm required-validate" namespace="/admin/initGame" onsubmit="return send()&&navTabSearch(this)">
		    <div class="subBar">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="bianconeri_interface_data_adjustment"/></button></div></div></li>
					<li>
						<s:text name="the_game_developers" /> <s:text name="do_not_operate" /> 
						<label style="color: red;"> <s:property value="%{result}"/> </label>
					</li>
				</ul>
		 </div>
		</s:form>
	</div>
</div>