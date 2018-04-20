package com.zwh.common.exception;

/**
 * Created by a on 15-10-29.
 * 牛豆不够的异常
 *
 * @author futao
 */
public class ScoreNotEnoughException extends BaseException {

	private static final long serialVersionUID = -2265295202056745943L;

	public ScoreNotEnoughException() {
        super("牛豆余额不足！");
    }

    public ScoreNotEnoughException(String msg) {
        super(msg);
    }

    public ScoreNotEnoughException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
