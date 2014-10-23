package com.jcwx.game.admin.constant;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jcwx.game.common.ResourceBundleService;
import com.jcwx.game.common.constants.OperationLogConstant;
import com.jcwx.game.web.OperationLogAnnotation;

/**
 * 操作日志类型(管理员)
 * 
 * @author Administrator
 * 
 */
public class OssLogConstant {

    @OperationLogAnnotation(label = "新增系统活动公告", type = OperationLogConstant.TYPE_NORMAL)
    public static final String ADD_ACTIVITY_NOTICE = "addActivityNotice";
    @OperationLogAnnotation(label = "新增功能点调整", type = OperationLogConstant.TYPE_NORMAL)
    public static final String ADD_FUNCTION_ADJUST = "addFunctionAdjust";

    @OperationLogAnnotation(label = "新增活动商城", type = OperationLogConstant.TYPE_NORMAL)
    public static final String ADD_MALL_ACTIVITY = "addMallActivity";

    @OperationLogAnnotation(label = "添加商城物品", type = OperationLogConstant.TYPE_NORMAL)
    public static final String ADD_MALL_NEW = "addMallNew";
    @OperationLogAnnotation(label = "新增修改活动", type = OperationLogConstant.TYPE_NORMAL)
    public static final String ADD_MODIFY_ACTIVITY = "addModifyActivity";
    @OperationLogAnnotation(label = "添加首充奖励", type = OperationLogConstant.TYPE_NORMAL)
    public static final String ADD_RECHARGE_ACTIVITY = "addRechargeActivity";

    @OperationLogAnnotation(label = "新增系统活动", type = OperationLogConstant.TYPE_NORMAL)
    public static final String ADD_SYSTEM_ACTIVITY = "addSystemActivity";

    @OperationLogAnnotation(label = "新增系统公告", type = OperationLogConstant.TYPE_NORMAL)
    public static final String ADMIN_SEND_MSG = "adminSendMsg";

    @OperationLogAnnotation(label = "删除系统活动公告", type = OperationLogConstant.TYPE_NORMAL)
    public static final String DEL_ACTIVITY_NOTICE = "delActivityNotice";

    @OperationLogAnnotation(label = "删除商城物品", type = OperationLogConstant.TYPE_NORMAL)
    public static final String DEL_MALL_NEW = "delMallNew";

    @OperationLogAnnotation(label = "删除首充奖励", type = OperationLogConstant.TYPE_NORMAL)
    public static final String DEL_RECHARGE_ACTIVITY = "delRechargeActivity";

    @OperationLogAnnotation(label = "物品删除", type = OperationLogConstant.TYPE_NORMAL)
    public static final String DELETE_GOODS = "deleteGoods";

    @OperationLogAnnotation(label = "服务端错误记录", type = OperationLogConstant.TYPE_NORMAL)
    public static final String ERROR_SERVER = "errorServer";

    private static Logger logger = Logger.getLogger(OssLogConstant.class);

    @OperationLogAnnotation(label = "登录", type = OperationLogConstant.TYPE_NORMAL)
    public static final String LOGIN = "login";

    public static Map<String, String> maptype = new HashMap<String, String>();

    @OperationLogAnnotation(label = "修改活动", type = OperationLogConstant.TYPE_NORMAL)
    public static final String MODIFY_ACTIVITY = "modifyActivity";

    @OperationLogAnnotation(label = "修改系统活动公告", type = OperationLogConstant.TYPE_NORMAL)
    public static final String MODIFY_ACTIVITY_NOTICE = "modifyActivityNotice";

    @OperationLogAnnotation(label = "修改活动商城", type = OperationLogConstant.TYPE_NORMAL)
    public static final String MODIFY_MALL_ACTIVITY = "modifyMallActivity";

    @OperationLogAnnotation(label = "修改商城物品", type = OperationLogConstant.TYPE_NORMAL)
    public static final String MODIFY_MALL_NEW = "modifyMallNew";

    @OperationLogAnnotation(label = "修改首充奖励", type = OperationLogConstant.TYPE_NORMAL)
    public static final String MODIFY_RECHARGE_ACTIVITY = "modifyRechargeActivity";

    @OperationLogAnnotation(label = "修改系统活动", type = OperationLogConstant.TYPE_NORMAL)
    public static final String MODIFY_SYSTEM_ACTIVITY = "modifySystemActivity";

    @OperationLogAnnotation(label = "class文件上传", type = OperationLogConstant.TYPE_NORMAL)
    public static final String OSS_CLASS_UPLOAD = "ossClassUpload";

  /*  @OperationLogAnnotation(label = "OSS热点功能统计", type = OperationLogConstant.TYPE_NORMAL)
    public static final String OSS_HOT_MOUDLE = "ossHotMoudle";*/

    @OperationLogAnnotation(label = "xml配置文件上传", type = OperationLogConstant.TYPE_NORMAL)
    public static final String OSS_XML_UPLOAD = "ossXMLUpload";

    @OperationLogAnnotation(label = "查询全部", type = OperationLogConstant.TYPE_NORMAL)
    public static final String QUERYALL = null;

    @OperationLogAnnotation(label = "发送信息", type = OperationLogConstant.TYPE_NORMAL)
    public static final String SEND_MSG = "sendMsg";

    @OperationLogAnnotation(label = "批量给玩家发送信件", type = OperationLogConstant.TYPE_NORMAL)
    public static final String SEND_MSG_TO_MORE_PLAYER = "sendMsgToMorePlayer";

    @OperationLogAnnotation(label = "给玩家发送信件", type = OperationLogConstant.TYPE_NORMAL)
    public static final String SEND_MSG_TO_PLAYER = "sendMsgToPlayer";

    @OperationLogAnnotation(label = "发送附件信件", type = OperationLogConstant.TYPE_NORMAL)
    public static final String SEND_STAGE = "sendStage";
    @OperationLogAnnotation(label = "韩国封魔抽奖", type = OperationLogConstant.TYPE_NORMAL)
    public static final String OSS_LOTTERY = "ossLottery";
    static {
	try {
	    initTypeArray();
	} catch (Exception e) {
	    logger.error(ResourceBundleService.getString("txt.exception"), e);
	}
    }

    /**
     * 加载class时进行反射处理
     * 
     * @throws Exception
     */
    public static void initTypeArray() throws Exception {
	// maptype.put("", "查询全部");
	Class<?> c = OssLogConstant.class;
	Field[] fs = c.getDeclaredFields();
	for (int i = 0; i < fs.length; i++) {
	    Field f = fs[i];
	    OperationLogAnnotation opan = f
		    .getAnnotation(OperationLogAnnotation.class);
	    if (opan != null) {
		String label = opan.label();
		String value = (String) f.get(null);
		maptype.put(value, label);
	    }

	}
    }

}
