package com.jcwx.game.admin.jsonview;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class JSONResponse {
    public static JSONResponse newInstance(HttpServletResponse response)
	    throws IOException {
	return new JSONResponse(response);
    }

    private PrintWriter out;
    private HttpServletResponse reponse;

    public JSONResponse(HttpServletResponse response) throws IOException {
	this.reponse = response;
	reponse.setContentType("text/html; charset=utf-8");
	this.out = response.getWriter();
    }

    public void responseJson(Collection<?> obj) {
	out.print(JSON.toJSONString(obj));
    }

    public void responseJson(Object obj) {
	out.print(JSON.toJSONString(obj));
    }

    public void responseJson(String str) {
	out.print(str);
    }
}
