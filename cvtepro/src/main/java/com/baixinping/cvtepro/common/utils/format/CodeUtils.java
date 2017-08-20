package com.baixinping.cvtepro.common.utils.format;

import java.io.UnsupportedEncodingException;

public class CodeUtils {
	public static String decode(String src) throws UnsupportedEncodingException{
		if(src != null && !"".equals(src.trim()))
		{
			return  new String(src.getBytes("ISO-8859-1"), "UTF-8");
		}
		return "";
	}
}
