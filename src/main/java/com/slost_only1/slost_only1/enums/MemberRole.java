package com.slost_only1.slost_only1.enums;

import com.slost_only1.slost_only1.base.BaseEnum;
import org.springframework.security.core.GrantedAuthority;

public enum MemberRole implements BaseEnum, GrantedAuthority {

    PARENT("PARENT", "학부모"),
    TEACHER("TEACHER", "선생님");

    private final String name;

    private final String label;

    MemberRole(String name, String label) {
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

    @Override
    public String getAuthority() {
        return name;
    }
}
