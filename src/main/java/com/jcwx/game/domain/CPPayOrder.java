package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class CPPayOrder implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3271128005569792292L;
    /** 平台标识 */
    private String channel;
    /** 平台订单号 */
    private String orderid;
    
    private Integer money;
    /** 订单创建时间 */
    private Date ordertime;
    /** 订单处理完成时间 */
    private Date paidtime;
    /** 大区ID */
    private String server;
    /** 角色ID */
    private String roleId;
    /** 角色名 */
    private String role;
    /** 等级 */
    private int level;
    /** 账号ID */
    private Integer playerId;
    /** 平台标识 */
    private String ptCode;
   
    private String loginName;
    /** 购买的游戏币数量 */
    private Double coins;
    /** 平台登录名 */
    private String uuid;
   
    public CPPayOrder() {
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Date getPaidtime() {
        return paidtime;
    }

    public void setPaidtime(Date paidtime) {
        this.paidtime = paidtime;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Double getCoins() {
        return coins;
    }

    public void setCoins(Double coins) {
        this.coins = coins;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}