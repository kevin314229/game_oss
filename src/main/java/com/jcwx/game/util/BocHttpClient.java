package com.jcwx.game.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.jcwx.game.util.transdata.ITransfer;
import com.jcwx.game.util.transdata.ITransfer.ContentTypeEnum;
import com.jcwx.game.util.transdata.ITransfer.TransferHandler;

/**
 * @author MingJun_Guo
 * @创建日期：2013年8月22日 15:41:30
 * 
 * @类说明：中行网银支付HTTP 请求
 */
public class BocHttpClient {

    private final static Logger log = Logger.getLogger(BocHttpClient.class);

    /**
     * 中行执行支付查询
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public static String sendPost(String url, Map<String, String> map)
	    throws Exception {

	StringBuilder result = new StringBuilder();
	// POST的URL
	HttpPost httppost = new HttpPost(url);
	// 建立HttpPost对象
	List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
	// 建立一个NameValuePair数组，用于存储欲传送的参数
	Set<String> keys = map.keySet();
	for (Iterator<String> i = keys.iterator(); i.hasNext();) {
	    String key = i.next();
	    params.add(new BasicNameValuePair(key, map.get(key)));
	}

	httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	// 设置编码
	HttpResponse response = new DefaultHttpClient().execute(httppost);
	// 发送Post,并返回一个HttpResponse对象
	// Header header = response.getFirstHeader("Content-Length");
	// String Length=header.getValue();
	// 上面两行可以得到指定的Header
	// response.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
	    // 得到返回的字符串
	    result.append(EntityUtils.toString(response.getEntity(), HTTP.UTF_8));

	    // 打印输出 如果是下载文件,可以用response.getEntity().getContent()返回InputStream
	    log.debug("执行查询返回值：" + result.toString());
	}
	return result.toString();
    }

    /**
     * 中行执行支付查询
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public static String sendPostMap(String url, Map<String, String> map)
	    throws Exception {

	StringBuilder result = new StringBuilder();
	// POST的URL
	HttpPost httppost = new HttpPost(url);
	// 建立HttpPost对象
	List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
	// 建立一个NameValuePair数组，用于存储欲传送的参数
	Set<String> keys = map.keySet();
	for (Iterator<String> i = keys.iterator(); i.hasNext();) {
	    String key = i.next();
	    params.add(new BasicNameValuePair(key, map.get(key)));
	}

	httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	// 设置编码
	HttpResponse response = new DefaultHttpClient().execute(httppost);
	// 发送Post,并返回一个HttpResponse对象
	// Header header = response.getFirstHeader("Content-Length");
	// String Length=header.getValue();
	// 上面两行可以得到指定的Header
	if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
	    // 得到返回的字符串
	    // result.append(EntityUtils.toString(response.getEntity()));
	    ITransfer transfer = TransferHandler
		    .getTransfer(ContentTypeEnum.JSON.getContentType());
	    // InputStream inputStream = re
	    // Map<String, Object> resultMap = transfer.readObject(inputStream);

	    // 打印输出 如果是下载文件,可以用response.getEntity().getContent()返回InputStream
	    log.debug("执行查询返回值：" + result.toString());
	}
	return result.toString();
    }
}
