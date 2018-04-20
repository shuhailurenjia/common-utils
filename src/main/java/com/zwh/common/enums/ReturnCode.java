package com.zwh.common.enums;

import java.text.MessageFormat;

/**
 * Created by llm on 17-06-05
 */
public enum ReturnCode {

    /*
    200，正确
    3**，参数错误
    4**，业务逻辑错误
    5**，服务端错误
    */

    SUCCESS(200, "成功", "成功"),
    PARAM_ERROR_301(301, "参数解密错误", "服务器异常，请重试"),
    PARAM_ERROR_302(302, "缺少参数", "服务器异常，请重试"),
    PARAM_ERROR_303(303, "参数长度错误", "服务器异常，请重试"),
    PARAM_ERROR_304(304, "参数格式错误", "服务器异常，请重试"),
    PARAM_ERROR_305(305, "参数整体为空", "服务器异常，请重试"),
    PARAM_ERROR_306(306, "参数校验异常", "服务器异常，请重试"),


    SYS_ERROR_403(403,"因为访问频繁，你已经被限制访问，稍后重试","因为访问频繁，你已经被限制访问，稍后重试"),


    SYS_ERROR_500(500,"服务端异常","服务器繁忙，请稍后再试"),
    SYS_ERROR_501(501,"登录已过期,请重新登录","登录已过期,请重新登录"),
    SYS_ERROR_502(502,"账号不存在","账号不存在"),
    SYS_ERROR_503(503,"账号或者密码不正确","账号或者密码不正确"),
    SYS_ERROR_504(504,"验证码错误","验证码错误"),
    SYS_ERROR_505(505,"短信验证码错误","短信验证码错误"),
    SYS_ERROR_506(506,"账号已停用","账号已停用"),
    SYS_ERROR_507(507,"账号异常，请修改密码","账号异常，请修改密码"),
    SYS_ERROR_508(508,"您已在另一台设备登录，如需继续操作请重新登录","您已在另一台设备登录，如需继续操作请重新登录"),
    SYS_ERROR_509(509,"您的密码已被修改，请重新登录","您的密码已被修改，请重新登录"),
    SYS_ERROR_510(510,"token校验失败","服务器繁忙，请稍后再试");

    private Integer code;
    private String responseMsg;
    private String showMsg;

    private ReturnCode(Integer code, String responseMsg, String showMsg) {
        this.code = code;
        this.responseMsg = responseMsg;
        this.showMsg = showMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public Integer getCode() {
        return this.code;
    }


    @Override
    public String toString() {
        return MessageFormat.format("name:{0},code:{1},responseMsg:{2},showMsg:{3}", this.name(), this.code, this.responseMsg, this.showMsg);
    }

    public static ReturnCode parseCode(Integer code){
        for (ReturnCode returnCode: ReturnCode.values()) {
            if (returnCode.getCode().compareTo(code)==0){
                return  returnCode;
            }
        }
        return  null;
    }

//    public static void main(String[] args) {
//        System.out.println(ReturnCode.parseCode(200));
//    }
}
