package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 游戏配置
 * 
 * @author Administrator
 * 
 */
public class OssGameConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private int configId; // 配置id

    private String content; // 配置内容

    private int type; // 配置类型，参考ConfigType

    public int getConfigId() {
	return configId;
    }

    public String getContent() {
	return content;
    }

    public int getType() {
	return type;
    }

    public void setConfigId(int configId) {
	this.configId = configId;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public void setType(int type) {
	this.type = type;
    }

}
