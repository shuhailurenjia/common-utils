package com.zwh.common.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseVoUtils {
	private static final String CODE = "rspCode";
	private static final String RSP_mSG = "rspMsg";
	private static final String SHOW_MSG = "showMsg";
	private static final String DATA = "data";
	private final Map<String, Object> map;

	public ResponseVoUtils() {
		map = new HashMap<>(4);
	}

	public static ResponseVoUtils create() {
		return new ResponseVoUtils();
	}

	public ResponseVoUtils success() {
		map.put(CODE, ResponeVOEnum.SUCCESS_200.getCode());
		map.put(RSP_mSG, ResponeVOEnum.SUCCESS_200.getRspMsg());
		map.put(SHOW_MSG, ResponeVOEnum.SUCCESS_200.getShowMsg());
		map.put(DATA, "");
		return this;
	}

	public ResponseVoUtils error(ResponeVOEnum code) {
		map.put(CODE, code.getCode());
		map.put(RSP_mSG, code.getRspMsg());
		map.put(SHOW_MSG, code.getShowMsg());
		map.put(DATA, "");
		return this;
	}

	public ResponseVoUtils addRspMsg(String msg) {
		map.put(RSP_mSG, msg);
		return this;
	}

	public ResponseVoUtils addShowMsg(String msg) {
		map.put(SHOW_MSG, msg);
		return this;
	}

	public ResponseVoUtils addData(Object data) {
		map.put(DATA, data);
		return this;
	}

	public Map<String, Object> toResult() {
		return this.map;
	}
}