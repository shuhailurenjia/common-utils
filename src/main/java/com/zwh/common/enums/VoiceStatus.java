package com.zwh.common.enums;

/**
 * 声纹状态信息的枚举
 * @author qinlei178030@credithc.com
 * @time 2018年3月21日
 * @version 0.0.1-SNAPSHOT
 */
public enum VoiceStatus {
	unUpload(0),
	upLoadedSucc(1),
	upLoadFail(2),
	
	unValidate(0),
	validateSucc(1),
	validateFail(2),
	validateing(3),
	
	unCheck(0),
	checkSucc(1),
	checkFail(2),
	checking(3);
	
	
	int value;
	VoiceStatus(int value){
		this.value = value;
	}
	
	public int value() {
		return value;
	}
	
}
