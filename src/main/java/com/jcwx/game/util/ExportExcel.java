package com.jcwx.game.util;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.OssContext;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.common.constants.GameConstant;
import com.jcwx.game.common.constants.OperationLogConstant;
import com.jcwx.game.common.constants.OssServerConstant;
import com.jcwx.game.common.constants.PtServerConstant;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.service.oss.IOssDictService;

public class ExportExcel {
    private static String dateToString(Date date) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String str = sdf.format(date);
	if (str.endsWith("00:00")) {
	    str = str.substring(0, 10);
	}
	return str;
    }

    @SuppressWarnings("unchecked")
    public static void export(String[] titles, String[] elements,
	    List entityList, String fileName, String sheetName,
	    HttpServletResponse response, Class cls, String sub)
	    throws Exception {
	BaseAdminContext baseAdminContext = OssContext.getBaseAdminContext();
	Map<String, String> operMap = queryDictMap(baseAdminContext
		.getProject().getProjectId(), DictTypeConstant.OPERTION_TYPE);
	Map<String, String> areaMap = queryDictMap(baseAdminContext
		.getProject().getProjectId(), DictTypeConstant.AREA_TYPE);
	Map<String, String> gameMap = queryDictMap(baseAdminContext
		.getProject().getProjectId(), DictTypeConstant.GAME_TYPE);
	Map<String, String> ptMap = queryDictMap(baseAdminContext.getProject()
		.getProjectId(), DictTypeConstant.PT_TYPE);

	if (elements.length != titles.length) {
	    throw new IndexOutOfBoundsException("标题数组和字段数组长度不一致");
	} else {
	    // 获得导出数据的列数
	    response.reset();
	    String filename = new String(fileName.getBytes("utf-8"),
		    "iso8859-1");//
	    response.setHeader("Content-Disposition", "attachment; filename="
		    + filename + sub);

	    // response.setHeader("Content-Disposition","attachment; filename="+fileName);
	    response.setContentType("application/vnd.ms-excel;charset=UTF-8");

	    OutputStream os = response.getOutputStream();
	    WritableWorkbook wwb = Workbook.createWorkbook(os);
	    WritableSheet ws = wwb.createSheet(sheetName, 0);
	    // WritableSheet ws2 = wwb.createSheet(sheetName+"2", 0);
	    try {
		// 写入列名
		for (int i = 0; i < titles.length; i++) {
		    WritableFont tFont = new WritableFont(WritableFont.ARIAL,
			    10, WritableFont.BOLD);
		    WritableCellFormat wcf = new WritableCellFormat(tFont);
		    wcf.setBorder(Border.NONE, BorderLineStyle.THIN);
		    wcf.setAlignment(Alignment.CENTRE);
		    Label labelName = new Label(i, 0, titles[i], wcf);
		    ws.addCell(labelName);
		    ws.setColumnView(i, 20);
		}
		// 写入数据
		if (entityList != null) {
		    WritableFont wFont = new WritableFont(WritableFont.ARIAL, 8);
		    WritableCellFormat wcf = new WritableCellFormat(wFont);
		    wcf.setBorder(Border.NONE, BorderLineStyle.THIN);
		    wcf.setAlignment(Alignment.CENTRE);
		    for (int i = 0; i < entityList.size(); i++) {
			JSONObject json = (JSONObject) JSON.toJSON(entityList
				.get(i));
			for (int j = 0; j < elements.length; j++) {
			    // Label label = new Label(j, i + 1,
			    // JSONObject.fromObject(entityList.get(i)).get(elements[j]).toString(),
			    // wcf);
			    Object element = new Object();
			    Object param = json.get(elements[j]);
			    if ("gameId".equals(elements[j])) {
				if (param != null) {
				    if (gameMap.containsKey(param.toString())) {
					element = gameMap.get(param.toString());
				    } else {
					if (GameConstant.gameMap
						.containsKey(param.toString())) {
					    element = GameConstant.gameMap
						    .get(param.toString());
					} else {
					    element = param.toString();
					}
				    }
				} else {
				    element = null;
				}
			    } else if ("areaId".equals(elements[j])) {
				if (param != null) {
				    if (areaMap.containsKey(param.toString())) {
					element = areaMap.get(param.toString());
				    } else {
					if (OssServerConstant.ossServerMap
						.containsKey(param.toString())) {
					    element = OssServerConstant.ossServerMap
						    .get(param.toString());
					} else {
					    element = param.toString();
					}
				    }
				} else {
				    element = null;
				}
			    } else if ("ptId".equals(elements[j])) {
				if (param != null) {
				    if (ptMap.containsKey(param.toString())) {
					element = ptMap.get(param.toString());
				    } else {
					if (PtServerConstant.ptTypeMap
						.containsKey(param.toString())) {
					    element = PtServerConstant.ptTypeMap
						    .get(param.toString());
					} else {
					    element = param.toString();
					}
				    }
				} else {
				    element = null;
				}
			    } else if ("operation".equals(elements[j])) {
				if (param != null) {
				    if (operMap.containsKey(param.toString())) {
					element = operMap.get(param.toString());
				    } else {
					if (OperationLogConstant.maptype
						.containsKey(param.toString())) {
					    element = OperationLogConstant.maptype
						    .get(param.toString());
					} else {
					    element = param.toString();
					}
				    }
				} else {
				    element = null;
				}
			    } else if ("carrierOperator2".equals(elements[j])) {
				param = json.get("carrierOperator");
				if (param != null) {
				    // element =
				    // PtServerConstant.ptTypeMap.get(param.toString());
				    if (ptMap.containsKey(param.toString())) {
					element = ptMap.get(param.toString());
				    } else {
					if (PtServerConstant.ptTypeMap
						.containsKey(param.toString())) {
					    element = PtServerConstant.ptTypeMap
						    .get(param.toString());
					} else {
					    element = param.toString();
					}
				    }
				} else {
				    element = null;
				}
			    } else {
				element = json.get(elements[j]);
			    }
			    if (element == null) {
				Label label = new Label(j, i + 1, "", wcf);
				ws.addCell(label);
			    } else {
				if (element instanceof Date) {
				    Date date = (Date) element;
				    Label label = new Label(j, i + 1,
					    dateToString(date), wcf);
				    ws.addCell(label);
				} else {
				    Label label = new Label(j, i + 1,
					    element.toString(), wcf);
				    ws.addCell(label);
				}

			    }

			}
		    }
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    } finally {
		wwb.write();
		wwb.close();
		os.close();
	    }
	}
    }

    @SuppressWarnings("unchecked")
    public static void exportSheets(String[] titles, String[] elements,
	    List entityList, String sheetName, Class cls, String[] titles2,
	    String[] elements2, List entityList2, String sheetName2,
	    Class cls2, HttpServletResponse response, String fileName,
	    String sub) throws Exception {

	if (elements.length != titles.length) {
	    throw new IndexOutOfBoundsException("标题数组和字段数组长度不一致");
	} else {
	    // 获得导出数据的列数
	    response.reset();
	    String filename = new String(fileName.getBytes("utf-8"),
		    "iso8859-1");//
	    response.setHeader("Content-Disposition", "attachment; filename="
		    + filename + sub);

	    // response.setHeader("Content-Disposition","attachment; filename="+fileName);
	    response.setContentType("application/vnd.ms-excel;charset=UTF-8");

	    OutputStream os = response.getOutputStream();
	    WritableWorkbook wwb = Workbook.createWorkbook(os);
	    WritableSheet ws = wwb.createSheet(sheetName, 0);
	    WritableSheet ws2 = wwb.createSheet(sheetName2, 0);
	    try {
		// 写入列名
		for (int i = 0; i < titles.length; i++) {
		    WritableFont tFont = new WritableFont(WritableFont.ARIAL,
			    10, WritableFont.BOLD);
		    WritableCellFormat wcf = new WritableCellFormat(tFont);
		    wcf.setBorder(Border.NONE, BorderLineStyle.THIN);
		    wcf.setAlignment(Alignment.CENTRE);
		    Label labelName = new Label(i, 0, titles[i], wcf);
		    ws.addCell(labelName);
		    ws.setColumnView(i, 20);
		}
		// 写入数据
		if (entityList != null) {
		    WritableFont wFont = new WritableFont(WritableFont.ARIAL, 8);
		    WritableCellFormat wcf = new WritableCellFormat(wFont);
		    wcf.setBorder(Border.NONE, BorderLineStyle.THIN);
		    wcf.setAlignment(Alignment.CENTRE);
		    for (int i = 0; i < entityList.size(); i++) {
			JSONObject json = (JSONObject) JSON.toJSON(entityList
				.get(i));
			for (int j = 0; j < elements.length; j++) {
			    // Label label = new Label(j, i + 1,
			    // JSONObject.fromObject(entityList.get(i)).get(elements[j]).toString(),
			    // wcf);
			    Object element = new Object();
			    Object param = json.get(elements[j]);
			    if ("gameId".equals(elements[j])) {
				if (param != null)
				    element = GameConstant.gameMap.get(param
					    .toString());
				else {
				    element = null;
				}
			    } else if ("areaId".equals(elements[j])) {
				if (param != null)
				    element = OssServerConstant.ossServerMap
					    .get(param.toString());
				else {
				    element = null;
				}
			    } else if ("ptId".equals(elements[j])) {
				if (param != null)
				    element = PtServerConstant.ptTypeMap
					    .get(param.toString());
				else {
				    element = null;
				}
			    } else if ("operation".equals(elements[j])) {
				if (param != null) {
				    element = OperationLogConstant.maptype
					    .get(param.toString());
				} else {
				    element = null;
				}
			    } else if ("carrierOperator2".equals(elements[j])) {
				param = json.get("carrierOperator");
				if (param != null) {
				    element = PtServerConstant.ptTypeMap
					    .get(param.toString());
				} else {
				    element = null;
				}
			    } else {
				element = json.get(elements[j]);
			    }
			    if (element == null) {
				Label label = new Label(j, i + 1, "", wcf);
				ws.addCell(label);
			    } else {
				if (element instanceof Date) {
				    Date date = (Date) element;
				    Label label = new Label(j, i + 1,
					    dateToString(date), wcf);
				    ws.addCell(label);
				} else {
				    Label label = new Label(j, i + 1,
					    element.toString(), wcf);
				    ws.addCell(label);
				}

			    }

			}
		    }
		}

		for (int i = 0; i < titles2.length; i++) {
		    WritableFont tFont = new WritableFont(WritableFont.ARIAL,
			    10, WritableFont.BOLD);
		    WritableCellFormat wcf = new WritableCellFormat(tFont);
		    wcf.setBorder(Border.NONE, BorderLineStyle.THIN);
		    wcf.setAlignment(Alignment.CENTRE);
		    Label labelName = new Label(i, 0, titles2[i], wcf);
		    ws2.addCell(labelName);
		    ws2.setColumnView(i, 20);
		}
		// 写入数据
		if (entityList2 != null) {
		    WritableFont wFont = new WritableFont(WritableFont.ARIAL, 8);
		    WritableCellFormat wcf = new WritableCellFormat(wFont);
		    wcf.setBorder(Border.NONE, BorderLineStyle.THIN);
		    wcf.setAlignment(Alignment.CENTRE);
		    for (int i = 0; i < entityList2.size(); i++) {
			JSONObject json = (JSONObject) JSON.toJSON(entityList2
				.get(i));
			for (int j = 0; j < elements2.length; j++) {
			    // Label label = new Label(j, i + 1,
			    // JSONObject.fromObject(entityList.get(i)).get(elements[j]).toString(),
			    // wcf);
			    Object element = new Object();
			    Object param = json.get(elements2[j]);
			    if ("gameId".equals(elements2[j])) {
				if (param != null)
				    element = GameConstant.gameMap.get(param
					    .toString());
				else {
				    element = null;
				}
			    } else if ("areaId".equals(elements2[j])) {
				if (param != null)
				    element = OssServerConstant.ossServerMap
					    .get(param.toString());
				else {
				    element = null;
				}
			    } else if ("ptId".equals(elements2[j])) {
				if (param != null)
				    element = PtServerConstant.ptTypeMap
					    .get(param.toString());
				else {
				    element = null;
				}
			    } else if ("operation".equals(elements2[j])) {
				if (param != null) {
				    element = OperationLogConstant.maptype
					    .get(param.toString());
				} else {
				    element = null;
				}
			    } else if ("carrierOperator2".equals(elements2[j])) {
				param = json.get("carrierOperator");
				if (param != null) {
				    element = PtServerConstant.ptTypeMap
					    .get(param.toString());
				} else {
				    element = null;
				}
			    } else {
				element = json.get(elements2[j]);
			    }
			    if (element == null) {
				Label label = new Label(j, i + 1, "", wcf);
				ws2.addCell(label);
			    } else {
				if (element instanceof Date) {
				    Date date = (Date) element;
				    Label label = new Label(j, i + 1,
					    dateToString(date), wcf);
				    ws2.addCell(label);
				} else {
				    Label label = new Label(j, i + 1,
					    element.toString(), wcf);
				    ws2.addCell(label);
				}

			    }

			}
		    }
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    } finally {
		wwb.write();
		wwb.close();
		os.close();
	    }
	}
    }

    private static Map<String, String> queryDictMap(Integer gameId, Integer type) {
	IOssDictService dictService = (IOssDictService) SpringService
		.getBean("ossDictService");
	List<OssDictData> dictList = dictService.getOssDictDataList(gameId,
		type);
	Map<String, String> dictMap = new HashMap<String, String>();
	if (dictList != null) {
	    for (OssDictData dictData : dictList) {
		dictMap.put(dictData.getDictValue(), dictData.getDictName());
	    }
	}
	return dictMap;
    }
}
