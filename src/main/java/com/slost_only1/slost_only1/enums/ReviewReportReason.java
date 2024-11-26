package com.slost_only1.slost_only1.enums;

import com.slost_only1.slost_only1.base.BaseEnum;

public enum ReviewReportReason implements BaseEnum {
    POLICY_VIOLATION("policy violation", "정책 위배"),
    PORNOGRAPHY("pornography", "음란물");

    private final String name;

    private final String label;

    ReviewReportReason(String name, String label) {
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
