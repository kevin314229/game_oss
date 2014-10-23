package com.jcwx.game.common.constants;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jcwx.game.web.OperationLogAnnotation;

public class PytTypeConstant {
    
    /** 获取，新增类型 */
    public static final int TYPE_GET = 1;

    /** 普通类型 */
    public static final int TYPE_NORMAL = 3;

    /** 消耗，减少类型 */
    public static final int TYPE_USE = 2;
    
    public static Map<String, String> maptype = new LinkedHashMap<String, String>();

    @OperationLogAnnotation(label = "增加军团军资", type = TYPE_NORMAL)
    public static final String TARGET_ADD_ARMY_GOLD = "addArmyGold";

    /** 后台添加 **/
    @OperationLogAnnotation(label = "增加礼券", type = TYPE_NORMAL)
    public static final String TARGET_ADD_BIND_GOLD = "addBindGold";
    /** 后台添加魔晶 **/
    @OperationLogAnnotation(label = "增加魔晶", type = TYPE_NORMAL)
    public static final String TARGET_ADD_GOLD = "addGold";

    /** 金币 **/
    @OperationLogAnnotation(label = "增加金币", type = TYPE_NORMAL)
    public static final String TARGET_ADD_SILVER = "addSilver";

    @OperationLogAnnotation(label = "消耗军团军资", type = TYPE_NORMAL)
    public static final String TARGET_USE_ARMY_GOLD = "useArmyGold";

    /** 礼券 **/
    @OperationLogAnnotation(label = "消费礼券", type = TYPE_NORMAL)
    public static final String TARGET_USE_BIND_GOLD = "useBindGold";
    /** 魔晶 **/
    @OperationLogAnnotation(label = "消费魔晶", type = TYPE_NORMAL)
    public static final String TARGET_USE_GOLD = "useGold";
    /** 消费 金币 **/
    @OperationLogAnnotation(label = "消费金币", type = TYPE_NORMAL)
    public static final String TARGET_USE_SILVER = "useSilver";



    static {
	try {
	    initTypeArray();
	} catch (Exception e) {
	}
    }

    public static Map<String, String> getMaptype() {
	return maptype;
    }

    /**
     * 加载class时进行反射处理
     * 
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void initTypeArray() throws Exception {
	maptype.put("queryAll", "查询全部");
	Class c = PytTypeConstant.class;
	Field[] fs = c.getDeclaredFields();
	for (int i = 0; i < fs.length; i++) {
	    Field f = fs[i];
	    OperationLogAnnotation pt = f
		    .getAnnotation(OperationLogAnnotation.class);
	    if (pt != null) {
		String label = pt.label();
		String value = (String) f.get(null);
		maptype.put(value, label);
	    }

	}
	
    }
    
    

}
