package com.jcwx.game.service.impl;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.OssContext;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.service.ISyncMoudle;
import com.jcwx.game.util.Connection;


/**
 * 同步模块
 * 
 * @author Administrator
 * 
 */
@Service
public class SyncMoudle implements ISyncMoudle {

    private static final String CONFIRM_SERVER_MOUDLEFUNC = " 访问服务器功能点同步出现问题，请检查。";
    private static final String CONFIRM_SERVERID_NOTFOUND = "<span class='color-red'>serverId %s not find  </span>";
    private static final String CONFIRM_SERVERNAME_ERROR = "%s 服务器 出现问题，请检查。 ";
    private static final String OSS_OBJECT_LIST = "ossObjectList";
    private static final String SERVER_RESULT_KEY = "result";
    private static final String STATE_NO = "no";
    private static final String STATE_OK = "ok";

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.ISyncMoudle#modifyActivity(java.util.Map)
     */
    @Override
    public String modifyActivity(Map<String, Object> object) throws Exception {
	String code = STATE_OK;
	try {
	    object = Connection.getInstance().sendMsg(object);
	    if (!object.get("code").equals(0)) {
		code = STATE_OK;
	    }
	} catch (Exception e) {
	    code = STATE_NO;
	}
	return code;
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.ISyncMoudle#syncMoudle(java.util.Map, java.util.Map, java.lang.String[])
     */
    @Override
    public String syncMoudle(Map<String, Object> querySynList,
	    Map<String, Object> synFunctionList, String[] serverIds) {

	StringBuffer buf = new StringBuffer();

	try {

	    querySynList =  Connection.getInstance().sendMsg(querySynList);
	    /***
	     * 查询不到的时候，直接返回，#验证
	     */
	    if (querySynList == null || querySynList.isEmpty()) {
		return StringUtils.EMPTY;
	    }

	    buf.append(syncMoudle0(querySynList.get(OSS_OBJECT_LIST),
		    synFunctionList, OssContext.getBaseAdminContext(),
		    serverIds));

	} catch (Exception e) {
	    buf.append(CONFIRM_SERVER_MOUDLEFUNC).append(e.getMessage());
	}
	return buf.toString();
    }

    private String syncMoudle0(Object object,
	    Map<String, Object> synFunctionList,
	    BaseAdminContext baseAdminContext, String[] serverIds) {
	Validate.notNull(object, OSS_OBJECT_LIST + " must not be null!");
	Function<String, Integer> convertIntegerList = new Function<String, Integer>() {

	    @Override
	    public Integer apply(String input) {
		return Integer.valueOf(input);
	    }

	};

	Collection<Integer> serverIntegerIds = Collections2.transform(
		Lists.newArrayList(serverIds), convertIntegerList);

	StringBuffer buf = new StringBuffer();

	for (Integer id : serverIntegerIds) {
	    OssServer ossServer = baseAdminContext.getOssServerById(id);
	    if (ossServer == null) {
		buf.append(String.format(CONFIRM_SERVERID_NOTFOUND, id));
		continue;
	    }
	    try {
		synFunctionList.put(OSS_OBJECT_LIST, object);
		synFunctionList.put("areaId", id);
		synFunctionList =  Connection.getInstance().interfaceSendMsg(id,synFunctionList);
		buf.append(ossServer.getName() + ":"
			+ synFunctionList.get(SERVER_RESULT_KEY));

	    } catch (Exception e) {
		buf.append(String.format(CONFIRM_SERVERNAME_ERROR,
			ossServer.getName()));
	    }
	}
	return buf.toString();
    }
}
