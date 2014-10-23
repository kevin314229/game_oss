package com.jcwx.game.admin.sta;

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
import com.jcwx.game.domain.OssLog;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.service.oss.IOssLogService;

import com.jcwx.game.util.transdata.ITransfer.ContentTypeEnum;

/** 服务器选择 */
@SuppressWarnings("unused")
@ParentPackage("base")
@Namespace("/admin/xmlupload")
@ResultPath("/")
public class XMLUploadAction extends BasalAction {

    private static final long serialVersionUID = 805121475994427314L;
    /** 总记录数 */
    private Integer allNum;

    /** 起始页号 */
    private Integer beginNum;

    /** 当前页数 */
    private Integer currPageNO;

    /** 每页记录数 */
    private Integer onePageNum;
    private List<OssLog> ossLogList;

    @Autowired
    private IOssLogService ossLogService;

    /** 总页数 */
    private Integer pages;

    private String test;

    private File xml; // 上传的文件

    private String xmlContentType;
    private String xmlFileName; // 文件名称
    private String content;
    private List<OssServer> serverList;
    
    private int[]   server;
    

    public Integer getAllNum() {
	return allNum;
    }

    public Integer getBeginNum() {
	return beginNum;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public List<OssLog> getOssLogList() {
	return ossLogList;
    }

    public Integer getPages() {
	return pages;
    }

    public String getTest() {
	return test;
    }

    public File getXml() {
	return xml;
    }

    public String getXmlContentType() {
	return xmlContentType;
    }

    public String getXmlFileName() {
	return xmlFileName;
    }

    @Action(value = "xmlupload_index", results = { @Result(name = "success", location = "../../admin/upload/xmlupload.jsp") })
    public String index() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
	    setOnePageNum(20);
	}
	beginNum = (currPageNO - 1) * onePageNum;
	OssServer ossServer = getBaseAdminContext().getCurrentOssServer();
	serverList=getBaseAdminContext().getOssServers();
	// allNum = ossLogService.getOssLogCount();
	// ossLogList = ossLogService.getOssLogListByPage(beginNum, onePageNum);
	allNum = ossLogService.getAllNumByQueryCondition(null, null, null,
		OssLogConstant.OSS_XML_UPLOAD, ossServer.getId());
	ossLogList = ossLogService.getOssLogListByQueryCondition(beginNum,
		onePageNum, null, null, null, OssLogConstant.OSS_XML_UPLOAD,
		ossServer.getId());
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return SUCCESS;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginNum(Integer beginNum) {
	this.beginNum = beginNum;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOssLogList(List<OssLog> ossLogList) {
	this.ossLogList = ossLogList;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setTest(String test) {
	this.test = test;
    }

    public void setXml(File xml) {
	this.xml = xml;
    }

    public void setXmlContentType(String xmlContentType) {
	this.xmlContentType = xmlContentType;
    }

    public void setXmlFileName(String xmlFileName) {
	this.xmlFileName = xmlFileName;
    }

    @Action(value = "xmlupload_upload", results = { @Result(name = "success", location = "../../admin/upload/xmlupload.jsp") })
    public String upload() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	InputStream inputStream = null;
	try {
	    inputStream = new FileInputStream(xml);
	    // OutputStream outputStream = new FileOutputStream(xml);
	    Map<String, Object> object = new HashMap<String, Object>();
	    int size = (int) xml.length();
	    String fileType = xmlFileName.substring(xmlFileName
		    .lastIndexOf("."));
	    // xmlFileName = FilenameUtils.getBaseName(xmlFileName);
	    byte[] fileName = xmlFileName.getBytes();
	    byte[] data = new byte[size];
	    // byte[] type = fileType.getBytes();
	    inputStream.read(data, 0, size);
	    // byte[] input = inputStream.read();
	    String name = new String(fileName);
	    // object.put("xmlName", xmlName);
	    object.put("data", data);
	    object.put("name", fileName);
	    // object.put("type", type);
	    object.put("handlerName", "XmlConfigLoadHandler");
	    // object.put("handlerName","XmlUploadHandler");
	    StringBuffer buffer = new StringBuffer();
	    for(int serverId:server){
		 OssServer ossServer = getBaseAdminContext().getOssServerById(serverId);
		try{
        		 Map<String, Object> resultMap = CONNECTION.sendMsg(
        			    serverId,ContentTypeEnum.STREAM.getContentType(), object);
        		 Integer result = (Integer) resultMap.get("code");
        		 if (result == 0) {
        		     buffer.append(ossServer.getName()+"上传成功！");
        		 }else{
        		     buffer.append(ossServer.getName()+"配置文件出错,上传失败！");
        		 }
        	}catch(Exception e){
        	    buffer.append(ossServer.getName()+"服务器异常！");
        	}
	    }
	 /*   ossLogService.createOssLog(OssLogConstant.OSS_XML_UPLOAD,
			"上传配置文件" + xmlFileName + ":"+buffer.toString());
	    */
	    ossLogService.createOssLog(OssLogConstant.OSS_XML_UPLOAD,"更新内容："+ content+
			  "；更新文件："+xmlFileName+ ":"+buffer.toString());

//	    JSONObject jsonObject = new JSONObject();
//	    jsonObject.put("statusCode", 200);
	    getPageMessage().setStatusCode(200);
	    getPageMessage().setMessage(buffer.toString());
//	    if (code == 0) {
//		actionMsg = "文件上传成功！";
//		getPageMessage().setStatusCode(200);
//		getPageMessage().setMessage(actionMsg);
//		ossLogService.createOssLog(OssLogConstant.OSS_XML_UPLOAD,
//			"上传配置文件" + xmlFileName + "成功");
//	    } else {
//		actionMsg = "加载失败，配置文件出错，或者这个配置文件不能被加载，请检查后再上传！";
//		getPageMessage().setStatusCode(300);
//		getPageMessage().setMessage(actionMsg);
//		ossLogService.createOssLog(OssLogConstant.OSS_XML_UPLOAD, "加载"
//			+ fileName + "失败，配置文件出错或配置文件不能被加载。");
//	    }
	    
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
