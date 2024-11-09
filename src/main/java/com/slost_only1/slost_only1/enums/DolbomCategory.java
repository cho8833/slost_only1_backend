package com.slost_only1.slost_only1.enums;

import com.slost_only1.slost_only1.base.BaseEnum;

public enum DolbomCategory implements BaseEnum {
    SW("sw", "소프트웨어"),
    MATH("math", "수학"),
    ENGLISH("english", "영어");



    private final String name;

    private final String label;

    DolbomCategory(String name, String label) {
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
