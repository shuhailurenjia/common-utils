package com.zwh.common.passport;

/**
 * 身份认证标示
 * Created by Cougar on 14-11-20.
 */
public enum AuthFlag {
    /**
     * 新游客(需要重新分配SID)
     */
    INVALID_GUEST(0),
    /**
     * 游客(不需要重新分配SID)
     */
    VALID_GUEST(1),
    /**
     * 合法的已登陆用户
     */
    VALID_USER(2),
    /**
     * SID已失效的登陆用户
     */
    INVALID_USER(9);

    // 定义私有变量
    private int flag;

    // 构造函数，枚举类型只能为私有
    private AuthFlag(int _flag) {
        this.flag = _flag;
    }

    @Override
    public String toString() {
        return String.valueOf(this.flag);
    }


    public static AuthFlag valueOf(int value) {
        for (AuthFlag c : AuthFlag.values()) {
            if (value == c.getValue())
                return c;
        }
        return null;
    }

    public Integer getValue() {
        return this.flag;
    }
}
