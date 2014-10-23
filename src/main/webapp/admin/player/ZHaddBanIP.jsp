<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
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
       
       function onSelect(){
    	 // var url = "ZHbanIP_select.action";
    	   // ipQueryForm.action=url;
    		//ipQueryForm.submit();
    		var form = $(ipQueryForm);
    		$.pdialog.reload("/zhxy/base/ZHbanIP_select.action",{data:form.serializeArray()});
    		<%-- ipQueryForm.action= "<%=basePath%>/zhxy/base/ZHbanIP_addBanIP.action";   --%>
       }
       function onSubmit(){
      	 // var url = "ZHbanIP_select.action";
      	   // ipQueryForm.action=url;
      		//ipQueryForm.submit();
      		
      		var form = $(ipQueryForm);
      		if(form.valid()){
      			//$.pdialog.reload("/zhxy/base/ZHbanIP_select.action",{data:form.serializeArray()});
      			$.pdialog.reload("/zhxy/base/ZHbanIP_addBanIP.action",{data:form.serializeArray()});
      		}
      		
         }
    </SCRIPT>
<s:head />
<s:include value="/admin/template/scriptMessage.jsp" />
<div class="pageHeader">
	<s:form action="ZHbanIP_addBanIP" id="ipQueryForm" name="ipQueryForm"
		namespace="/zhxy/base" method="post" theme="simple"
		cssClass="pageForm required-validate"
		onsubmit="return  $(this).valid()&&dialogSearch(this)">
		<div class="searchBar">
			<div class="searchDiv_left">
				<label style="width: 100px; text-align: right;">IP:</label> <input
					type="text" name="playerIP"
					value="<s:property value="loginlog.ip"/>">
				<s:text name="due_to_the_time" />
				:
				<s:textfield name="endDate" id="endDate" maxlength="10" size="12"
					cssClass="required date"></s:textfield>
				(23：59：59)
			</div>
			<div class="searchButton2">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="onSubmit()">
									<s:text name="add" />
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="searchBar" style="margin-top: 50px">
			<div class="searchDiv_left">
				<label style="width: 100px;"><s:text name="players_account" />、<s:text name="players_name" />:</label><input type="text"
					name="playerId">
			</div>
			<div class="searchButton2">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="onSelect()"><s:text name="inquiry" /></button>
							</div>
						</div>
					</li>
				</ul>

			</div>
		</div>

	</s:form>

	<s:if test="loginlog!=null">
		<div class="pageContent">
			<table class="list" width="100%" layoutH="90">
				<thead>
					<tr>
						<th><s:text name="players_account" /></th>
						<th><s:text name="players_name" /></th>
						<th>IP</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><s:property value="loginlog.loginName" /></td>
						<td><s:property value="loginlog.nickName" /></td>
						<td><s:property value="loginlog.ip" /></td>
						<%-- <td><s:property value="login."/></td> --%>
					</tr>
				</tbody>
			</table>
		</div>
	</s:if>
</div>
