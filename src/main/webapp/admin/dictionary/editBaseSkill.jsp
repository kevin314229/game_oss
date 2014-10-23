<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改技能信息</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
    <script language="javascript">
		function checkForm(o) {
			if(o.roleCode.value == ''){
				alert("请输入角色编号！"); return false;
			}
			if(o.roleName.value == ''){
				alert("请输入角色名称！"); return false;
			}
			if(o.roleDesc.value == ''){
				alert("请输入角色说明！"); return false;
			}
			
			return true;
		}
	</script>
  </head>
  
  <body>
  	<div id="container">
  		<div id="divLeft">
			<b>数据字典：修改技能信息</b>
			<span class="color-red"><s:property value="errorInfo"/></span>
			<span class="color-gr"><s:property value="successInfo"/></span>
		</div>
		<div id="divLeft">
	    <s:form action="updateBaseSkill" method="post" theme="simple">
		技能名称：<s:textfield name="name" />&nbsp;&nbsp;&nbsp;&nbsp;
		技能ID：<s:textfield name="skillId" />&nbsp;&nbsp;&nbsp;&nbsp;
		释放速度：<s:textfield name="speed" />&nbsp;&nbsp;&nbsp;&nbsp;
		成长值：<s:textfield name="growup" />&nbsp;&nbsp;&nbsp;&nbsp;
		<br/><br/>
         技能类型：<s:select list="#{'1':'基本武学','2':'门派武学','3':'江湖绝学','4':'江湖武学','11':'生活技能'}" 
         name="skillType" listKey="key" listValue="value"></s:select>
                &nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        武学类型：<s:select list="#{'1':'剑法','2':'刀法','3':'棍法','4':'杖法','5':'拳脚','101':'内功','102':'轻功','0':'无效'}" 
         name="martialType" listKey="key" listValue="value"></s:select>
                &nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        所属门派：<s:select list="#{'1':'华山派','2':'少林派','3':'血刀门','4':'白驼山','0':'无效'}" 
         name="school" listKey="key" listValue="value"></s:select>
		<br/><br/>
		技能描述：<s:textarea name="description" cols="50" rows="5"></s:textarea>
		<br/><br/>
		    	<s:submit value="确认修改" style="cursor:hand"></s:submit>
		    	<s:reset value="重置" style="cursor:hand"></s:reset>
		    	<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseSkill.action','_self');">
	    </s:form>
	    </div>
    </div>
  </body>
</html>