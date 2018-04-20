/**
 * Project Name:phapp_v
 * File Name:UrlService.java
 * Package Name:com.thinkgem.jeesite.modules.interfaction.common
 * Date:2016年9月7日下午5:25:18
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
 */

package com.zwh.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zwh.common.util.GetSystemUrl;
import com.zwh.common.util.MessageCodeUtils;

import net.sf.json.JSONObject;

/**
 * ClassName:UrlService (获取固定的Url) Date:2017年5月2日09:43:04
 * 
 * @Author LCY
 * @Version 1.0
 * @see
 */
public class PhappUrlUtil {
	
	private static Logger logger=LoggerFactory.getLogger(PhappUrlUtil.class);
	
	/**
	 * getCreditUrl:(获取征信页面url)
	 * 
	 * @Author LCY
	 * @param parms
	 * @param result
	 * @return
	 */
	public static  Map<String,Object> getUrl(JSONObject parms,String serverName) {
		Map<String,Object> result = new HashMap<String,Object>();
	    MessageCodeUtils.initParms(result);
		String url = "";
		String creditUrl="";
		String crmId = parms.getString("crmId");
		int type = parms.getInt("type");
		switch (type) {
		case 1000:
			url = getToolUrl(crmId,serverName);
			creditUrl=getCreditUrl(crmId,serverName);
			break;
		}
		JSONObject params = new JSONObject();
		params.put("url", url);
		params.put("creditUrl", creditUrl);
		MessageCodeUtils.success200(result, params);
		return result;
	}
	
	/**
	 * getCreditUrl:(获取工具页面url)
	 * 
	 * @Author LCY
	 * @return
	 */
	private static String getToolUrl(String crmId,String serverName) {
		String creditUrl = serverName; 
		logger.info("获取征信页面url："+creditUrl);
		creditUrl = creditUrl + "/phb/web/tool/toolIndex?crmId="+crmId;
		return creditUrl;
	}
	
	/**
	 * getCreditUrl:(获取工具页面url)
	 * 
	 * @Author LCY
	 * @return
	 */
	private static String getCreditUrl(String crmId,String serverName) {
		String creditUrl = serverName;
		logger.info("获取征信页面url："+creditUrl);
		creditUrl = creditUrl + "/phb/web/credit/credit?crmId="+crmId+"&flag="+1;;
		return creditUrl;
	}

}
