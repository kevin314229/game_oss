package com.jcwx.game.util;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.OssContext;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.service.oss.IOssDictService;

public class ServerListToMap {
    /**
     * 实现将前台的服务器选项转换为字符串
     * 
     * @param list
     *            当前选择项目的所有的服务器列表
     * @param areas
     *            选择的服务器数组
     * @param ptId
     *            选择的平台ID
     * @return
     */
    public static String arrayToString(List<OssServer> list, String[] areas,
	    String ptId) {
	String areaId = null;
	BaseAdminContext baseAdminContext = OssContext.getBaseAdminContext();

	Map<String, String> dataMap = queryDictMap(baseAdminContext
		.getProject().getProjectId(), DictTypeConstant.AREA_TYPE);
	// 判断数组是否为空且为有效值 分页传递的数组可能为null无效数值
	if (areas != null && areas.length > 0 && !areas[0].equals("null")) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < areas.length; i++) {
		sb.append(areas[i] + ",");
	    }
	    areaId = sb.toString();
	    areaId = areaId.substring(0, areaId.length() - 1);
	    // 服务器数值长度是否为1 改变分页的pageNum传递过来的服务器是以1,2,3形式的
	    if (areas.length == 1) {
		areas = areas[0].split(",");
		// 判断areas是否是根据分页传递过来的数据
		if ("-1".equals(areas[0])) {
		    // 判断平台是否选择，没选择者服务器ID不赋值，存在值，则取平台下面的服务器进行封装
		    if (ptId == null)
			areaId = null;
		    else {
			sb.setLength(0);
			// 封装服务器ID
			for (OssServer temp : list) {
			    if (temp.getServerCode().equals(ptId)) {
				String id = dataMap.get(temp.getName());
				if (id == null) {
				    sb.append(temp.getId() + ",");
				} else {
				    sb.append(id + ",");
				}
				// sb. append(temp.getId()+",");
			    }
			}
			areaId = sb.toString();
			if (areaId.length() > 0)
			    areaId = areaId.substring(0, areaId.length() - 1);
		    }
		}
	    }
	} else if (areas == null || areas.length == 0
		|| areas[0].equals("null")) {
	    // 如果数组为空或为无效值，判断平台是否为空，不为空则选择平台下面的所有服务器
	    if (ptId != null && !"".equals(ptId)) {
		StringBuffer sb = new StringBuffer();
		for (OssServer temp : list) {
		    if (temp.getServerCode().equals(ptId)) {
			// sb. append(temp.getId()+",");
			String id = dataMap.get(temp.getName());
			if (id == null) {
			    sb.append(temp.getId() + ",");
			} else {
			    sb.append(id + ",");
			}
		    }
		}
		areaId = sb.toString();
		if (areaId.length() > 0)
		    areaId = areaId.substring(0, areaId.length() - 1);
	    }
	}
	if (areaId == null || "".equals(areaId)) {
	    StringBuffer sb = new StringBuffer();
	    for (OssServer temp : list) {
		// sb. append(temp.getId()+",");
		String id = dataMap.get(temp.getName());
		if (id == null) {
		    sb.append(temp.getId() + ",");
		} else {
		    sb.append(id + ",");
		}
	    }
	    areaId = sb.toString();
	    areaId = areaId.substring(0, areaId.length() - 1);
	}

	return areaId;
    }
    public static String arrayToStringByPtId(List<OssServer> list, String[] areas,
	    String ptId) {
	String areaId = null;
	BaseAdminContext baseAdminContext = OssContext.getBaseAdminContext();

	Map<String, String> dataMap = queryDictMap(baseAdminContext
		.getProject().getProjectId(), DictTypeConstant.AREA_TYPE);
	// 判断数组是否为空且为有效值 分页传递的数组可能为null无效数值
	if (areas != null && areas.length > 0 && !areas[0].equals("null")) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < areas.length; i++) {
		sb.append(areas[i] + ",");
	    }
	    areaId = sb.toString();
	    areaId = areaId.substring(0, areaId.length() - 1);
	    // 服务器数值长度是否为1 改变分页的pageNum传递过来的服务器是以1,2,3形式的
	    if (areas.length == 1) {
		areas = areas[0].split(",");
		// 判断areas是否是根据分页传递过来的数据
		if ("-1".equals(areas[0])) {
		    // 判断平台是否选择，没选择者服务器ID不赋值，存在值，则取平台下面的服务器进行封装
		    if (ptId == null)
			areaId = null;
		    else {
			sb.setLength(0);
			// 封装服务器ID
			for (OssServer temp : list) {
			    if (temp.getServerId()==Integer.parseInt(ptId)) {
				String id = dataMap.get(temp.getName());
				if (id == null) {
				    sb.append(temp.getId() + ",");
				} else {
				    sb.append(id + ",");
				}
				// sb. append(temp.getId()+",");
			    }
			}
			areaId = sb.toString();
			if (areaId.length() > 0)
			    areaId = areaId.substring(0, areaId.length() - 1);
		    }
		}
	    }
	} else if (areas == null || areas.length == 0
		|| areas[0].equals("null")) {
	    // 如果数组为空或为无效值，判断平台是否为空，不为空则选择平台下面的所有服务器
	    if (ptId != null && !"".equals(ptId)) {
		StringBuffer sb = new StringBuffer();
		for (OssServer temp : list) {
		    if (temp.getServerId()==Integer.parseInt(ptId)) {
			// sb. append(temp.getId()+",");
			String id = dataMap.get(temp.getName());
			if (id == null) {
			    sb.append(temp.getId() + ",");
			} else {
			    sb.append(id + ",");
			}
		    }
		}
		areaId = sb.toString();
		if (areaId.length() > 0)
		    areaId = areaId.substring(0, areaId.length() - 1);
	    }
	}
	if (areaId == null || "".equals(areaId)) {
	    StringBuffer sb = new StringBuffer();
	    for (OssServer temp : list) {
		// sb. append(temp.getId()+",");
		String id = dataMap.get(temp.getName());
		if (id == null) {
		    sb.append(temp.getId() + ",");
		} else {
		    sb.append(id + ",");
		}
	    }
	    areaId = sb.toString();
	    areaId = areaId.substring(0, areaId.length() - 1);
	}

	return areaId;
    }
    public static Map<String, String> convert(List<OssServer> list)
	    throws Exception {
	Map<String, String> map = new LinkedHashMap<String, String>();
	BaseAdminContext baseAdminContext = OssContext.getBaseAdminContext();
	Map<String, String> dataMap = queryDictMap(baseAdminContext
		.getProject().getProjectId(), DictTypeConstant.AREA_TYPE);
	for (OssServer ossServer : list) {
	    // map.put(ossServer.getId()+"", ossServer.getName());
	    String id = dataMap.get(ossServer.getName());
	    if (id == null) {
		map.put(ossServer.getId() + "", ossServer.getName());
	    } else {
		map.put(id, ossServer.getName());
	    }
	}
	return map;
    }

    public static Map<String, String> convert(List<OssServer> list, int ptId)
	    throws Exception {
	Map<String, String> map = new LinkedHashMap<String, String>();
	BaseAdminContext baseAdminContext = OssContext.getBaseAdminContext();
	Map<String, String> dataMap = queryDictMap(baseAdminContext
		.getProject().getProjectId(), DictTypeConstant.AREA_TYPE);

	for (OssServer ossServer : list) {
	    if (ossServer.getServerId() == ptId) {
		String id = dataMap.get(ossServer.getName());
		if (id == null) {
		    map.put(ossServer.getId() + "", ossServer.getName());
		} else {
		    map.put(id, ossServer.getName());
		}
		// map.put(ossServer.getId()+"", ossServer.getName());
	    }
	}
	return map;
    }

    public static Map<String, String> convert(List<OssServer> list,
	    String ptCode) throws Exception {
	Map<String, String> map = new LinkedHashMap<String, String>();
	BaseAdminContext baseAdminContext = OssContext.getBaseAdminContext();
	Map<String, String> dataMap = queryDictMap(baseAdminContext
		.getProject().getProjectId(), DictTypeConstant.AREA_TYPE);
	for (OssServer ossServer : list) {
	    if (ossServer.getServerCode().equals(ptCode)) {
		String id = dataMap.get(ossServer.getName());
		if (id == null) {
		    map.put(ossServer.getId() + "", ossServer.getName());
		} else {
		    map.put(id, ossServer.getName());
		}
		// map.put(ossServer.getId()+"", ossServer.getName());
	    }
	}
	return map;
    }

    public static String dateArrayToStrings(Date[] dates) {
	String[] strArr = new String[dates.length];
	for (int i = 0; i < dates.length; i++) {
	    strArr[i] = DateService.getDateFormatStr(dates[i], "yyyy-MM-dd");
	}

	return JSON.toJSONString(strArr);
    }

    public static Date[] dateToArray(String beginDate, String endDate) {
	Date temp1 = DateService.getDateByStrAndFormat(beginDate, "yyyy-MM-dd");
	Date temp2 = DateService.getDateByStrAndFormat(endDate, "yyyy-MM-dd");
	int days = DateService.DayBetween(temp1, temp2);
	Date[] array = new Date[days + 1];
	for (int i = 0; i < days + 1; i++) {
	    array[i] = temp1;
	    temp1 = DateService.dateIncreaseByDay(temp1, 1);
	}
	return array;
    }

    public static String[] dateToStrArr(String beginDate, String endDate) {
	Date temp1 = DateService.getDateByStrAndFormat(beginDate, "yyyy-MM-dd");
	Date temp2 = DateService.getDateByStrAndFormat(endDate, "yyyy-MM-dd");
	int days = DateService.DayBetween(temp1, temp2);
	String[] array = new String[days + 1];
	for (int i = 0; i < days + 1; i++) {
	    array[i] = DateService.getDateFormatStr(temp1, "yyyy-MM-dd");
	    temp1 = DateService.dateIncreaseByDay(temp1, 1);
	}
	return array;
    }

    /**
     * 查询数据字典map
     * 
     * @param gameId
     * @param type
     * @return
     */
    public static Map<String, String> queryDictMap(Integer gameId, Integer type) {
	IOssDictService dictService = (IOssDictService) SpringService
		.getBean("ossDictService");
	List<OssDictData> dictList = dictService.getOssDictDataList(gameId,
		type);
	Map<String, String> dictMap = new HashMap<String, String>();
	if (dictList != null) {
	    for (OssDictData dictData : dictList) {
		dictMap.put(dictData.getDictName(), dictData.getDictValue());
	    }
	}
	return dictMap;
    }

    public static String[] strToArr(String areaIds) {
	String[] arr = areaIds.split(",");
	BaseAdminContext baseAdminContext = OssContext.getBaseAdminContext();
	Map<String, String> dataMap = queryDictMap(baseAdminContext
		.getProject().getProjectId(), DictTypeConstant.AREA_TYPE);
	List<OssServer> list = baseAdminContext.getOssServers();
	for (int i = 0; i < arr.length; i++) {
	    for (OssServer temp : list) {
		String id = dataMap.get(temp.getName());
		if (id != null)
		    if (id.indexOf(arr[i]) > 0) {
			arr[i] = id;
		    }
	    }
	}

	return arr;
    }
}
