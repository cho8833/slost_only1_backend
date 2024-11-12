package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.Gender;
import com.slost_only1.slost_only1.model.TeacherProfile;

import java.time.LocalDate;

public record TeacherProfileRes(Long id, String name, Gender gender, String profileImageUrl, LocalDate birthday,
                                String profileName) {

    @QueryProjection
    public TeacherProfileRes {
    }

    public static TeacherProfileRes from(TeacherProfile teacher) {
        return new TeacherProfileRes(teacher.getId(),
                teacher.getName(),
                teacher.getGender(),
                teacher.getProfileImageUrl(),
                teacher.getBirthday(),
                teacher.getProfileName());
    }
}
