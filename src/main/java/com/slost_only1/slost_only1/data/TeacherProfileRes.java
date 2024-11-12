package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.Gender;

import java.time.LocalDate;

public record TeacherProfileRes(Long id, String name, Gender gender, String profileImageUrl, LocalDate age,
                                String profileName) {

    @QueryProjection
    public TeacherProfileRes {
    }
}
