<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<script type="text/javascript" language="javascript">
		function deleteCache(key,nickName) {
			if (confirm("<s:text name="are_you_sure_you_want_to_delete_the_user_cache" />？")){
				self.location ="<%=basePath %>/admin/base/playerCacheManage_modify.action?key="+key+"&nickName="+nickName;
			}
		}
		
		
		function modifyAll(){
			alertMsg.confirm(" <s:text name="warning" />！  <s:text name="are_you_sure_you_put_all_the_players_kicked_off_the_assembly_line" />  ？  ",{okCall:function (){
				$.ajax({
		            type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
		            //dataType: "json",//<s:text name="return" />json<s:text name="data_format" />
		            url: "<%=basePath%>/admin/base/playerCacheManage_modifyAll.action",
		            cache: false,
		            data: null,//<s:text name="data_to_be_sent" />
		            success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
		            	if(msg=="ok"){
		            		alertMsg.correct('<s:text name="successful_operation" />！');                                                                                                                                                                                                                                                                                                                   
		            	}else{
		            		alertMsg.error("<s:text name="operation_failed" />，<s:text name="please_try_again_later" />..");
		            	}
		            }
			   });
			}});
		}
		
</script>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="playerCacheManage_index" namespace="/admin/base" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		     <div class="searchDiv_left">
					Key： <s:textfield id="key" name="Key"  size="20"></s:textfield>
			 
					<%-- <s:text name="players_account" /> --%><s:text name="the" />Key(<s:text name="or_role_name" />)：<s:textfield id="nickName" name="nickName"  size="20"></s:textfield>
			 </div>
	    	<div class="searchButton2">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
				</ul>
			</div>
			
			<div class="searchButton2">
				<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="modifyAll()" value=""><s:text name="kick_all_players" />(<s:text name="no_title" />)</button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
	 
</div>

<div class="pageContent" layoutH="0" >
	    <table class="list" width="100%" >
	    	<thead>
		    <tr>
	    	    <th><s:text name="the_upper" />Key</th>
	    	    <th><s:text name="this_layer" />Key</th>
	    	    <th><s:text name="the_name_of_the" /></th>
	    	    <th><s:text name="describe" /></th>
	    	    <th><s:text name="creation_time" /></th>
	    	    <th><s:text name="destruction_of_time" /></th>
	    	    <th><s:text name="whether_can_be_manually_removed" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
			<s:iterator value="ossCachesList" status="offsets" var="oneRow">
			<tr >
		    	    <td><s:property value="topKey"/></td>
		    	    <td><s:property value="key"/> <s:property value="name"/>  </td>
		    	    <td>
		    	    <s:property value="@com.jcwx.game.common.constants.CacheConstant@getTypeLabel('playerOnlineCache')"/>
		    	    </td>
		    	    <td><s:property value="describe"/> </td>
		    	    <td><s:property value="createTime"/> </td>
		    	    <td><s:property value="disposeTime"/> </td>
		    	    <s:if test="isClear==1"> 
		    	    <td>
		    	    <a href="javascript:deleteCache('${oneRow.topKey}','${oneRow.name }')" style="color:green"><s:text name="remove" /></a>
		    	    </td>
		    	    </s:if>
		    	    <s:else>
		    	    <td>
		    	   	<s:text name="no" />
		    	    </td>
		    	    </s:else> 
		    </tr>
			</s:iterator>
			</tbody>
		</table>
</div>

