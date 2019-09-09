package com.yzw.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class week02utils {
	//转换为html格式
	public static String toHtml(String text) {
		StringBuffer buffer=new StringBuffer();
		String[] split = text.split("\n\r");
		for (String string : split) {
			buffer.append("<p>");
			String replaceAll = string.replaceAll("(\r)", "<br>");
			buffer.append(replaceAll);
			buffer.append("</P>   ");
		}
		return buffer.toString();
	}
	//判断是否为手机号
	public static boolean isPhone(String phone) {
		Pattern pattern = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
		Matcher matcher = pattern.matcher(phone);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	//判断是否为邮箱地址
	public static boolean isEmail(String email) {
		Pattern pattern=Pattern.compile("^([a-z0-9A-Z]+)@([a-z0-9A-Z]+\\\\.)+[a-zA-Z]{2,}$");
		Matcher matcher=pattern.matcher(email);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
}
