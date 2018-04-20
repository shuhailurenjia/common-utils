package com.zwh.common.enums;

/**
 * Created by llm on 2017/4/17.
 * wap端1，ios端2，安卓端3，M站5
 */
public enum SourceType {

    Wap(1),
    IOS(2),
    Android(3),
    MStation(5);
    private  Integer soureType;

    /**
     *构造函数
     * @param _type
     */
    private  SourceType(Integer _type){
        this.soureType=_type;
    }

    @Override
    public String toString() {
        return  String.valueOf(this.soureType);
    }

    public Integer getValue(){
        return this.soureType;
    }

    public  static  SourceType valueOf(Integer soureType){
        for (SourceType type: SourceType.values()) {
            if (soureType.equals(type.getValue() )){
                return  type;
            }
        }
        return  null;
    }
}
