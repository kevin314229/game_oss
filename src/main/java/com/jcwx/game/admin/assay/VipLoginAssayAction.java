package com.jcwx.game.admin.assay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;


/**
 * vip登录分析
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class VipLoginAssayAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Double avg;

    /** 开始时间 */
    private String beginDate;
    private List<Map<String, Object>> loginHourAssayList;

    private Integer loginNum;

    private String percent;

    private Integer vipNum;

    public Double getAvg() {
	return avg;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public List<Map<String, Object>> getLoginHourAssayList() {
	return loginHourAssayList;
    }

    public Integer getLoginNum() {
	return loginNum;
    }

    public String getPercent() {
	return percent;
    }

    public Integer getVipNum() {
	return vipNum;
    }

    public void setAvg(Double avg) {
	this.avg = avg;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setLoginHourAssayList(
	    List<Map<String, Object>> loginHourAssayList) {
	this.loginHourAssayList = loginHourAssayList;
    }

    public void setLoginNum(Integer loginNum) {
	this.loginNum = loginNum;
    }

    public void setPercent(String percent) {
	this.percent = percent;
    }

    public void setVipNum(Integer vipNum) {
	this.vipNum = vipNum;
    }

    /**
     * 新vip玩家数据分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "vipLoginAssay", results = { @Result(name = "success", location = "/admin/assay/vipLoginAssay.jsp") })
    public String vipLoginAssay() throws Exception {

	// 要用 23:59:59 数据库为datatime
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("handlerName", "AssayPlayerVipHandler");
	object.put("methodName", "vipLoginAssay");
	object = CONNECTION.sendMsg(object);

	vipNum = (Integer) object.get("vipNum");// 截至指定时间所有所vip数量
	loginNum = (Integer) object.get("loginNum");// vip指定日期的玩家登录数量
	loginHourAssayList = (List<Map<String, Object>>) object
		.get("loginHourAssayList");// vip登陆的时间段分布 结果: h:小时点 c:人次
	avg = new BigDecimal(object.get("avg").toString()).doubleValue();// vip用户每日平均在线时长
									 // (秒)

	if (vipNum == 0) {
	    percent = "0";
	} else {
	    double k = (double) loginNum / vipNum * 100;
	    BigDecimal big = new BigDecimal(k);
	    percent = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
		    + "%";
	}

	// TODO,详细内容请查看游戏查询中定义

	// TODO 要显示的结果集,*当前只能查询某一天的记录 -->
	// Vip用户每日登陆数以及所占VIP用户数的百分比；vip用户每日登陆的时间段分布；vip用户每日平均在线时长 ===> 百分比
	// loginNum/vipNum

	return "success";
    }

}
