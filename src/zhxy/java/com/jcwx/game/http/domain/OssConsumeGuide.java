package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 消费指引
 * 
 * @author csp
 * 
 */
public class OssConsumeGuide implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 消费指引ID */
    private Integer consumeGuideId;
    /** 创建时间 */
    private Date createTime;
    /** 消费指引描述 */
    private String describe;
    /** 最后修改时间 */
    private Date moldfyTime;
    /** 消费指引名称 */
    private String name;
    /** 是否开放 */
    private int opened;
    /** 消费指引前往界面：0 -无 1-商城 2-优惠礼包 3-充值 4-灵药园 5-炼金 .... */
    private Integer path;
    /** 消费指引排序 */
    private Integer sort;

    public OssConsumeGuide() {
    }

    public Integer getConsumeGuideId() {
	return consumeGuideId;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getDescribe() {
	return describe;
    }

    public Date getMoldfyTime() {
	return moldfyTime;
    }

    public String getName() {
	return name;
    }

    public int getOpened() {
	return opened;
    }

    public Integer getPath() {
	return path;
    }

    public Integer getSort() {
	return sort;
    }

    public void setConsumeGuideId(Integer consumeGuideId) {
	this.consumeGuideId = consumeGuideId;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDescribe(String describe) {
	this.describe = describe;
    }

    public void setMoldfyTime(Date moldfyTime) {
	this.moldfyTime = moldfyTime;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOpened(int opened) {
	this.opened = opened;
    }

    public void setPath(Integer path) {
	this.path = path;
    }

    public void setSort(Integer sort) {
	this.sort = sort;
    }

}
