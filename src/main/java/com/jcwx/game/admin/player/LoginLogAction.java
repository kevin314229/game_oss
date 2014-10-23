package com.jcwx.game.admin.player;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.LoginLog;
import com.jcwx.game.domain.LoginLogInfo;


/** 登录历史查询:查看登录历史 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class LoginLogAction extends BasalAction {

    private static final long serialVersionUID = -4396683640040295005L;

    /** 总记录数 */
    private Integer allNum;

    /** 开始时间 */
    private String beginDate;

    /** JFreeChar插件在Action中传递的参数 */
    private JFreeChart chart;

    /** 当前页数 */
    private Integer currPageNO;

    /** 结束时间 */
    private String endDate;

    /** 页面图片请求地址 */
    private String imgSrc;

    /** 页面图片请求地址2 */
    private String imgSrc2 = null;
    /** 搜索关键字 */
    private String keyword;
    /** 玩家登录历史统计信息 */
    private List<LoginLogInfo> loginLogInfoList;

    /** 玩家登录详情查询 */
    private List<LoginLog> loginLogList;

    /** 玩家帐号 */
    private String loginName;

    /** 玩家名称 */
    private String nickName;

    /** 每页记录数 */
    private Integer onePageNum;

    // private PlayerCache playerCache;

    /** 排序标记 */
    private String orderFlag;

    /** 总页数 */
    private Integer pages;

    /** 玩家id */
    private Integer playerID;

    /** 查询标识 日、月、年 */
    private String queryFlag;
    
    private String categories;
    
    private String data;
    
    private String dayData;
    private String loginTime;

    /** 详细查看登录详细 */
    @SuppressWarnings("unchecked")
    @Action(value = "browseLogin_browseLoginLog", results = { @Result(name = "success", location = "../../admin/player/loginLog/browseLoginLog.jsp") })
    public String browseLoginLog() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !("".equals(beginDate))) {
	    beginTime = DateService.getDateFirstTime(beginDate);

	} else {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateFirstTime(endDate);
	}
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "DESC";
	}
	onePageNum = 20; // 每页20条记录
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("playerID", playerID);
	object.put("keyword", keyword);
	object.put("orderFlag", orderFlag);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browseLoginLog");
	object = CONNECTION.sendMsg(object);
	allNum = (Integer) object.get("allNum");
	loginLogList = (List<LoginLog>) object.get("loginLogList");
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	} else if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}

	return SUCCESS;
    }

    /** 分时段-天-3D柱状 */
    @Action(value = "loginStatistics_browseLoginLogCountByDay", results = { @Result(name = "browseLoginLogCountByDay", type = "chart", params = {
	    "width", "900", "height", "450" }) })
    public String browseLoginLogCountByDay() {
	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	setChart(getChartLoginLogCountByDay(beginTime, endTime));
	return "browseLoginLogCountByDay";
    }

    /** 分时段-天-饼图 */
    @Action(value = "loginStatistics_browseLoginLogCountByDay2", results = { @Result(name = "browseLoginLogCountByDay2", type = "chart", params = {
	    "width", "900", "height", "450" }) })
    public String browseLoginLogCountByDay2() {
	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	setChart(getChartLoginLogCountByDay2(beginTime, endTime));
	return "browseLoginLogCountByDay2";
    }

    /**
     * -------------------------------------------JFreeChar请求处理start------------
     * ------------------------------------------
     */
    /** 分时段-月 */
    @Action(value = "loginStatistics_browseLoginLogCountByMonth", results = { @Result(name = "browseLoginLogCountByMonth", type = "chart", params = {
	    "width", "900", "height", "450" }) })
    public String browseLoginLogCountByMonth() {
	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	setChart(getChartLoginLogCountByMonth(beginTime, endTime));
	return "browseLoginLogCountByMonth";
    }

    /** 分时段统计总登录数 */
    @Action(value = "loginStatistics_browseLoginLogCountByTime", results = { @Result(name = "success", location = "../../admin/player/loginLog/browseLoginLogCountByTime.jsp") })
    public String browseLoginLogCountByTime() {
	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    // beginTime=DateService.getCurrentDayLastUtilDate();
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	if (queryFlag == null)
	    queryFlag = "byMonth";

	if (queryFlag.equals("byMonth")) {
	   data=getDataLoginLogCountByMonth(beginTime,endTime);
	} else if (queryFlag.equals("byDay")) {
	    data=getDataLoginLogCountByDay(beginTime,endTime);
	    
//	    imgSrc = "admin/base/loginStatistics_browseLoginLogCountByDay.action?beginDate="
//		    + beginDate + "&endDate=" + endDate;
//	    imgSrc2 = "admin/base/loginStatistics_browseLoginLogCountByDay2.action?beginDate="
//		    + beginDate + "&endDate=" + endDate;
	} else if (queryFlag.equals("byYear")) {
	    data=getDataLoginLogCountByYear(beginTime,endTime);
//	    imgSrc = "admin/base/loginStatistics_browseLoginLogCountByYear.action?beginDate="
//		    + beginDate + "&endDate=" + endDate;
	}

	return SUCCESS;
    }

    /** 分时段-年 */
    @Action(value = "loginStatistics_browseLoginLogCountByYear", results = { @Result(name = "browseLoginLogCountByYear", type = "chart", params = {
	    "width", "900", "height", "450" }) })
    public String browseLoginLogCountByYear() {
	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	setChart(getChartLoginLogCountByYear(beginTime, endTime));
	return "browseLoginLogCountByYear";
    }

    /** 查看登录历史 */
    @SuppressWarnings("unchecked")
    @Action(value = "browseLogin_browseLoginLogInfo", results = { @Result(name = "success", location = "../../admin/player/loginLog/browseLoginLogInfo.jsp") })
    public String browseLoginLogInfo() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"), "utf-8");
	    addActionMessage(getActionMsg());
	}
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "loginAmountDESC";
	}
	if (onePageNum == null || onePageNum == 0) {
	    onePageNum = 20; // 每页20条记录
	}

	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	Map<String, Object> object = new HashMap<String, Object>();
	if (loginTime!= null &&! "".equals(orderFlag)) {
	    object.put("loginTime", loginTime);
	}else{
	    object.put("loginTime", null);
	}
	object.put("keyword", keyword);
	object.put("orderFlag", orderFlag);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("handlerName", "LoginLogHandler");
	object = CONNECTION.sendMsg(object);
	allNum = (Integer) object.get("allNum");// 总记录数
	loginLogInfoList = (List<LoginLogInfo>) object.get("loginLogInfoList");
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	} else if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return SUCCESS;
    }

    /**
     * -------------------------------------------JFreeChar请求处理end--------------
     * ----------------------------------------
     */

    public Integer getAllNum() {
	return allNum;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public JFreeChart getChart() {
	return chart;
    }

    /** --------------日-------------------------------- */
    /** ------3D柱状----------- */
    public JFreeChart getChartLoginLogCountByDay(Date beginTime, Date endTime) {
	beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	chart = ChartFactory.createBarChart3D("登录统计图", // 图表标题
		"小时", // 目录轴的显示标签
		"登录数", // 数值轴的显示标签
		getDataSetLoginLogCountByDay(beginTime, endTime), // 数据集
		// PlotOrientation.HORIZONTAL , // 图表方向：水平
		PlotOrientation.VERTICAL, // 图表方向：垂直
		false, // 是否显示图例(对于简单的柱状图必须是false)
		false, // 是否生成工具
		false // 是否生成URL链接
		);

	String str = "(" + beginDate + " -- " + endDate + ")";
	// 重新设置图标标题，改变字体
	chart.setTitle(new TextTitle("登录天统计" + str, new Font("黑体", Font.ITALIC,
		22)));
	CategoryPlot plot = (CategoryPlot) chart.getPlot();
	// 取得横轴
	CategoryAxis categoryAxis = plot.getDomainAxis();
	// 设置横轴显示标签的字体
	categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 18));
	// 分类标签以45度角倾斜
	categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
	// 取得纵轴
	NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
	// 设置纵轴显示标签的字体
	numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 18));

	// 设置柱的颜色和数字
	BarRenderer3D renderer = new BarRenderer3D();
	renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	renderer.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 11));
	renderer.setBaseItemLabelsVisible(true);
	// 避免因为数值过小，显示不明显，或则看不到
	renderer.setMinimumBarLength(10);
	renderer.setSeriesPaint(0, new Color(0xff00));
	plot.setRenderer(renderer);

	// 设置背景
	chart.setBackgroundPaint(new Color(215, 228, 245));
	// 设置网格背景颜色
	plot.setBackgroundPaint(Color.white);
	// 设置网格竖线颜色
	plot.setDomainGridlinePaint(Color.pink);
	// 设置网格横线颜色
	plot.setRangeGridlinePaint(Color.pink);

	return chart;
    }

    /** ------饼图----------- */
    public JFreeChart getChartLoginLogCountByDay2(Date beginTime, Date endTime) {
	beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	String str = "(" + beginDate + " -- " + endDate + ")";
	chart = ChartFactory.createPieChart3D("登录-天统计图", // 图表标题
		getDataSetLoginLogCountByDay2(beginTime, endTime), // 数据
		true, // 是否显示图例
		false, // 是否显示工具提示
		false // 是否生成URL
		);
	// 重新设置图标标题，改变字体
	chart.setTitle(new TextTitle("登录-天统计图" + str, new Font("黑体",
		Font.ITALIC, 18)));
	// 取得统计图标的第一个图例
	LegendTitle legend = chart.getLegend(0);
	// 修改图例的字体
	legend.setItemFont(new Font("宋体", Font.BOLD, 12));
	// 获得饼图的Plot对象
	PiePlot plot = (PiePlot) chart.getPlot();
	// 设置饼图各部分的标签字体
	plot.setLabelFont(new Font("宋体", Font.BOLD, 12));
	// 设定背景透明度（0-1.0之间）
	plot.setBackgroundAlpha(0.9f);
	// 设定前景透明度（0-1.0之间）
	plot.setForegroundAlpha(0.8f);
	return chart;
    }

    /**
     * ------------------------------JFreeChar数据处理-----start--------------------
     * --------------------------------
     */
    /** --------------月-------------------------------- */
    public JFreeChart getChartLoginLogCountByMonth(Date beginTime, Date endTime) {
	beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	chart = ChartFactory.createBarChart3D("登录统计图", // 图表标题
		"月份", // 目录轴的显示标签
		"登录数", // 数值轴的显示标签
		getDataSetLoginLogCountByMonth(beginTime, endTime), // 数据集
		// PlotOrientation.HORIZONTAL , // 图表方向：水平
		PlotOrientation.VERTICAL, // 图表方向：垂直
		false, // 是否显示图例(对于简单的柱状图必须是false)
		false, // 是否生成工具
		false // 是否生成URL链接
		);

	String str = "(" + beginDate + " -- " + endDate + ")";
	// 重新设置图标标题，改变字体
	chart.setTitle(new TextTitle("登录月统计" + str, new Font("宋体", Font.ITALIC,
		22)));
	CategoryPlot plot = (CategoryPlot) chart.getPlot();
	// 取得横轴
	CategoryAxis categoryAxis = plot.getDomainAxis();
	// 设置横轴显示标签的字体
	categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 18));
	// 分类标签以45度角倾斜
	categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
	// 取得纵轴
	NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
	// 设置纵轴显示标签的字体
	numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 18));

	// 设置柱的颜色和数字
	BarRenderer3D renderer = new BarRenderer3D();
	renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	renderer.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 11));
	renderer.setBaseItemLabelsVisible(true);
	// 避免因为数值过小，显示不明显，或则看不到
	renderer.setMinimumBarLength(10);
	// renderer.setSeriesPaint(0, new Color(0xff00));
	plot.setRenderer(renderer);

	// 设置背景
	chart.setBackgroundPaint(new Color(215, 228, 245));
	// 设置网格背景颜色
	plot.setBackgroundPaint(Color.white);
	// 设置网格竖线颜色
	plot.setDomainGridlinePaint(Color.pink);
	// 设置网格横线颜色
	plot.setRangeGridlinePaint(Color.pink);

	return chart;
    }

    /** --------------年-------------------------------- */
    public JFreeChart getChartLoginLogCountByYear(Date beginTime, Date endTime) {
	beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	chart = ChartFactory.createBarChart3D("登录统计图", // 图表标题
		"月份", // 目录轴的显示标签
		"登录数", // 数值轴的显示标签
		getDataSetLoginLogCountByYear(beginTime, endTime), // 数据集
		// PlotOrientation.HORIZONTAL , // 图表方向：水平
		PlotOrientation.VERTICAL, // 图表方向：垂直
		false, // 是否显示图例(对于简单的柱状图必须是false)
		false, // 是否生成工具
		false // 是否生成URL链接
		);

	String str = "(" + beginDate + " -- " + endDate + ")";
	// 重新设置图标标题，改变字体
	chart.setTitle(new TextTitle("登录年统计" + str, new Font("黑体", Font.ITALIC,
		22)));
	CategoryPlot plot = (CategoryPlot) chart.getPlot();
	// 取得横轴
	CategoryAxis categoryAxis = plot.getDomainAxis();
	// 设置横轴显示标签的字体
	categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 18));
	// 分类标签以45度角倾斜
	categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
	// 取得纵轴
	NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
	// 设置纵轴显示标签的字体
	numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 18));

	// 设置柱的颜色和数字
	BarRenderer3D renderer = new BarRenderer3D();
	renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	renderer.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 11));
	renderer.setBaseItemLabelsVisible(true);
	// 避免因为数值过小，显示不明显，或则看不到
	renderer.setMinimumBarLength(10);
	renderer.setSeriesPaint(0, new Color(0xC1BA27));
	plot.setRenderer(renderer);

	// 设置背景
	chart.setBackgroundPaint(new Color(215, 228, 245));
	// 设置网格背景颜色
	plot.setBackgroundPaint(Color.white);
	// 设置网格竖线颜色
	plot.setDomainGridlinePaint(Color.pink);
	// 设置网格横线颜色
	plot.setRangeGridlinePaint(Color.pink);

	return chart;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    @SuppressWarnings("unchecked")
    private CategoryDataset getDataSetLoginLogCountByDay(Date beginTime,
	    Date endTime) {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	List<HashMap> dayList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browLoginLogCountByDay");
	try {
	    object = CONNECTION.sendMsg(object);
	    dayList = (List<HashMap>) object.get("dayList");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	for (HashMap m : dayList) {
	    String mon = m.get("h") + "时";
	    Integer total = Integer.valueOf(m.get("total").toString());
	    dataset.addValue(total, "", mon);
	}

	return dataset;
    }

    @SuppressWarnings("unchecked")
    private DefaultPieDataset getDataSetLoginLogCountByDay2(Date beginTime,
	    Date endTime) {
	DefaultPieDataset dataset = new DefaultPieDataset();
	List<HashMap> dayList = new ArrayList<HashMap>();
	// ILoginLogService loginLogService = (ILoginLogService)
	// SpringService.getBean("loginLogService");
	// List<HashMap> list =
	// loginLogService.getLoginLogCountByDay(beginTime,endTime);
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browLoginLogCountByDay2");
	try {
	    object = CONNECTION.sendMsg(object);
	    dayList = (List<HashMap>) object.get("dayList");// ip 集合
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// 重新排序-冒泡,按登录数
	HashMap temMap = new HashMap();
	for (int i = dayList.size() - 1; i >= 1; i--) {
	    for (int j = 0; j < i; j++) {
		HashMap m1 = dayList.get(j);
		Integer m1total = Integer.valueOf(m1.get("total").toString());
		HashMap m2 = dayList.get(j + 1);
		Integer m2total = Integer.valueOf(m2.get("total").toString());
		if (m1total > m2total) {
		    temMap = dayList.get(j);
		    dayList.set(j, dayList.get(j + 1));
		    dayList.set(j + 1, temMap);
		}
	    }
	}
	// 排序结束
	for (HashMap m : dayList) {
	    Integer total = Integer.valueOf(m.get("total").toString());
	    String mon = m.get("h") + "时(" + total + ")";
	    dataset.setValue(mon, total);
	}
	return dataset;
    }

    @SuppressWarnings("unchecked")
    private CategoryDataset getDataSetLoginLogCountByMonth(Date beginTime,
	    Date endTime) {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	List<HashMap> monthList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browLoginLogCountByMonth");
	try {
	    object = CONNECTION.sendMsg(object);
	    monthList = (List<HashMap>) object.get("monthList");// ip 集合
	} catch (Exception e) {
	    e.printStackTrace();
	}
	for (HashMap m : monthList) {
	    Integer total = Integer.valueOf(m.get("total").toString());
	    String mon = m.get("mon") + "日";
	    dataset.addValue(total, "", mon);
	}

	return dataset;
    }

    @SuppressWarnings("unchecked")
    private CategoryDataset getDataSetLoginLogCountByYear(Date beginTime,
	    Date endTime) {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	List<HashMap> yearList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browLoginLogCountByYear");
	try {
	    object = CONNECTION.sendMsg(object);
	    yearList = (List<HashMap>) object.get("yearList");// ip 集合
	} catch (Exception e) {
	    e.printStackTrace();
	}
	for (HashMap m : yearList) {
	    Integer total = Integer.valueOf(m.get("total").toString());
	    String mon = m.get("y") + "月";
	    dataset.addValue(total, "", mon);
	}
	return dataset;
    }

    
    /** 分时段统计总登录数 */
    @Action(value = "loginStatistics_index", results = { @Result(name = "success", location = "../../admin/player/loginLog/browseLoginLogCountByTime.jsp") })
    public String browseLoginStatistics() {
	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    // beginTime=DateService.getCurrentDayLastUtilDate();
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	if (queryFlag == null)
	    queryFlag = "byMonth";

	if (queryFlag.equals("byMonth")) {
	    imgSrc = "admin/base/loginStatistics_browseLoginLogCountByMonth.action?beginDate="
		    + beginDate + "&endDate=" + endDate;
	} else if (queryFlag.equals("byDay")) {
	    imgSrc = "admin/base/loginStatistics_browseLoginLogCountByDay.action?beginDate="
		    + beginDate + "&endDate=" + endDate;
	    imgSrc2 = "admin/base/loginStatistics_browseLoginLogCountByDay2.action?beginDate="
		    + beginDate + "&endDate=" + endDate;
	} else if (queryFlag.equals("byYear")) {
	    imgSrc = "admin/base/loginStatistics_browseLoginLogCountByYear.action?beginDate="
		    + beginDate + "&endDate=" + endDate;
	}

	return SUCCESS;
    }
    
    @SuppressWarnings("unchecked")
    private String getDataLoginLogCountByMonth(Date beginTime,
	    Date endTime) {
	List<HashMap> monthList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browLoginLogCountByMonth");
	try {
	    object = CONNECTION.sendMsg(object);
	    monthList = (List<HashMap>) object.get("monthList");// ip 集合
	} catch (Exception e) {
	    e.printStackTrace();
	}
	JSONObject jsonObject = new JSONObject();
	JSONArray result = new JSONArray();
	for (Map m : monthList) {
	    Integer total = Integer.valueOf(m.get("total").toString());
	    JSONArray array = new   JSONArray();
	    array.add(m.get("mon") + "日");
	    array.add(total);
	    result.add(array);
	}
	jsonObject.put("data", result);
//	jsonObject.put("name", "月分段");
	JSONArray jsonArray = new JSONArray();
	jsonArray.add(jsonObject);
	return jsonArray.toJSONString();
    }
    @SuppressWarnings("unchecked")
    private String getDataLoginLogCountByDay(Date beginTime,
	    Date endTime) {
	List<HashMap> dayList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browLoginLogCountByDay");
	try {
	    object = CONNECTION.sendMsg(object);
	    dayList = (List<HashMap>) object.get("dayList");
	} catch (Exception e) {
	    e.printStackTrace();
	}
//	for (HashMap m : dayList) {
//	    String mon = m.get("h") + "时";
//	    Integer total = Integer.valueOf(m.get("total").toString());
//	}

	JSONObject jsonObject = new JSONObject();
	JSONArray result = new JSONArray();
	for (Map m : dayList) {
	    Integer total = Integer.valueOf(m.get("total").toString());
	    JSONArray array = new   JSONArray();
	    array.add(m.get("h") + "时");
	    array.add(total);
	    result.add(array);
	}
	jsonObject.put("data", result);
//	jsonObject.put("name", "月分段");
	JSONArray jsonArray = new JSONArray();
	jsonArray.add(jsonObject);
	return jsonArray.toJSONString();
    }
    
    
    @SuppressWarnings("unchecked")
    private String getDataLoginLogCountByDay2(Date beginTime,
	    Date endTime) {
	DefaultPieDataset dataset = new DefaultPieDataset();
	List<HashMap> dayList = new ArrayList<HashMap>();
	// ILoginLogService loginLogService = (ILoginLogService)
	// SpringService.getBean("loginLogService");
	// List<HashMap> list =
	// loginLogService.getLoginLogCountByDay(beginTime,endTime);
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browLoginLogCountByDay2");
	try {
	    object = CONNECTION.sendMsg(object);
	    dayList = (List<HashMap>) object.get("dayList");// ip 集合
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// 排序结束
	for (HashMap m : dayList) {
	    Integer total = Integer.valueOf(m.get("total").toString());
	    String mon = m.get("h") + "时(" + total + ")";
	    dataset.setValue(mon, total);
	}
	return null;
    }
    @SuppressWarnings("unchecked")
    private String getDataLoginLogCountByYear(Date beginTime,
	    Date endTime) {
	List<HashMap> yearList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browLoginLogCountByYear");
	try {
	    object = CONNECTION.sendMsg(object);
	    yearList = (List<HashMap>) object.get("yearList");// ip 集合
	} catch (Exception e) {
	    e.printStackTrace();
	}
//	for (HashMap m : yearList) {
//	    Integer total = Integer.valueOf(m.get("total").toString());
//	    String mon = m.get("y") + "月";
//	    dataset.addValue(total, "", mon);
//	}
	JSONObject jsonObject = new JSONObject();
	JSONArray result = new JSONArray();
	for (Map m : yearList) {
	    Integer total = Integer.valueOf(m.get("total").toString());
	    JSONArray array = new   JSONArray();
	    array.add(m.get("y") + "月");
	    array.add(total);
	    result.add(array);
	}
	jsonObject.put("data", result);
//	jsonObject.put("name", "月分段");
	JSONArray jsonArray = new JSONArray();
	jsonArray.add(jsonObject);
	return jsonArray.toJSONString();
    }
    public String getEndDate() {
	return endDate;
    }

    public String getImgSrc() {
	return imgSrc;
    }

    public String getImgSrc2() {
	return imgSrc2;
    }

    public String getKeyword() {
	return keyword;
    }

    public List<LoginLogInfo> getLoginLogInfoList() {
	return loginLogInfoList;
    }

    public List<LoginLog> getLoginLogList() {
	return loginLogList;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    /**
     * ------------------------------JFreeChar数据处理-----end----------------------
     * ------------------------------
     */

    public String getOrderFlag() {
	return orderFlag;
    }

    public Integer getPages() {
	return pages;
    }

    public Integer getPlayerID() {
	return playerID;
    }

    public String getQueryFlag() {
	return queryFlag;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setChart(JFreeChart chart) {
	this.chart = chart;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setImgSrc(String imgSrc) {
	this.imgSrc = imgSrc;
    }

    public void setImgSrc2(String imgSrc2) {
	this.imgSrc2 = imgSrc2;
    }

    public void setKeyword(String keyword) {
	this.keyword = keyword;
    }

    public void setLoginLogInfoList(List<LoginLogInfo> loginLogInfoList) {
	this.loginLogInfoList = loginLogInfoList;
    }

    public void setLoginLogList(List<LoginLog> loginLogList) {
	this.loginLogList = loginLogList;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOrderFlag(String orderFlag) {
	this.orderFlag = orderFlag;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPlayerID(Integer playerID) {
	this.playerID = playerID;
    }

    public void setQueryFlag(String queryFlag) {
	this.queryFlag = queryFlag;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

}
