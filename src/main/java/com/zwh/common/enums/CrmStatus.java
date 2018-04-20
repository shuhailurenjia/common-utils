package com.zwh.common.enums;

/**
 * Created by dell-3020 on 2017/5/4.
 */
public enum CrmStatus {
    on(1),
    out(2);
    private Integer _value;

    private  CrmStatus(Integer value){
        this._value=value;
    }


    public Integer getValue(){
        return this._value;
    }

    public CrmStatus valueOf(Integer value){
        for (CrmStatus crmStatus: CrmStatus.values()) {
            if (crmStatus._value.equals(value)){
                return  crmStatus;
            }
        }
        return  null;
    }

//    public static void main(String[] args) {
//        System.out.println(CrmStatus.on);
//    }
}
