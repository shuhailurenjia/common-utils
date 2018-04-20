/**
 * Project Name:phapp_v
 * File Name:Cache.java
 * Package Name:com.thinkgem.jeesite.common.utils
 * Date:2016年8月30日下午2:53:06
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
*/

package com.zwh.common.util;
/**
 * ClassName:Cache (缓存数据  )
 * Date:     2016年8月30日 下午2:53:06
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class Cache {

	private String key;//缓存ID   
    private Object value;//缓存数据   
    private long timeOut;//更新时间   
    private boolean expired; //是否终止   
    
    private  int cacheSize;//设置缓存的大小
    
    public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public Cache() {   
            super();   
    }   

    public Cache(String key, Object value, long timeOut, boolean expired) {   
            this.key = key;   
            this.value = value;   
            this.timeOut = timeOut;   
            this.expired = expired;   
    }   

    public String getKey() {   
            return key;   
    }   

    public long getTimeOut() {   
            return timeOut;   
    }   

    public Object getValue() {   
            return value;   
    }   

    public void setKey(String string) {   
            key = string;   
    }   

    public void setTimeOut(long l) {   
            timeOut = l;   
    }   

    public void setValue(Object object) {   
            value = object;   
    }   

    public boolean isExpired() {   
            return expired;   
    }   

    public void setExpired(boolean b) {   
            expired = b;   
    }   
}

