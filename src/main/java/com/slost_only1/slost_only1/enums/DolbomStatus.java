package com.slost_only1.slost_only1.enums;

import com.slost_only1.slost_only1.base.BaseEnum;

public enum DolbomStatus implements BaseEnum {
    MATCHING("matching", "찾는중"),
    RESERVED("reserved", "예약됨"),
    DONE("done", "완료됨"),
    CANCEL("cancel", "취소");



    private final String name;

    private final String label;

    DolbomStatus(String name, String label) {
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
