package com.zwh.common.util;

import org.apache.commons.lang3.StringUtils;

import com.zwh.common.enums.ReturnCode;

import java.util.Map;

public class ReturnCodeUtils {


	/**
	 * success200:(成功)
	 * @Author airufei
	 * @param map 接口返回的集合
	 * @param data 查询返回数据
	 * @return
	 */
	public static Map<String, Object> success200(Map<String, Object> map,Object data) {
		String[] aArray = {};
		map.put("rspCode", ReturnCode.SUCCESS.getCode());
		map.put("rspMsg",ReturnCode.SUCCESS.getResponseMsg());
		map.put("showMsg",ReturnCode.SUCCESS.getShowMsg());
		if(data!=null)
		{
			map.put("data", data);
		}else
		{
			map.put("data", aArray);
		}
		
		return map;
	}

	/**
	 * initParms:(初始化返回参数)
	 * @Author airufei
	 * @param map
	 * @return
	 */
	public static Map<String, Object> initParms(Map<String, Object> map) {
		String[] aArray = {};
		ReturnCode success = ReturnCode.SUCCESS;
		map.put("rspCode",success.getCode());
		map.put("rspMsg", success.getResponseMsg());
		map.put("showMsg",success.getShowMsg());
		map.put("data", aArray);
		return map;
	}
	
	/**
	 * success200:(成功)
	 * @Author airufei
	 * @param map 接口返回的集合
	 * @param data 查询返回数据
	 * @param showMsg  展示的消息
	 * @return
	 */
	public static Map<String, Object> success200(Map<String, Object> map,Map<String, Object> data, String showMsg) {
		ReturnCodeUtils.success200(map,data);
		if (StringUtils.isNotBlank(showMsg)) {
			map.put("showMsg", showMsg);
		}
		return map;
	}
	
	/**
	 * 错误 500
	 * @param map
	 * @return
	 */
	public static Map<String, Object> error(Map<String, Object> map,String rspCode) {
		String[] aArray = {};
		String[] src =null;
		if(StringUtils.isNotBlank(rspCode))
		{
			ReturnCode returnCode = ReturnCode.parseCode(Integer.parseInt(rspCode));
			if (returnCode!=null){
				map.put("rspCode", returnCode.getCode());
				map.put("rspMsg", returnCode.getResponseMsg());
				map.put("showMsg",returnCode.getShowMsg());
			}
		}

		return map;
	}
	/**
	 * 错误 自定义错误提示
	 * @param map
	 * @param showMsg
	 * @return
	 */
	public static Map<String, Object> error(Map<String, Object> map, String showMsg, String rsMsg,String rspCode) {
		if(StringUtils.isNotBlank(rspCode))
		{
			ReturnCodeUtils.error(map, rspCode);
		}else
		{
			rspCode=ReturnCode.SYS_ERROR_500.getCode().toString();
			ReturnCodeUtils.error(map,rspCode);
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

}
