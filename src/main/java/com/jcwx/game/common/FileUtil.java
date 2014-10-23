package com.jcwx.game.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.log4j.Logger;

public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class);

    // 文件路径
    private static final String PROPERTY_FILE;
    static {
	PROPERTY_FILE = replaceSpace(Thread.currentThread()
		.getContextClassLoader().getResource("").getPath()
		+ "id.txt");
    }

    public static int readWriteData() {
	BufferedReader reader = null;
	BufferedWriter writer = null;

	try {
	    reader = new BufferedReader(new FileReader(new File(PROPERTY_FILE)));
	    String ID = reader.readLine();
	    reader.close();
	    int newID = Integer.parseInt(ID.trim()) + 1; // id+1
	    writer = new BufferedWriter(new FileWriter(new File(PROPERTY_FILE)));
	    writer.write(newID + "");
	    writer.close();
	    return newID;
	} catch (Exception e) {
	    logger.error(ResourceBundleService.getString("txt.exception"), e);
	    return 0;
	} finally {
	    if (reader != null)
		reader = null;
	    if (writer != null)
		writer = null;
	}
    }

    public static String replaceSpace(String path) {
	return path.replace("%20", " ");
    }
}
