package com.jcwx.game.domain;

import java.io.Serializable;

public class OssMonitor implements Serializable {
    /** cpu使用率 */
    private double cupRatio;
    /** JVM剩余内存 */
    private Long freeMemory;
    /** JVM最大可使用内存 */
    private Long maxMemory;
    /** OS剩余的物理内存 */
    private Long ossFreePhysicalMemory;
    /**  */
    private Long ossMonitorID;
    /** OS总的物理内存 */
    private Long osTotalMemorySize;
    /** JVM可用内存 */
    private Long totalMemory;
    /** 线程总数 */
    private Integer totalThread;

    public double getCupRatio() {
	return cupRatio;
    }

    public Long getFreeMemory() {
	return freeMemory;
    }

    public Long getMaxMemory() {
	return maxMemory;
    }

    public Long getOssFreePhysicalMemory() {
	return ossFreePhysicalMemory;
    }

    public Long getOssMonitorID() {
	return ossMonitorID;
    }

    public Long getOsTotalMemorySize() {
	return osTotalMemorySize;
    }

    public Long getTotalMemory() {
	return totalMemory;
    }

    public Integer getTotalThread() {
	return totalThread;
    }

    public void setCupRatio(double cupRatio) {
	this.cupRatio = cupRatio;
    }

    public void setFreeMemory(Long freeMemory) {
	this.freeMemory = freeMemory;
    }

    public void setMaxMemory(Long maxMemory) {
	this.maxMemory = maxMemory;
    }

    public void setOssFreePhysicalMemory(Long ossFreePhysicalMemory) {
	this.ossFreePhysicalMemory = ossFreePhysicalMemory;
    }

    public void setOssMonitorID(Long ossMonitorID) {
	this.ossMonitorID = ossMonitorID;
    }

    public void setOsTotalMemorySize(Long osTotalMemorySize) {
	this.osTotalMemorySize = osTotalMemorySize;
    }

    public void setTotalMemory(Long totalMemory) {
	this.totalMemory = totalMemory;
    }

    public void setTotalThread(Integer totalThread) {
	this.totalThread = totalThread;
    }

}
