package com.jcwx.game.admin.assay;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.icu.text.SimpleDateFormat;
import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.admin.vo.PageMessage;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.ZArenaDay;


/**
 * <精英地下城>副本死亡等级统计
 * 
 * @author Administrator 2013-10-16
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class ZArenaAction extends BaseInfoAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<ZArenaDay> arenaDays = new ArrayList<ZArenaDay>();
    // 开始时间
    private Date beginTime;

    // 结束时间
    private Date endTime;

    private Object jobJsonStr;

    private PageMessage pageMessage = PageMessage.getOkMessage();

    @Action(value = "arena_export")
    public void exportExcel() {
	OutputStream os = null;

	try {
	    SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
	    if (beginTime == null) {

		beginTime = DateService.getCurrentMonthFirstDay();
	    }
	    if (endTime == null) {
		endTime = new java.util.Date();
	    }
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("beginTime", beginTime);
	    object.put("endTime", endTime);
	    object.put("handlerName", "ZArenaHandler");
	    List<ZArenaDay> zArenaDays = null;
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		zArenaDays = (List<ZArenaDay>) object.get("ossZarenaDayList");
	    }

	    HttpServletResponse response = ServletActionContext.getResponse();
	    os = response.getOutputStream();
	    response.setContentType("application/vnd.ms-excel");
	    response.setHeader(
		    "Content-disposition",
		    "attachment; filename="
			    + URLEncoder.encode(
				    "竞技场统计" + format.format(new Date())
					    + ".xls", "UTF-8"));
	    // 建立excel文件
	    WritableWorkbook wbook = Workbook.createWorkbook(os);
	    // sheet名称
	    WritableSheet wsheet = wbook.createSheet("竞技场统计", 0);
	    // 设置样式,新建一个宋体字体，设置字号为16，设置字体加粗
	    WritableFont titlefont = new WritableFont(
		    WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
	    WritableCellFormat titleFormat = new WritableCellFormat(titlefont);
	    // 设置第0行的高度
	    wsheet.setRowView(0, 480);

	    // 设置水平居左
	    titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
	    // 设置垂直居中
	    titleFormat
		    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	    // 设置数字样式
	    WritableFont contentfont = new WritableFont(
		    WritableFont.createFont("宋体"), 10);
	    // NumberFormats.FLOAT表示是数字样式
	    WritableCellFormat contentFormat = new WritableCellFormat(
		    contentfont, NumberFormats.FLOAT);

	    wsheet.addCell(new Label(0, 0, "日期", titleFormat));
	    wsheet.addCell(new Label(1, 0, "总次数", titleFormat));
	    wsheet.addCell(new Label(2, 0, "单人匹配总人数", titleFormat));
	    wsheet.addCell(new Label(3, 0, "单人匹配总次数", titleFormat));
	    wsheet.addCell(new Label(4, 0, "单人匹配真人次数", titleFormat));
	    wsheet.addCell(new Label(5, 0, "多人匹配总人数", titleFormat));
	    wsheet.addCell(new Label(6, 0, "多人匹配总次数", titleFormat));
	    wsheet.addCell(new Label(7, 0, "多人匹配真人次数", titleFormat));
	    wsheet.addCell(new Label(8, 0, "1战意战斗人数", titleFormat));
	    wsheet.addCell(new Label(9, 0, "1战意战斗次数", titleFormat));
	    wsheet.addCell(new Label(10, 0, "3战意战斗人数", titleFormat));
	    wsheet.addCell(new Label(11, 0, "3战意战斗次数", titleFormat));
	    wsheet.addCell(new Label(12, 0, "胜利总场次", titleFormat));
	    wsheet.addCell(new Label(13, 0, "失败总场次", titleFormat));
	    wsheet.addCell(new Label(14, 0, "战/弓/法分别胜场", titleFormat));
	    wsheet.addCell(new Label(15, 0, "战/弓/法分别负场", titleFormat));
	    wsheet.addCell(new Label(16, 0, "消耗小/大号角数", titleFormat));
	    wsheet.addCell(new Label(17, 0, "留存小/大号角数", titleFormat));

	    for (int i = 0; i < zArenaDays.size(); i++) {
		wsheet.addCell(new Label(0, i + 1, format.format(zArenaDays
			.get(i).getDateTime()), contentFormat));
		wsheet.addCell(new Label(1, i + 1, String.valueOf(zArenaDays
			.get(i).getTotalCount()), contentFormat));
		wsheet.addCell(new Label(2, i + 1, String.valueOf(zArenaDays
			.get(i).getTotalPersonSmatch()), contentFormat));
		wsheet.addCell(new Label(3, i + 1, String.valueOf(zArenaDays
			.get(i).getTotalCountSmatch()), contentFormat));
		wsheet.addCell(new Label(4, i + 1, String.valueOf(zArenaDays
			.get(i).getSmatchLiveview()), contentFormat));
		wsheet.addCell(new Label(5, i + 1, String.valueOf(zArenaDays
			.get(i).getMultiPersonMatch()), contentFormat));
		wsheet.addCell(new Label(6, i + 1, String.valueOf(zArenaDays
			.get(i).getMultiCountMatch()), contentFormat));
		wsheet.addCell(new Label(7, i + 1, String.valueOf(zArenaDays
			.get(i).getMmatchLiveview()), contentFormat));
		wsheet.addCell(new Label(8, i + 1, String.valueOf(zArenaDays
			.get(i).getPersonFight()), contentFormat));
		wsheet.addCell(new Label(9, i + 1, String.valueOf(zArenaDays
			.get(i).getCountFight()), contentFormat));
		wsheet.addCell(new Label(10, i + 1, String.valueOf(zArenaDays
			.get(i).getThreePersonFight()), contentFormat));
		wsheet.addCell(new Label(11, i + 1, String.valueOf(zArenaDays
			.get(i).getThreeCountFight()), contentFormat));
		wsheet.addCell(new Label(12, i + 1, String.valueOf(zArenaDays
			.get(i).getWinCount()), contentFormat));
		wsheet.addCell(new Label(13, i + 1, String.valueOf(zArenaDays
			.get(i).getFailCount()), contentFormat));
		wsheet.addCell(new Label(14, i + 1, String.valueOf(zArenaDays
			.get(i).getWarriorWin()
			+ "/"
			+ String.valueOf(zArenaDays.get(i).getArcherWin())
			+ "/"
			+ String.valueOf(zArenaDays.get(i).getMasterWin())),
			contentFormat));
		wsheet.addCell(new Label(15, i + 1, String.valueOf(zArenaDays
			.get(i).getWarriorLose()
			+ "/"
			+ String.valueOf(zArenaDays.get(i).getArcherLose())
			+ "/"
			+ String.valueOf(zArenaDays.get(i).getMasterLose())),
			contentFormat));
		wsheet.addCell(new Label(16, i + 1, String.valueOf(zArenaDays
			.get(i).getConsumeSmartHorn()
			+ "/"
			+ String.valueOf(zArenaDays.get(i)
				.getConsumeLargeHorn())), contentFormat));
		wsheet.addCell(new Label(17, i + 1, String.valueOf(zArenaDays
			.get(i).getRetentionSmartHorn()
			+ "/"
			+ String.valueOf(zArenaDays.get(i)
				.getRetentionLargeHorn())), contentFormat));
	    }

	    for (int i = 0; i < 18; i++) {
		CellView autoColumnWidth = new CellView();
		autoColumnWidth.setAutosize(true);
		wsheet.setColumnView(i, autoColumnWidth);

	    }

	    try {
		wbook.write(); // 写入文件
	    } catch (Exception e1) {
		e1.printStackTrace();
	    }
	    try {
		wbook.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (os != null)
		try {
		    os.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
    }

    public List<ZArenaDay> getArenaDays() {
	return arenaDays;
    }

    public Date getBeginTime() {
	return beginTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public Object getJobJsonStr() {
	return jobJsonStr;
    }

    @Action(value = "arena_index", results = { @Result(name = "success", location = "../../admin/assay/zArenaDayAssay.jsp") })
    public String query() throws Exception {
	if (beginTime == null) {

	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null) {
	    endTime = new java.util.Date();
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "ZArenaHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		arenaDays = (List<ZArenaDay>) object.get("ossZarenaDayList");
	    }
	    JSONArray array = new JSONArray();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    for (ZArenaDay z : arenaDays) {
		String date = formatter.format(z.getDateTime());
		JSONObject dateObject = new JSONObject();
		dateObject.put("name", date);

		JSONArray dataArray2 = new JSONArray();
		dateObject.put("data", dataArray2);

		// 二维数组
		// 2
		dataArray2 = new JSONArray();
		dataArray2.add(1);
		dataArray2.add(z.getTotalPersonSmatch());// 第一天设为100%
		dateObject.getJSONArray("data").add(dataArray2);

		dataArray2 = new JSONArray();
		dataArray2.add(2);
		dataArray2.add(z.getMultiPersonMatch());
		dateObject.getJSONArray("data").add(dataArray2);

		dataArray2 = new JSONArray();
		dataArray2.add(3);
		dataArray2.add(z.getMultiCountMatch());
		dateObject.getJSONArray("data").add(dataArray2);

		dataArray2 = new JSONArray();
		dataArray2.add(4);
		dataArray2.add(z.getWinCount());
		dateObject.getJSONArray("data").add(dataArray2);

		array.add(dateObject);

	    }

	    jobJsonStr = array.toJSONString();
	    System.out.println(jobJsonStr);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return SUCCESS;
    }

    public void setArenaDays(List<ZArenaDay> arenaDays) {
	this.arenaDays = arenaDays;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setJobJsonStr(Object jobJsonStr) {
	this.jobJsonStr = jobJsonStr;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
