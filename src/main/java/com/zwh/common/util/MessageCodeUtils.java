package com.zwh.common.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class MessageCodeUtils {

	// 格式：状态码|返回消息|显示消息

	public static final String SUCCESS_200 = "200|成功|成功";

	public static final String SUCCESS_201 = "201|重复|重复";

	public static final String SUCCESS_202 = "202|请求成功|请求成功";

	public static final String ERROR_403 = "403|因为访问频繁，你已经被限制访问，稍后重试|因为访问频繁，你已经被限制访问，稍后重试";

	public static final String ERROR_404 = "404|没有找到符合要求的订单|该客户未在普惠帮占位，不可进件(404)";

	public static final String ERROR_405 = "405|订单可能被释放或者回收|该客户未在普惠帮占位，不可进件(405)";

	public static final String ERROR_406 = "406|未上传身份证|该客户经理未在普惠帮录入身份证号，请先补录后进件(406)";

	public static final String ERROR_407 = "407|没有找到符合要求的订单|您已经达到抢单上限";
	
	public static final String ERROR_408 = "408|没有找到符合要求的数据|没有找到符合要求的数据";
	
	public static final String ERROR_409 = "409|没有找到符合要求的数据|邀请码输入错误";

	public static final String ERROR_500 = "500|服务端异常|服务器繁忙，请稍后再试";
	/**
	 * ERROR_501:(登录超时，session过期)
	 */
	public static final String ERROR_501 = "501|登录已过期,请重新登录|登录已过期,请重新登录";
	/**
	 * ERROR_502:(账号不存在)
	 */
	public static final String ERROR_502 = "502|账号不存在|账号不存在";
	/**
	 * ERROR_503:(账号或者密码不正确)
	 */
	public static final String ERROR_503 = "503|账号或者密码不正确|账号或者密码不正确";
	public static final String ERROR_504 = "504|验证码错误|验证码错误";
	public static final String ERROR_505 = "505|短信验证码错误|短信验证码错误";
	public static final String ERROR_506 = "506|账号已停用|账号已停用";
	public static final String ERROR_507 = "507|账号异常，请修改密码|账号异常，请修改密码";
	/**
	 * ERROR_508:(您已在另一台设备登录，如需继续操作请重新登录)
	 */
	public static final String ERROR_508 = "508|您已在另一台设备登录，如需继续操作请重新登录|您已在另一台设备登录，如需继续操作请重新登录";

	/**
	 * ERROR_509:(您的密码已被修改)
	 */
	public static final String ERROR_509 = "509|您的密码已被修改，请重新登录|您的密码已被修改，请重新登录";
	/**
	 * ERROR_508:(您已在另一台设备登录，如需继续操作请重新登录)
	 */
	public static final String ERROR_510 = "510|token校验失败|服务器繁忙，请稍后再试";

	public static final String ERROR_511 = "511|未找到订单状态信息|该客户未在普惠帮占位，不可进件(511)";
	/**
	 * ERROR_301:(参数无法解密)
	 */
	public static final String ERROR_301 = "301|参数无法解密|服务器繁忙，请稍后再试";
	/**
	 * ERROR_302:(缺少参数)
	 */
	public static final String ERROR_302 = "302|缺少参数|服务器繁忙，请稍后再试";
	/**
	 * ERROR_303:(参数长度错误)
	 */
	public static final String ERROR_303 = "303|参数长度错误|服务器繁忙，请稍后再试";
	/**
	 * ERROR_304:(参数格式错误)
	 */
	public static final String ERROR_304 = "304|参数格式错误|服务器繁忙，请稍后再试";
	/**
	 * ERROR_305:(参数整体为空)
	 */
	public static final String ERROR_305 = "305|参数整体为空|服务器繁忙，请稍后再试";
	/**
	 * ERROR_306:(参数校验异常)
	 */
	public static final String ERROR_306 = "306|参数校验异常|服务器繁忙，请稍后再试";

	public static final String ERROR_307 = "307|参数校验异常|参数校验异常(307)";
	
	
	public static final String ERROR_310 = "310|该客户不是客户经理角色|当前功能只对客户经理开放";

	/**
	 * 获取状态码
	 *
	 * @param msg
	 * @return
	 */
	public static String getRspCode(String msg) {
		String[] src = msg.split("\\|");
		return src[0];
	}

	/**
	 * 获取信息
	 *
	 * @param msg
	 * @return
	 */
	public static String getRspMsg(String msg) {
		String[] src = msg.split("\\|");
		return src[1];
	}

	/**
	 * 获取显示信息
	 *
	 * @param msg
	 * @return
	 */
	public static String getShowMsg(String msg) {
		String[] src = msg.split("\\|");
		return src[2];
	}

	/**
	 * success200:(成功)
	 *
	 * @param map
	 *            接口返回的集合
	 * @param data
	 *            查询返回数据
	 * @return
	 * @Author airufei
	 */
	public static Map<String, Object> success200(Map<String, Object> map, Object data) {
		String[] aArray = {};
		String[] src = SUCCESS_200.split("\\|");
		map.put("rspCode", src[0]);
		map.put("rspMsg", src[1]);
		map.put("showMsg", src[2]);
		if (data != null) {
			map.put("data", data);
		} else {
			map.put("data", aArray);
		}

		return map;
	}

	/**
	 * success201:(重复)
	 *
	 * @param map
	 *            接口返回的集合
	 * @param data
	 *            查询返回数据
	 * @param showMsg
	 *            展示的消息
	 * @return
	 */
	public static Map<String, Object> success201(Map<String, Object> map, Map<String, Object> data, String showMsg) {
		MessageCodeUtils.success201(map, data);
		if (StringUtils.isNotBlank(showMsg)) {
			map.put("showMsg", showMsg);
		}
		return map;
	}

	/**
	 * success201:(重复)
	 *
	 * @param map
	 *            接口返回的集合
	 * @param data
	 *            查询返回数据
	 * @return
	 */
	public static Map<String, Object> success201(Map<String, Object> map, Object data) {
		String[] aArray = {};
		String[] src = SUCCESS_201.split("\\|");
		map.put("rspCode", src[0]);
		map.put("rspMsg", src[1]);
		map.put("showMsg", src[2]);
		if (data != null) {
			map.put("data", data);
		} else {
			map.put("data", aArray);
		}

		return map;
	}

	/**
	 * success202:(请求成功)
	 *
	 * @param map
	 *            接口返回的集合
	 * @param data
	 *            查询返回数据
	 * @return
	 */
	public static Map<String, Object> success202(Map<String, Object> map, Object data) {
		String[] aArray = {};
		String[] src = SUCCESS_202.split("\\|");
		map.put("rspCode", src[0]);
		map.put("rspMsg", src[1]);
		map.put("showMsg", src[2]);
		if (data != null) {
			map.put("data", data);
		} else {
			map.put("data", aArray);
		}

		return map;
	}

	/**
	 * success200:(成功)
	 *
	 * @param map
	 *            接口返回的集合
	 * @param data
	 *            查询返回数据
	 * @param showMsg
	 *            展示的消息
	 * @return
	 * @Author airufei
	 */
	public static Map<String, Object> success202(Map<String, Object> map, Map<String, Object> data, String showMsg) {
		MessageCodeUtils.success202(map, data);
		if (StringUtils.isNotBlank(showMsg)) {
			map.put("showMsg", showMsg);
		}
		return map;
	}

	/**
	 * validate304:(参数校验失败)
	 *
	 * @param map
	 *            接口返回的集合
	 * @param data
	 *            查询返回数据
	 * @return
	 */
	public static Map<String, Object> validate304(Map<String, Object> map, Object data) {
		String[] aArray = {};
		String[] src = ERROR_304.split("\\|");
		map.put("rspCode", src[0]);
		map.put("rspMsg", src[1]);
		map.put("showMsg", src[2]);
		if (data != null) {
			map.put("data", data);
		} else {
			map.put("data", aArray);
		}

		return map;
	}

	/**
	 * initParms:(初始化返回参数)
	 *
	 * @param map
	 * @return
	 * @Author airufei
	 */
	public static Map<String, Object> initParms(Map<String, Object> map) {
		String[] aArray = {};
		String[] src = SUCCESS_200.split("\\|");
		map.put("rspCode", src[0]);
		map.put("rspMsg", src[1]);
		map.put("showMsg", src[2]);
		map.put("data", aArray);
		return map;
	}

	/**
	 * success200:(成功)
	 *
	 * @param map
	 *            接口返回的集合
	 * @param data
	 *            查询返回数据
	 * @param showMsg
	 *            展示的消息
	 * @return
	 * @Author airufei
	 */
	public static Map<String, Object> success200(Map<String, Object> map, Map<String, Object> data, String showMsg) {
		MessageCodeUtils.success200(map, data);
		if (StringUtils.isNotBlank(showMsg)) {
			map.put("showMsg", showMsg);
		}
		return map;
	}

	/**
	 * 错误 500
	 *
	 * @param map
	 * @return
	 */
	public static Map<String, Object> error(Map<String, Object> map, String rspCode) {
		String[] src = null;
		if (StringUtils.isNotBlank(rspCode)) {
			if (rspCode.equals("301")) {
				src = ERROR_301.split("\\|");
			}
			if (rspCode.equals("302")) {
				src = ERROR_302.split("\\|");
			}
			if (rspCode.equals("303")) {
				src = ERROR_303.split("\\|");
			}
			if (rspCode.equals("304")) {
				src = ERROR_304.split("\\|");
			}
			if (rspCode.equals("305")) {
				src = ERROR_305.split("\\|");
			}
			if (rspCode.equals("306")) {
				src = ERROR_306.split("\\|");
			}
			if (rspCode.equals("307")) {
				src = ERROR_307.split("\\|");
			}
			if (rspCode.equals("310")) {
				src = ERROR_310.split("\\|");
			}
			if (rspCode.equals("403")) {
				src = ERROR_403.split("\\|");
			}
			if (rspCode.equals("404")) {
				src = ERROR_404.split("\\|");
			}
			if (rspCode.equals("405")) {
				src = ERROR_405.split("\\|");
			}
			if (rspCode.equals("406")) {
				src = ERROR_406.split("\\|");
			}
			if (rspCode.equals("407")) {
				src = ERROR_407.split("\\|");
			}
			if (rspCode.equals("408")) {
				src = ERROR_408.split("\\|");
			}
			if (rspCode.equals("409")) {
				src = ERROR_409.split("\\|");
			}
			if (rspCode.equals("500")) {
				src = ERROR_500.split("\\|");
			}
			if (rspCode.equals("501")) {
				src = ERROR_501.split("\\|");
			}
			if (rspCode.equals("502")) {
				src = ERROR_502.split("\\|");
			}
			if (rspCode.equals("503")) {
				src = ERROR_503.split("\\|");
			}
			if (rspCode.equals("504")) {
				src = ERROR_504.split("\\|");
			}
			if (rspCode.equals("505")) {
				src = ERROR_505.split("\\|");
			}
			if (rspCode.equals("506")) {
				src = ERROR_506.split("\\|");
			}
			if (rspCode.equals("507")) {
				src = ERROR_507.split("\\|");
			}
			if (rspCode.equals("508")) {
				src = ERROR_508.split("\\|");
			}
			if (rspCode.equals("509")) {
				src = ERROR_509.split("\\|");
			}
			if (rspCode.equals("510")) {
				src = ERROR_510.split("\\|");
			}
			if (rspCode.equals("511")) {
				src = ERROR_511.split("\\|");
			}

		}
		if (src != null) {
			map.put("rspCode", src[0]);
			map.put("rspMsg", src[1]);
			map.put("showMsg", src[2]);
			map.put("data", "");
		}
		return map;
	}

	/**
	 * 错误 自定义错误提示
	 *
	 * @param map
	 * @param showMsg
	 * @return
	 */
	public static Map<String, Object> error(Map<String, Object> map, String showMsg, String rsMsg, String rspCode) {
		if (StringUtils.isNotBlank(rspCode)) {
			MessageCodeUtils.error(map, rspCode);
		} else {
			rspCode = "500";
			MessageCodeUtils.error(map, rspCode);
		}
		if (StringUtils.isNotBlank(showMsg)) {
			map.put("showMsg", showMsg);
		}
		if (StringUtils.isNotBlank(rsMsg)) {
			map.put("rspMsg", rsMsg);
		}
		if (StringUtils.isNotBlank(rspCode)) {
			map.put("rspCode", rspCode);
		}
		return map;
	}

	/**
	 * 是否调用成功
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isSuccess(Map<String, Object> map) {
		if (map != null) {
			return "200".equals(map.get("rspCode"));
		}
		return false;
	}
}
