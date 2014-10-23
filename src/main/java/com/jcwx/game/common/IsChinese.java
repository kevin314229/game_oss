package com.jcwx.game.common;

/**
 * @category 判断是否含中文的工具类
 * @author houdl
 * 
 */
public class IsChinese {
    /**
     * @category 判断字符串是否全为中文
     * @param str
     * @return boolean
     */
    public static boolean isAllChinese(String str) {
	char ch[] = str.toCharArray();
	for (int i = 0; i < ch.length; i++) {
	    if (!isChinese(ch[i])) {
		return false;
	    }
	}
	return true;
    }

    private static boolean isChinese(char c) {
	Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
		|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
		|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
		|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
		|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	    return true;
	}
	return false;
    }

    /**
     * @category 判断字符串是否包含中文
     * @param str
     * @return boolean
     */
    public static boolean isContainChinese(String str) {
	char ch[] = str.toCharArray();
	for (int i = 0; i < ch.length; i++) {
	    if (isChinese(ch[i])) {
		return true;
	    }
	}
	return false;
    }
}
