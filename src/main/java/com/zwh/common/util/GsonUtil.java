package com.zwh.common.util;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Gson工具类
 * Created by zhangchenglong on 2016/12/7.
 */
public class GsonUtil {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Gson getGson(){
        return getGson(DEFAULT_DATE_FORMAT);
    }

    public static Gson getGson(String dateFormat){
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (StringUtils.isBlank(dateFormat)) {
            gsonBuilder.setDateFormat(DEFAULT_DATE_FORMAT);
        } else {
            gsonBuilder.setDateFormat(dateFormat);
        }
        gsonBuilder.serializeNulls();
        gsonBuilder.disableHtmlEscaping();
        Gson gson = gsonBuilder.create();
        return gson;
    }

    public static String toJson(Object src) {
        if (src!=null && src instanceof String) {
            return (String)src;
        }
        Gson gson = getGson();
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json,Class<T> clazz) {
        Gson gson = getGson();
        return gson.fromJson(json,clazz);
    }

    public static <T> T fromJson(String json,Type type) {
        Gson gson = getGson();
        return gson.fromJson(json,type);
    }

    public static String getString(JsonObject jsonObject,String memberName) {
        String value = null;
        JsonElement ele = jsonObject.get(memberName);
        if (ele!= null) {
            if (ele instanceof JsonArray || ele instanceof JsonObject) {
                value = toJson(ele);
            } else {
                value = ele.getAsString();
            }
        }
        return value;
    }

    public static Float getFloat(JsonObject jsonObject,String memberName) {
        Float value = null;
        JsonElement ele = jsonObject.get(memberName);
        if (ele!= null) {
            value = ele.getAsFloat();
        }
        return value;
    }

    public static Integer getInteger(JsonObject jsonObject,String memberName) {
        Integer value = null;
        JsonElement ele = jsonObject.get(memberName);
        if (ele!= null) {
            value = ele.getAsInt();
        }
        return value;
    }

    public static Boolean getBoolean(JsonObject jsonObject,String memberName) {
        Boolean value = null;
        JsonElement ele = jsonObject.get(memberName);
        if (ele!= null) {
            value = ele.getAsBoolean();
        }
        return value;
    }

    public static JsonArray getJsonArray (JsonObject jsonObject,String memberName) {
        JsonElement ele = getJsonElement(jsonObject, memberName);
        JsonArray array = null;
        if (ele!=null) {
            if (ele.isJsonPrimitive()) {
                array = getJsonArray(ele.getAsString());
            } else {
                array = ele.getAsJsonArray();
            }
        }
        return array;
    }

    public static JsonArray getJsonArray(String json) {
        return getJsonElement(json).getAsJsonArray();
    }

    public static JsonObject getJsonObject (JsonObject jsonObject,String memberName) {
        JsonElement ele = getJsonElement(jsonObject, memberName);
        JsonObject obj = null;
        if (ele!=null) {
            if (ele.isJsonPrimitive()) {
                obj = getJsonObject(ele.getAsString());
            } else {
                obj = ele.getAsJsonObject();
            }
        }
        return obj;
    }

    public static JsonObject getJsonObject(String json) {
        return getJsonElement(json).getAsJsonObject();
    }

    public static JsonElement getJsonElement (JsonObject jsonObject,String memberName) {
        JsonElement ele = jsonObject.get(memberName);
        return ele;
    }

    public static JsonElement getJsonElement (String json) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(json);
    }

    /**
     * Gson转为Fastjson JSONObject
     * FastJson JSONObject 实现了序列化
     * @param jsonObject
     * @return
     */
    public static JSONObject toFastjson(JsonObject jsonObject) {
        return JSONObject.parseObject(jsonObject.toString());
    }

    /**
     * Gson转为Fastjson JSONArray
     * FastJson JSONArray 实现了序列化
     * @param jsonArray
     * @return
     */
    public static JSONArray toFastjsonArray(JsonObject jsonArray) {
        return JSONObject.parseArray(jsonArray.toString());
    }


}




