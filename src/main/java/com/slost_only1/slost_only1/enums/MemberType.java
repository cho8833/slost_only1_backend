package com.slost_only1.slost_only1.enums;

import com.slost_only1.slost_only1.base.BaseEnum;

public enum MemberType implements BaseEnum {

    PARENT("parent", "학부모"),
    TEACHER("teacher", "선생님");

    private final String name;

    private final String label;

    MemberType(String name, String label) {
        this.name = name;
        this.label = label;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getDescr() {
        return null;
    }
}
