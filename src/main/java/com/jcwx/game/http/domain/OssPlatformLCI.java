/**   
 * @Title: OssPlatformLCI.java 
 * @Package com.jcwx.game.http.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liushang 364173778@qq.com
 * @date 2013年12月24日 下午4:05:20 
 * @version V1.0   
 */
package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * @ClassName: OssPlatformLCI
 * @Description: 平台等级职业信息 Platform level career information
 * @author liushang 364173778@qq.com
 * @date 2013年12月24日 下午4:05:20
 * 
 */
@SuppressWarnings("serial")
public class OssPlatformLCI implements Serializable {
    /** 等级 */
    private Integer level;
    /** 人数 */
    private Integer number;
    /** 职业 */
    private Integer occupation;

    public Integer getLevel() {
	return this.level;
    }

    public Integer getNumber() {
	return this.number;
    }

    public Integer getOccupation() {
	return this.occupation;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setNumber(Integer number) {
	this.number = number;
    }

    public void setOccupation(Integer occupation) {
	this.occupation = occupation;
    }

}
