/**
 * Project Name:phapp_v
 * File Name:ValidateInterfaceParms.java
 * Package Name:com.thinkgem.jeesite.common.utils
 * Date:2016年8月17日下午3:53:24
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
 */

package com.zwh.common.util;

import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

/**
 * ClassName: ValidateInterfaceParms(接口参数校验)
 * 
 * @Version 1.0
 * @Author: airufei date: 2016年8月17日 下午3:53:27
 */

public class ValidateUtil {

	/**
	 * getdecryptBasedDes:(获取解密数据)
	 * 
	 * @Author airufei
	 * @param jsonParams
	 * @param result
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JSONObject getdecryptBasedDes(String jsonParams, Map result) {
		JSONObject pm =null;
		if (jsonParams != null&&jsonParams.length()>0&&!"null".equals(jsonParams)) {
			jsonParams = DESCryptUtil.decryptBasedDes(jsonParams);// 参数解密
			if (jsonParams == null) {
				MessageCodeUtils.error(result, null, null, "301");
			}
		    pm = JSONObject.fromObject(jsonParams);
		}else
		{
			MessageCodeUtils.error(result, null, null, "305");
		}
		return pm;

	}

	
	/**
	 * getdecryptBasedDes:(获取CRM调用接口解密数据)
	 * 
	 * @Author airufei
	 * @param jsonParams
	 * @param result
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JSONObject getCrmdecryptBasedDes(String jsonParams, Map result,String aeskey) {
		JSONObject pm =null;
		if (jsonParams != null&&jsonParams.length()>0&&!"null".equals(jsonParams)) {
			jsonParams=jsonParams.replace(" ", "+");
			jsonParams = CryptAES.AES_CRM_Decrypt(jsonParams,aeskey);// 参数解密
			if (jsonParams == null) {
				MessageCodeUtils.error(result, null, null, "301");
			}
		    pm = JSONObject.fromObject(jsonParams);
		}else
		{
			MessageCodeUtils.error(result, null, null, "305");
		}
		return pm;

	}

	/**
	 * validateCommonParms:(接口公共参数校验)
	 * 
	 * @Author airufei
	 * @param pm
	 *            接口入参数（明文）
	 * @param jsonParams
	 *            接口入参数（密文）
	 * @param result
	 *            校验结果
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JSONObject validateCommonParms(String jsonParams, Map result) {
		JSONObject pm = null;
		boolean res = true;
		String msg = "参数校验成功！";
		String code = "200";
		pm = getdecryptBasedDes(jsonParams, result);
		if (pm != null) {
			if (!pm.has("deviceCode")) {
				msg = "参数deviceCode不存在";
				res = false;
				code = "302";
			}
			if (!pm.has("sourceType")) {
				msg = "参数sourceType不存在";
				res = false;
				code = "302";
			}
			if (!pm.has("version")) {
				msg = "参数version不存在";
				res = false;
				code = "302";
			}
			if (!pm.has("tokenId")) {
				msg = "参数tokenId不存在";
				res = false;
				code = "302";
			}
			if (res) {
                String deviceCode = StringUtils.objectToString(pm
						.get("deviceCode"));
                String tokenId = StringUtils.objectToString(pm.get("tokenId"));
                String sourceType = StringUtils.objectToString(pm
						.get("sourceType"));
                String version = StringUtils.objectToString(pm.get("version"));
				if (deviceCode == null
						|| (deviceCode != null && deviceCode.length() < 1)) {
					msg = "tokenId不能为空";
					res = false;
					code = "303";
				}
				if (sourceType == null
						|| (sourceType != null && sourceType.length() < 1)) {
					msg = "sourceType不能为空";
					res = false;
					code = "303";
				}
				if (tokenId == null
						|| (tokenId != null && tokenId.length() < 1)) {
					msg = "tokenId不能为空";
					res = false;
					code = "303";
				}
				if (version == null
						|| (version != null && version.length() < 1)) {
					msg = "version不能为空";
					res = false;
					code = "303";
				}
			}
		}else
		{
			MessageCodeUtils.error(result, null, null, "306");
		}
		MessageCodeUtils.error(result, null, msg, code);
		if (!res) {
			pm = null;
		}
		return pm;
	}
	
	/**
	 * <br>描 述：公共参数校验
	 * <br>作 者：xieyj
	 * <br>历 史: (版本) 作者 时间 注释
	 * @param jsonParams
	 * @param resultMsg
	 */
	public static void validateCommonParams(JSONObject jsonParams, Map<String, Object> resultMsg) {
		
		boolean res = true;
		String code = "200";
		StringBuilder msgSb = new StringBuilder();
		
		if (!jsonParams.has("deviceCode")) {
			msgSb.append("参数deviceCode不存在,");
			res = false;
			code = "302";
		}
		
		if (!jsonParams.has("sourceType")) {
			msgSb.append("参数sourceType不存在,");
			res = false;
			code = "302";
		}
		
		if (!jsonParams.has("version")) {
			msgSb.append("参数version不存在,");
			res = false;
			code = "302";
		}
		
		if (!jsonParams.has("tokenId")) {
			msgSb.append("参数tokenId不存在,");
			res = false;
			code = "302";
		}
		
		if (res) {
            String deviceCode = StringUtils.objectToString(jsonParams.get("deviceCode"));
            String tokenId = StringUtils.objectToString(jsonParams.get("tokenId"));
            String sourceType = StringUtils.objectToString(jsonParams.get("sourceType"));
            String version = StringUtils.objectToString(jsonParams.get("version"));
			
			if (StringUtils.isBlank(deviceCode)) {
				msgSb.append("tokenId不能为空,");
				res = false;
				code = "303";
			}
			
			if (StringUtils.isBlank(sourceType)) {
				msgSb.append("sourceType不能为空,");
				res = false;
				code = "303";
			}
			
			if (StringUtils.isBlank(tokenId)) {
				msgSb.append("tokenId不能为空,");
				res = false;
				code = "303";
			}
			
			if (StringUtils.isBlank(version)) {
				msgSb.append("version不能为空,");
				res = false;
				code = "303";
			}
		}
		
		if (!res) {
			MessageCodeUtils.error(resultMsg, null, msgSb.toString(), code);
		}
	}

	/**
	 * 获取客户进件详情信息 参数验证
	 * 
	 * @param pm
	 * @return
	 */
	public static JSONObject validateCustomerDetailParms(JSONObject pm) {
		JSONObject json = new JSONObject();
		boolean res = true;
		String msg = "参数校验成功！";
		String code = "200";
		if (!pm.has("keyword")) {
			msg = "参数keyword不存在";
			res = false;
			code = "302";
		}
		if (!pm.has("phonenumber")) {
			msg = "参数phonenumber不存在";
			res = false;
			code = "302";
		}
		if (!pm.has("clientMesId")) {
			msg = "参数clientMesId不存在";
			res = false;
			code = "302";
		}
		json.put("msg", msg);
		json.put("res", res);
		json.put("code", code);
		return json;
	}


	/**
	 * 获取客户列表参数验证
	 * 
	 * @param pm
	 * @return
	 */
	public static boolean validateParms(JSONObject pm) {
		boolean res = true;
		if (!pm.has("pageNo")) {
			res = false;
		}
		if (!pm.has("state")) {
			res = false;
		}
		if (!pm.has("keyword")) {
			res = false;
		}
		if (!pm.has("status")) {
			res = false;
		}
		if (!pm.has("loan_time")) {
			res = false;
		}
		if (!pm.has("repay_date")) {
			res = false;
		}
		if (!pm.has("order_time")) {
			res = false;
		}
		return res;
	}

	/**
	 * validateLoginParms:(登录参数校验)
	 * 
	 * @Author airufei
	 * @param pm
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean validateLoginParms(JSONObject pm, Map result) {
		boolean res = true;
		String msg = "参数校验成功！";
		String code = "200";
		if (!pm.has("accountPassword")) {
			msg = "参数accountPassword不存在";
			res = false;
			code = "302";
		}
		if (!pm.has("accountNumber")) {
			msg = "参数accountNumber不存在";
			res = false;
			code = "302";
		}
		if (!pm.has("timestamp")) {
			msg = "参数timestamp不存在";
			res = false;
			code = "302";
		}
        String timestamp = StringUtils.objectToString(pm.get("timestamp"));
        String accountNumber = StringUtils.objectToString(pm
				.get("accountNumber"));
        String accountPassword = StringUtils.objectToString(pm
				.get("accountPassword"));
		if (timestamp == null || (timestamp != null && timestamp.length() < 1)) {
			msg = "timestamp不能为空";
			res = false;
			code = "303";
		}
		if (accountNumber == null
				|| (accountNumber != null && accountNumber.length() < 1)) {
			msg = "accountNumber不能为空";
			res = false;
			code = "303";
        } else if (!StringUtils.isMobile(accountNumber)) {
			msg = "accountNumber格式不正确";
			res = false;
			code = "304";
		}
		if (accountPassword == null
				|| (accountPassword != null && accountPassword.length() < 1)) {
			msg = "accountPassword不能为空";
			res = false;
			code = "303";
		}
		MessageCodeUtils.error(result, null, msg, code);
		return res;
	}
	
	
	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_PHONE ="^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	 /**
     * 校验手机号
     * 
     * @param phone
     * @return 校验通过返回true，否则返回false
     */
	public static boolean isPhone(String phone){
		return Pattern.matches(REGEX_PHONE, phone);
	}
	
	

	 /**
     * 校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
}
