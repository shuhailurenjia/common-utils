package com.zwh.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class GetSystemUrl {
	/*
	 * 获取访问服务器的端口号IP
	 */
	public static String getSystemUrl(HttpServletRequest request) {
		// 用于获取服务器IP 端口号 项目名
		int localPort = request.getServerPort();
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		String url = "";
		if (localPort == 80 || localPort == 443) {
			url = scheme + "://" + serverName + request.getContextPath();
		} else {
			url = scheme + "://" + serverName + ":" + request.getServerPort() + request.getContextPath();
		}
		return url;
	}

	/*
	 * 获取系统时间戳
	 */
	public static String getTimeUrl() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date d = new Date();
		String str = sdf.format(d);
		return str;
	}
	
	public static String getIpUrl(HttpServletRequest request) {
		// 用于获取服务器IP 端口号 项目名
		int localPort = request.getServerPort();
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		String url = "";
		if (localPort == 80 || localPort == 443) {
			url = scheme + "://" + serverName;
		} else {
			url = scheme + "://" + serverName + ":" + request.getServerPort();
		}
		return url;
	}
}
