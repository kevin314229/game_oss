package com.jcwx.game.common.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KeyToolServlet extends HttpServlet {

    private static final long serialVersionUID = 3965137368981476745L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter out = resp.getWriter();

	out.print(output(null));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	PrintWriter out = resp.getWriter();
	String key1 = req.getParameter("key1");
	String key2 = req.getParameter("key2");
	String key3 = req.getParameter("key3");
	String key4 = req.getParameter("key4");
	String key5 = req.getParameter("key5");
	String key6 = req.getParameter("key6");
	String key7 = req.getParameter("key7");
	String key8 = req.getParameter("key8");

	if (key1 == null || key2 == null || key3 == null || key4 == null
		|| key5 == null || key6 == null || key7 == null || key8 == null) {
	    out.print(output("Error:Please Input Key"));
	    return;
	}
	String encKey1 = "".equals(key1) ? "" : MasterKeyUtil.encKey(key1);
	String encKey2 = "".equals(key2) ? "" : MasterKeyUtil.encKey(key2);
	String encKey3 = "".equals(key3) ? "" : MasterKeyUtil.encKey(key3);
	String encKey4 = "".equals(key4) ? "" : MasterKeyUtil.encKey(key4);
	String encKey5 = "".equals(key5) ? "" : MasterKeyUtil.encKey(key5);
	String encKey6 = "".equals(key6) ? "" : MasterKeyUtil.encKey(key6);
	String encKey7 = "".equals(key7) ? "" : MasterKeyUtil.encKey(key7);
	String encKey8 = "".equals(key8) ? "" : MasterKeyUtil.encKey(key8);

	StringBuffer sb2 = new StringBuffer();
	sb2.append("Encrypted Key1 is: <span style='color:#5A9E17'>" + key1
		+ "</span> ==>  <span style='color:ff0000;'>" + encKey1
		+ "</span><br/>");
	sb2.append("Encrypted Key2 is: <span style='color:#5A9E17'>" + key2
		+ "</span> ==>  <span style='color:ff0000;'>" + encKey2
		+ "</span><br/>");
	sb2.append("Encrypted Key3 is: <span style='color:#5A9E17'>" + key3
		+ "</span> ==>  <span style='color:ff0000;'>" + encKey3
		+ "</span><br/>");
	sb2.append("Encrypted Key4 is: <span style='color:#5A9E17'>" + key4
		+ "</span> ==>  <span style='color:ff0000;'>" + encKey4
		+ "</span><br/>");
	sb2.append("Encrypted Key5 is: <span style='color:#5A9E17'>" + key5
		+ "</span> ==>  <span style='color:ff0000;'>" + encKey5
		+ "</span><br/>");
	sb2.append("Encrypted Key6 is: <span style='color:#5A9E17'>" + key6
		+ "</span> ==>  <span style='color:ff0000;'>" + encKey6
		+ "</span><br/>");
	sb2.append("Encrypted Key7 is: <span style='color:#5A9E17'>" + key7
		+ "</span> ==>  <span style='color:ff0000;'>" + encKey7
		+ "</span><br/>");
	sb2.append("Encrypted Key8 is: <span style='color:#5A9E17'>" + key8
		+ "</span> ==>  <span style='color:ff0000;'>" + encKey8
		+ "</span><br/>");
	out.print(output(sb2.toString()));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	// ServletContext context = getServletContext();
	// String encCheckKey = context.getInitParameter("checkKey");
	// if (encCheckKey != null) {
	// SignUtil.setCheckKey(MasterKeyUtil.decKey(encCheckKey));
	// }
	// String timeout = context.getInitParameter("checkKeyTimeout");
	// if (timeout != null)
	// SignUtil.setTimeout(Long.valueOf(timeout).longValue());
    }

    private String output(String message) {
	StringBuffer sb = new StringBuffer();
	sb.append("<html><head><title>KeyTool</title></head><body><form action=\"keytool\" method=\"post\">");
	sb.append("<table>");
	sb.append("<tr><td>Please Input Key1:</td>");
	sb.append("<td><input type=\"textfield\" name=\"key1\"></td>");
	sb.append("</tr>");

	sb.append("<tr><td>Please Input Key2:</td>");
	sb.append("<td><input type=\"textfield\" name=\"key2\"></td>");
	sb.append("</tr>");

	sb.append("<tr><td>Please Input Key3:</td>");
	sb.append("<td><input type=\"textfield\" name=\"key3\"></td>");
	sb.append("</tr>");

	sb.append("<tr><td>Please Input Key4:</td>");
	sb.append("<td><input type=\"textfield\" name=\"key4\"></td>");
	sb.append("</tr>");

	sb.append("<tr><td>Please Input Key5:</td>");
	sb.append("<td><input type=\"textfield\" name=\"key5\"></td>");
	sb.append("</tr>");

	sb.append("<tr><td>Please Input Key6:</td>");
	sb.append("<td><input type=\"textfield\" name=\"key6\"></td>");
	sb.append("</tr>");

	sb.append("<tr><td>Please Input Key7:</td>");
	sb.append("<td><input type=\"textfield\" name=\"key7\"></td>");
	sb.append("</tr>");

	sb.append("<tr><td>Please Input Key8:</td>");
	sb.append("<td><input type=\"textfield\" name=\"key8\"></td>");
	sb.append("</tr>");

	sb.append("<tr><td><input type=\"submit\"></td>");
	sb.append("</tr></table>");
	if (message != null) {
	    sb.append("<div>" + message + "</div>");
	}
	sb.append("</form></body></html>");
	return sb.toString();
    }
}
