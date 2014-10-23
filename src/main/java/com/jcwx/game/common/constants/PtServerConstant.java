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
public class PtServerConstant {

    /** 爱贝 */
    @CommonAnnotation(label = "爱贝")
    public static final String AI_BEI = "aiBei";

    /** 安智 */
    @CommonAnnotation(label = "安智")
    public static final String AN_ZHI = "anZhi";

    /** 应用汇 */
    @CommonAnnotation(label = "应用汇")
    public static final String APP_HUI = "appHui";

    /** 苹果商店 */
    @CommonAnnotation(label = "苹果")
    public static final String APP_STORE = "appStore";

    /** 百度游戏 */
    @CommonAnnotation(label = "百度游戏")
    public static final String BAIDU_GAME = "baiDuGame";

    /**
     * 百度-百付宝
     */
    @CommonAnnotation(label = "百度百付宝")
    public static final String BAIDU_PAY360 = "baiDuPay360";

    /**
     * 宝软
     */
    @CommonAnnotation(label = "宝软")
    public static final String BAO_RUAN = "baoRuan";

    /** 超好玩 */
    @CommonAnnotation(label = "超好玩")
    public static final String CHW = "chw";

    /** 畅玩 */
    @CommonAnnotation(label = "畅玩")
    public static final String CW = "cw";

    /** 顶尖 */
    @CommonAnnotation(label = "顶尖")
    public static final String DJ = "dj";

    /** 百度多酷 */
    @CommonAnnotation(label = "百度多酷")
    public static final String DK = "dk";

    /**
     * 当乐
     */
    @CommonAnnotation(label = "当乐")
    public static final String DL = "dl";

    /** 韩国Efun */
    @CommonAnnotation(label = "韩国Efun")
    public static final String EFUN = "efun";

    /**
     * 飞流九天
     */
    @CommonAnnotation(label = "飞流九天")
    public static final String FLJT = "fljt";

    /**
     * 凤凰手游
     */
    @CommonAnnotation(label = "凤凰")
    public static final String I_FENG = "I_Feng";

    /** 九城 */
    @CommonAnnotation(label = "九城")
    public static final String JC = "jc";

    /**
     * 机锋
     */
    @CommonAnnotation(label = "机锋")
    public static final String JI_FENG = "jiFeng";

    /**
     * 酷狗
     */
    @CommonAnnotation(label = "酷狗")
    public static final String KU_GOU = "kuGou";

    /** 快用 */
    @CommonAnnotation(label = "快用")
    public static final String KY = "ky";

    /** 联想 */
    @CommonAnnotation(label = "联想")
    public static final String LENOVO = "lenovo";

    private static Logger logger = Logger.getLogger(PtServerConstant.class);

    /** 爱游戏 */
    @CommonAnnotation(label = "爱游戏")
    public static final String LOVEGAME = "loveGame";

    /**
     * 360
     */
    @CommonAnnotation(label = "360平台")
    public static final String M360 = "360";

    /** 3g门户 */
    @CommonAnnotation(label = "3g门户")
    public static final String M3G = "m3g";

    /**
     * 91
     */
    @CommonAnnotation(label = "91助手")
    public static final String M91 = "91";

    /** 木蚂蚁 */
    @CommonAnnotation(label = "木蚂蚁")
    public static final String MANT = "mant";

    /** 移动MM */
    @CommonAnnotation(label = "移动MM")
    public static final String MM = "mm";

    /** 拇指玩 */
    @CommonAnnotation(label = "拇指玩")
    public static final String Mu_Zhi_Wan = "muzhiwan";
    /** N多 */
    @CommonAnnotation(label = "N多")
    public static final String NDUO = "nduo";

    /** OPPO */
    @CommonAnnotation(label = "OPPO")
    public static final String OPPO = "oppo";

    /**
     * 琵琶
     */
    @CommonAnnotation(label = "琵琶")
    public static final String PI_PA = "piPa";
    /**
     * 苹果园
     */
    @CommonAnnotation(label = "苹果园")
    public static final String PING_GUO_YUAN = "pgy";

    /** 宾谷 */
    @CommonAnnotation(label = "宾谷")
    public static final String PINGCOO = "pingCoo";

    /** pp助手 */
    @CommonAnnotation(label = "pp助手")
    public static final String PP = "pp25";
    public static Map<String, String> ptTypeMap = new LinkedHashMap<String, String>();

    /** 起点 */
    @CommonAnnotation(label = "起点")
    public static final String Qi_Dian = "qiDian";

    /** 天海 */
    @CommonAnnotation(label = "天海")
    public static final String SKY_SEA = "skySea";

    /** 手游天下 */
    @CommonAnnotation(label = "手游天下")
    public static final String SYTX = "sytx";

    /** 爱游戏 */
    @CommonAnnotation(label = "台湾运营商")
    public static final String TAIWAN = "taiwan";

    /**
     * 同步推
     */
    @CommonAnnotation(label = "同步推")
    public static final String TONG_BU = "tongBu";

    /** 腾王 */
    @CommonAnnotation(label = "腾王")
    public static final String TW = "tw";

    /**
     * uc
     */
    @CommonAnnotation(label = "uc九游")
    public static final String UC = "uc";

    /**
     * 51玩
     */
    @CommonAnnotation(label = "51玩")
    public static final String WAN_51 = "Wan_51";

    /**
     * 豌豆荚
     */
    @CommonAnnotation(label = "豌豆荚")
    public static final String WAN_DOU_JIA = "wanDouJia";

    /**
     * 页游91玩
     */
    @CommonAnnotation(label = "页游91玩")
    public static final String WEB_91_WAN = "web91wan";

    /** 武汉手游 */
    @CommonAnnotation(label = "武汉手盟")
    public static final String WHSM = "whsm";

    /** 精彩无限 */
    @CommonAnnotation(label = "精彩无限")
    public static final String WONDER = "wonder";

    /** 兄弟玩 */
    @CommonAnnotation(label = "兄弟玩")
    public static final String XDW = "xdw";

    /**
     * 小米
     */
    @CommonAnnotation(label = "小米")
    public static final String XIAOMI = "xm";

    /**
     * 丫丫玩
     */
    @CommonAnnotation(label = "丫丫玩")
    public static final String YaYaWan = "yayaWan";

    /** 优蜜 */
    @CommonAnnotation(label = "优蜜")
    public static final String YouMi = "youmi";

    /** 有信 */
    @CommonAnnotation(label = "有信")
    public static final String YouXin = "youxin";
    /** 游戏基地 */
    @CommonAnnotation(label = "游戏基地")
    public static final String YXJD = "yxjd";

    /**
     * 指点传媒（游乐）
     */
    @CommonAnnotation(label = "指点传媒")
    public static final String ZDCM = "zdcm";

    static {
	try {
	    initTypeArray();
	} catch (Exception e) {
	    logger.error("异常:", e);
	}
    }

    public static Map<String, String> getptTypeMap() {
	return ptTypeMap;
    }

    /**
     * 加载class时进行反射处理
     * 
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void initTypeArray() throws Exception {
	Class c = PtServerConstant.class;
	Field[] fs = c.getDeclaredFields();
	for (int i = 0; i < fs.length; i++) {
	    Field f = fs[i];
	    CommonAnnotation pt = f.getAnnotation(CommonAnnotation.class);
	    if (pt != null) {
		String label = pt.label();
		String value = (String) f.get(null);
		ptTypeMap.put(value, label);
	    }

	}
    }

}
