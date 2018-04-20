package com.zwh.common.enums;

/**
 * 前端展示客户状态的枚举类
 * @author qinlei178030@credithc.com
 * @time 2018年3月13日
 * @version 0.0.1-SNAPSHOT
 */
public enum CustomerStatus {
	//前端展示的客户状态 ： 0=已占位 ,1=已释放 ,2=已收回 ,3=未被抢
	unkown(-1),//未知状态
	occupied(0),
	release(1),
	backed(2),
	unrobbed(3);
	
	
	int value;
	
	CustomerStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
