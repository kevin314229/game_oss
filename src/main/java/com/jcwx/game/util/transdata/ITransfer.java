package com.jcwx.game.util.transdata;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public interface ITransfer {

    public enum ContentTypeEnum {
	JSON("application/json;charset=utf-8"), STREAM(
		"application/octet-stream");

	/**
	 * 为空或者找不到contentType都采用STREAM 流传递。 明确为JSON才采用JSON传递
	 * 
	 * @param contentType
	 * @return
	 */
	public static ContentTypeEnum convertToContentType(String contentType) {
	    // Validate.notNull(contentType,"param contentType must not be null!");
	    if (StringUtils.isBlank(contentType)
		    || STREAM.getContentType().equals(contentType)) {
		return STREAM;
	    }
	    if (JSON.getContentType().equals(contentType)) {
		return JSON;
	    }

	    return STREAM;
	}

	private String contentType = null;

	private ContentTypeEnum(String type) {
	    contentType = type;
	}

	public String getContentType() {
	    return contentType;
	}

    }

    public class TransferHandler {
	public static ITransfer getTransfer(String contentType) {
	    ContentTypeEnum enumTransfer = ContentTypeEnum
		    .convertToContentType(contentType);
	    if (ContentTypeEnum.JSON.equals(enumTransfer)) {
		return new JsonTrans();
	    } else {
		return new StreamTrans();
	    }
	}
    }

    public static final String READ_ENCODING = "UTF-8";

    public static final String RESPONSE_ENCODING = "UTF-8";

    public ContentTypeEnum contentType();

    public boolean isException(InputStream inputStream,
	    String httpHeaderErrClass) throws Exception;

    public Exception readException(InputStream inputStream) throws Exception;

    public Map<String, Object> readObject(InputStream inputStream)
	    throws Exception;

    public void writeObject(OutputStream outputStream,
	    Map<String, Object> paraMap, String key) throws Exception;
}
