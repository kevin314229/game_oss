package com.jcwx.game.domain;

import java.util.List;

public class PlayerInfo {
    private Integer level;

    private List<TestPlayer> playerList;

    public Integer getLevel() {
	return level;
    }

    public List<TestPlayer> getPlayerList() {
	return playerList;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setPlayerList(List<TestPlayer> playerList) {
	this.playerList = playerList;
    }

    @Override
    public String toString() {
	StringBuffer result = new StringBuffer();
	result.append(this.level);
	for (TestPlayer p : playerList)
	    result.append("\n" + p.toString());
	return result.toString();

    }

}
