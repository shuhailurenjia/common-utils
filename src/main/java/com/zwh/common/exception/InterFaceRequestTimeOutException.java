package com.zwh.common.exception;

/**
 * Created by a on 15-10-30.
 */
public class InterFaceRequestTimeOutException extends BaseException {

	private static final long serialVersionUID = 7756633985181751045L;

	public InterFaceRequestTimeOutException() {
        super("接口请求超时");
    }
}
