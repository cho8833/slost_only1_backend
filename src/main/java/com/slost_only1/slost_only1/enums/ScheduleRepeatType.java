package com.slost_only1.slost_only1.enums;


import com.slost_only1.slost_only1.base.BaseEnum;

public enum ScheduleRepeatType implements BaseEnum {
    DAILY("daily", "일간"),
    WEEKLY("weekly", "주간"),
    MONTHLY("yearly", "월간");

    private final String name;

    private final String label;

    ScheduleRepeatType(String name, String label) {
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
