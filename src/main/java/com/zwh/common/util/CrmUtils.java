package com.zwh.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class CrmUtils {
	private static Logger logger = LoggerFactory.getLogger(CrmUtils.class);
	/*
	 * 登录调用
	 */
	public static Map loginByCrm(String accountNumber,String accountPassword,String loginUrl,String aeskey) throws Exception{
		MapTools mt = new MapTools(); // map工具类
		Map rsMap = new HashMap();
		JSONObject params=new JSONObject();
		params.put("phonenumber", accountNumber);
		params.put("password", accountPassword);
		logger.info("===========weixin_markeing接口加密================");
		JSONObject jsonParams = new JSONObject();
		//System.out.println(params.toString());
		String strParams = CryptAES.AES_CRM_Encrypt(params.toString(),aeskey);
		jsonParams.put("jsonParams", strParams);
		// 调用crm
		String xml = HttpXmlClient.post(loginUrl,jsonParams);
		String backInfo=CryptAES.AES_CRM_Decrypt(xml,aeskey);
		rsMap = mt.parseJSON2Map(backInfo);
		logger.info("===========================weixin_markeing调用完成================");
		return rsMap;
	}
//	/**
//	 * @秘钥加密
//	 * @param pkey  time.pkey  时间戳+明文秘钥
//	 * @return 
//	 * @throws NoSuchAlgorithmException 
//	 */
//	public static String getToken(String pkey) throws  Exception { 
//		
//		CryptAES ca = new CryptAES();
//		String key = "Z8ZRQQhz9Mz0Z6Cf";  //16位key
//		//String key = "HKFesdh921HSdY2h";
//		
//		//AES加密
//		String encText = ca.AES_Encrypt(key, pkey);
//		//System.out.println("AES 加密:"+encText);
//		//System.out.println("AES 加密:"+encText.length());
//		
//		//MD5加密
////		String md5Text = EncoderHandler.encodeByMD5(encText);
////		System.out.println("MD5  加密:"+md5Text);
////		System.out.println("MD5  加密:"+md5Text.length());
//		
//		return encText;
//	}
	/*
	 * 修改密码调用
	 */
	public static Map updatePwd(String loginUrl,String userName,String oldPwd,String newPwd,String tokenkey) throws Exception{
		long time = new Date().getTime();// 生成当前时间戳
		String pkey = RandomUtil.random(5); // 随机生成
//		String token = getToken(pkey + time);
		String token = CryptAES.getToken(tokenkey,pkey + time);
		MapTools mt = new MapTools(); // map工具类
		Map rsMap = new HashMap();
		Map<String, String> params = new HashMap<String, String>();
		params.put("user_phone", userName);
		params.put("old_pwd", oldPwd);
		params.put("new_pwd", newPwd);
		params.put("new_pwd1", newPwd);
		params.put("time", String.valueOf(time));
		params.put("token", token);
		params.put("pkey", pkey);
		// 调用crm
		String xml = HttpXmlClient.post(loginUrl, params);
		rsMap = mt.parseJSON2Map(xml);
		return rsMap;
	}
	/*
	 * 离职接口调用
	 */
	public static Map dimission(String loginUrl,String userName,String tokenkey) throws Exception{
		long time = new Date().getTime();// 生成当前时间戳
		String pkey = RandomUtil.random(5); // 随机生成
//		String token = getToken(pkey + time);
		String token = CryptAES.getToken(tokenkey,pkey + time);
		MapTools mt = new MapTools(); // map工具类
		Map rsMap = new HashMap();
		Map<String, String> params = new HashMap<String, String>();
		params.put("phonenumber", userName);
		params.put("time", String.valueOf(time));
		params.put("token", token);
		params.put("pkey", pkey);
		// 调用crm
		String xml = HttpXmlClient.post(loginUrl, params);
		rsMap = mt.parseJSON2Map(xml);
		return rsMap;
	}
}
