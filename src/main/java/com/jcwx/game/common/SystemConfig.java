package com.jcwx.game.common;

import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jcwx.game.common.security.MasterKeyUtil;

public class SystemConfig {

    /** 游戏是否开发模式（开服设为false） */
    public static boolean devMode;

    /** 服务端socketIP */
    public static String gameSocketIp;

    /** 服务端socket端口 */
    public static int gameSocketPort;

    /** 是否验证密码（开服后设为true） */
    public static boolean isNotCheckPasswd;

    /** 是否开启本地后台登录(若开启控制中心可设为false) */
    public static boolean isOnLocalOSSLogin;

    /** 语言环境 */
    public static String locale;

    private static Logger logger = Logger.getLogger(SystemConfig.class);

    /** 用户中心登录使用的key */
    public static String loginKey;

    /** 在线人数最大值,如果为负数表示不限制 */
    public static int onlineMaxNum;

    /** 玩家操作日志表分表规则 1 按月分表 2 按周分表 */
    public static int operationLogTableSplit;

    /** 控制中心地址,例htpp://192.168.1.50/SSO 目前没有使用到(若开启控制中心则需要使用）! */
    public static String ossCenterAddress;

    /** OSS key */
    public static String ossCenterKey;

    /** 充值Key */
    public static String payKey;

    /** 游戏当前版本对应的资源路径 */
    public static String resUrl;

    /** 游戏部署服务名字 */
    public static String serverName;

    /** 游戏部署服务版本 */
    public static String serverVersion;

    /** 玩家加密验证密钥 **/
    public static String verifyKey = "D&8S9DSDFSD%%$#@#$%$%%";

    public static String getGameSocketIp() {
	return gameSocketIp;
    }

    public static String getResUrl() {
	return resUrl;
    }

    public static String getServerVersion() {
	return serverVersion;
    }

    /**
     * 初始化系统参数（从config.xml文件中读取的值）
     */
    public static void initSystemConfigParams() {
	try {
	    Properties p = new Properties();
	    p.loadFromXML(SystemConfig.class
		    .getResourceAsStream("/config/config.xml"));
	    Class<SystemConfig> c = SystemConfig.class;
	    SystemConfig sc = c.newInstance();
	    Field[] fs = c.getDeclaredFields();

	    for (int i = 0; i < fs.length; i++) {
		Field f = fs[i];
		if (!"logger".equals(f.getName())
			&& !"verifyKey".equals(f.getName())) {
		    String value = p.getProperty("sysconfig." + f.getName());
		    if (!StringUtils.isNotEmpty(value)) {
			continue;
		    }
		    value = MasterKeyUtil.decStr(value);
		    // System.out.println("value:"+value+",type:"+f.getType());

		    if (f.getType().toString().equals("int")
			    || f.getType().equals(Integer.class)) {
			f.set(sc, Integer.parseInt(value));
		    } else if (f.getType().toString().equals("boolean")) {
			f.set(sc, Boolean.parseBoolean(value));
		    } else if (f.getType().equals(String.class)) {
			f.set(sc, value);
		    }
		}
	    }
	} catch (Exception e) {
	    logger.error(ResourceBundleService.getString("txt.exception"), e);
	}
    }

    public static void setGameSocketIp(String gameSocketIp) {
	SystemConfig.gameSocketIp = gameSocketIp;
    }

    public static void setResUrl(String resUrl) {
	SystemConfig.resUrl = resUrl;
    }

    public static void setServerVersion(String serverVersion) {
	SystemConfig.serverVersion = serverVersion;
    }

    public int getGameSocketPort() {
	return gameSocketPort;
    }

    public String getLocale() {
	return locale;
    }

    public String getLoginKey() {
	return loginKey;
    }

    public int getOnlineMaxNum() {
	return onlineMaxNum;
    }

    public int getOperationLogTableSplit() {
	return operationLogTableSplit;
    }

    public String getOssCenterAddress() {
	return ossCenterAddress;
    }

    public String getOssCenterKey() {
	return ossCenterKey;
    }

    public String getPayKey() {
	return payKey;
    }

    public String getServerName() {
	return serverName;
    }

    public boolean isDevMode() {
	return devMode;
    }

    public boolean isNotCheckPasswd() {
	return isNotCheckPasswd;
    }

    public boolean isOnLocalOSSLogin() {
	return isOnLocalOSSLogin;
    }

    public void setDevMode(boolean devMode) {
	SystemConfig.devMode = devMode;
    }

    public void setGameSocketPort(int gameSocketPort) {
	SystemConfig.gameSocketPort = gameSocketPort;
    }

    public void setLocale(String locale) {
	SystemConfig.locale = locale;
    }

    public void setLoginKey(String loginKey) {
	SystemConfig.loginKey = loginKey;
    }

    public void setNotCheckPasswd(boolean isNotCheckPasswd) {
	SystemConfig.isNotCheckPasswd = isNotCheckPasswd;
    }

    public void setOnlineMaxNum(int onlineMaxNum) {
	SystemConfig.onlineMaxNum = onlineMaxNum;
    }

    public void setOnLocalOSSLogin(boolean isOnLocalOSSLogin) {
	SystemConfig.isOnLocalOSSLogin = isOnLocalOSSLogin;
    }

    public void setOperationLogTableSplit(int operationLogTableSplit) {
	SystemConfig.operationLogTableSplit = operationLogTableSplit;
    }

    public void setOssCenterAddress(String ossCenterAddress) {
	SystemConfig.ossCenterAddress = ossCenterAddress;
    }

    public void setOssCenterKey(String ossCenterKey) {
	SystemConfig.ossCenterKey = ossCenterKey;
    }

    public void setPayKey(String payKey) {
	SystemConfig.payKey = payKey;
    }

    public void setServerName(String serverName) {
	SystemConfig.serverName = serverName;
    }

}
