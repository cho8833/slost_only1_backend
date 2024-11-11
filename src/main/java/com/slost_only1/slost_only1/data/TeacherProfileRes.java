package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.Gender;
import lombok.Getter;

@Getter
public class TeacherProfileRes {

    private final Long id;

    private final String name;

    private final Gender gender;

    private final String profileImageUrl;

    private final Long age;

    private final String profileName;

    @QueryProjection
    public TeacherProfileRes(Long id, String name, Gender gender, String profileImageUrl, Long age, String profileName) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.profileImageUrl = profileImageUrl;
        this.age = age;
        this.profileName = profileName;
    }
}
