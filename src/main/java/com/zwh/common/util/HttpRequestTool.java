/**
 * Project Name:phapp
 * File Name:B.java
 * Package Name:com.thinkgem.jeesite.modules.ph.interfacefactory.crm
 * Date:2016年7月8日上午9:56:45
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
 */

package com.zwh.common.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import net.sf.json.JSONObject;

/**
 * ClassName: RequsetAPITool(用一句话描述这个类表示什么)
 * 
 * @Version 1.0
 * @Author: airufei date: 2016年7月8日 上午10:34:18
 */
public class HttpRequestTool {

	/**
	 * getInfo:(请求API接口并返回信息)
	 * 
	 * @Author airufei
	 * @param parms
	 *            请求参数
	 * @param url
	 *            请求地址
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */

	private static HttpRequestTool httptool;
	/**
	 * 私有构造函数
	 * 
	 */
	private HttpRequestTool() {

	}

	/**
	 * getInstance:(获取线程安全的单例)
	 * 
	 * @Author airufei
	 * @return
	 */
	public static HttpRequestTool getInstance() {
		if (httptool == null) {
			synchronized (HttpRequestTool.class) {
				httptool = new HttpRequestTool();
			}
		}
		return httptool;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getInfoToJson(JSONObject parms, String url)
			throws ClientProtocolException, IOException {
		    JSONObject jsonObject = null;
		    String resultStr = HttpXmlClient.post(url, parms);
			if (resultStr != null&&resultStr.length()>0) {
				jsonObject = JSONObject.fromObject(resultStr);
			}
		return jsonObject;
	}
	
	/**
	 * getInDecryptDataToJson:(发POST请求并获取解密数据)
	 * @Author airufei
	 * @param parms
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getDecryptDataToJson(JSONObject parms, String url, String aeskey)
			throws ClientProtocolException, IOException {
		    JSONObject jsonObject = null;
		    String resultStr = HttpXmlClient.post(url, parms);
		    resultStr=resultStr.replace(" ", "+");
		    resultStr = CryptAES.AES_CRM_Decrypt(resultStr,aeskey);// 参数解密
			if (resultStr != null&&resultStr.length()>0) {
				jsonObject = JSONObject.fromObject(resultStr);
			}
		return jsonObject;
	}
	
	/**
	 * getInfoToStr:(POST请求返回字符串 ——不带解密)
	 * @Author airufei
	 * @param parms
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String getInfoToString(JSONObject parms, String url)
			throws ClientProtocolException, IOException {
		    String resultStr = HttpXmlClient.post(url, parms);
		return resultStr;
	}
	
	/**
	 * post 请求
	 * @param parms
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getInfoToStr(Map<String, String> parms, String url)
			throws ClientProtocolException, IOException {
		String resultStr = HttpXmlClient.post(url, parms);
		return resultStr;
	}
	
	/**
	 * getDecryptDataToStr:(POST请求返回字符串 ——带解密)
	 * @Author airufei
	 * @param parms
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String getDecryptDataToStr(JSONObject parms, String url, String aeskey)
			throws ClientProtocolException, IOException {
		    String resultStr = HttpXmlClient.post(url, parms);
		    resultStr=resultStr.replace(" ", "+");
		    resultStr = CryptAES.AES_CRM_Decrypt(resultStr,aeskey);// 参数解密
		return resultStr;
	}
	
	public JSONObject getInfoToStr(String url)
			throws ClientProtocolException, IOException {
		JSONObject jsonObject = null;
		url = url.replaceAll("&", "%26");
		url = url.replaceAll(" ", "%20");
		String resultStr = HttpXmlClient.get(url);
		if (resultStr != null && resultStr.length() > 0) {
			jsonObject = JSONObject.fromObject(resultStr);
		}
		return jsonObject;
	}
}
