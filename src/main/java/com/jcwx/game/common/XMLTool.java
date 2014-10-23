package com.jcwx.game.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLTool {
    public static Document getRoot(String filePath) throws DocumentException {
	return new SAXReader().read(new File(filePath));
    }

    public static void main(String[] argv) {
	XMLTool dom4jParser = new XMLTool();

	File dir = new File(XMLTool.class.getResource("/config/map").getFile());
	File[] files = dir.listFiles();

	dom4jParser.modifyDocument(files[0]);

    }

    /**
     * 将xml文档写进对应路径的文件里面
     * 
     * @param document
     * @param filePath
     */
    public static void write(Document document, String filePath) {
	XMLWriter writer = null;
	try {
	    OutputFormat format = OutputFormat.createPrettyPrint();
	    writer = new XMLWriter(new FileOutputStream(filePath), format);
	    writer.write(document);
	    writer.close();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void modifyDocument(File inputXml) {

	try {
	    SAXReader saxReader = new SAXReader();
	    Document document = saxReader.read(inputXml);

	    List list = document.selectNodes("//content/cmap");
	    Iterator iter = list.iterator();
	    while (iter.hasNext()) {
		Element attribute = (Element) iter.next();
		System.out.println(attribute.attributeValue("id"));
		attribute.addAttribute("type1", "0");
		XMLWriter output = new XMLWriter(new FileWriter(inputXml));
		output.write(document);
		output.close();
	    }
	} catch (DocumentException e) {
	    System.out.println(e.getMessage());
	}

	catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    /*
     * public static void main(String[] args) { try { File dir = new
     * File(XMLTool.class.getResource("/config/map") .getFile()); File[] files =
     * dir.listFiles(); for (File f : files) { SAXReader saxReader = new
     * SAXReader(); Document document = saxReader.read(f); Element element =
     * (Element) document.selectNodes( "//content/cmap").get(0);
     * element.addAttribute("type", "0");
     * 
     * write(document, f.getPath()); }
     * 
     * String path = FileUtil.replaceSpace(PlayerCache.class.getResource(
     * "/config/task.xml").getFile()); SAXReader saxReader = new SAXReader();
     * Document document = saxReader.read(new File(path)); List list =
     * document.selectNodes("//tasks/task" ); Iterator iter=list.iterator();
     * while ( iter.hasNext() ) { Element element=(Element)iter.next();
     * System.out.println("name:"+element.attributeValue("id")); }
     * 
     * list =
     * document.selectNodes("//tasks/task/taskAccept/conditions/condition" );
     * iter=list.iterator(); while ( iter.hasNext() ) { Element
     * element=(Element)iter.next();
     * System.out.println("name:"+element.attributeValue("id")); }
     * 
     * 
     * } catch (Exception e) { e.printStackTrace(); } }
     */
}
