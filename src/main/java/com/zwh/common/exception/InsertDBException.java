package com.zwh.common.exception;

/**
 * Created by a on 15-10-30.
 */
public class InsertDBException extends BaseException {

	private static final long serialVersionUID = -1035990100621477640L;

	public InsertDBException() {
        super("入库操作出现错误");
    }

    public InsertDBException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
