<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
  <head>
    <title>导航</title>
 	<SCRIPT src="<%=basePath%>/media/default/js/jquery-1.5.1.min.js" type=text/javascript></SCRIPT>
	<SCRIPT src="<%=basePath%>/media/default/js/jquery.cookie.js" type=text/javascript></SCRIPT> 
	<SCRIPT src="<%=basePath%>/media/default/js/jquery.accordion.menu.js" type=text/javascript></SCRIPT>
	<LINK href="<%=basePath%>/media/default/css/jquery.accordion.menu.css" type=text/css rel=stylesheet>
  <base target="mainFrame">
  </head>
  <body>

	<UL class="menu expandfirst" id=menu2>
	  <LI><A class=m1 href="#">金币与消费</A> 
	  <UL>
		<LI><A href="<%=basePath%>/admin/pay/payStat.action">充值金额统计</A> 
		<LI><A href="<%=basePath%>/admin/pay/queryPay.action">充值记录查询</A> 
		<LI><A href="<%=basePath%>/admin/pay/queryMoneyConsume.action">金币使用记录</A> 
		<LI><A href="<%=basePath%>/admin/pay/queryPayRank.action">玩家充值排行</A>
	  </UL>
	  <LI><A class=m2 href="#">在线与注册</A> 
	  <UL>
		<LI><A href="<%=basePath%>/admin/online/queryOnlinePlayer.action">当前在线用户</A>
		<LI><A href="<%=basePath%>/admin/online/queryTodayOnlineInfo.action">查看今天在线</A> 
		<LI><A href="<%=basePath%>/admin/online/queryBeforeOnlineInfo.action">历史在线数据</A> 
		<LI><A href="<%=basePath%>/admin/base/historyOnlineStatistics_browseHistoryOnlineStatistics.action">在线数据统计</A> 
		<LI><A href="<%=basePath%>/admin/online/queryAllPlayer.action">所有注册用户</A> 
		<LI><A href="<%=basePath%>/admin/online/queryPlayerRegisterStatByDay.action">注册数据统计</A> 
		<LI><A href="<%=basePath%>/admin/online/queryPlayerRegisterStatByHour.action">分时注册统计</A> 
	  </UL>
	  <LI><A class=m3 href="#">消息与通知</A> 
	  <UL>
		<LI><A href="<%=basePath%>/admin/message/sendSysMsg.jsp">发送系统消息</A> 
		<LI><A href="<%=basePath%>/admin/message/adminSysMsg.action">系统消息管理</A> 
		<LI><A href="<%=basePath%>/admin/base/comment_browseComment.action">查看投诉意见</A> 
		<LI><A href="<%=basePath%>/admin/message/sendMsgToPlayer.jsp">给玩家发信</A> 
		<LI><A href="<%=basePath%>/admin/message/sendMsgToMorePlayer.jsp">批量给玩家发信</A> 
	  </UL>
	  <LI><A class=m4 href="#">玩家</A> 
	  <UL>
		<LI><A href="<%=basePath%>/admin/base/playerInfo_browsePlayerInfo.action">查看玩家信息</A> 
		<LI><A href="<%=basePath%>/admin/player/adminPlayerBanChat.action">玩家禁言</A> 
		<LI><A href="<%=basePath%>/admin/base/banAccounts_browseBanAccounts.action">封禁帐号</A> 
		<LI><A href="<%=basePath%>/admin/base/banIP_browseBanIP.action">封禁IP</A> 
		<LI><A href="<%=basePath%>/admin/base/iPStats_browseIP.action">IP统计</A> 
		<LI><A href="<%=basePath%>/admin/base/loginLog_browseLoginLogCountByTime.action">登录统计</A> 
		<LI><A href="<%=basePath%>/admin/base/loginLog_browseLoginLogInfo.action">登录历史查询</A>
		<LI><A href="<%=basePath%>/admin/base/playerRename_browsePlayerRename.action">玩家改名</A> 
	  </UL>
	  <LI><A class=m5 href="#">系统管理</A> 
	  <UL>
		<LI><A href="<%=basePath%>/admin/base/ossUser_browseOssUser.action">用户管理</A> 
		<LI><A href="<%=basePath%>/admin/base/ossRole_browseOssRole.action">角色管理</A> 
		<LI><A href="<%=basePath%>/admin/base/ossMenu_browseOssMenu.action">系统功能管理</A> 
		<LI><A href="<%=basePath%>/admin/base/ossUser_onlinOssUser.action">在线管理员</A> 
		
	  </UL>
	  <LI><A class=m6 href="#">系统监控</A> 
	  <UL>
	 	 <LI><A href="<%=basePath%>/admin/system/serverInf.jsp">系统信息</A>
	 	 <LI><A href="<%=basePath%>/admin/base/memoryMonitor_browseMemoryMonitorByTime.action">内存、CPU分析</A>
	 	 <LI><A href="<%=basePath %>/admin/monitor/realTimeMonitor.jsp">实时内存监控</A>
	 	 <LI><A href="<%=basePath %>/admin/monitor/realTimeCPU.jsp">实时CPU监控</A>
	 	 <LI><A href="<%=basePath %>/admin/monitor/realTimeTotalThread.jsp">实时线程监控</A>
	  </UL>
	  
	
	</UL>

  </body>
</html>
