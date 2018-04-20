package com.zwh.common.util;

import org.apache.commons.httpclient.NameValuePair;

import java.util.Map;

/**
 * Created by a on 15-10-29.
 */
public class DataTypeConvertUtil {

    public static NameValuePair[] mapConvertNameValuePairArray(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        NameValuePair[] nameValuePairs = new NameValuePair[map.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            nameValuePairs[i] = new NameValuePair(entry.getKey(), entry.getValue());
            i++;
        }
        return nameValuePairs;
    }
}
