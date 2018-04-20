package com.zwh.common.constant;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by a on 15-5-5.
 */
public class DefineValue {

    public static Map<Integer, String> registFromMap = Maps.newLinkedHashMap();

    static {
        registFromMap.put(0, "后台添加");
        registFromMap.put(1, "安卓");
        registFromMap.put(2, "ios");
//        registFromMap.put(3, "vip.laimiya");
//        registFromMap.put(4, "jn.laimiya");
//        registFromMap.put(5, "www.laimiya");
        registFromMap.put(100, "其他位置的入口");
        registFromMap.put(7, "crm后台");
        registFromMap.put(8, "admin后台");
        registFromMap.put(6, "邀请注册");
        registFromMap.put(999, "聚米");
    }
}
