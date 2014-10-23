<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
	<div class="pageHeader">
  		<div class="accountInfo">
			<div class="alertInfo">
				<p><s:text name="the_current_time_zone" />ID：<s:property value="@java.util.TimeZone@getDefault().getID()"/></p>
				<p><s:text name="the_current_time_zone_name" />：<s:property value="@java.util.TimeZone@getDefault().getDisplayName()"/></p>
			</div>
				<p>	<s:text name="keep" />OSS<s:text name="time_zone_consistent_with_the_server_time_zone" />,</p>
				<p><s:text name="if_the_different_zones" />，<s:text name="will_lead_to_abnormal_use_of_time-related_functions" />。</p>
			</div>
		</div>	
	<div>
	<div class="pageFormContent">
				    <s:form action="gmt_update" method="post" theme="simple" onsubmit="return $(this).valid()&&navTabSearch(this)">
					    <table>
					    	<tr>
					    	 <td><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="change_the_time_zone" /></button></div></div></td>
					    	    <td>
					    	    	<select name="gmtValue">
											<option value="">请设置您所在时区</option>
											<option value="GMT-12">(GMT-12.00)国际日期变更线西</option>
											<option value="GMT-11">(GMT-11.00)中途岛，萨摩亚群岛</option>
											<option value="GMT-10">(GMT-10.00)夏威夷</option>
											<option value="GMT-9">(GMT-9.00)阿拉斯加</option>
											<option value="GMT-8">(GMT-8.00)太平洋时间（美国和加拿大）；蒂华纳</option>
											<option value="GMT-7">(GMT-7.00)奇瓦瓦，拉巴斯，马扎特兰</option>
											<option value="GMT-7">(GMT-7.00)山地时间（美国和加拿大）</option>
											<option value="GMT-7">(GMT-7.00)亚利桑那</option>
											<option value="GMT-6">(GMT-6.00)瓜达拉哈拉，墨西哥城，蒙特雷</option>
											<option value="GMT-6">(GMT-6.00)萨斯喀彻温</option>
											<option value="GMT-6">(GMT-6.00)中部时间（美国和加拿大）</option>
											<option value="GMT-6">(GMT-6.00)中美洲</option>
											<option value="GMT-5">(GMT-5.00)波哥大，利马，基多</option>
											<option value="GMT-5">(GMT-5.00)东部时间（美国和加拿大）</option>
											<option value="GMT-5">(GMT-5.00)印第安那州（东部）</option>
											<option value="GMT-4">(GMT-4.00)大西洋时间（加拿大）</option>
											<option value="GMT-4">(GMT-4.00)加拉加斯，拉巴斯</option>
											<option value="GMT-4">(GMT-4.00)圣地亚哥</option>
											<option value="GMT-3">(GMT-3.00)纽芬兰</option>
											<option value="GMT-3">(GMT-3.00)巴西利亚</option>
											<option value="GMT-3">(GMT-3.00)布宜诺斯艾利斯，乔治敦</option>
											<option value="GMT-3">(GMT-3.00)格陵兰</option>
											<option value="GMT-2">(GMT-2.00)中大西洋</option>
											<option value="GMT-1">(GMT-1.00)佛得角群岛</option>
											<option value="GMT-1">(GMT-1.00)亚速尔群岛</option>
											<option value="GMT+0">(GMT)格林威治标准时间，都柏林，爱丁堡，伦敦，里斯本</option>
											<option value="GMT+0">(GMT)卡萨布兰卡，蒙罗维亚</option>
											<option value="GMT+1">(GMT+1.00)阿姆斯特丹，柏林，伯尔尼，罗马，斯德哥尔摩，维也纳</option>
											<option value="GMT+1">(GMT+1.00)贝尔格莱德，布拉迪斯拉发，布达佩斯，卢布尔雅那，布拉格</option>
											<option value="GMT+1">(GMT+1.00)布鲁塞尔，哥本哈根，马德里，巴黎</option>
											<option value="GMT+1">(GMT+1.00)萨拉热窝，斯科普里，华沙，萨格勒布</option>
											<option value="GMT+1">(GMT+1.00)中非西部</option>
											<option value="GMT+2">(GMT+2.00)布加勒斯特</option>
											<option value="GMT+2">(GMT+2.00)哈拉雷，比勒陀利亚</option>
											<option value="GMT+2">(GMT+2.00)赫尔辛基，基辅，里加，索非亚，塔林，维尔纽斯</option>
											<option value="GMT+2">(GMT+2.00)开罗</option>
											<option value="GMT+2">(GMT+2.00)雅典，贝鲁特，伊斯坦布尔，明斯克</option>
											<option value="GMT+2">(GMT+2.00)耶路撒冷</option>
											<option value="GMT+3">(GMT+3.00)巴格达</option>
											<option value="GMT+3">(GMT+3.00)科威特，利雅得</option>
											<option value="GMT+3">(GMT+3.00)莫斯科，圣彼得堡，伏尔加格勒</option>
											<option value="GMT+3">(GMT+3.00)内罗毕</option>
											<option value="GMT+3">(GMT+3.00)德黑兰</option>
											<option value="GMT+4">(GMT+4.00)阿布扎比，马斯喀特</option>
											<option value="GMT+4">(GMT+4.00)巴库，第比利斯，埃里温</option>
											<option value="GMT+4.5">(GMT+4.30)喀布尔</option>
											<option value="GMT+5">(GMT+5.00)叶卡捷琳堡</option>
											<option value="GMT+5">(GMT+5.00)伊斯兰堡，卡拉奇，塔什干</option>
											<option value="GMT+5.5">(GMT+5.30)马德拉斯，加尔各答，孟买，新德里</option>
											<option value="GMT+5.75">(GMT+5.45)加德满都</option>
											<option value="GMT+6">(GMT+6.00)阿拉木图，新西伯利亚</option>
											<option value="GMT+6">(GMT+6.00)阿斯塔纳，达卡</option>
											<option value="GMT+6">(GMT+6.00)斯里哈亚华登尼普拉</option>
											<option value="GMT+6">(GMT+6.30)仰光</option>
											<option value="GMT+7">(GMT+7.00)克拉斯诺亚尔斯克</option>
											<option value="GMT+7">(GMT+7.00)曼谷，河内，雅加达</option>
											<option value="GMT+8" selected>(GMT+8.00)北京，重庆，香港特别行政区，乌鲁木齐，台北</option>
											<option value="GMT+8">(GMT+8.00)吉隆坡，新加坡</option>
											<option value="GMT+8">(GMT+8.00)珀斯</option>
											<option value="GMT+8">(GMT+8.00)伊尔库茨克，乌兰巴图</option>
											<option value="GMT+9">(GMT+9.00)大坂，东京，札幌</option>
											<option value="GMT+9">(GMT+9.00)汉城</option>
											<option value="GMT+9">(GMT+9.00)雅库茨克</option>
											<option value="GMT+9.5">(GMT+9.30)阿德莱德</option>
											<option value="GMT+9.5">(GMT+9.30)达尔文</option>
											<option value="GMT+10">(GMT+10.00)布里斯班</option>
											<option value="GMT+10">(GMT+10.00)符拉迪沃斯托克（海参崴）</option>
											<option value="GMT+10">(GMT+10.00)关岛，莫尔兹比港</option>
											<option value="GMT+10">(GMT+10.00)霍巴特</option>
											<option value="GMT+10">(GMT+10.00)堪塔拉，墨尔本，悉尼</option>
											<option value="GMT+11">(GMT+11.00)马加丹，索罗门群岛，新喀里多尼亚</option>
											<option value="GMT+12">(GMT+12.00)奥克兰，惠灵顿</option>
											<option value="GMT+12">(GMT+12.00)斐济，堪察加半岛，马绍尔群岛</option>
											<option value="GMT+13">(GMT+13.00)努库阿洛法</option>

									</select>
					    	    </td>
					   	</tr>
					    
					    </table>
				    </s:form>
			</div>