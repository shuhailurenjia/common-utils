package com.zwh.common.exception;

/**
 * Created by a on 15-10-29.
 * 所有异常的根类
 *
 * @author futao
 */
public class BaseException extends Exception {
	private static final long serialVersionUID = -2411472803795044576L;

	private int errorCode;
    private String msg;

    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(int errorCode, String msg) {
        super(msg);
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
