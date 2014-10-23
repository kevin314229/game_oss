package com.jcwx.game.http.domain;

import java.util.Date;

/**
 * @ClassName: OssModifyActivity
 * @Description: OssModifyActivity Oss活动修改模型，新增两列，采取继承方式新增功能
 * @author liushang 364173778@qq.com
 * @date 2013年12月13日 下午1:52:41
 * 
 */
public class OssModifyActivity extends OssActivity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer playerBaseTimes;
    private Integer playerTimes;

    /** 活动的排序，展示的顺序 */
    /*private Integer rank;*/
    
    public OssModifyActivity() {
	super();
    }

    public OssModifyActivity(Integer id, Integer type, Integer characteristic,
	    Integer times, String name, String activityDesc, String rule,
	    Date openTime, Date overTime, Integer playerTimes,
	    Integer playerBaseTimes) {
	super(id, type, characteristic, times, name, activityDesc, rule,
		openTime, overTime);
	this.playerTimes = playerTimes;
	this.playerBaseTimes = playerBaseTimes;
	//this.rank=rank;
    }

    
    /*public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }*/

    public Integer getPlayerBaseTimes() {
	return this.playerBaseTimes;
    }

    public Integer getPlayerTimes() {
	return this.playerTimes;
    }

    public void setPlayerBaseTimes(Integer playerBaseTimes) {
	this.playerBaseTimes = playerBaseTimes;
    }

    public void setPlayerTimes(Integer playerTimes) {
	this.playerTimes = playerTimes;
    }

}
