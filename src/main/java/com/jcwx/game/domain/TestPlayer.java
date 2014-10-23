package com.jcwx.game.domain;

public class TestPlayer {
    private Integer level;
    private String password;
    private String userName;

    public TestPlayer() {

    }

    public TestPlayer(String name, String password, Integer ip) {
	this.userName = name;
	this.password = password;
	this.level = ip;
    }

    public Integer getLevel() {
	return level;
    }

    public String getPassword() {
	return password;
    }

    public String getUserName() {
	return userName;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    @Override
    public String toString() {
	return this.userName + " " + this.password + " " + this.level;
    }
}
