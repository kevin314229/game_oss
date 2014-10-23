package com.jcwx.game.common.constants;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jcwx.game.web.CommonAnnotation;

/**
 * 各平台的code字典
 * 
 * @author Administrator
 * 
 */
public class GameConstant {

    /** pp助手 */
    @CommonAnnotation(label = "封魔")
    public static final String game1 = "1";

    /**
     * uc
     */
    @CommonAnnotation(label = "战魂西游")
    public static final String game2 = "2";

    public static Map<String, String> gameMap = new LinkedHashMap<String, String>();

    private static Logger logger = Logger.getLogger(GameConstant.class);

    static {
	try {
	    initTypeArray();
	} catch (Exception e) {
	    logger.error("异常:", e);
	}
    }

    public static Map<String, String> getGameMap() {
	return gameMap;
    }

    /**
     * 加载class时进行反射处理
     * 
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void initTypeArray() throws Exception {
	Class c = GameConstant.class;
	Field[] fs = c.getDeclaredFields();
	for (int i = 0; i < fs.length; i++) {
	    Field f = fs[i];
	    CommonAnnotation pt = f.getAnnotation(CommonAnnotation.class);
	    if (pt != null) {
		String label = pt.label();
		String value = (String) f.get(null);
		gameMap.put(value, label);
	    }

	}
    }

}
