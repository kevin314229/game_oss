package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 新手步骤 用于与 oss 对接
 * 
 * @author Administrator
 * 
 */
public class OssNewRecordInfo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 统计时间
    private Date createDate;
    // 记录统计信息
    private String newRecordInfo;

    public Date getCreateDate() {
	return createDate;
    }

    public String getNewRecordInfo() {
	return newRecordInfo;
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    public void setNewRecordInfo(String newRecordInfo) {
	this.newRecordInfo = newRecordInfo;
    }

}
