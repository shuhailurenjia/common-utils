package com.zwh.common.enums;

import java.text.MessageFormat;

/**
 * Created by dell-3020 on 2017/6/16.
 */
public enum InsuranceReturnCode {

    INSURANCE_RETURN_CODE_2000(2000, 3, "爬取成功", "授权成功"),
     INSURANCE_RETURN_CODE_2001(2001, 1, "登陆成功", "授权中"), 
   // INSURANCE_RETURN_CODE_2001(2001, 3, "爬取成功", "授权成功"),2018-4-12
    INSURANCE_RETURN_CODE_2002(2002, 2, "查询结果为空", "查询结果为空"),
    INSURANCE_RETURN_CODE_3001(3001, 0, "请输入图片验证码", "请输入图片验证码"),
    INSURANCE_RETURN_CODE_3002(3002, 0,"请输入短信验证码", "请输入短信验证码"),
    INSURANCE_RETURN_CODE_3003(3003, 0,"请输入图片和短信验证码", "请输入图片和短信验证码"),
    INSURANCE_RETURN_CODE_4000(4000, 0,"请求参数错误", "请求参数错误"),
    INSURANCE_RETURN_CODE_4001(4001, 0,"用户名或密码错误", "用户名或密码错误"),
    INSURANCE_RETURN_CODE_4002(4002, 0,"图片验证码错误", "图片验证码错误"),
    INSURANCE_RETURN_CODE_4003(4003, 0,"短信验证码错误", "短信验证码错误"),
    INSURANCE_RETURN_CODE_4004(4004, 0,"登陆过期,请重新登陆", "登陆过期,请重新登陆"),
    INSURANCE_RETURN_CODE_4005(4005, 0,"请求次数超过限制", "请求次数超过限制"),
    INSURANCE_RETURN_CODE_4006(4006, 0,"账户被锁定", "账户被锁定"),
    INSURANCE_RETURN_CODE_4007(4007, 0,"姓名不正确", "姓名不正确"),
    INSURANCE_RETURN_CODE_4008(4008, 0,"身份证号不正确", "身份证号不正确"),
    INSURANCE_RETURN_CODE_4013(4013, 0,"此用户不存在", "此用户不存在"),
    INSURANCE_RETURN_CODE_4017(4017, 0,"网站正在维护，服务不可用", "网站正在维护，服务不可用"),
    INSURANCE_RETURN_CODE_4024(4024, 0,"请登录网站进行身份认证", "请登录网站进行身份认证"),
    INSURANCE_RETURN_CODE_4027(4027, 0,"当前账户绑定手机号被多个账户使用，重新绑定前不能使用手机号登录等服务", "手机号绑定多个账户，请登录网站解绑"),
    INSURANCE_RETURN_CODE_4097(4097, 0,"授权入口维护中", "授权入口维护中"),
    INSURANCE_RETURN_CODE_4098(4098, 0,"授权入口维护中", "授权入口维护中"),
    INSURANCE_RETURN_CODE_4099(4099, 0,"登录未知错误", "登录未知错误"),
    INSURANCE_RETURN_CODE_5000(5000, 2, "内部错误", "内部错误，授权失败"),
    INSURANCE_RETURN_CODE_5001(5001, 0,"该保险公司抓取暂时不支持", "该保险公司抓取暂时不支持"),
    INSURANCE_RETURN_CODE_5003(5003, 2, "网络错误", "网络错误，授权失败"),
    INSURANCE_RETURN_CODE_6001(6001, 2, "三方系统维护", "三方系统维护");



    private  Integer returnCode;
    private  Integer localCode;
    private  String codeInfo;
    private String showInfo;

   private InsuranceReturnCode(Integer returnCode, Integer localCode, String codeInfo, String showInfo) {
        this.returnCode = returnCode;
        this.localCode = localCode;
        this.codeInfo = codeInfo;
        this.showInfo = showInfo;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public Integer getLocalCode() {
        return localCode;
    }

    public String getCodeInfo() {
        return codeInfo;
    }

    public String getShowInfo() {
        return showInfo;
    }

    @Override
    public String toString(){
       return MessageFormat.format("name:{0},returnCode-{1},localCode-{2},codeInfo-{3},showInfo-{4}",this.name(),this.getReturnCode(),this.getLocalCode(),this.getCodeInfo(),this.getShowInfo());
    }

    public static  InsuranceReturnCode parseCode(String returnCode){
        for (InsuranceReturnCode entity: InsuranceReturnCode.values()) {
            if (entity.getReturnCode().toString().compareTo(returnCode)==0){
                return  entity;
            }
        }
        return  null;
    }

}
