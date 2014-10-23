<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
			long timePageStart = System.currentTimeMillis();		
%>




<%


	class EnvServlet {
		public long timeUse = 0;
		public Hashtable htParam = new Hashtable();
		private Hashtable htShowMsg = new Hashtable();

		public void setHashtable() {
			Properties me = System.getProperties();
			Enumeration em = me.propertyNames();
			while (em.hasMoreElements()) {
				String strKey = (String) em.nextElement();
				String strValue = me.getProperty(strKey);
				htParam.put(strKey, strValue);
			}
		}

		public void getHashtable(String strQuery) {
			Enumeration em = htParam.keys();
			while (em.hasMoreElements()) {
				String strKey = (String) em.nextElement();
				String strValue = new String();
				if (strKey.indexOf(strQuery, 0) >= 0) {
					strValue = (String) htParam.get(strKey);
					htShowMsg.put(strKey, strValue);
				}
			}
		}

		public String queryHashtable(String strKey) {
			strKey = (String) htParam.get(strKey);
			return strKey;
		}

		public long test_int() {
			long timeStart = System.currentTimeMillis();
			int i = 0;
			while (i < 3000000)
				i++;
			long timeEnd = System.currentTimeMillis();
			long timeUse = timeEnd - timeStart;
			return timeUse;
		}

		public long test_sqrt() {
			long timeStart = System.currentTimeMillis();
			int i = 0;
			double db = (double) new Random().nextInt(1000);
			while (i < 200000) {
				db = Math.sqrt(db);
				i++;
			}
			long timeEnd = System.currentTimeMillis();
			long timeUse = timeEnd - timeStart;
			return timeUse;
		}
	}
%>
<%
	EnvServlet env = new EnvServlet();
	env.setHashtable();
	String action = new String(" ");
	String act = new String("action");
	if (request.getQueryString() != null
			&& request.getQueryString().indexOf(act, 0) >= 0)
		action = request.getParameter(act);
%>






<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
   <title>系统管理页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type=text/css>
body { background-color:#D6D3CE; font-size: 12px; margin-top:0px;

}
TD {
	FONT-SIZE: 12px
}
INPUT {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
TEXTAREA {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
SELECT {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
SPAN {
	FONT-SIZE: 12px; POSITION: static
}
A:link {
	COLOR: #000000; TEXT-DECORATION: none
}
A:visited {
	COLOR: #000000; TEXT-DECORATION: none
}
a:hover  { color:#428EFF;text-decoration:underline; }
A.highlight:link {
	COLOR: red; TEXT-DECORATION: none
}
A.highlight:visited {
	COLOR: red; TEXT-DECORATION: none
}
A.highlight:hover {
	COLOR: red
}
A.thisclass:link {
	FONT-WEIGHT: bold; TEXT-DECORATION: none
}
A.thisclass:visited {
	FONT-WEIGHT: bold; TEXT-DECORATION: none
}
A.thisclass:hover {
	FONT-WEIGHT: bold
}
A.navlink:link {
	COLOR: #000000; TEXT-DECORATION: none
}
A.navlink:visited {
	COLOR: #000000; TEXT-DECORATION: none
}
A.navlink:hover {
	COLOR: #003399; TEXT-DECORATION: none
}
.twidth {
	WIDTH: 760px
}
.content {
	FONT-SIZE: 14px; MARGIN: 5px 20px; LINE-HEIGHT: 140%; FONT-FAMILY: Tahoma,宋体
}
.aTitle {
	FONT-WEIGHT: bold; FONT-SIZE: 15px
}
TD.forumHeaderBackgroundAlternate {
	BACKGROUND-IMAGE: url(./images/admin_top_bg.gif); COLOR: #000000; BACKGROUND-COLOR: #799ae1
}
#TableTitleLink A:link {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:visited {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:active {
	COLOR: #ffffff; TEXT-DECORATION: none
}
#TableTitleLink A:hover {
	COLOR: #ffffff; TEXT-DECORATION: underline
}
TD.forumRow {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; BACKGROUND: #F1F3F5; PADDING-BOTTOM: 3px; PADDING-TOP: 3px
}
TH {
	FONT-WEIGHT: bold; FONT-SIZE: 12px; BACKGROUND-IMAGE: url(./images/admin_bg_1.gif); COLOR: white; BACKGROUND-COLOR: #4455aa
}
TD.bodytitle {
	BACKGROUND-IMAGE: url(./images/admin_bg_2.gif)
}
TD.bodytitle1 {
	BACKGROUND-IMAGE: url(./images/admin_bg_3.gif)
}
TD.tablebody1 {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; BACKGROUND: #bebbdb; PADDING-BOTTOM: 3px; PADDING-TOP: 3px
}
TD.forumRowHighlight {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; BACKGROUND: #E4EDF9; PADDING-BOTTOM: 3px; PADDING-TOP: 3px
}
.tableBorder {
	BORDER-RIGHT: #183789 1px solid; BORDER-TOP: #183789 1px solid; BORDER-LEFT: #183789 1px solid; WIDTH: 98%; BORDER-BOTTOM: #183789 1px solid; BACKGROUND-COLOR: #ffffff
}
.tableBorder1 {WIDTH: 98%;}

.helplink {
	FONT: 10px verdana,arial,helvetica,sans-serif; CURSOR: help; TEXT-DECORATION: none
}
.copyright {
	PADDING-RIGHT: 1px; BORDER-TOP: #6595d6 1px dashed; PADDING-LEFT: 1px; PADDING-BOTTOM: 1px; FONT: 11px verdana,arial,helvetica,sans-serif; COLOR: #4455aa; PADDING-TOP: 1px; TEXT-DECORATION: none

}
</style>
<style>
A       { COLOR: #000000; TEXT-DECORATION: none}
A:hover { COLOR: #003399}
body,td,span { font-size: 9pt}
.input  { BACKGROUND-COLOR: #ffffff;BORDER:#003399 1px solid;FONT-SIZE: 9pt}
.backc  { BACKGROUND-COLOR: #739ADE;BORDER:#003399 1px solid;FONT-SIZE: 9pt;color:white}
.PicBar { background-color: #74B7EB; border: 1px solid #000000; height: 12px;}
.PicBar2 { background-color: #EB3838; border: 1px solid #000000; height: 12px;}
.PicBar3 { background-color: green; border: 1px solid #000000; height: 12px;}
.tableBorder {BORDER-RIGHT: #183789 1px solid; BORDER-TOP: #183789 1px solid; BORDER-LEFT: #183789 1px solid; BORDER-BOTTOM: #183789 1px solid; BACKGROUND-COLOR: #ffffff; WIDTH: 760;}
.divcenter {
	position:absolute;
	height:30px;
	z-index:1000;
	left: 101px;
	top: 993px;
}
</STYLE>
<script language="javascript">
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
eval("txt" + sid + ".innerHTML=\"<a href='#' title='关闭此项'><font face='Wingdings' color=#FFFFFF>x</font></a>\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
eval("txt" + sid + ".innerHTML=\"<a href='#' title='打开此项'><font face='Wingdings' color=#FFFFFF>y</font></a>\";");
}
}
</SCRIPT>
	

  </head>
  
<body >
  <CENTER>
			<table class="tableBorder" width="760" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<th width="30%" height="23" valign="middle"
						class="tableHeaderText">
						<STRONG>服务器测试结果</STRONG>
					</th>
				</tr>
				<tr>
					<td valign="middle">
						选项：
						<a href="#ServerInfo">服务器相关参数</a> |
						<a href="#JAVAInfo">JAVA相关参数</a> |
						<a href="#Paramter">参数查询</a> |
						<a href="#ServerAbility">服务器运算能力</a> |
						<a href="#ISpeedTest">服务器连接速度</a> |
						<a href="javascript:location.reload()">刷新</a><a name="ServerInfo"></a>
				</tr>
				<tr>
			
					<td>
						<table border="0" cellpadding="0" cellspacing="1"
							class="tableBorder">	
							<!-- 服务器相关参数 -->
							<tr>
								<th height="22" class="tableHeaderText"
									onclick="showsubmenu(0)">
									<font color=#FFFFFF><strong>服务器相关参数</strong>
									</font>

									<a href="#top" title="返回顶部"><font face='Webdings'
										color=#FFFFFF>5</font>
									</a>
									<span id=txt0 name=txt0><a href='#' title='关闭此项'><font
											face='Wingdings' color=#FFFFFF>x</font>
									</a>
									</span>
								</th>
							</tr>

							<tr>
								<td style="" id='submenu0'>
									<table border=0 width=100% cellspacing=1 cellpadding=3
										bgcolor="#003399">
										<tr bgcolor="#FFFFFF" height="22">
											<td width="130">
												&nbsp;服务器名
											</td>
											<td colspan="3" height="22">
												&nbsp;<%=request.getServerName()%>(<%=request.getRemoteAddr()%>)
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td>
												&nbsp;服务器操作系统
											</td>
											<td colspan="3">
												&nbsp;<%=env.queryHashtable("os.name")%>
												<%=env.queryHashtable("os.version")%>
												<%=env.queryHashtable("sun.os.patch.level")%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td>
												&nbsp;服务器操作系统类型
											</td>
											<td>
												&nbsp;<%=env.queryHashtable("os.arch")%></td>
											<td>
												&nbsp;服务器操作系统模式
											</td>
											<td>
												&nbsp;<%=env.queryHashtable("sun.arch.data.model")%>位
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td>
												&nbsp;服务器所在地区
											</td>
											<td>
												&nbsp;<%=env.queryHashtable("user.country")%></td>
											<td>
												&nbsp;服务器语言
											</td>
											<td>
												&nbsp;<%=env.queryHashtable("user.language")%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td>
												&nbsp;服务器时区
											</td>
											<td>
												&nbsp;<%=env.queryHashtable("user.timezone")%></td>
											<td>
												&nbsp;服务器时间
											</td>
											<td>
												&nbsp;<%=new java.util.Date()%>
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td>
												&nbsp;服务器解译引擎
											</td>
											<td width="170">
												&nbsp;<%=getServletContext().getServerInfo()%></td>
											<td width="130">
												&nbsp;服务器端口
											</td>
											<td width="170">
												&nbsp;<%=request.getServerPort()%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td height="22">
												&nbsp;当前用户
											</td>
											<td height="22" colspan="3">
												&nbsp;<%=env.queryHashtable("user.name")%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td>
												&nbsp;用户目录
											</td>
											<td colspan="3">
												&nbsp;<%=env.queryHashtable("user.dir")%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td align=left bgcolor="#FFFFFF">
												&nbsp;本文件实际路径
											</td>
											<td height="8" colspan="3" bgcolor="#FFFFFF">
												&nbsp;<%=request.getRealPath(request.getServletPath())%></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>


						<!-- Java运行环境 -->
						<a name="JAVAInfo" id="JAVAInfo"></a>
						<br>
						<table border="0" cellpadding="0" cellspacing="1"
							class="tableBorder">
							<tr>
								<th height="22" class="tableHeaderText"
									onclick="showsubmenu(1)">
									<font color=#FFFFFF><strong>JAVA相关参数</strong>
									</font>
									<a href="#top" title="返回顶部"><font face='Webdings'
										color=#FFFFFF>5</font>
									</a>
									<span id=txt1 name=txt1><a href='#' title='关闭此项'><font
											face='Wingdings' color=#FFFFFF>x</font>
									</a>
									</span>
								</th>
							</tr>
							<tr>
								<td style="" id='submenu1'>
									<table border=0 width=100% cellspacing=1 cellpadding=3
										bgcolor="#003399">
										<tr bgcolor="#739ADE" height="22">
											<td width="30%">
												&nbsp;名称
											</td>
											<td width="50%" height="22">
												&nbsp;英文名称
											</td>
											<td width="20%" height="22">
												&nbsp;版本
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;JAVA运行环境名称
											</td>
											<td width="50%" height="22">
												&nbsp;<%=env.queryHashtable("java.runtime.name")%></td>
											<td width="20%" height="22">
												&nbsp;<%=env.queryHashtable("java.runtime.version")%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;JAVA运行环境说明书名称
											</td>
											<td width="50%" height="22">
												&nbsp;<%=env.queryHashtable("java.specification.name")%></td>
											<td width="20%" height="22">
												&nbsp;<%=env.queryHashtable("java.specification.version")%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;JAVA虚拟机名称
											</td>
											<td width="50%" height="22">
												&nbsp;<%=env.queryHashtable("java.vm.name")%></td>
											<td width="20%" height="22">
												&nbsp;<%=env.queryHashtable("java.vm.version")%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;JAVA虚拟机说明书名称
											</td>
											<td width="50%" height="22">
												&nbsp;<%=env.queryHashtable("java.vm.specification.name")%></td>
											<td width="20%" height="22">
												&nbsp;<%=env.queryHashtable("java.vm.specification.version")%></td>
										</tr>
										<%
											float fFreeMemory = (float) Runtime.getRuntime().freeMemory();
											float fTotalMemory = (float) Runtime.getRuntime().totalMemory();
											float maxMemory = (float) Runtime.getRuntime().maxMemory();
											
											float fPercent2 = fTotalMemory / maxMemory * 100; //最大
											float fPercent = fFreeMemory / fTotalMemory *(fTotalMemory / maxMemory) * 100 ; //已分配
											//float fPercent = fFreeMemory / fTotalMemory *(fTotalMemory / maxMemory) * 100 ; //
											
										%>
										<tr bgcolor="#FFFFFF" height="22">
											<td height="22">
												&nbsp;JAVA虚拟机已使用内存(已分配-比例)：
											</td>
											<td height="22" colspan="2">
												<img align=absmiddle class=PicBar2
													width='<%=0.85 * ((fTotalMemory- fFreeMemory )/ fTotalMemory *(fTotalMemory / maxMemory) * 100)%>%'>
												&nbsp;<%=(fTotalMemory- fFreeMemory) / 1024 / 1024%>M
											</td>
										</tr>
										
										
										
										
										
										
										<tr bgcolor="#FFFFFF" height="22">
											<td height="22">
												&nbsp;JAVA虚拟机剩余内存：
											</td>
											<td height="22" colspan="2">
												<img align=absmiddle class=PicBar3
													width='<%=0.85 * fPercent%>%'>
												&nbsp;<%=fFreeMemory / 1024 / 1024%>M
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td height="22">
												&nbsp;JAVA虚拟机分配内存
											</td>
											<td height="22" colspan="2">
												<img align=absmiddle class=PicBar width='<%=0.85*fPercent2%>%'>
												&nbsp;<%=fTotalMemory / 1024 / 1024%>M
											</td>
										</tr>
										
										<tr bgcolor="#FFFFFF" height="22">
											<td height="22">
												&nbsp;JAVA虚拟机最大内存：
											</td>
											<td height="22" colspan="2">
												<img align=absmiddle class=PicBar
													width='85%'>
												&nbsp;<%=maxMemory / 1024 / 1024%>M
											</td>
										</tr>
									</table>
									<table border=0 width=100% cellspacing=1 cellpadding=3
										bgcolor="#003399">
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;参数名称
											</td>
											<td width="70%" height="22">
												&nbsp;参数路径
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;java.class.path
											</td>
											<td width="70%" height="22">
												&nbsp;<%=env.queryHashtable("java.class.path")
							.replaceAll(
									env.queryHashtable("path.separator"),
									env.queryHashtable("path.separator")
											+ "<br>&nbsp;")%>
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;java.home
											</td>
											<td width="70%" height="22">
												&nbsp;<%=env.queryHashtable("java.home")%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;java.endorsed.dirs
											</td>
											<td width="70%" height="22">
												&nbsp;<%=env.queryHashtable("java.endorsed.dirs")%></td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;java.library.path
											</td>
											<td width="70%" height="22">
												&nbsp;<%=env.queryHashtable("java.library.path")
							.replaceAll(
									env.queryHashtable("path.separator"),
									env.queryHashtable("path.separator")
											+ "<br>&nbsp;")%>
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td width="30%">
												&nbsp;java.io.tmpdir
											</td>
											<td width="70%" height="22">
												&nbsp;<%=env.queryHashtable("java.io.tmpdir")%></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

						<!-- 服务器运算 -->
						<a name="Paramter" id="Paramter"></a>
						<br>
						<form action="?action=query" method="post" name="queryform">
							<table border="0" cellpadding="0" cellspacing="1"
								class="tableBorder">
								<tr>
									<th height="22" class="tableHeaderText"
										onclick="showsubmenu(2)">
										<font color=#FFFFFF><strong>参数查询</strong>
										</font>
										<a href="#top" title="返回顶部"><font face='Webdings'
											color=#FFFFFF>5</font>
										</a>
										<span id=txt2 name=txt2><a href='#' title='关闭此项'><font
												face='Wingdings' color=#FFFFFF>x</font>
										</a>
										</span>
									</th>
								</tr>
								<tr>
									<td style="" id='submenu2'>
										<table border=0 width=100% cellspacing=1 cellpadding=3
											bgcolor="#003399">
											<tr bgcolor="#ffffff" height="22">
												<td>
													&nbsp;请查询系统的参数信息(
													<a href="#" onClick="document.queryform.submit()">枚举所有参数信息</a>)
												</td>
											</tr>
											<tr bgcolor="#FFFFFF" height="22">
												<td align=center>
													<input type="text" name="query" size="70">
													&nbsp;&nbsp;
													<input type="submit" value="提交">
													&nbsp;
													<input type="reset" value="重置">
												</td>
											</tr>
										</table>
										<table border=0 width=100% cellspacing=1 cellpadding=3
											bgcolor="#003399">
											<%
												if (action.equals("query")) {
													String query = request.getParameter("query");
													env.getHashtable(query);
													if (env.htShowMsg.size() > 0)
														out
																.println("<tr bgcolor=\"#fcc79b\" height=\"22\">\n<td>&nbsp;参数名称</td>\n<td>&nbsp;参数信息</td>\n</tr>");
													else
														out
																.println("<tr bgcolor=\"#fcc79b\" height=\"22\">\n<td>&nbsp;<font color=red>出错信息：</font></td>\n<td>&nbsp;<font color=red>没有找到你所查询的内容，请输入所要查询的参数，如果不确认，可以进行抽象查询，输入所包含字母。</font></td>\n</tr>");
													Enumeration em = env.htShowMsg.keys();
													while (em.hasMoreElements()) {
														String strParam = (String) em.nextElement();
														String strParamValue = (String) env.htShowMsg.get(strParam);
														if (strParam.indexOf(".path", 0) >= 0) {
															strParamValue = strParamValue.replaceAll(env
																	.queryHashtable("path.separator"), env
																	.queryHashtable("path.separator")
																	+ "<br>&nbsp;");
														}
														out
																.println("<tr bgcolor=\"#FFFFFF\" height=\"22\"><td width=\"30%\">&nbsp;"
																		+ strParam
																		+ "</td><td width=\"70%\">&nbsp;"
																		+ strParamValue + "</td></tr>");
													}
												}
											%>
										</table>
									</td>
								</tr>
							</table>
						</form>
						<a name="ServerAbility" id="ServerAbility"></a>
						<table border="0" cellpadding="0" cellspacing="1"
							class="tableBorder">
							<tr>
								<th height="22" class="tableHeaderText"
									onclick="showsubmenu(3)">
									<font color=#FFFFFF><strong>服务器运算能力</strong>
									</font>
									<a href="#top" title="返回顶部"><font face='Webdings'
										color=#FFFFFF>5</font>
									</a>
									<span id=txt3 name=txt3><a href='#' title='关闭此项'><font
											face='Wingdings' color=#FFFFFF>x</font>
									</a>
									</span>
								</th>
							</tr>
							<tr>
								<td style="" id='submenu3'>
									<table border=0 width=100% cellspacing=1 cellpadding=3
										bgcolor="#003399">
										<tr bgcolor="#739ADE" height="22">
											<td colspan="3">
												&nbsp;&nbsp;
												<font face='Webdings'>4</font> 让服务器执行300万次加法(
												<font color="#000000">整数运算</font>)和20万次开方(浮点运算)，记录其所使用的时间。
											</td>
										</tr>
										<tr height="22" bgcolor="#FFFFFF">
											<td align=center bgcolor="#FFFFFF">
												<font color="#000000">可 供 参 考 的 服 务 器 列 表</font>
											</td>
											<td>
												整数运算
											</td>
											<td>
												浮点运算
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td align=left>
												&nbsp;公司的电脑&nbsp;(CPU:Celeron 1G&nbsp; 内存:256M)
											</td>
											<td>
												60 毫秒
											</td>
											<td>
												70 毫秒
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td align=left>
												&nbsp;家里的电脑&nbsp;(CPU:Duron 1G&nbsp; 内存:384M)
											</td>
											<td>
												20 毫秒
											</td>
											<td>
												10 毫秒
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td align=left>
												&nbsp;
												<a href="http://free3.e-168.cn/cnforum/jspenv.jsp"
													target="_blank">中国网聚服务器&nbsp; (CPU:Intel Pentium III 1G
													内存:768M)</a>
											</td>
											<td>
												20 毫秒
											</td>
											<td>
												3 毫秒
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height="22">
											<td align=left>
												&nbsp;
												<a href="http://ibmclub.hust.edu.cn/myweb/ggyy/jspenv.jsp"
													target="_blank">IBM俱乐部&nbsp; (CPU:IIntel(R) Celeron(R)
													CPU 1.70G 内存:256M)</a>
											</td>
											<td>
												3 毫秒
											</td>
											<td>
												7 毫秒
											</td>
										</tr>
										<tr bgcolor="#FFFFFF" height=25>
											<td align=left>
												&nbsp;
												<font color=red>您正在使用的这台服务器</font>&nbsp;
												<INPUT name="button2" type="button" class=backc
													onclick="javascript:location.reload()" value="重新测试">
											</td>
											<td>
												<font color=red><%=env.test_int()%> 毫秒</font>
											</td>
											<td>
												<font color=red><%=env.test_sqrt()%> 毫秒</font>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						
						
						 <a name="ISpeedTest" id="ISpeedTest"></a><br>
 <%
 	if(action.equals("SpeedTest"))
	{
 %>
<div id="testspeed"> 
  <table width="200" border="0" cellspacing="0" cellpadding="0" class="divcenter">
    <tr> 
      <td height="30" align=center><p><font color="#000000"><span id=txt5>网速测试中，请稍候...</span></font></p></td>
    </tr>
  </table>
</div>
<%}%>

  <table border="0" cellpadding="0" cellspacing="1" bgcolor="#003399" class="tableBorder">
  <tr> 
    <th height="25" class="tableHeaderText" onclick="showsubmenu(4)"><font color="#FFFFFF"><strong>服务器连接速度</strong></font> 
      
<a href="#top" title="返回顶部"><font face='Webdings' color=#FFFFFF>5</font></a> <span id=txt4 name=txt4><a href='#' title='关闭此项'><font face='Wingdings' color=#FFFFFF>x</font></a></span> 

    </th>
  </tr>
  <tr> 
    <td bgcolor="#F8F9FC" style="display" id='submenu4'> <table width="100%" border="0" cellspacing=1 cellpadding=3 bgcolor="#003399">
        <tr bgcolor="#FFFFFF"> 
          <td width="80">接入设备</td>
          <td width="420">&nbsp;连接速度(理想值)</td>
          <td width="100">下载速度(理想值)</td>
        </tr>
<tr bgcolor="#FFFFFF"> 
          <td>56k Modem</td>
          <td><img align=absmiddle class=PicBar width='1%'> 56 Kbps</td><td>&nbsp;7.0 k/s</td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td>64k ISDN</td>
          <td><img align=absmiddle class=PicBar width='1%'> 64 Kbps</td><td>&nbsp;8.0 k/s</td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td>512k ADSL</td>
          <td><img align=absmiddle class=PicBar width='5%'> 512 Kbps</td><td>&nbsp;64.0 k/s</td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td height="19">1.5M Cable</td>
          <td><img align=absmiddle class=PicBar width='15%'> 1500 Kbps</td><td>&nbsp;187.5 k/s</td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td>5M FTTP</td>
          <td><img align=absmiddle class=PicBar width='50%'> 5000 Kbps</td><td>&nbsp;625.0 k/s</td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td>当前连接速度</td>
<%
if(action.equals("SpeedTest"))
{
		out.println("<script language='JavaScript'>var tSpeedStart=new Date();</script>")	;
		out.println("<!--\n");
		for(int i=0;i<1000;i++)
		{out.println("####################################################################################################");}
		out.println("-->\n");
		out.println("<script language='JavaScript'>var tSpeedEnd=new Date();</script>\n");
		out.println("<script language='JavaScript'>");
		out.println("var iSpeedTime=0;iSpeedTime=(tSpeedEnd - tSpeedStart) / 1000;");
		out.println("if(iSpeedTime>0) iKbps=Math.round(Math.round(100 * 8 / iSpeedTime * 10.5) / 10); else iKbps=10000 ;");
		out.println("var iShowPer=Math.round(iKbps / 100);");
		out.println("if(iShowPer<1) iShowPer=1;  else if(iShowPer>82)   iShowPer=82;");
		out.println("</script>\n");
		out.println("<script language='JavaScript'>") ;
		out.println("document.write('<td><img align=absmiddle class=PicBar width=\"' + iShowPer + '%\">' + iKbps + ' Kbps');");
		out.println("</script>\n");
		out.println("</td><td>&nbsp;<a href='?action=SpeedTest' title=测试连接速度><u>");
		out.println("<script language='JavaScript'>");
		out.println("document.write(Math.round(iKbps/8*10)/10+ ' k/s');");
		out.println("</script>\n") ;
		out.println("</u></a></td>");
%>
<script>
txt5.innerHTML="网速测试完毕!"
testspeed.style.visibility="hidden"
</script>
<%
}
else
{out.println("<td></td><td>&nbsp;<a href='?action=SpeedTest' title=测试连接速度><u>开始测试</u></a></td>");}
%>
        </tr>
      </table></td>
  </tr>
</table>
<%
	long timePageEnd = System.currentTimeMillis();
	long timePageUse=timePageEnd-timePageStart;
%>
<table border=0 cellpadding=0 cellspacing=1 class=tableBorder>
  <tr>
    <td height="59" align=center>页面执行时间：约<%=timePageUse%>毫秒</td>
  </tr>
</table>
						
						
						
						
						
</body>
</html>
