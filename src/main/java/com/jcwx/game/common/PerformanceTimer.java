package com.jcwx.game.common;

/**
 * 计行时间计算工具
 * 
 * @author run
 * @Create 2013-3-28
 */
public class PerformanceTimer {
    private long _begin;

    public PerformanceTimer() {
	_begin = System.currentTimeMillis();
    }

    public long get() {
	return System.currentTimeMillis() - this._begin;
    }

    public String getStr() {
	long l1 = System.currentTimeMillis() - this._begin;
	return "(" + l1 + "ms)";
    }

    public void reset() {
	this._begin = System.currentTimeMillis();
    }

}
