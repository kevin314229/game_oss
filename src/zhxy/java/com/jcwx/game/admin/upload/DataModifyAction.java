package com.jcwx.game.admin.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.admin.vo.PageMessage;
import com.jcwx.game.domain.OssLog;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.service.oss.IOssLogService;

import com.jcwx.game.util.transdata.ITransfer.ContentTypeEnum;

/** 服务器选择 */
@SuppressWarnings("unused")
@ParentPackage("base")
@Namespace("/zhxy/data")
@ResultPath("/")
public class DataModifyAction extends BasalAction {

    private static final long serialVersionUID = 805121475994427314L;
    /** 总记录数 */
    private Integer allNum;

    /** 起始页号 */
    private Integer beginNum;

    private File classUpload; // 上传的文件

    private String classUploadContentType;

    private String classUploadFileName; // 文件名称
    /** 当前页数 */
    private Integer currPageNO;

    /** 每页记录数 */
    private Integer onePageNum;

    private Integer operatorType;

    private List<OssLog> ossLogList;

    @Autowired
    private IOssLogService ossLogService;

    private String packageName;
    /** 总页数 */
    private Integer pages;
    
    private List<OssServer> serverList;
    
    private int[]   server;
    
    private String servers;

    public Integer getAllNum() {
	return allNum;
    }

    public Integer getBeginNum() {
	return beginNum;
    }

    public File getClassUpload() {
	return classUpload;
    }

    public String getClassUploadContentType() {
	return classUploadContentType;
    }

    public String getClassUploadFileName() {
	return classUploadFileName;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public Integer getOperatorType() {
	return operatorType;
    }

    public List<OssLog> getOssLogList() {
	return ossLogList;
    }

    public String getPackageName() {
	return packageName;
    }

    public Integer getPages() {
	return pages;
    }

    @Action(value = "dataModify_index", results = { @Result(name = "success", location = "../../zhxy/upload/dataModify_index.jsp") })
    public String index() throws IOException {
	
	return SUCCESS;
    }

    @Action(value = "dataModify_recover", results = { @Result(name = "success", location = "../../zhxy/upload/classupload.jsp") })
    public void reloadClass() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	   /* object.put("operatorType", operatorType);
	    object.put("methodName", "operatorUploadFile");*/
	    object.put("handlerName", "oss.DataModifyHandler");
	    StringBuffer buffer = new StringBuffer();
//	    String[] ser = servers.split(",");
	    Map<String, Object> resultMap = CONNECTION.sendMsg(object);
	    Integer result = (Integer) resultMap.get("code");
	    if (result == 0) {
		buffer.append("数据修复成功！");     
	    }else{
		buffer.append("数据修复失败！");     
	    }
	    JSONObject jsonObject = new JSONObject();
	    getPageMessage().setStatusCode(200);
	    getPageMessage().setMessage(buffer.toString());
//	    PageMessage pageMessage = getPageMessage();
	    getJSONResponse().responseJson(getPageMessage());
//	    reponse.getWriter().print(getPageMessage());

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginNum(Integer beginNum) {
	this.beginNum = beginNum;
    }

    public void setClassUpload(File classUpload) {
	this.classUpload = classUpload;
    }

    public void setClassUploadContentType(String classUploadContentType) {
	this.classUploadContentType = classUploadContentType;
    }

    public void setClassUploadFileName(String classUploadFileName) {
	this.classUploadFileName = classUploadFileName;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOperatorType(Integer operatorType) {
	this.operatorType = operatorType;
    }

    public void setOssLogList(List<OssLog> ossLogList) {
	this.ossLogList = ossLogList;
    }

    public void setPackageName(String packageName) {
	this.packageName = packageName;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    @Action(value = "uploadClass_upload", results = { @Result(name = "success", location = "../../zhxy/upload/classupload.jsp") })
    public String upload() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	InputStream inputStream = null;
	try {
	    inputStream = new FileInputStream(classUpload);
	    Map<String, Object> object = new HashMap<String, Object>();
	    int size = (int) classUpload.length();
	    String fileType = classUploadFileName.substring(classUploadFileName
		    .lastIndexOf("."));
	    // byte[] fileName=classUploadFileName.getBytes();
	    byte[] data = new byte[size];
	    inputStream.read(data, 0, size);
	    // String name = new String(fileName);
	    object.put("data", data);
	    object.put("packageName", packageName);
	    object.put("name", classUploadFileName);
	    object.put("handlerName", "oss.UploadClasssHandler");
	    StringBuffer buffer = new StringBuffer();
	    for(int serverId:server){
		 OssServer ossServer = getBaseAdminContext().getOssServerById(serverId);
		try{
        		 Map<String, Object> resultMap = CONNECTION.sendMsg(serverId, object);
        		 Integer result = (Integer) resultMap.get("code");
        		 if (result == 0) {
        		     buffer.append(ossServer.getName()+"上传成功！");
        		 }else{
        		     buffer.append(ossServer.getName()+"上传失败！");
        		 }
		}catch(Exception e){
		    buffer.append(ossServer.getName()+"上传失败！");
		}
	    }
	   

	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("statusCode", 200);
	    setActionMsg(buffer.toString());
	    getPageMessage().setStatusCode(200);
	    getPageMessage().setMessage(getActionMsg());
	    ossLogService.createOssLog(OssLogConstant.OSS_CLASS_UPLOAD,
			"上传class文件" + classUploadFileName + "："+buffer.toString());
	    /*
	       * else { actionMsg = "加载失败，class文件出错，或者这个配置文件不能被加载，请检查后再上传！";
	       * pageMessage.setStatusCode(300);
	       * pageMessage.setMessage(actionMsg);
	       * ossLogService.createOssLog(OssLogConstant.OSS_XML_UPLOAD,
	       * "加载"+fileName+"失败，配置文件出错或配置文件不能被加载。"); }
	       */
	    
	    getJSONResponse().responseJson(getPageMessage());
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		inputStream.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return null;
    }

    public List<OssServer> getServerList() {
        return serverList;
    }

    public void setServerList(List<OssServer> serverList) {
        this.serverList = serverList;
    }

    public int[] getServer() {
        return server;
    }

    public void setServer(int[] server) {
        this.server = server;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }
}
