<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script>

function vilidateFile(){
    var b=document.getElementById("file_upload");
    if(b.value=="") {
    	alertMsg.error('<s:text name="please_select_upload_files" />！');
        return false;
    } 
    
 /*    else {
        var FileName=new String(b.value);//<s:text name="file_name" />
        var extension=new String (FileName.substring(FileName.lastIndexOf(".")+1,FileName.length));//<s:text name="file_extension" />
        if(extension=="xml"){
        	
        }else {
        	alertMsg.error('<s:text name="please_select" />xml<s:text name="file" />！');
            return false;
        }
    } */
    return true;
}

function refreshList(json){
	  if (json.statusCode == DWZ.statusCode.ok){  
		  alertMsg.correct(json.message);
	  }else{
		  alertMsg.error(json.message); 
	  }
	  navTab.reload("<%=basePath %>/admin/base/upload_index.action");  
}
</script>
	<s:include value="/admin/template/scriptMessage.jsp"/>
 <form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
	 
</form>
 <s:form action="upload_index" rel="pagerForm" method="post" name="queryForm" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
 </s:form> 
 <div class="pageHeader">
	<s:form action="upload_upload"  method="post"  enctype="multipart/form-data" 
		id="queryPayForm" theme="simple" cssClass="pageForm required-validate"  onsubmit="return vilidateFile()&&iframeCallback(this,refreshList)">
 	   <div class="searchBar" >
		   <div  class="searchDiv_left">
		   <s:file  name="upload" id="file_upload"  />
		   <s:text name="package_path" />：<input type="text" id="path" name="path" value="/admin/system" style="width:150px"> 
		 <input type="checkbox" name="isAbsolute" value="1"/> 是否绝对路径
		  </div>
	   <div class="searchButton2">
			<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="upload" /></button></div></div></li>
			</ul>
		 </div>
			</div>
	 </s:form>
	</div>
	
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="64" style="table-layout: fixed;">
	    <thead>
	    	<tr>
	    	    <th>ID</th>
	    	    <th><s:text name="account" /></th>
	    	    <th><s:text name="record" />IP</th>
	    	    <th>ip<s:text name="region" /></th>
	    	    <th><s:text name="operation_type" /></th>
	    	    <th><s:text name="operating_content" /></th>
	    	    <th><s:text name="operation_date" /></th>
	    	    <th><s:text name="name_of_the_server_area" /> </th>
	    	    <th><s:text name="name_of_the_operator" /> </th>
	    	   
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossLogList" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
						 <s:property value="#oneRow.id"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.name"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.loginIp"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.ipAddress"/>
		    	    </td>
		    	     <td>
		    	     
						 <s:property  value="@com.jcwx.game.admin.constant.OssLogConstant@maptype[#oneRow.operationType]" />
					
		    	    </td>
		    	     <td class="tdwarp" title="<s:property value="#oneRow.operationMsg"/>">
						 <s:property value="#oneRow.operationMsg"/>
		    	    </td>
		    	     <td>
						 <s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.createTime"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.serverName"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.businessName"/>
		    	    </td>
		    	   
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <s:include value="/admin/template/paging.jsp"/>
	 </div>