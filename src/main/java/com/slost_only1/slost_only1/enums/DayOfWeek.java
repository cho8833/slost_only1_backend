package com.slost_only1.slost_only1.enums;

import com.slost_only1.slost_only1.base.BaseEnum;


public enum DayOfWeek implements BaseEnum {
    MON("monday", "월요일"),
    TUE("tuesday", "화요일"),
    WED("wednesday", "수요일"),
    THU("thursday", "목요일"),
    FRI("friday", "금요일"),
    SAT("saturday", "토요일"),
    SUN("sunday", "일요일");



    private final String name;

    private final String label;

    DayOfWeek(String name, String label) {
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
