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
	  <LI><A class=m2 href="#">在线-注册-登录</A> 
	  <UL>
		<LI><A href="<%=basePath%>/admin/online/queryOnlinePlayer.action">当前在线用户</A>
		<LI><A href="<%=basePath%>/admin/online/queryTodayOnlineInfo.action">查看今天在线</A> 
		<LI><A href="<%=basePath%>/admin/online/queryBeforeOnlineInfo.action">历史在线数据</A> 
		<LI><A href="<%=basePath%>/admin/base/historyOnlineStatistics_browseHistoryOnlineStatistics.action">在线数据统计</A> 
		<LI><A href="<%=basePath%>/admin/online/queryAllPlayer.action">所有注册用户</A> 
		<LI><A href="<%=basePath%>/admin/online/queryPlayerRegisterStatByDay.action">注册数据统计</A> 
		<LI><A href="<%=basePath%>/admin/online/queryPlayerRegisterStatByHour.action">分时注册统计</A> 
		<LI><A href="<%=basePath%>/admin/base/iPStats_browseIP.action">IP统计</A> 
		<LI><A href="<%=basePath%>/admin/base/loginStatistics_browseLoginLogCountByTime.action">登录统计</A> 
		<LI><A href="<%=basePath%>/admin/base/browseLogin_browseLoginLogInfo.action">登录历史查询</A>
	  </UL>
	  <LI><A class=m3 href="#">数据分析</A> 
	  <UL>
	    <LI><A href="<%=basePath%>/admin/base/playerInfo_browsePlayerInfo.action">查看玩家信息</A> 
	  </UL>
	  <LI><A class=m4 href="#">GM消息与通知</A> 
	  <UL>
		<LI><A href="<%=basePath%>/admin/message/sendSysMsg_index.action">发送系统消息</A> 
		<LI><A href="<%=basePath%>/admin/message/adminSysMsg_adminSysMsg.action">系统消息管理</A> 
		<LI><A href="<%=basePath%>/admin/message/sendMsgToPlayer_index.action">给玩家发信</A> 
		<LI><A href="<%=basePath%>/admin/message/sendMsgToMorePlayer_index.action">批量给玩家发信</A> 
	  </UL>
	  <LI><A class=m5 href="#">GM管理工具</A> 
	  <UL>
	    <LI><A href="<%=basePath%>/admin/gm/updatePlayer_index.action">修改玩家信息</A>
	    <LI><A href="<%=basePath%>/admin/base/addResources.action?view=view">资源发放</A> 
	    <LI><A href="<%=basePath%>/admin/player/banChat_adminPlayerBanChat.action">玩家禁言</A> 
	    <LI><A href="<%=basePath%>/admin/base/banAccounts_browseBanAccounts.action">封禁帐号</A>
	    <LI><A href="<%=basePath%>/admin/base/banIP_browseBanIP.action">封禁IP</A> 
	    <LI><A href="<%=basePath%>/admin/base/playerRename_browsePlayerRename.action">玩家改名</A> 
	    <LI><A href="<%=basePath%>/admin/base/comment_browseComment.action">查看投诉意见</A> 
	    <LI><A href="<%=basePath%>/admin/base/systemConfig_browseSystemConfig.action">系统参数管理</A> 
	  </UL>
	  <LI><A class=m6 href="#">系统管理</A> 
	  <UL>
	     <LI><A href="<%=basePath%>/admin/base/ossUser_browseOssUser.action">用户管理</A> 
		 <LI><A href="<%=basePath%>/admin/base/ossRole_browseOssRole.action">角色管理</A> 
	 	 <LI><A href="<%=basePath%>/admin/base/ossServer_serverList.action">服务器管理</A>
	 	 <LI><A href="<%=basePath%>/admin/base/ossMenu_browseOssMenu.action">系统功能管理</A> 
	 	 <LI><A href="<%=basePath%>/admin/system/password_index.action">修改密码</A> 
		 <LI><A href="<%=basePath%>/admin/base/ossUser_onlinOssUser.action">在线管理员</A>
	  </UL>
	  </LI>
	  </UL>
  </body>
</html>
