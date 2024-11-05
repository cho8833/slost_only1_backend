package com.slost_only1.slost_only1.enums;


import com.slost_only1.slost_only1.base.BaseEnum;

public enum Gender implements BaseEnum {
    MALE("male", "남성"),
    FEMALE("female", "여성");


    private final String name;

    private final String label;

    Gender(String name, String label) {
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
