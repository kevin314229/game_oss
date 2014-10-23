package com.jcwx.game.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.jcwx.game.admin.constant.OssDateFormatUtils;

/**
 * 时间&日期服务
 */

public class DateService {

    public static final int DATE_BY_DAY = 3; // 按天分表
    public static final int DATE_BY_MONTH = 1; // 按月分表
    public static final int DATE_BY_WEEK = 2; // 按周分表
    /** 默认时间 */
    private static java.util.Date defaultDate = null;

    // private static Logger logger = Logger.getLogger(DateService.class);

    /**
     * 获得指定格式的日期对象
     * 
     * @param date
     * @param formatStr
     * @return
     */
    public static java.util.Date changeDateFormat(Date date, String formatStr) {
	SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
	try {
	    date = formatter.parse(formatter.format(date));
	} catch (ParseException e) {
	    // logger.error(ResourceBundleService.getString("txt.exception"),
	    // e);
	}

	return date;
    }

    /**
     * 日期增加-按日增加
     * 
     * @param date
     * @param days
     * @return
     */
    public static Date dateIncreaseByDay(Date date, int days) {

	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	cal.setTime(date);
	cal.add(Calendar.DATE, days);

	return cal.getTime();
    }

    /** 返回两个日期之间的天数 */
    public static Integer DayBetween(Date earlyDay, Date lateDay) {
	int day = (int) ((lateDay.getTime() - earlyDay.getTime()) / (1000 * 60 * 60 * 24L));
	return day;
    }

    /** 距离当前时间天数,整数 */
    public static Integer distanceNowDay(Date dueDate) {
	int day = (int) ((new Date().getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24));
	return day;
    }

    /** 距离当前时间天数，小数 */
    public static double distanceNowDayDouble(Date dueDate) {
	Date d1 = new Date();// 当前时间
	double l = (double) dueDate.getTime() - d1.getTime();
	double d = l / (24 * 60 * 60 * 1000);
	return d;
    }

    /** 距离当前时间秒数 */
    public static Integer distanceNowSecond(Date date) {
	int second = (int) ((System.currentTimeMillis() - date.getTime()) / 1000L);
	return second;
    }

    /**
     * 得到timer毫秒以后的时间
     * 
     * @param timer
     * @return
     */
    public static java.util.Date getAfterUtilDate(long timer) {
	return new java.util.Date(getCurrentUtilDate().getTime() + timer);
    }

    /**
     * 获得当前时间，返回字符串(yyyy-MM-dd HH:mm:ss)
     * 
     * @return
     */
    public static String getCurrentDateAsString() {
	SimpleDateFormat formatter = new SimpleDateFormat(OssDateFormatUtils.ISO_NO_T_DATETIME_FORMAT.getPattern());
	return formatter.format(getCurrentUtilDate());
    }

    /**
     * 获得当前时间，返回用户自定义格式字符串
     * 
     * @param formatStr
     * @return
     */
    public static String getCurrentDateAsStringCustom(String formatStr) {
	SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
	return formatter.format(getCurrentUtilDate());
    }

    /**
     * 获得当天的00点00分00秒，返回java.util.Date
     * 
     * @return
     */
    public static java.util.Date getCurrentDayFirstUtilDate() {
	Calendar c = Calendar.getInstance();
	c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
		c.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
	return c.getTime();
    }

    /**
     * 获得当天的23点59分59秒，返回java.util.Date
     * 
     * @return
     */
    public static java.util.Date getCurrentDayLastUtilDate() {
	Calendar c = Calendar.getInstance();
	c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
		c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
	return c.getTime();
    }

    /**
     * 获得当前时间的小时
     * 
     * @return
     */
    public static int getCurrentHour() {
	return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获得当前时间的小时分钟和秒
     * 
     * @return 返回一个数字8点半=83000，19点=190000
     */
    public static int getCurrentHourMinuSec() {
	Calendar c = Calendar.getInstance();
	return c.get(Calendar.HOUR_OF_DAY) * 10000 + c.get(Calendar.MINUTE)
		* 100 + c.get(Calendar.SECOND);
    }

    /** 获取当前月的第一天 00:00:00 */
    public static Date getCurrentMonthFirstDay() {
	// java.text.SimpleDateFormat df = new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.set(Calendar.DAY_OF_MONTH, 1);
	gc.set(Calendar.HOUR_OF_DAY, 0);
	gc.set(Calendar.MINUTE, 0);
	gc.set(Calendar.SECOND, 0);
	return gc.getTime();
    }

    /**
     * 获得当月的第一天的00点00分00秒，返回java.util.Date
     * 
     * @return
     */
    public static java.util.Date getCurrentMonthFirstUtilDate() {
	Calendar c = Calendar.getInstance();
	c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 01, 00, 00, 00);
	return c.getTime();
    }

    /**
     * 获得当前时间，返回java.sql.Date
     * 
     * @return
     */
    public static java.sql.Date getCurrentSqlDate() {
	return new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * 获得当前时间，返回java.util.Date
     * 
     * @return
     */
    public static java.util.Date getCurrentUtilDate() {
	return Calendar.getInstance().getTime();
    }

    /**
     * 获得当前时间，返回年份
     * 
     * @return
     */
    public static int getCurrentYear() {
	return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获得某个字符串的时间
     * 
     * @param dateStr
     * @return
     */
    public static Date getDateByStrAndFormat(String dateStr, String formatStr) {
	SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
	try {
	    return formatter.parse(dateStr);
	} catch (ParseException e) {
	    // logger.error(ResourceBundleService.getString("txt.exception"),
	    // e);
	}
	return getCurrentUtilDate();
    }

    /**
     * 获取指定时的的 00:00 00
     * 
     * @param date
     * @return
     */
    public static Date getDateFirstTime(Date date) {
	java.util.Calendar caled = java.util.Calendar.getInstance();
	caled.setTime(date);
	caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
	caled.set(java.util.Calendar.MINUTE, 0);
	caled.set(java.util.Calendar.SECOND, 0);
	return caled.getTime();
    }

    /**
     * 获得某个格式为 yyyy-MM-dd 的日期的 00:00:00 的时间
     * 
     * @param dateStr
     * @return
     */
    public static Date getDateFirstTime(String dateStr) {
	SimpleDateFormat formatter = new SimpleDateFormat(OssDateFormatUtils.ISO_NO_T_DATETIME_FORMAT.getPattern());
	try {
	    return formatter.parse(dateStr + " 00:00:00");
	} catch (ParseException e) {
	    // logger.error(ResourceBundleService.getString("txt.exception"),
	    // e);
	}
	return getCurrentMonthFirstUtilDate();
    }

    /**
     * 获得指定格式的日期的字符串
     * 
     * @param date
     * @param formatStr
     * @return
     */
    public static String getDateFormatStr(Date date, String formatStr) {
	SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
	return formatter.format(date);
    }

    /**
     * 获取指定时间的23:59:59
     * 
     * @param date
     * @return
     */
    public static Date getDateLastTime(Date date) {
	java.util.Calendar caled = java.util.Calendar.getInstance();
	caled.setTime(date);
	caled.set(java.util.Calendar.HOUR_OF_DAY, 23);
	caled.set(java.util.Calendar.MINUTE, 59);
	caled.set(java.util.Calendar.SECOND, 59);
	return caled.getTime();
    }

    /**
     * 获得某个格式为 yyyy-MM-dd 的日期的 23:59:59 的时间
     * 
     * @param dateStr
     * @return
     */
    public static Date getDateLastTime(String dateStr) {
	SimpleDateFormat formatter = new SimpleDateFormat(OssDateFormatUtils.ISO_NO_T_DATETIME_FORMAT.getPattern());
	try {
	    return formatter.parse(dateStr + " 23:59:59");
	} catch (ParseException e) {
	    // logger.error(ResourceBundleService.getString("txt.exception"),
	    // e);
	}
	return getCurrentDayLastUtilDate();
    }

    /**
     * 获得指定日期的00点00分00秒，返回java.util.Date
     * 
     * @return
     */
    public static java.util.Date getDayFirstUtilDate(Date date) {
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
		c.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
	return c.getTime();
    }

    /**
     * 获得指定日期的23点59分59秒，返回java.util.Date
     * 
     * @return
     */
    public static java.util.Date getDayLastUtilDate(Date date) {
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
		c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
	return c.getTime();
    }

    /**
     * 获得默认时间(1970-01-01 00:00:00)
     * 
     * @return
     */
    public static java.util.Date getDefaultDate() {
	if (defaultDate == null) {
	    Calendar c = Calendar.getInstance();
	    c.set(1970, 01, 01, 00, 00, 00);
	    defaultDate = c.getTime();
	}
	return defaultDate;
    }

    //
    /** 获取上个月的第一天 00:00:00 */
    public static Date getFirstDayOfLastMonth() {
	// java.text.SimpleDateFormat df = new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.set(Calendar.DAY_OF_MONTH, 1);
	gc.add(Calendar.MONTH, -1);
	gc.set(Calendar.HOUR_OF_DAY, 0);
	gc.set(Calendar.MINUTE, 0);
	gc.set(Calendar.SECOND, 0);
	return gc.getTime();
    }

    /** 获取上个月的最后一天 00:00:00 */
    public static Date getLastDayOfLastMonth() {
	// java.text.SimpleDateFormat df = new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.set(Calendar.DAY_OF_MONTH, 1);
	gc.add(Calendar.DATE, -1);
	gc.set(Calendar.HOUR_OF_DAY, 23);
	gc.set(Calendar.MINUTE, 59);
	gc.set(Calendar.SECOND, 59);
	return gc.getTime();
    }

    /** 获取本月的最后一天 00:00:00 */
    public static Date getLastDayOfThisMonth() {
	// java.text.SimpleDateFormat df = new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.set(Calendar.DAY_OF_MONTH, 1);
	gc.add(Calendar.MONTH, 1);
	gc.add(Calendar.DATE, -1);
	gc.set(Calendar.HOUR_OF_DAY, 23);
	gc.set(Calendar.MINUTE, 59);
	gc.set(Calendar.SECOND, 59);
	return gc.getTime();
    }

    /**
     * 得到当前时间分钟的正点分钟后
     * 
     * @param timer
     * @return
     */
    public static java.util.Date getMinuteAfterUtilDate(int minute) {
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	cal.setTime(getCurrentUtilDate());
	cal.add(Calendar.MINUTE, minute);
	cal.set(Calendar.SECOND, 0);
	return cal.getTime();
    }

    /**
     * 根据分表策略获取分表后缀
     * 
     * @param type
     *            分表策略
     * @return
     */
    public static String getTableSuffixByType(int type) {
	Calendar c = Calendar.getInstance();
	String tableSuffix = "";

	if (type == DATE_BY_MONTH) {
	    String strMonth = c.get(Calendar.MONTH) + 1 + "";
	    if (strMonth.length() == 1)
		strMonth = "0" + strMonth;

	    tableSuffix = c.get(Calendar.YEAR) + "" + (strMonth + "01");
	    return tableSuffix;
	} else if (type == DATE_BY_WEEK) {
	    int a = c.get(Calendar.DAY_OF_WEEK);
	    c.add(Calendar.DATE, 2 - a); // 本周的第一天
	    String strMonth = c.get(Calendar.MONTH) + 1 + "";
	    if (strMonth.length() == 1)
		strMonth = "0" + strMonth;
	    String strDay = c.get(Calendar.DAY_OF_MONTH) + "";
	    if (strDay.length() == 1)
		strDay = "0" + strDay;

	    tableSuffix = c.get(Calendar.YEAR) + "" + (strMonth + strDay);
	    return tableSuffix;
	} else if (type == DATE_BY_DAY) {
	    String strMonth = c.get(Calendar.MONTH) + 1 + "";
	    if (strMonth.length() == 1)
		strMonth = "0" + strMonth;

	    tableSuffix = c.get(Calendar.YEAR) + ""
		    + (strMonth + c.get(Calendar.DAY_OF_MONTH) + 1);
	    return tableSuffix;
	}

	return tableSuffix;
    }

    /** 根据指定日期取定约定的表名后缀 */
    public static String getTableSuffixByTypeAndDate(int type, Date date) {
	String tableSuffix = "";
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	if (type == DATE_BY_MONTH) {
	    String strMonth = c.get(Calendar.MONTH) + 1 + "";
	    if (strMonth.length() == 1)
		strMonth = "0" + strMonth;

	    tableSuffix = c.get(Calendar.YEAR) + "" + (strMonth + "01");
	    return tableSuffix;
	} else if (type == DATE_BY_WEEK) {
	    int a = c.get(Calendar.DAY_OF_WEEK);
	    c.add(Calendar.DATE, 2 - a); // 本周第一天，也就是 2-a天前！

	    String strMonth = c.get(Calendar.MONTH) + 1 + "";
	    if (strMonth.length() == 1)
		strMonth = "0" + strMonth;
	    String strDay = (c.get(Calendar.DAY_OF_MONTH)) + "";
	    if (strDay.length() == 1)
		strDay = "0" + strDay;

	    tableSuffix = c.get(Calendar.YEAR) + "" + (strMonth + strDay);
	    return tableSuffix;
	} else if (type == DATE_BY_DAY) {
	    String strMonth = c.get(Calendar.MONTH) + 1 + "";
	    if (strMonth.length() == 1)
		strMonth = "0" + strMonth;

	    tableSuffix = c.get(Calendar.YEAR) + ""
		    + (strMonth + c.get(Calendar.DAY_OF_MONTH) + 1);
	    return tableSuffix;
	}

	return tableSuffix;
    }

    /**
     * 获取离本周末凌晨0点的间隔时间
     * 
     * @return
     */
    public static int getWeekendTime() {
	Calendar c = Calendar.getInstance();
	int datePos = c.get(Calendar.DAY_OF_WEEK) == 1 ? 0 : (8 - c
		.get(Calendar.DAY_OF_WEEK));
	long a = c.getTimeInMillis();
	c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
		c.get(Calendar.DAY_OF_MONTH) + datePos, 23, 59, 59);
	System.out.println("c:" + c.toString());
	long b = c.getTimeInMillis();
	return (int) ((b - a) / 1000);
    }

    /** 判断两日期是不是同一天 */
    public static boolean isSameDay(Date d1, Date d2) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	if (formatter.format(d1).equals(formatter.format(d2))) {
	    return true;
	} else {
	    return false;
	}

    }

    public static void main(String[] args) {
	System.out.println("getWeekendTime:" + getWeekendTime() / 3600);
	// Date d = getCurrentMonthFirstDay();
	// java.text.SimpleDateFormat df = new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// System.out.println(getFirstDayOfLastMonth());
	// System.out.println( getTableSuffixByType(1) );

	// System.out.println( getTableSuffixByType(2) );
	// Calendar c = Calendar.getInstance();
	// int a = c.get(Calendar.DAY_OF_WEEK);
	// c.add( Calendar.DAY_OF_YEAR, 1-a );
	// System.out.println( c.get( Calendar.DAY_OF_WEEK) );
	// System.out.println( c.get(Calendar.MONTH) +"."+c.get(Calendar.));

	// System.out.println( getTableSuffixByType(2) );
	/*
	 * Calendar now = Calendar.getInstance(); // int a =
	 * c.get(Calendar.DAY_OF_WEEK); // c.add( Calendar.DAY_OF_YEAR, 1-a );
	 * // System.out.println( c.get( Calendar.DAY_OF_WEEK) ); //
	 * System.out.println( (c.get(Calendar.MONTH)+1)
	 * +"."+(c.get(Calendar.DAY_OF_MONTH)+1)); int a =
	 * now.get(Calendar.DAY_OF_WEEK); now.add( Calendar.DATE, 2-a );
	 * //本周的第一天 System.out.println( now.get(Calendar.MONTH)+","+a);
	 * System.out.println(getTableSuffixByTypeAndDate(2,new Date()));
	 * System.out.println("date before 10 days : " +
	 * (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE) + "-" +
	 * now.get(Calendar.YEAR));
	 * 
	 * Date d = getDayFirstUtilDate(new Date()); System.out.println(d);
	 */

    }

    /** 返回两个日期之间的的分钟数 */
    public static Integer MinuteBetween(Date earlyDay, Date lateDay) {
	int min = (int) ((lateDay.getTime() - earlyDay.getTime()) / (1000 * 60));
	return min;
    }

    /**
     * 将java.util.Date转化为HH:00格式的字符串
     * 
     * @param date
     * @return
     */
    public static String parseDateToHourMinuteString(java.util.Date date) {
	SimpleDateFormat formatter = new SimpleDateFormat("HH:00");
	return formatter.format(date);
    }

    /**
     * 将日期转换成报告使用的日期字符串
     * 
     * @param date
     * @return
     */
    public static String parseDateToReportTimeString(Date date) {
	SimpleDateFormat formatter = new SimpleDateFormat(
		ResourceBundleService.getString("txt.common.dateTimeFormat"));
	return formatter.format(date);
    }

    /**
     * 将日期转换为字符串默认格式:yyyy-MM-dd HH:mm:ss)
     * 
     * @param date
     * @return
     */
    public static String parseDateToString(java.util.Date date) {
	SimpleDateFormat formatter = new SimpleDateFormat(OssDateFormatUtils.ISO_NO_T_DATETIME_FORMAT.getPattern());
	return formatter.format(date);
    }

    /**
     * 将字符串转换为日期java.sql.Date)
     * 
     * @param dateStr
     * @return
     */
    public static java.sql.Date parseStringToSqlDate(String dateStr) {
	return java.sql.Date.valueOf(dateStr);
    }

    /**
     * 将时间(毫秒)转化为字符串
     * 
     * @param time
     * @return 格式 (XX时XX分XX秒)
     */
    public static String parseTimeToString(long time) {
	StringBuffer stringBuffer = new StringBuffer();
	long hour = 0, minute = 0, second = 0;
	time = time / 1000;
	if (time >= 3600) {
	    hour = time / 3600;
	    stringBuffer.append(hour);
	    stringBuffer.append(ResourceBundleService
		    .getString("txt.common.hour"));
	}
	if (time >= 60) {
	    minute = (time - (hour * 3600)) / 60;
	    stringBuffer.append(minute);
	    stringBuffer.append(ResourceBundleService
		    .getString("txt.common.minute"));
	}
	second = time - (hour * 3600) - (minute * 60);
	stringBuffer.append(second);
	stringBuffer.append(ResourceBundleService
		.getString("txt.common.second"));
	return stringBuffer.toString();
    }

}
