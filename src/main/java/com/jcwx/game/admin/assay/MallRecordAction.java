package com.jcwx.game.admin.assay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.admin.vo.PageMessage;
import com.jcwx.game.common.DateService;
import com.jcwx.game.http.domain.OssMallRecord;


/**
 * 商城消耗记录
 * 
 * @author 小平 2013-11-18
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class MallRecordAction extends BaseInfoAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // 开始时间
    private Date beginTime;
    // 普通商品总数量
    private int count;

    // 结束时间
    private Date endTime;
    /*
     * //查询类型 private Integer selType;
     */
    // 销售金额数据
    private List<JSONObject> goldJsonList;
    // 普通魔晶总数量
    private Double goldSum;
    // 销售商品数据
    private List<JSONObject> jsonList;
    private List<OssMallRecord> mallRecordList = new ArrayList<OssMallRecord>();
    // 普通商品总数量
    private int secretCount;
    // 普通魔晶总数量
    private Double secretGoldSum;
    // 总金额
    private double sumGold;
    // 玩家拥有的魔晶数
    private Long supGoldSum;
    // 花费的总魔晶数
    private Double totalGold;
    // 普通商品总数量
    private int vipCount;
    // 普通魔晶总数量
    private Double vipGoldSum;

    private PageMessage pageMessage = PageMessage.getOkMessage();

    public Date getBeginTime() {
	return beginTime;
    }

    public int getCount() {
	return count;
    }

    public Date getEndTime() {
	return endTime;
    }

    public List<JSONObject> getGoldJsonList() {
	return goldJsonList;
    }

    public Double getGoldSum() {
	return goldSum;
    }

    public List<JSONObject> getJsonList() {
	return jsonList;
    }

    public List<OssMallRecord> getMallRecordList() {
	return mallRecordList;
    }

    public int getSecretCount() {
	return secretCount;
    }

    public Double getSecretGoldSum() {
	return secretGoldSum;
    }

    public double getSumGold() {
	return sumGold;
    }

    public Long getSupGoldSum() {
	return supGoldSum;
    }

    public Double getTotalGold() {
	return totalGold;
    }

    public int getVipCount() {
	return vipCount;
    }

    public Double getVipGoldSum() {
	return vipGoldSum;
    }

    @Action(value = "mallRecord_index", results = { @Result(name = "success", location = "../../admin/assay/mallRecordAssay.jsp") })
    public String query() throws Exception {
	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	} else {
	    beginTime = DateService.getDateFirstTime(beginTime);
	}
	if (endTime == null) {
	    endTime = DateService.getDateLastTime(new java.util.Date());
	} else {
	    endTime = DateService.getDateLastTime(endTime);
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "MallRecordHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		List<OssMallRecord> list = (List<OssMallRecord>) object
			.get("ossMallRecordList");
		totalGold = Double.valueOf(object.get("totalGold").toString()); // 总魔晶数
		supGoldSum = Long.valueOf(object.get("supGoldSum").toString()); // 玩家剩余魔晶总数
		goldSum = Double.valueOf(object.get("totalNormalGold")
			.toString()); // 普通销售 总计
		vipGoldSum = Double.valueOf(object.get("totalVipGold")
			.toString()); // vip 总计
		secretGoldSum = Double.valueOf(object.get("totalsecretGold")
			.toString()); // 神秘商店 总计

		jsonList = new ArrayList<JSONObject>();
		goldJsonList = new ArrayList<JSONObject>();
		for (int i = 0; i < list.size(); i++) {
		    OssMallRecord mrd = list.get(i);
		    count = count + mrd.getNumber();
		    vipCount = vipCount + mrd.getVipNumber();
		    secretCount = secretCount + mrd.getSecretNumber();

		    sumGold = sumGold + mrd.getGoldSum();
		    mrd.setServerName(getBaseAdminContext().getName());
		    if (goldSum == 0) {
			mrd.setScale(0);
		    } else {
			BigDecimal b = new BigDecimal(mrd.getPrice()
				* mrd.getNumber() * 100 / goldSum);
			mrd.setScale(b.setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue());
		    }
		    if (vipGoldSum == 0) {
			mrd.setVipScale(0);
		    } else {
			BigDecimal c = new BigDecimal(mrd.getVipPrice()
				* mrd.getVipNumber() * 100 / vipGoldSum);
			mrd.setVipScale(c.setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue());
		    }
		    if (secretGoldSum == 0) {
			mrd.setSecretScale(0);
		    } else {
			BigDecimal d = new BigDecimal(mrd.getSecretSum() * 100
				/ secretGoldSum);
			mrd.setSecretScale(d.setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue());
		    }

		    BigDecimal e = new BigDecimal((mrd.getPrice()
			    * mrd.getNumber() + mrd.getVipPrice()
			    * mrd.getVipNumber())
			    * 100 / totalGold);
		    mrd.setNormalScale(e.setScale(2, BigDecimal.ROUND_HALF_UP)
			    .doubleValue());

		    BigDecimal f = new BigDecimal(mrd.getSecretSum() * 100
			    / totalGold);
		    mrd.setTotalSecretScale(f.setScale(2,
			    BigDecimal.ROUND_HALF_UP).doubleValue());

		    BigDecimal g = new BigDecimal(mrd.getGoldSum() * 100
			    / totalGold);
		    mrd.setTotalScale(g.setScale(2, BigDecimal.ROUND_HALF_UP)
			    .doubleValue());

		    mallRecordList.add(mrd);

		    JSONObject goldJson = new JSONObject();
		    goldJson.put("cc", mrd.getGoldSum());
		    goldJson.put("temp", mrd.getGoodName());
		    goldJsonList.add(goldJson);

		    JSONObject json = new JSONObject();
		    json.put(
			    "c",
			    mrd.getNumber() + mrd.getSecretNumber()
				    + mrd.getVipNumber());
		    json.put("hh", mrd.getGoodName());
		    jsonList.add(json);
		}

		Collections.sort(mallRecordList,
			new Comparator<OssMallRecord>() {
			    @Override
			    public int compare(OssMallRecord p2,
				    OssMallRecord p1) {
				return (int) (p1.getGoldSum() - p2.getGoldSum());
			    }
			});

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setCount(int count) {
	this.count = count;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setGoldJsonList(List<JSONObject> goldJsonList) {
	this.goldJsonList = goldJsonList;
    }

    public void setGoldSum(Double goldSum) {
	this.goldSum = goldSum;
    }

    public void setJsonList(List<JSONObject> jsonList) {
	this.jsonList = jsonList;
    }

    public void setMallRecordList(List<OssMallRecord> mallRecordList) {
	this.mallRecordList = mallRecordList;
    }

    public void setSecretCount(int secretCount) {
	this.secretCount = secretCount;
    }

    public void setSecretGoldSum(Double secretGoldSum) {
	this.secretGoldSum = secretGoldSum;
    }

    public void setSumGold(double sumGold) {
	this.sumGold = sumGold;
    }

    public void setSupGoldSum(Long supGoldSum) {
	this.supGoldSum = supGoldSum;
    }

    public void setTotalGold(Double totalGold) {
	this.totalGold = totalGold;
    }

    public void setVipCount(int vipCount) {
	this.vipCount = vipCount;
    }

    public void setVipGoldSum(Double vipGoldSum) {
	this.vipGoldSum = vipGoldSum;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
