package com.jcwx.game.common.code;

import com.jcwx.game.admin.constant.OssServerConstant;

/**
 * 标准Code消息处理
 * 
 * @author Administrator
 * 
 */
public class StandCodeMessage implements ICodeMessage {

    private Integer code;

    public StandCodeMessage(Integer code) {
	super();
	this.code = code;
    }

    @Override
    public Integer getCode() {
	return code;
    }

    @Override
    public String getActionMsg() {
	return code == 0 ? OssServerConstant.CODE_OK
		: OssServerConstant.CODE_ERROR;
    }
    
    public static StandCodeMessage newInstance(Integer code){
	return new StandCodeMessage(code);
    }

}
