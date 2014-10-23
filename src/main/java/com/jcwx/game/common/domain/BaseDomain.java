package com.jcwx.game.common.domain;

import java.io.Serializable;

import com.jcwx.game.common.constants.GlobalConstants;

public abstract class BaseDomain implements Serializable {

    /** 对象更新标识，用于数据库同步 */
    private int modifyFlag;

    protected void exeGet() {

    }

    protected void exeSet() {
	if (modifyFlag != GlobalConstants.MODIFY_TRUE)
	    modifyFlag = GlobalConstants.MODIFY_TRUE;
    }

    public int getModifyFlag() {
	return modifyFlag;
    }

    /**
     * 重置对象更新标识
     */
    public void resetModifyFlag() {
	modifyFlag = GlobalConstants.MODIFY_FALSE;
    }

}
