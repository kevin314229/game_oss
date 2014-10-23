package com.jcwx.game.common.code.util;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import com.jcwx.game.common.code.StandCodeMessage;

public class CodeUtil {

    private static final String NO_RETURN_CODE_KEY = "no return code key!";

    private static final String CODE = "code";

    /**
     * 建立标准Code处理方式
     * 
     * 仅仅返回Code的标准处理方式,OK,ERROR两种结果
     * 
     * @param map
     * @return
     */
    public static String getActionMsgByMap(Map<String, Object> map) {
	return StandCodeMessage.newInstance(getCode(map)).getActionMsg();
    }

    /**
     * 得到Map中的Code
     * 
     * @param object
     * @return
     */
    public static Integer getCode(Map<String, Object> object) {
	return Integer.valueOf(String.valueOf(Validate.notNull(
		object.get(CODE), NO_RETURN_CODE_KEY)));

    }

}
