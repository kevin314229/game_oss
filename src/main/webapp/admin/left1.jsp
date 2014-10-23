<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
  <head>
    <title><s:text name="navigation" /></title>
 	<SCRIPT src="<%=basePath%>/media/default/js/jquery-1.5.1.min.js" type=text/javascript></SCRIPT>
	<SCRIPT src="<%=basePath%>/media/default/js/jquery.cookie.js" type=text/javascript></SCRIPT> 
	<SCRIPT src="<%=basePath%>/media/default/js/jquery.accordion.menu.js" type=text/javascript></SCRIPT>
	<LINK href="<%=basePath%>/media/default/css/jquery.accordion.menu.css" type=text/css rel=stylesheet>
	<style type="text/css">
	.title_other{
		background: #8FC6E3; background-color: #8FC6E3; 
	}
	
	
	</style>
	
	<script type="text/javascript">
		var aId="";
		function check(obj){
			/* if(aId!=""){
			    document.getElementById(aId).className="";
			}
		    document.getElementById(obj.id).className="title_other";
		    aId=obj.id;  */
 			if(aId!=""){
			    //document.getElementById(aId).style.backgroundImage="url(http://www.iteye.com/upload/logo/user/37073/648c9ed4-f54b-3f4a-ac54-905f5a483307.gif)";
			   document.getElementById(aId).style.backgroundColor="#CAE4F1";
			    /* document.getElementById(aId).style.background="#CBE5F2;"; */
			}
		    document.getElementById(obj.id).style.backgroundColor="#8FC6E3";
		    aId=obj.id; 
		}
	</script>
  <base target="mainFrame">
  </head>
  <body>
	<UL class="menu expandfirst" id=menu2>
	  <s:iterator value="ossMenus" var="oneRow"  status="offset">
	    		<s:if test="#oneRow.pageUrl ==null||#oneRow.pageUrl eq ''">
	    		   <s:if test="#offset.index != 0">
	    		      </UL>
	  				</LI>
	    		   </s:if>
	    		   <LI><A class=m${offset.index}  href="#">${oneRow.name}</A>
	    		    <UL>
	    		</s:if>
	    		<s:else><LI><A id="a${offset.index}" onclick="check(this)" href="<%=basePath%>${oneRow.pageUrl}.action">${oneRow.name}</A></s:else>
	  </s:iterator>
	  	</ul>
	  	</LI>
	  </UL>
  </body>
</html>
