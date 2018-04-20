package com.zwh.common.util;

public enum ResponeVOEnum {
	// 格式：状态码|返回消息|显示消息

	SUCCESS_200(200, "成功", "成功"),

	SUCCESS_201(201, "重复", "重复"),

	SUCCESS_202(202, "请求成功", "求成功"),

	ERROR_403(403, "因为访问频繁，你已经被限制访问，稍后重试", "因为访问频繁，你已经被限制访问，稍后重试"),

	ERROR_404(404, "没有找到符合要求的订单", "没有找到符合要求的订单"),

	ERROR_405(405, "订单可能被释放或者回收", "订单可能被释放或者回收"),

	ERROR_406(406, "未上传身份证", "未上传身份证"),
	
	ERROR_407(407, "根据邀请码未找到营业部经理", "根据邀请码未找到营业部经理"),
	
	ERROR_408(408, "该客户经理未上传声纹信息", "该客户经理未上传声纹信息"),
	
	ERROR_409(409, "该客户经理的声纹信息未通过审核", "该客户经理的声纹信息未通过审核"),

	ERROR_500(500, "服务端异常", "服务器繁忙，请稍后再试"),
	/**
	 * ERROR_501:(登录超时，session过期)
	 */
	ERROR_501(501, "登录已过期,请重新登录", "登录已过期,请重新登录"),
	/**
	 * ERROR_502:(账号不存在)
	 */
	ERROR_502(502, "账号不存在", "账号不存在"),
	/**
	 * ERROR_503:(账号或者密码不正确)
	 */
	ERROR_503(503, "账号或者密码不正确", "账号或者密码不正确"),
	/**
	 * 验证码错误
	 */
	ERROR_504(504, "验证码错误", "验证码错误"),
	/**
	 * 短信验证码错误
	 */
	ERROR_505(505, "短信验证码错误", "短信验证码错误"),
	/**
	 * 账号已停用
	 */
	ERROR_506(506, "账号已停用", "账号已停用"),
	/**
	 * 账号异常，请修改密码
	 */
	ERROR_507(507, "账号异常，请修改密码", "账号异常，请修改密码"),
	/**
	 * ERROR_508:(您已在另一台设备登录，如需继续操作请重新登录)
	 */
	ERROR_508(508, "您已在另一台设备登录，如需继续操作请重新登录", "您已在另一台设备登录，如需继续操作请重新登录"),

	/**
	 * ERROR_509:(您的密码已被修改)
	 */
	ERROR_509(509, "您的密码已被修改，请重新登录", "您的密码已被修改，请重新登录"),
	/**
	 * ERROR_508:(您已在另一台设备登录，如需继续操作请重新登录)
	 */
	ERROR_510(510, "token校验失败", "服务器繁忙，请稍后再试"),

	ERROR_511(511, "未找到订单状态信息", "未找到订单状态信息"),
	
	ERROR_512(512, "第三方调用失败", "服务器繁忙，请稍后再试"),
	/**
	 * ERROR_301:(参数无法解密)
	 */
	ERROR_301(301, "参数无法解密", "服务器繁忙，请稍后再试"),
	/**
	 * ERROR_302:(缺少参数)
	 */
	ERROR_302(302, "缺少参数", "服务器繁忙，请稍后再试"),
	/**
	 * ERROR_303:(参数长度错误)
	 */
	ERROR_303(303, "参数长度错误", "服务器繁忙，请稍后再试"),
	/**
	 * ERROR_304:(参数格式错误)
	 */
	ERROR_304(304, "参数格式错误", "服务器繁忙，请稍后再试"),
	/**
	 * ERROR_305:(参数整体为空)
	 */
	ERROR_305(305, "参数整体为空", "服务器繁忙，请稍后再试"),
	/**
	 * ERROR_306:(参数校验异常)
	 */
	ERROR_306(306, "参数校验异常", "服务器繁忙，请稍后再试"),
	
	ERROR_307(307, "无效的邀请码", "服务器繁忙，请稍后再试"),
	
	ERROR_308(308, "无效的客户经理标识", "无效的客户经理标识");

	private int code;
	private String rspMsg;
	private String showMsg;

	ResponeVOEnum(int code, String rspMsg, String showMsg) {
		this.code = code;
		this.rspMsg = rspMsg;
		this.showMsg = showMsg;
	}

	public int getCode() {
		return code;
	}

	public String getRspMsg() {
		return rspMsg;
	}

	public String getShowMsg() {
		return showMsg;
	}
}
