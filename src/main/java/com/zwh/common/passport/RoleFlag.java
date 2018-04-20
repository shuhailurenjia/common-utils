package com.zwh.common.passport;

/**
 * 角色枚举
 * Created by user on 2017/3/29.
 */
public enum RoleFlag {

	/**
	 * 未验证角色
	 */
	InvalidRole(0),
    /**
     * 客户经理
     */
    Manager(1),

    /**
     * 合伙人
     */
    Partner(2);

    // 定义私有变量
    private int flag;

    // 构造函数，枚举类型只能为私有
    private RoleFlag(int _flag) {
        this.flag = _flag;
    }

    @Override
    public String toString() {
        return String.valueOf(this.flag);
    }

    public Integer getValue() {
        return this.flag;
    }

    public static RoleFlag valueOf(int value) {
        for (RoleFlag c : RoleFlag.values()) {
            if (value == c.getValue())
                return c;
        }
        return null;
    }

}
