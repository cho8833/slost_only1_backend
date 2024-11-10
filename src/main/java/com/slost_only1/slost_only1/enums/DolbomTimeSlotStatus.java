package com.slost_only1.slost_only1.enums;


import com.slost_only1.slost_only1.base.BaseEnum;

public enum DolbomTimeSlotStatus implements BaseEnum {
    RESERVED("reserved", "예약됨"),
    DONE("done", "완료됨"),
    CANCEL("cancel", "취소됨");



    private final String name;

    private final String label;

    DolbomTimeSlotStatus(String name, String label) {
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
