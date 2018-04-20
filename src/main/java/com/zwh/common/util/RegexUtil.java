package com.zwh.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by a on 15-3-28.
 */
public class RegexUtil {


    /**
     * 用户注册时候的匹配，只匹配汉字、半角英文、数字、下划线
     *
     * @param userName
     * @return
     */
    public static boolean isUserNameLegal(String userName) {

        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$");
        Matcher matcher = p.matcher(userName);
        return matcher.find();
    }
}
