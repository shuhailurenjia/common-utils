package com.zwh.common.enums;

/**
 * Created by a on 16-4-21.
 */
public enum SortType {

    DESC("desc"),
    ASC("asc");

    private String type;

    private SortType(String _type) {
        this.type = _type;
    }

    public String getValue() {
        return this.type;
    }
}
