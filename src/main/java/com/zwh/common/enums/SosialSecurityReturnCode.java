package com.zwh.common.enums;

import java.text.MessageFormat;

/**
 * 网银爬取返回码
 * Created by dell-3020 on 2017/6/16.
 */
public enum SosialSecurityReturnCode {

    RETURN_CODE_2000(2000, 3, "爬取成功", "授权成功"),
    RETURN_CODE_2001(2001, 1, "授权成功", "授权中"),
    RETURN_CODE_2002(2002, 2, "查询结果为空", "查询结果为空"),
    RETURN_CODE_3001(3001, 0, "请输入图片验证码", "请输入图片验证码"),
    RETURN_CODE_3002(3002, 0, "请输入短信验证码", "请输入短信验证码"),
    RETURN_CODE_3003(3003, 0, "请输入图片和短信验证码", "请输入图片和短信验证码"),
    RETURN_CODE_3004(3004, 0, "请输入查询密码(社保卡金融账户后6位！)", "请输入查询密码(社保卡金融账户后6位！)"),
    RETURN_CODE_4000(4000, 0, "请求参数错误", "请求参数错误"),
    RETURN_CODE_4001(4001, 0, "用户名或密码错误", "用户名或密码错误"),
    RETURN_CODE_4002(4002, 0, "图片验证码错误", "图片验证码错误"),
    RETURN_CODE_4003(4003, 0, "短信验证码错误", "短信验证码错误"),
    RETURN_CODE_4004(4004, 0, "登陆过期,请重新登陆", "登陆过期,请重新登陆"),
    RETURN_CODE_4005(4005, 0, "请求次数超过限制", "请求次数超过限制"),
    RETURN_CODE_4006(4006, 0, "账户被锁定", "账户被锁定"),
    RETURN_CODE_4007(4007, 0, "姓名不正确", "姓名不正确"),
    RETURN_CODE_4008(4008, 0, "身份证号不正确", "身份证号不正确"),
    RETURN_CODE_4009(4009, 0, "首次登陆需要修改密码", "首次登陆需要修改密码"),
    RETURN_CODE_4010(4010, 0, "查询密码错误(社保卡金融账户后6位！)", "查询密码错误(社保卡金融账户后6位！)"),
    RETURN_CODE_4011(4011, 0, "医保（社保）卡号或身份证号错误", "医保（社保）卡号或身份证号错误"),
    RETURN_CODE_4012(4012, 0, "账号的参保状态为暂停参保", "账号的参保状态为暂停参保"),
    RETURN_CODE_4013(4013, 0, "此用户不存在", "此用户不存在"),
    RETURN_CODE_4014(4014, 0, "登录失败,根据账号查出多条用户信息", "登录失败,根据账号查出多条用户信息"),
    RETURN_CODE_4015(4015, 0, "未发现社保信息，区县入口选择错误或网络异常", "未发现社保信息，区县入口选择错误或网络异常"),
    RETURN_CODE_4016(4016, 0, "账号没有访问社保缴费信息权限或未认证", "账号没有访问社保缴费信息权限或未认证"),
    RETURN_CODE_4017(4017, 0, "网站正在维护，服务不可用", "网站正在维护，服务不可用"),
    RETURN_CODE_4018(4018, 0, "网上注册登记使用的手机号码与社保系统中登记的手机号码不一致", "网上注册登记使用的手机号码与社保系统中登记的手机号码不一致"),
    RETURN_CODE_4019(4019, 0, "对不起，您当前的查询密码安全级别过低，为了保证您的个人信息安全， 请输入联名卡号进行登录，给您带来的不便请您谅解", "对不起，您当前的查询密码安全级别过低，为了保证您的个人信息安全， 请输入联名卡号进行登录，给您带来的不便请您谅解"),
    RETURN_CODE_4020(4020, 0, "需要您对用户注册信息进行补充和验证", "需要您对用户注册信息进行补充和验证"),
    RETURN_CODE_4021(4021, 0, "社保个人代码为10位个人代码,如不足十位前面补0，邗江参保人员在原社保个人代码前加8，江都区参保人员在原社保个人代码前加66", "社保个人代码为10位个人代码,如不足十位前面补0，邗江参保人员在原社保个人代码前加8，江都区参保人员在原社保个人代码前加66"),
    RETURN_CODE_4022(4022, 0, "多账户关联，请使用社保账号登录方式登陆", "多账户关联，请使用社保账号登录方式登陆"),
    RETURN_CODE_4023(4023, 0, "账号未激活", "账号未激活"),
    RETURN_CODE_4024(4024, 0, "请登录网站进行身份认证", "请登录网站进行身份认证"),
    RETURN_CODE_4097(4097, 0,"授权入口维护中", "授权入口维护中"),
    RETURN_CODE_4098(4098, 0,"授权入口维护中", "授权入口维护中"),
    RETURN_CODE_4099(4099, 0, "登录未知错误", "登录未知错误"),
    RETURN_CODE_5000(5000, 2, "内部错误", "授权失败"),
    RETURN_CODE_5001(5001, 0, "城市社保抓取暂时不支持", "城市社保抓取暂时不支持"),
    RETURN_CODE_5003(5003, 2, "网络错误", "网络错误，授权失败");



    private  Integer returnCode;
    private  Integer localCode;
    private  String codeInfo;
    private String showInfo;

   private SosialSecurityReturnCode(Integer returnCode, Integer localCode, String codeInfo, String showInfo) {
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

    public static SosialSecurityReturnCode parseCode(String returnCode){
        for (SosialSecurityReturnCode entity: SosialSecurityReturnCode.values()) {
            if (entity.getReturnCode().toString().compareTo(returnCode)==0){
                return  entity;
            }
        }
        return  null;
    }

}
