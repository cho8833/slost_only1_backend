package com.slost_only1.slost_only1.enums;


import com.slost_only1.slost_only1.base.BaseEnum;

public enum TeacherProfileStatus implements BaseEnum {
    PENDING("pending", "심사 대기중"),
    APPROVED("approved", "승인됨"),
    REJECTED("rejected", "반려됨");


    private final String name;

    private final String label;

    TeacherProfileStatus(String name, String label) {
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
