package com.jcwx.game.domain;

/** 系统相关信息 */
public class MonitorInfoBean {
    /** cpu使用率. */
    private double cpuRatio;

    /** 剩余内存. */
    private long freeMemory;

    /** 剩余的物理内存. */
    private long freePhysicalMemorySize;

    /** 最大可使用内存. */
    private long maxMemory;

    /** 操作系统. */
    private String osName;

    /** 可使用内存. */
    private long totalMemory;

    /** 总的物理内存. */
    private long totalMemorySize;

    /** 线程总数. */
    private int totalThread;

    /** 已使用的物理内存. */
    private long usedMemory;

    public double getCpuRatio() {
	return cpuRatio;
    }

    public long getFreeMemory() {
	return freeMemory;
    }

    public long getFreePhysicalMemorySize() {
	return freePhysicalMemorySize;
    }

    public long getMaxMemory() {
	return maxMemory;
    }

    public String getOsName() {
	return osName;
    }

    public long getTotalMemory() {
	return totalMemory;
    }

    public long getTotalMemorySize() {
	return totalMemorySize;
    }

    public int getTotalThread() {
	return totalThread;
    }

    public long getUsedMemory() {
	return usedMemory;
    }

    public void setCpuRatio(double cpuRatio) {
	this.cpuRatio = cpuRatio;
    }

    public void setFreeMemory(long freeMemory) {
	this.freeMemory = freeMemory;
    }

    public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
	this.freePhysicalMemorySize = freePhysicalMemorySize;
    }

    public void setMaxMemory(long maxMemory) {
	this.maxMemory = maxMemory;
    }

    public void setOsName(String osName) {
	this.osName = osName;
    }

    public void setTotalMemory(long totalMemory) {
	this.totalMemory = totalMemory;
    }

    public void setTotalMemorySize(long totalMemorySize) {
	this.totalMemorySize = totalMemorySize;
    }

    public void setTotalThread(int totalThread) {
	this.totalThread = totalThread;
    }

    public void setUsedMemory(long usedMemory) {
	this.usedMemory = usedMemory;
    }

}
