package com.jcwx.game.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class XMLUtil {

    private static final Logger logger = LoggerFactory.getLogger(XMLUtil.class);

    /**
     * 获取xml输入流
     * 
     * @param path
     *            config后的路径
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getXMLInputStream(String configPath)
	    throws FileNotFoundException {
	String projectPath = Thread.currentThread().getContextClassLoader()
		.getResource("").getPath();
	String path = projectPath + File.separator + configPath;
	File file = new File(path);
	InputStream in = null;
	if (file.exists())
	    in = new FileInputStream(file);
	if (in == null) {
	    logger.error(path);
	    throw new FileNotFoundException();
	}
	return in;
    }

}
