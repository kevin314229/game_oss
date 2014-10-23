package com.jcwx.game.common;

public class BytesUtils {

    public static String substring(String src, int start_idx, int end_idx) {
	byte[] b = src.getBytes();
	String tgt = "";
	for (int i = start_idx; i <= end_idx; i++) {
	    tgt += (char) b[i];
	}
	return tgt;
    }
}
