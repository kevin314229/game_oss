package com.jcwx.game.util;

import org.apache.commons.lang3.StringUtils;

public class Page {

    private static final String DESC = "DESC";

    private static final int DEFAULT_PAGE_SIZE = 20;
    
    private static final int DEFAULT_CURRENT_NO = 1;
    
    /** 当前页数 */
    private Integer currPageNO;
    /** 总记录数 */
    private Integer allNum;
    /** 每页记录数 */
    private Integer onePageNum;
    /** 总页数 */
    private Integer pages;
    /** 排序asc|desc */
    private String orderFlag;

    public Page(Integer currPageNO, Integer onePageNum) {
	super();
	this.currPageNO = currPageNO;
	this.onePageNum = onePageNum;
    }

    public Integer getCurrPageNO() {
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	/*if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}*/
	return currPageNO;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
	// 判断有多少页
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}

    }

    public Integer getOnePageNum() {
	// 当未设置页大小时 默认20
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = DEFAULT_PAGE_SIZE;
	}
	return onePageNum;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public Integer getBeginNum() {
	Integer beginNum = (getCurrPageNO() - 1) * getOnePageNum();
	if (beginNum < 0)
	    beginNum = 0;
	return beginNum;
    }

    public Integer getPages() {
	return pages;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }
    
    /**
     * 设置当前页和页大小
     * @param currPageNO //当前页
     * @param onePageNum // 页大小
     * @return
     */
    public static Page createPageCurrentOneSize(Integer currPageNO, Integer onePageNum) {
	return new Page(currPageNO, onePageNum);
    }
    
    public static Page createDefaultPage() {
	return new Page(DEFAULT_CURRENT_NO, DEFAULT_PAGE_SIZE);
    }

    public String getOrderFlag() {
	if(StringUtils.isBlank(orderFlag)){
	    orderFlag = DESC;
	}
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

   
}
