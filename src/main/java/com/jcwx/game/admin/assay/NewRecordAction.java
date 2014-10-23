package com.jcwx.game.admin.assay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.http.domain.OssNewRecord;
import com.jcwx.game.http.domain.OssNewRecordInfo;
import com.jcwx.game.http.domain.OssNewRecords;


/** 新手流失分析 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class NewRecordAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Date beginDate;
    private Date endDate;

    // 留下来的概率
    private List<OssNewRecords> ossNewRecordsList = new ArrayList<OssNewRecords>();

    public Date getBeginDate() {
	return beginDate;
    }

    public Date getEndDate() {
	return endDate;
    }

    public List<OssNewRecords> getOssNewRecordsList() {
	return ossNewRecordsList;
    }

    @Action(value = "newRecord_index", results = { @Result(name = "success", location = "../../admin/assay/newRecordAssay.jsp") })
    public String query() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	if (beginDate == null) {
	    beginDate = DateService.getDateFirstTime(new java.util.Date());
	}
	if (endDate == null) {
	    endDate = DateService.getDateFirstTime(new java.util.Date());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginDate", beginDate);
	object.put("endDate", endDate);
	object.put("handlerName", "NewRecordHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		List<OssNewRecordInfo> ossNewRecordInfoList = (List<OssNewRecordInfo>) object
			.get("ossNewRecordInfoList");
		for (OssNewRecordInfo info : ossNewRecordInfoList) {
		    OssNewRecords ossNewRecords = new OssNewRecords();
		    ossNewRecords.setCreateDate(info.getCreateDate());
		    List<OssNewRecord> ossNewRecordList = new ArrayList<OssNewRecord>();
		    String recordInfo[] = info.getNewRecordInfo().split(",");
		    int sumNub = 0;
		    for (String rinfo : recordInfo) {
			String recordIf[] = rinfo.split("-");
			sumNub = sumNub + Integer.valueOf(recordIf[1]);
		    }
		    // 统计进入游戏人数
		    OssNewRecord record = new OssNewRecord();
		    record.setLoseNub(0);
		    record.setLoseProbability(0.0);
		    record.setStep(1);
		    record.setTotalNub(sumNub);
		    ossNewRecordList.add(record);
		    // 统计每步流失的人数
		    for (int i = 0; i <= recordInfo.length - 2; i++) {
			String recordIf[] = recordInfo[i].split("-");
			Integer step = Integer.valueOf(recordIf[0]);
			Integer nub = Integer.valueOf(recordIf[1]);
			OssNewRecord ossNewRecord = new OssNewRecord();
			ossNewRecord.setStep(step + 1);
			ossNewRecord.setLoseNub(nub);
			sumNub = sumNub - ossNewRecord.getLoseNub();
			ossNewRecord.setTotalNub(sumNub);
			BigDecimal b = new BigDecimal(
				(double) ossNewRecord.getLoseNub() * 100
					/ (double) record.getTotalNub());
			ossNewRecord.setLoseProbability(b.setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue());
			ossNewRecordList.add(ossNewRecord);
		    }
		    ossNewRecords.setOssNewRecordList(ossNewRecordList);
		    OssNewRecord ossNewRecord = ossNewRecordList.get(0);
		    OssNewRecord ossNewRecord2 = ossNewRecordList
			    .get(ossNewRecordList.size() - 1);
		    BigDecimal b = new BigDecimal(
			    (double) ossNewRecord2.getTotalNub() * 100
				    / (double) ossNewRecord.getTotalNub());
		    ossNewRecords.setProbability(b.setScale(2,
			    BigDecimal.ROUND_HALF_UP).doubleValue());
		    ossNewRecordsList.add(ossNewRecords);
		}

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setBeginDate(Date beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public void setOssNewRecordsList(List<OssNewRecords> ossNewRecordsList) {
	this.ossNewRecordsList = ossNewRecordsList;
    }

}
