package com.zwh.common.exception;

/**
 * Created by a on 15-10-30.
 */
public class SourceLockedException extends BaseException {

	private static final long serialVersionUID = -5249931643283217710L;

	public SourceLockedException() {
        super("有资源被锁");
    }

    public SourceLockedException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
