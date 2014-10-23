package com.jcwx.game.admin.online;

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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.service.oss.IDataHistoryService;


/** 在线数据统计:分时段统计在线数据 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class HistoryOnlineStatisticsAction extends BasalAction {

    private static final long serialVersionUID = 4163073770924473776L;

    private Long begin;

    private String beginDate;

    /** JFreeChar插件在Action中传递的参数 */
    private JFreeChart chart;

    @Autowired
    private IDataHistoryService dataHistoryService;

    private Long end;

    private String endDate;

    /** 页面图片请求地址 */
    private String imgSrc;

    /** 页面图片宽度 */
    private Integer imgSrcWidth;

    private List<HashMap<String, Object>> listForTable;

    /** 查询标识 日、月、年 */
    private String queryFlag;
    
    private String data;

    /** ----------JFreeChar Action请求-----start--------------------------------- */
    @Action(value = "historyOnlineStatistics_browseHistoryOnlineByDay", results = { @Result(name = "browseHistoryOnlineByDay", type = "chart", params = {
	    "width", "${imgSrcWidth}", "height", "450" }) })
    public String browseHistoryOnlineByDay() {
	setChart(getChartHistoryOnlineByDay(begin, end));
	return "browseHistoryOnlineByDay";
    }

    @Action(value = "historyOnlineStatistics_browseHistoryOnlineByHour", results = { @Result(name = "browseHistoryOnlineByHour", type = "chart", params = {
	    "width", "${imgSrcWidth}", "height", "450" }) })
    public String browseHistoryOnlineByHour() {
	setChart(getChartHistoryOnlineByHour(begin, end));
	return "browseHistoryOnlineByHour";
    }

    @Action(value = "historyOnlineStatistics_browseHistoryOnlineByHour2", results = { @Result(name = "browseHistoryOnlineByHour2", type = "chart", params = {
	    "width", "${imgSrcWidth}", "height", "500" }) })
    public String browseHistoryOnlineByHour2() {
	setChart(getChartHistoryOnlineByHour2(begin, end));
	return "browseHistoryOnlineByHour2";
    }

    /** 分时统计数据 */
    @Action(value = "historyOnlineStatistics_browseHistoryOnlineStatistics", results = { @Result(name = "success", location = "../../admin/online/browseHistoryOnlineStatistics.jsp") })
    public String browseHistoryOnlineStatistics() {
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

	begin = Long.parseLong(DateService.getDateFormatStr(beginTime,
		"yyyyMMddHHmm"));
	end = Long.parseLong(DateService.getDateFormatStr(endTime,
		"yyyyMMddHHmm"));

	if (queryFlag == null)
	    queryFlag = "byDay";

	if (queryFlag.equals("byDay")) {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("begin", begin);
	    object.put("end", end);
	    object.put("handlerName", "HistoryOnlineStatisticsHandler");
	    object.put("methodName", "browDataSetHistoryOnlineByDay");
	    listForTable = new ArrayList<HashMap<String, Object>>();
	    try {
		object = CONNECTION.sendMsg(object);
		List<HashMap<String, Object>> dayList = (List<HashMap<String, Object>>) object
			.get("dayList");// ip 集合
		for (HashMap<String, Object> m : dayList) {
		    Integer num = Integer.valueOf(m.get("num").toString());
		    String d = m.get("d").toString().substring(0, 8) + "日";
		    HashMap<String, Object> map = new HashMap<String, Object>();
		    map.put("d", d);
		    map.put("num", num);
		    listForTable.add(map);
		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }

//	    imgSrc = "admin/base/historyOnlineStatistics_browseHistoryOnlineByDay.action?begin="
//		    + begin + "&end=" + end;
	    
	   data = getDataHistoryOnlineByDay(begin,end);

	} else if (queryFlag.equals("byHour")) {
	    if (DateService.DayBetween(beginTime, endTime) >= 7) {
		addActionMessage("Sorry,由于查询数据量过大，请选择日期相隔天数不要超过7天!");
		imgSrc = "media/default/images/sorry.gif";
	    } else {
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("begin", begin);
		object.put("end", end);
		object.put("handlerName", "HistoryOnlineStatisticsHandler");
		object.put("methodName", "browDataSetHistoryOnlineByHour");

		try {
		    object = CONNECTION.sendMsg(object);
		    List<HashMap<String, Object>> dayList = (List<HashMap<String, Object>>) object
			    .get("hourList");// ip 集合
		    listForTable = new ArrayList<HashMap<String, Object>>();
		    for (HashMap<String, Object> m : dayList) {
			Integer num = Integer.valueOf(m.get("num").toString());
			String d = m.get("d").toString();
			String d1 = d.substring(0, 4);
			String d2 = d.substring(4, 6);
			String d3 = d.substring(6, 8);
			String d4 = d.substring(8, 10);
			String dd = d1 + "-" + d2 + "-" + d3 + " " + d4 + "时";
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("d", dd);
			map.put("num", num);
			listForTable.add(map);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
//		imgSrc = "admin/base/historyOnlineStatistics_browseHistoryOnlineByHour.action?begin="
//			+ begin + "&end=" + end;
		data = getDataHistoryOnlineByHour(begin,end);
	    }
	} else if (queryFlag.equals("byHour2")) {
	    if (DateService.DayBetween(beginTime, endTime) >= 7) {
		addActionMessage("Sorry,由于查询数据量过大，请选择日期相隔天数不要超过7天!");
		imgSrc = "media/default/images/sorry.gif";
	    } else {
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("begin", begin);
		object.put("end", end);
		object.put("handlerName", "HistoryOnlineStatisticsHandler");
		object.put("methodName", "browDataSetHistoryOnlineByHour");
		try {
		    object = CONNECTION.sendMsg(object);
		    listForTable = new ArrayList<HashMap<String, Object>>();
		    List<HashMap<String, Object>> dayList = (List<HashMap<String, Object>>) object
			    .get("hourList");// ip 集合
		    for (HashMap<String, Object> m : dayList) {
			Integer num = Integer.valueOf(m.get("num").toString());
			String d = m.get("d").toString();
			String d1 = d.substring(0, 4);
			String d2 = d.substring(4, 6);
			String d3 = d.substring(6, 8);
			String d4 = d.substring(8, 10);
			String dd = d1 + "-" + d2 + "-" + d3 + " " + d4 + "时";
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("d", dd);
			map.put("num", num);
			listForTable.add(map);
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		}
//		imgSrc = "admin/base/historyOnlineStatistics_browseHistoryOnlineByHour2.action?begin="
//			+ begin + "&end=" + end;
		data = getDataHistoryOnlineByHour(begin,end);
	    }
	}

	return SUCCESS;
    }

    /** ----------JFreeChar Action请求-----end----------------------------------- */

    public Long getBegin() {
	return begin;
    }

    /** ----------时 曲线-end----------------------- */
    /**
     * ----------JFreeChar数据处理-----end------------------------------------------
     * -----------------------
     */

    public String getBeginDate() {
	return beginDate;
    }

    public JFreeChart getChart() {
	return chart;
    }

    /**
     * ----------JFreeChar数据处理-----start----------------------------------------
     * -------------------------
     */
    /** ----------天-start----------------------- */
    public JFreeChart getChartHistoryOnlineByDay(Long begin, Long end) {
	chart = ChartFactory.createBarChart3D("在线统计图", // 图表标题
		"日期", // 目录轴的显示标签
		"最高在线人数", // 数值轴的显示标签
		getDataSetHistoryOnlineByDay(begin, end), // 数据集
		// PlotOrientation.HORIZONTAL , // 图表方向：水平
		PlotOrientation.VERTICAL, // 图表方向：垂直
		false, // 是否显示图例(对于简单的柱状图必须是false)
		false, // 是否生成工具
		false // 是否生成URL链接
		);

	// 重新设置图标标题，改变字体
	chart.setTitle(new TextTitle("分时段在线人数统计-天", new Font("黑体", Font.ITALIC,
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
	renderer.setMinimumBarLength(0);
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

    /** ----------时 柱状-end----------------------- */

    /** ----------时 柱状-start----------------------- */
    public JFreeChart getChartHistoryOnlineByHour(Long begin, Long end) {
	chart = ChartFactory.createBarChart("在线统计图", // 图表标题
		"日期", // 目录轴的显示标签
		"最高在线人数", // 数值轴的显示标签
		getDataSetHistoryOnlineByHour(begin, end), // 数据集
		// PlotOrientation.HORIZONTAL , // 图表方向：水平
		PlotOrientation.VERTICAL, // 图表方向：垂直
		false, // 是否显示图例(对于简单的柱状图必须是false)
		false, // 是否生成工具
		false // 是否生成URL链接
		);

	// 重新设置图标标题，改变字体
	chart.setTitle(new TextTitle("分时段在线人数统计-时", new Font("黑体", Font.ITALIC,
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
	BarRenderer renderer = new BarRenderer();
	renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	renderer.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 10));
	renderer.setBaseItemLabelsVisible(true);
	// 避免因为数值过小，显示不明显，或则看不到
	// renderer.setMinimumBarLength(0);
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

    /** ----------时 曲线-start----------------------- */
    public JFreeChart getChartHistoryOnlineByHour2(Long begin, Long end) {
	chart = ChartFactory.createLineChart3D("", "时间(小时)", "最高在线人数",
		getDataSetHistoryOnlineByHour2(begin, end),
		PlotOrientation.VERTICAL, true, true, false);
	// 3D
	// imgChart = ChartFactory.createLineChart3D("", "X轴", "Y轴",
	// getDataSet(), PlotOrientation.VERTICAL, true, true, false);
	chart.setBackgroundPaint(Color.white);
	chart.setBorderVisible(true);// 边框可见
	TextTitle title = new TextTitle("分时段在线统计-时", new Font("宋体", Font.BOLD,
		20));
	// 解决曲线图片标题中文乱码问题
	chart.setTitle(title);
	// 解决图表底部中文乱码问题
	chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
	CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
	categoryplot.setBackgroundPaint(Color.lightGray);
	categoryplot.setRangeGridlinePaint(Color.white);

	// Y轴
	NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
	numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	// 是否显示零点
	numberaxis.setAutoRangeIncludesZero(true);
	numberaxis.setAutoTickUnitSelection(true);
	// 解决Y轴标题中文乱码
	numberaxis.setLabelFont(new Font("sans-serif", Font.PLAIN, 12));
	// numberaxis.setTickUnit(new NumberTickUnit(10000));//Y轴数据间隔

	// x轴
	CategoryAxis domainAxis = categoryplot.getDomainAxis();
	// 解决x轴坐标上中文乱码
	domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
	// 解决x轴标题中文乱码
	domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 14));
	// 用于显示X轴刻度
	domainAxis.setTickMarksVisible(true);
	// 设置X轴45度
	domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

	LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
		.getRenderer();// 数据点
	lineandshaperenderer
		.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 11));
	// series 点（即数据点）可见
	lineandshaperenderer.setBaseShapesVisible(false);
	// 显示数据点的数据
	lineandshaperenderer
		.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	// 显示折线图点上的数据
	lineandshaperenderer.setBaseItemLabelsVisible(true);

	return chart;
    }

    @SuppressWarnings("unchecked")
    private CategoryDataset getDataSetHistoryOnlineByDay(Long begin, Long end) {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	List<HashMap<String, Object>> dayList = new ArrayList<HashMap<String, Object>>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("begin", begin);
	object.put("end", end);
	object.put("handlerName", "HistoryOnlineStatisticsHandler");
	object.put("methodName", "browDataSetHistoryOnlineByDay");
	try {
	    object = CONNECTION.sendMsg(object);
	    dayList = (List<HashMap<String, Object>>) object.get("dayList");// ip
									    // 集合
	} catch (Exception e) {
	    e.printStackTrace();
	}
	imgSrcWidth = 900;
	for (HashMap<String, Object> m : dayList) {
	    imgSrcWidth = dayList.size() * 60;
	    imgSrcWidth = imgSrcWidth <= 900 ? 900 : imgSrcWidth;
	    Double num = Double.valueOf(m.get("num").toString());
	    String d = m.get("d").toString().substring(0, 8) + "日";
	    dataset.addValue(num, "", d);
	}
	return dataset;
    }

    /** ----------天-end----------------------- */

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private CategoryDataset getDataSetHistoryOnlineByHour(Long begin, Long end) {

	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	// IDataHistoryService dataHistoryService =
	// (IDataHistoryService)SpringService.getBean("dataHistoryService");
	// List<HashMap> list =
	// dataHistoryService.DataHistoryListByHour(begin,end);
	List<HashMap> hourList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("begin", begin);
	object.put("end", end);
	object.put("handlerName", "HistoryOnlineStatisticsHandler");
	object.put("methodName", "browDataSetHistoryOnlineByHour");
	try {
	    object = CONNECTION.sendMsg(object);
	    hourList = (List<HashMap>) object.get("hourList");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	imgSrcWidth = 900;
	for (HashMap m : hourList) {
	    imgSrcWidth = hourList.size() * 30;
	    imgSrcWidth = imgSrcWidth <= 900 ? 900 : imgSrcWidth;
	    Double num = Double.valueOf(m.get("num").toString());
	    String d = m.get("d").toString();
	    String d1 = d.substring(0, 4);
	    String d2 = d.substring(4, 6);
	    String d3 = d.substring(6, 8);
	    String d4 = d.substring(8, 10);
	    dataset.addValue(num, "", d1 + "-" + d2 + "-" + d3 + " " + d4 + "时");
	}

	return dataset;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private CategoryDataset getDataSetHistoryOnlineByHour2(Long begin, Long end) {

	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	// IDataHistoryService dataHistoryService =
	// (IDataHistoryService)SpringService.getBean("dataHistoryService");
	// List<HashMap> list =
	// dataHistoryService.DataHistoryListByHour(begin,end);
	// IDataHistoryService dataHistoryService =
	// (IDataHistoryService)SpringService.getBean("dataHistoryService");
	// List<HashMap> list =
	// dataHistoryService.DataHistoryListByHour(begin,end);
	List<HashMap> hourList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("begin", begin);
	object.put("end", end);
	object.put("handlerName", "HistoryOnlineStatisticsHandler");
	object.put("methodName", "browDataSetHistoryOnlineByHour2");
	try {
	    object = CONNECTION.sendMsg(object);
	    hourList = (List<HashMap>) object.get("hourList");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	imgSrcWidth = 900;
	for (HashMap m : hourList) {
	    imgSrcWidth = hourList.size() * 30;
	    imgSrcWidth = imgSrcWidth <= 900 ? 900 : imgSrcWidth;
	    Double num = Double.valueOf(m.get("num").toString());
	    String d = m.get("d").toString();
	    String d1 = d.substring(0, 4);
	    String d2 = d.substring(4, 6);
	    String d3 = d.substring(6, 8);
	    String d4 = d.substring(8, 10);
	    dataset.addValue(num, "", d1 + "-" + d2 + "-" + d3 + " " + d4 + "时");
	}
	return dataset;
    }
    
    
    @SuppressWarnings("unchecked")
    private String getDataHistoryOnlineByDay(Long begin, Long end) {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	List<HashMap<String, Object>> dayList = new ArrayList<HashMap<String, Object>>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("begin", begin);
	object.put("end", end);
	object.put("handlerName", "HistoryOnlineStatisticsHandler");
	object.put("methodName", "browDataSetHistoryOnlineByDay");
	try {
	    object = CONNECTION.sendMsg(object);
	    dayList = (List<HashMap<String, Object>>) object.get("dayList");// ip
									    // 集合
	} catch (Exception e) {
	    e.printStackTrace();
	}
//	imgSrcWidth = 900;
//	for (HashMap<String, Object> m : dayList) {
//	    imgSrcWidth = dayList.size() * 60;
//	    imgSrcWidth = imgSrcWidth <= 900 ? 900 : imgSrcWidth;
//	    Double num = Double.valueOf(m.get("num").toString());
//	    String d = m.get("d").toString().substring(0, 8) + "日";
//	    dataset.addValue(num, "", d);
//	}
	JSONObject jsonObject = new JSONObject();
	JSONArray result = new JSONArray();
	for (Map m : dayList) {
	    Double num = Double.valueOf(m.get("num").toString());
	    JSONArray array = new   JSONArray();
	    array.add(m.get("d") + "日");
	    array.add(num);
	    result.add(array);
	}
	if(dayList==null||dayList.size()==0){
//	    JSONArray array = new   JSONArray();
//	    array.add("0日");
//	    array.add(0);
//	    result.add(array);
//	    JSONArray array2 = new   JSONArray();
//	    array2.add("1日");
//	    array2.add(44);
//	    result.add(array2);
	    return "";
	}
	jsonObject.put("data", result);
//	jsonObject.put("name", "月分段");
	JSONArray jsonArray = new JSONArray();
	jsonArray.add(jsonObject);
	return jsonArray.toJSONString();
    }

    /** ----------天-end----------------------- */

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private String getDataHistoryOnlineByHour(Long begin, Long end) {

	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	// IDataHistoryService dataHistoryService =
	// (IDataHistoryService)SpringService.getBean("dataHistoryService");
	// List<HashMap> list =
	// dataHistoryService.DataHistoryListByHour(begin,end);
	List<HashMap> hourList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("begin", begin);
	object.put("end", end);
	object.put("handlerName", "HistoryOnlineStatisticsHandler");
	object.put("methodName", "browDataSetHistoryOnlineByHour");
	try {
	    object = CONNECTION.sendMsg(object);
	    hourList = (List<HashMap>) object.get("hourList");
	} catch (Exception e) {
	    e.printStackTrace();
	}
//	imgSrcWidth = 900;
//	for (HashMap m : hourList) {
//	    imgSrcWidth = hourList.size() * 30;
//	    imgSrcWidth = imgSrcWidth <= 900 ? 900 : imgSrcWidth;
//	    Double num = Double.valueOf(m.get("num").toString());
//	    String d = m.get("d").toString();
//	    String d1 = d.substring(0, 4);
//	    String d2 = d.substring(4, 6);
//	    String d3 = d.substring(6, 8);
//	    String d4 = d.substring(8, 10);
//	    dataset.addValue(num, "", d1 + "-" + d2 + "-" + d3 + " " + d4 + "时");
//	}
	JSONObject jsonObject = new JSONObject();
	JSONArray result = new JSONArray();
	for (Map m : hourList) {
	    Double num = Double.valueOf(m.get("num").toString());
	    JSONArray array = new   JSONArray();
	    String d = m.get("d").toString();
	    String d1 = d.substring(0, 4);
	    String d2 = d.substring(4, 6);
	    String d3 = d.substring(6, 8);
	    String d4 = d.substring(8, 10);
	    array.add(d1 + "-" + d2 + "-" + d3 + " " + d4 + "时");
	    array.add(num);
	    result.add(array);
	}
	
	if(hourList==null||hourList.size()==0){
	    JSONArray array = new   JSONArray();
	    array.add("0日");
	    array.add(0);
	    result.add(array);
	    JSONArray array2 = new   JSONArray();
	    array2.add("1日");
	    array2.add(44);
	    result.add(array2);
	    return "";
	}
	jsonObject.put("data", result);
//	jsonObject.put("name", "月分段");
	JSONArray jsonArray = new JSONArray();
	jsonArray.add(jsonObject);
	return jsonArray.toJSONString();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private String getDataHistoryOnlineByHour2(Long begin, Long end) {

	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	// IDataHistoryService dataHistoryService =
	// (IDataHistoryService)SpringService.getBean("dataHistoryService");
	// List<HashMap> list =
	// dataHistoryService.DataHistoryListByHour(begin,end);
	// IDataHistoryService dataHistoryService =
	// (IDataHistoryService)SpringService.getBean("dataHistoryService");
	// List<HashMap> list =
	// dataHistoryService.DataHistoryListByHour(begin,end);
	List<HashMap> hourList = new ArrayList<HashMap>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("begin", begin);
	object.put("end", end);
	object.put("handlerName", "HistoryOnlineStatisticsHandler");
	object.put("methodName", "browDataSetHistoryOnlineByHour2");
	try {
	    object = CONNECTION.sendMsg(object);
	    hourList = (List<HashMap>) object.get("hourList");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	imgSrcWidth = 900;
	for (HashMap m : hourList) {
	    imgSrcWidth = hourList.size() * 30;
	    imgSrcWidth = imgSrcWidth <= 900 ? 900 : imgSrcWidth;
	    Double num = Double.valueOf(m.get("num").toString());
	    String d = m.get("d").toString();
	    String d1 = d.substring(0, 4);
	    String d2 = d.substring(4, 6);
	    String d3 = d.substring(6, 8);
	    String d4 = d.substring(8, 10);
	    dataset.addValue(num, "", d1 + "-" + d2 + "-" + d3 + " " + d4 + "时");
	}
	return null;
    }

    public Long getEnd() {
	return end;
    }

    public String getEndDate() {
	return endDate;
    }

    public String getImgSrc() {
	return imgSrc;
    }

    public Integer getImgSrcWidth() {
	return imgSrcWidth;
    }

    public List<HashMap<String, Object>> getListForTable() {
	return listForTable;
    }

    public String getQueryFlag() {
	return queryFlag;
    }

    public void setBegin(Long begin) {
	this.begin = begin;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setChart(JFreeChart chart) {
	this.chart = chart;
    }

    public void setEnd(Long end) {
	this.end = end;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setImgSrc(String imgSrc) {
	this.imgSrc = imgSrc;
    }

    public void setImgSrcWidth(Integer imgSrcWidth) {
	this.imgSrcWidth = imgSrcWidth;
    }

    public void setListForTable(List<HashMap<String, Object>> listForTable) {
	this.listForTable = listForTable;
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
    
    
}
