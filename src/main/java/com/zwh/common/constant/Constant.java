package com.zwh.common.constant;

public class Constant {
	public static final String MD5_CODE = "login_phb";// MD5盐值
	public static final String ROB_CUSTOMER_REDIS_KEY = "com:phb:channelCustomer:";// 抢单rediskey
	public static final String ROB_CUSTOMER_NUMBER_KEY = "com:phb:channelCustomer:number";// 记录客户经理当天内抢单的数目
	public static final int ROB_CUSTOMER_MAX_NUM = 100;// 客户经理当天内抢单的最大数目

	public static String getKeyWhenROB(final String key) {
		return ROB_CUSTOMER_REDIS_KEY + key;
	}

	public static String getKeyWhenGetROBList(final String key) {
		return ROB_CUSTOMER_NUMBER_KEY + key;
	}

}
