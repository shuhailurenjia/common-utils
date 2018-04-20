package com.zwh.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-6-5 Time: 上午10:30 To change this template use File | Settings | File Templates.
 */
public class JsonSerializeUtil {

    private final static Logger logger = LoggerFactory.getLogger(JsonSerializeUtil.class);

    /**
     * json字符串反序列化对象
     */
    public static <T> T jsonToObject(String value, Class<T> c) {
        T o = null;
        if (StringUtils.isEmpty(value)) {
            return o;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            o = mapper.readValue(value, c);
        } catch (IOException e) {
            logger.error(e.getMessage() + "-jsonString:" + value + "\tclassName:" + c.getName(), e.getCause());
            return null;
        }
        return o;
    }


    /**
     * json字符串反序列化对象
     */
    public static <T> List<T> jsonToList(String value, Class<T> c) {
        List<T> beanList = new ArrayList<T>();

        if (StringUtils.isEmpty(value)) {
            return beanList;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();

            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, c);

            beanList = mapper.readValue(value, javaType);
        } catch (IOException e) {
            logger.error(e.getMessage() + "-jsonString:" + value + "\tclassName: " + c.getClass(), e.getCause());
            return null;
        }
        return beanList;
    }

    public static <T> List<T> jsonToLinkList(String value, Class<T> c) {
        List<T> beanList = new ArrayList<T>();

        if (StringUtils.isEmpty(value)) {
            return beanList;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
//			beanList = mapper.readValue(value, new TypeReference<List<T>>(){});
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, c);

            beanList = mapper.readValue(value, javaType);
        } catch (IOException e) {
            logger.error(e.getMessage() + "-jsonString:" + value + "\tclassName: " + c.getClass(), e.getCause());
            return null;
        }
        return beanList;
    }

    /**
     * 对象序列化json
     */
    public static String objectToJson(Object o) {
        if (o == null) {
            return null;
        }
        ObjectMapper om = new ObjectMapper();
        Writer w = new StringWriter();
        String json = null;
        try {
            om.writeValue(w, o);
            json = w.toString();
            w.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
            return null;
        }
        return json;
    }

    public static <T> T jsonToObject(String value, TypeReference typeReference) {
        T o = null;
        if (StringUtils.isEmpty(value)) {
            return o;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            o = mapper.readValue(value, typeReference);
        } catch (IOException e) {
            logger.error(e.getMessage() + "-jsonString:" + value + "\tclassName:" + typeReference.getType().toString(), e.getCause());
            return null;
        }
        return o;
    }
}
