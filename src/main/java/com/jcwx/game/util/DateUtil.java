package com.jcwx.game.util;

import java.util.Date;

import com.jcwx.game.common.DateService;


/**
 * 时间类
 * 
 * @author csp
 * 
 */
public class DateUtil {

    private String beginTime;

    private String endTime;

    private Date beginDate;
    
    private Date endDate;
    

    public String getBeginTime() {
	return beginTime;
    }

    public void setBeginTime(String beginTime) {
	this.beginTime = beginTime;
    }

    public String getEndTime() {
	return endTime;
    }

    public void setEndTime(String endTime) {
	this.endTime = endTime;
    }

    public Date getBeginDate() {
	if (beginTime != null && !"".equals(beginTime)) {
	    beginDate = DateService.getDateFirstTime(beginDate);
	} else {
	    beginDate = DateService.getCurrentDayFirstUtilDate();
	}
	return beginDate;
    }

    
    public Date getEndDate() {
	
	if (endTime == null || "".equals(endTime)) {
	    endDate= DateService.getCurrentDayLastUtilDate();
	} else {
	    endDate = DateService.getDateLastTime(endTime);
	}
	return endDate;
    }

    

}
