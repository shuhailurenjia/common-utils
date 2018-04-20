package com.zwh.common.exception;

/**
 * Created by a on 15-10-30.
 */
public class UnExpectException extends BaseException {

	private static final long serialVersionUID = -1525935190108639997L;

	public UnExpectException() {
        super("接口请求出现异常");
    }

    public UnExpectException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
