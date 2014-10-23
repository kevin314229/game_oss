package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

import com.jcwx.game.common.DateService;

public class DataHistory implements Serializable {

    private static final long serialVersionUID = 4284572230409019936L;

    /** 数据历史编号(格式:YYYYMMDDHHMM) */
    private Long dataHistoryID;
    /** 数据历史编号创建的时间 */
    private Date dataHistoryTime;

    /** 在线玩家数量 */
    private Integer onlinePlayerNum;

    public Long getDataHistoryID() {
	return dataHistoryID;
    }

    public Date getDataHistoryTime() {
	return dataHistoryTime;
    }

    public Date getDataHistoryTimeByID() {
	return DateService.getDateByStrAndFormat(dataHistoryID.toString(),
		"yyyyMMddHHmm");
    }

    public Integer getOnlinePlayerNum() {
	return onlinePlayerNum;
    }

    public void setDataHistoryID(Long dataHistoryID) {
	this.dataHistoryID = dataHistoryID;
    }

    public void setDataHistoryTime(Date dataHistoryTime) {
	this.dataHistoryTime = dataHistoryTime;
    }

    public void setOnlinePlayerNum(Integer onlinePlayerNum) {
	this.onlinePlayerNum = onlinePlayerNum;
    }

}