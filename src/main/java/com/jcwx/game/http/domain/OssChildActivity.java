package com.jcwx.game.http.domain;

import java.io.Serializable;

public class OssChildActivity implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String iconName;
    private Integer id;
    private String menuName;

    public String getIconName() {
	return iconName;
    }

    public Integer getId() {
	return id;
    }

    public String getMenuName() {
	return menuName;
    }

    public void setIconName(String iconName) {
	this.iconName = iconName;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setMenuName(String menuName) {
	this.menuName = menuName;
    }

}
