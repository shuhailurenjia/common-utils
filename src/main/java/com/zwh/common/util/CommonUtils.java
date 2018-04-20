package com.zwh.common.util;

import java.util.Date;
import java.util.Random;

public class CommonUtils {
	
	public static final boolean TEST = true;

	// 生成随机串
	public static String getRandomString(int length) {
		//String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	public static String getNowTimeStampStr() {
		return String.valueOf(new Date().getTime()/1000);
	}
	
}
