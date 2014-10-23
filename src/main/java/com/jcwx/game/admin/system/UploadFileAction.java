package com.jcwx.game.admin.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.OssLog;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.service.oss.IOssLogService;
@SuppressWarnings("unused")
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class UploadFileAction extends BasalAction {
    private static final long serialVersionUID = 80514475994427314L;
    private static Logger logger = Logger.getLogger(UploadFileAction.class);
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


    private File upload; // 上传的文件

    private String uploadContentType;
    private String uploadFileName; // 文件名称
    
    private String path;

    private int isAbsolute;
    @Action(value = "upload_index", results = { @Result(name = "success", location = "../../admin/upload/upload_index.jsp") })
    public String uploadFileIndex(){
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
	// allNum = ossLogService.getOssLogCount();
	// ossLogList = ossLogService.getOssLogListByPage(beginNum, onePageNum);
	allNum = ossLogService.getAllNumByQueryCondition(null, null, null,
		"uploadFile", ossServer.getId());
	ossLogList = ossLogService.getOssLogListByQueryCondition(beginNum,
		onePageNum, null, null, null, "uploadFile",
		ossServer.getId());
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return SUCCESS;
	
    }
    @Action(value = "upload_index2", results = { @Result(name = "success", location = "../../admin/upload/upload_index2.jsp") })
    public String uploadFileIndex2(){
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
	// allNum = ossLogService.getOssLogCount();
	// ossLogList = ossLogService.getOssLogListByPage(beginNum, onePageNum);
	allNum = ossLogService.getAllNumByQueryCondition(null, null, null,
		"uploadFile", ossServer.getId());
	ossLogList = ossLogService.getOssLogListByQueryCondition(beginNum,
		onePageNum, null, null, null, "uploadFile",
		ossServer.getId());
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return SUCCESS;
	
    }
    @Action(value = "upload_upload", results = { @Result(name = "success", location = "../../admin/upload/upload_index.jsp") })
    public String upload() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	InputStream inputStream = null;
/*	String projectPath = System.getProperty("user.dir");*/
	String projectPath = ServletActionContext.getServletContext().getRealPath("/");
	try {
//	    String pathStr = this.getClass().getResource("/").getPath(); 
//	    String teString = ServletActionContext.getServletContext().getRealPath("/");
	    inputStream = new FileInputStream(upload);
	    // OutputStream outputStream = new FileOutputStream(upload);
	    int size = (int) upload.length();
	    byte[] fileName = uploadFileName.getBytes();
	    byte[] data = new byte[size];
//	    inputStream.read(data, 0, size);
//	    projectPath = System.getProperty("user.dir");
//	    if(projectPath!=null&&projectPath.indexOf("bin")>0){
//		projectPath.replaceAll("bin", "webapps"+File.separator+"ROOT");
//	    }
	    if(isAbsolute==0){
		path=projectPath+File.separator+path+ File.separator + uploadFileName;
	    }
	    File file = new File(path);
		if (file.exists())
			file.delete();
		OutputStream outputStream = new FileOutputStream(path);
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, len);
			outputStream.flush();
		}
		outputStream.close();			
		IOUtils.closeQuietly(inputStream);
	    // PageMessage message = PageMessage.getOkMessage();
	    // pageMessage.setStatusCode(200);
	    // pageMessage.setMessage("操作成功");
	    // pageMessage.setCallbackType("closeCurrent");

	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("statusCode", 200);
//	    if (true) {
		setActionMsg("文件上传成功！");
		getPageMessage().setStatusCode(200);
		getPageMessage().setMessage(getActionMsg());
		ossLogService.createOssLog("uploadFile",
			"上传配置文件" + uploadFileName + "成功");
//	    } else {
//		actionMsg = "加载失败，配置文件出错，或者这个配置文件不能被加载，请检查后再上传！";
//		getPageMessage().setStatusCode(300);
//		getPageMessage().setMessage(actionMsg);
//		ossLogService.createOssLog(OssLogConstant.OSS_XML_UPLOAD, "加载"
//			+ fileName + "失败，配置文件出错或配置文件不能被加载。");
//	    }
	    
	    getJSONResponse().responseJson(getPageMessage());
	} catch (Exception e) {
	 logger.error(path+"路径不存在",e);
	    getPageMessage().setMessage(path+"路径不存在");
	    getJSONResponse().responseJson(getPageMessage()); 
	} finally {
	    try {
		inputStream.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return null;
    }
    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public Integer getBeginNum() {
        return beginNum;
    }

    public void setBeginNum(Integer beginNum) {
        this.beginNum = beginNum;
    }

    public Integer getCurrPageNO() {
        return currPageNO;
    }

    public void setCurrPageNO(Integer currPageNO) {
        this.currPageNO = currPageNO;
    }

    public Integer getOnePageNum() {
        return onePageNum;
    }

    public void setOnePageNum(Integer onePageNum) {
        this.onePageNum = onePageNum;
    }

    public List<OssLog> getOssLogList() {
        return ossLogList;
    }

    public void setOssLogList(List<OssLog> ossLogList) {
        this.ossLogList = ossLogList;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public int getIsAbsolute() {
        return isAbsolute;
    }
    public void setIsAbsolute(int isAbsolute) {
        this.isAbsolute = isAbsolute;
    }
    
}
