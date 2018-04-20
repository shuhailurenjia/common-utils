package com.zwh.common.enums;

/**
 *  是否显示“一键添加”按钮的枚举类
 * @author qinlei178030@credithc.com
 * @time 2018年3月13日
 * @version 0.0.1-SNAPSHOT
 */
public enum AddButton {
	
	show(1),
	hidden(2);
	
	
	int value;
	AddButton(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
