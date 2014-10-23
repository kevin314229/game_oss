package com.jcwx.game.common.constants;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jcwx.game.web.CommonAnnotation;

public class OssServerConstant {

    private static Logger logger = Logger.getLogger(PtServerConstant.class);

    public static Map<String, String> ossServerMap = new LinkedHashMap<String, String>();

    /** 服务器 */
    @CommonAnnotation(label = "页游内侧")
    public static final String server110 = "110";

    /** 服务器 */
    @CommonAnnotation(label = "91玩页游3区")
    public static final String server112 = "112";

    /** 服务器 */
    @CommonAnnotation(label = "91玩页游4区")
    public static final String server114 = "114";

    /** 服务器 */
    @CommonAnnotation(label = "苹果合1区(1-2)")
    public static final String server116 = "116";
    /** 服务器 */
    @CommonAnnotation(label = "精彩内侧")
    public static final String server131 = "131";
    /** 服务器 */
    @CommonAnnotation(label = "91玩页游5区")
    public static final String server147 = "147";
    /** 服务器 */
    @CommonAnnotation(label = "苹果三区")
    public static final String server160 = "160";
    /** 服务器 */
    @CommonAnnotation(label = "MM合1区（1,2） ")
    public static final String server162 = "162";
    /** 服务器 */
    @CommonAnnotation(label = "合服大区（混30-32） ")
    public static final String server179 = "179";
    /** 服务器 */
    @CommonAnnotation(label = "苹果四区 ")
    public static final String server180 = "180";
    /** 服务器 */
    @CommonAnnotation(label = "精彩内侧（线上bug修复服）")
    public static final String server184 = "184";
    /** 服务器 */
    @CommonAnnotation(label = "混三十三区 ")
    public static final String server185 = "185";
    /** 服务器 */
    @CommonAnnotation(label = " 九城1区魔神降临  ")
    public static final String server186 = "186";
    /** 服务器 */
    @CommonAnnotation(label = "混三十四区 ")
    public static final String server187 = "187";
    /** 服务器 */
    @CommonAnnotation(label = "九城2区噩梦深渊 ")
    public static final String server188 = "188";
    /** 服务器 */
    @CommonAnnotation(label = "九城3区王者归来 ")
    public static final String server189 = "189";
    /** 服务器 */
    @CommonAnnotation(label = "九城4区封印之心 ")
    public static final String server190 = "190";
    /** 服务器 */
    @CommonAnnotation(label = "九城5区黑影重现  ")
    public static final String server191 = "191";
    /** 服务器 */
    @CommonAnnotation(label = "混三十五区 ")
    public static final String server192 = "192";
    /** 服务器 */
    @CommonAnnotation(label = "混（16-29区） ")
    public static final String server193 = "193";
    /** 服务器 */
    @CommonAnnotation(label = " 混三十六区  ")
    public static final String server194 = "194";
    /** 服务器 */
    @CommonAnnotation(label = "精彩内侧充值")
    public static final String server195 = "195";
    /** 服务器 */
    @CommonAnnotation(label = " 混三十七区  ")
    public static final String server196 = "196";
    /** 服务器 */
    @CommonAnnotation(label = "西游1服大闹天空")
    public static final String server449 = "449";
    /** 服务器 */
    @CommonAnnotation(label = " 混三十八区  ")
    public static final String server451 = "451";
    /** 服务器 */
    @CommonAnnotation(label = "混（1-15区） ")
    public static final String server89 = "89";
    /** 服务器 */
    @CommonAnnotation(label = "91玩页游1区")
    public static final String server94 = "94";
    /** 服务器 */
    @CommonAnnotation(label = "91玩页游2区")
    public static final String server98 = "98";
    static {
	try {
	    initTypeArray();
	} catch (Exception e) {
	    logger.error("异常:", e);
	}
    }

    public static Map<String, String> getptTypeMap() {
	return ossServerMap;
    }

    /*** 战魂西游服务器 ***/

    /**
     * 加载class时进行反射处理
     * 
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void initTypeArray() throws Exception {
	Class c = OssServerConstant.class;
	Field[] fs = c.getDeclaredFields();
	for (int i = 0; i < fs.length; i++) {
	    Field f = fs[i];
	    CommonAnnotation pt = f.getAnnotation(CommonAnnotation.class);
	    if (pt != null) {
		String label = pt.label();
		String value = (String) f.get(null);
		ossServerMap.put(value, label);
	    }

	}
    }

}
