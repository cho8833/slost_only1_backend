package com.slost_only1.slost_only1.enums;

import com.slost_only1.slost_only1.base.BaseEnum;

public enum Age implements BaseEnum {
    BABY("영아", "~1세"),
    TODDLER("영유아", "2~3세"),
    KINDERGARTEN("유치원생", "4~6세"),
    LOWER_ELEMENTARY("초등 저학년", "7~9세"),
    HIGHER_ELEMENTARY("초등 고학년", "10~12세");

    private final String name;

    private final String label;

    Age(String name, String label) {
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
