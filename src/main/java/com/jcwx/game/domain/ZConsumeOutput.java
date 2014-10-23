/**
 * 
 */
package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ZConsumeOutput implements Serializable {

    /** 消耗金币 */
    private Long consumSilver;
    /**  */
    private Date id;
    /** 单笔最大角色名 */
    private String nickName;
    /** 手机橙装总消耗 */
    private Long orangeIncmo;
    /** pc橙装总消耗 */
    private Long orangeIncpc;
    /** 橙装总消耗 */
    private Long orangeIncTotal;
    /** 手机橙装总产出 */
    private Long orangeOutmo;
    /** PC橙装总产出 */
    private Long orangeOutpc;
    /** 橙装总产出 */
    private Long orangeOutTotal;
    /** 金币产出 */
    private Long outSilver;

    /** PC金币消费 */
    private Long pcConsum;
    /** PC金币产出 */
    private Long pcOut;
    /** 手机金币消费 */
    private Long phoneConsum;
    /** 手机金币产出 */
    private Long phoneOut;

    /** 手机紫装总消耗 */
    private Long purpleIncmo;
    /** pc紫装总消耗 */
    private Long purpleIncpc;
    /** 紫装总消耗 */
    private Long purpleIncTotal;
    /** 手机紫装总产出 */
    private Long purpleOutmo;

    /** PC紫装总产出 */
    private Long purpleOutpc;
    /** 紫装总产出 */
    private Long purpleOutTotal;
    /** 服务器留存金币数 */
    private Long remainSilver;
    /** 单笔最大 */
    private Integer singleBig;

    /** 是否是临时数据,true1临时数据，false0正式数据 */
    private Boolean tmpFlag;

    public ZConsumeOutput() {

    }

    public Long getConsumSilver() {
	return consumSilver;
    }

    public Date getId() {
	return id;
    }

    public String getNickName() {
	return nickName;
    }

    public Long getOrangeIncmo() {
	return orangeIncmo;
    }

    public Long getOrangeIncpc() {
	return orangeIncpc;
    }

    public Long getOrangeIncTotal() {
	return orangeIncTotal;
    }

    public Long getOrangeOutmo() {
	return orangeOutmo;
    }

    public Long getOrangeOutpc() {
	return orangeOutpc;
    }

    public Long getOrangeOutTotal() {
	return orangeOutTotal;
    }

    public Long getOutSilver() {
	return outSilver;
    }

    public Long getPcConsum() {
	return pcConsum;
    }

    public Long getPcOut() {
	return pcOut;
    }

    public Long getPhoneConsum() {
	return phoneConsum;
    }

    public Long getPhoneOut() {
	return phoneOut;
    }

    public Long getPurpleIncmo() {
	return purpleIncmo;
    }

    public Long getPurpleIncpc() {
	return purpleIncpc;
    }

    public Long getPurpleIncTotal() {
	return purpleIncTotal;
    }

    public Long getPurpleOutmo() {
	return purpleOutmo;
    }

    public Long getPurpleOutpc() {
	return purpleOutpc;
    }

    public Long getPurpleOutTotal() {
	return purpleOutTotal;
    }

    public Long getRemainSilver() {
	return remainSilver;
    }

    public Integer getSingleBig() {
	return singleBig;
    }

    public Boolean getTmpFlag() {
	return tmpFlag;
    }

    public void setConsumSilver(Long consumSilver) {
	this.consumSilver = consumSilver;
    }

    public void setId(Date id) {
	this.id = id;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOrangeIncmo(Long orangeIncmo) {
	this.orangeIncmo = orangeIncmo;
    }

    public void setOrangeIncpc(Long orangeIncpc) {
	this.orangeIncpc = orangeIncpc;
    }

    public void setOrangeIncTotal(Long orangeIncTotal) {
	this.orangeIncTotal = orangeIncTotal;
    }

    public void setOrangeOutmo(Long orangeOutmo) {
	this.orangeOutmo = orangeOutmo;
    }

    public void setOrangeOutpc(Long orangeOutpc) {
	this.orangeOutpc = orangeOutpc;
    }

    public void setOrangeOutTotal(Long orangeOutTotal) {
	this.orangeOutTotal = orangeOutTotal;
    }

    public void setOutSilver(Long outSilver) {
	this.outSilver = outSilver;
    }

    public void setPcConsum(Long pcConsum) {
	this.pcConsum = pcConsum;
    }

    public void setPcOut(Long pcOut) {
	this.pcOut = pcOut;
    }

    public void setPhoneConsum(Long phoneConsum) {
	this.phoneConsum = phoneConsum;
    }

    public void setPhoneOut(Long phoneOut) {
	this.phoneOut = phoneOut;
    }

    public void setPurpleIncmo(Long purpleIncmo) {
	this.purpleIncmo = purpleIncmo;
    }

    public void setPurpleIncpc(Long purpleIncpc) {
	this.purpleIncpc = purpleIncpc;
    }

    public void setPurpleIncTotal(Long purpleIncTotal) {
	this.purpleIncTotal = purpleIncTotal;
    }

    public void setPurpleOutmo(Long purpleOutmo) {
	this.purpleOutmo = purpleOutmo;
    }

    public void setPurpleOutpc(Long purpleOutpc) {
	this.purpleOutpc = purpleOutpc;
    }

    public void setPurpleOutTotal(Long purpleOutTotal) {
	this.purpleOutTotal = purpleOutTotal;
    }

    public void setRemainSilver(Long remainSilver) {
	this.remainSilver = remainSilver;
    }

    public void setSingleBig(Integer singleBig) {
	this.singleBig = singleBig;
    }

    public void setTmpFlag(Boolean tmpFlag) {
	this.tmpFlag = tmpFlag;
    }

}
