package com.zwh.common.exception;

/**
 * Created by a on 15-10-30.
 */
public class ParameterWrongException extends BaseException {
	private static final long serialVersionUID = -5819649626483473575L;

	public ParameterWrongException() {
        super("请求参数错误");
    }

    public ParameterWrongException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
