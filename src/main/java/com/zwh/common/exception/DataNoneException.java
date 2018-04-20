package com.zwh.common.exception;

/**
 * Created by a on 15-10-30.
 */
public class DataNoneException extends BaseException {
	private static final long serialVersionUID = -4191883408824288442L;

	public DataNoneException() {
        super("已经没有数据了");
    }

    public DataNoneException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
