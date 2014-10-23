//package com.jcwx.game.service.oss.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.jcwx.game.common.DateService;
//import com.jcwx.game.common.ResourceBundleService;
//import com.jcwx.game.dao.IBaseDAO;
//import com.jcwx.game.domain.MonitorInfoBean;
//import com.jcwx.game.domain.OssMonitor;
//import com.jcwx.game.service.oss.IMonitorService;
//import com.jcwx.game.service.oss.IOssMonitorService;
//
//@Service
//public class OssMonitorService implements IOssMonitorService{
//	
//	private static Logger logger = Logger.getLogger(OssMonitorService.class);
//	@Autowired
//	private IBaseDAO baseDao;
//	
//
//	public void createOssMonitor() {
//		OssMonitor ossMonitor = new OssMonitor();
//		String currDateStr = DateService.getCurrentDateAsStringCustom("yyyyMMddHHmm");
//		Long begin = Long.parseLong(currDateStr.toString());
//		ossMonitor.setOssMonitorID(begin);
//		 IMonitorService monitorService = new MonitorService();    
//         MonitorInfoBean monitorInfo = null;
//         try{
//        	 monitorInfo = monitorService.getMonitorInfoBean();
//        	 ossMonitor.setCupRatio(monitorInfo.getCpuRatio());
//        	 ossMonitor.setFreeMemory(monitorInfo.getFreeMemory());
//        	 ossMonitor.setMaxMemory(monitorInfo.getMaxMemory());
//        	 ossMonitor.setOssFreePhysicalMemory(monitorInfo.getFreePhysicalMemorySize());
//        	 ossMonitor.setOsTotalMemorySize(monitorInfo.getTotalMemorySize());
//        	 ossMonitor.setTotalMemory(monitorInfo.getTotalMemory());
//        	 ossMonitor.setTotalThread(monitorInfo.getTotalThread());
//        	 baseDao.insert("OssMonitor.createOssMonitor", ossMonitor);
//         }catch(Exception e){
//        	 logger.error(ResourceBundleService.getString("txt.exception"),e);
//         }
//		
//	}
//
//	public void deleteOssMonitorByID(Integer ossMonitorID) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public OssMonitor getOssMonitorByID(Integer ossMonitorID) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public List<OssMonitor> getOssMonitorList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void updateOssMonitor(OssMonitor ossMonitor) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	/**根据时间获取系统信息列表*/
//	public List<OssMonitor> getOssMonitorMinutesListByMinute(Long begin, Long end){
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("begin", begin);
//		params.put("end", end);
//		return baseDao.queryForList("OssMonitor.getOssMonitorMinutesListByMinute", params);
//	}
//
//
//
// }
