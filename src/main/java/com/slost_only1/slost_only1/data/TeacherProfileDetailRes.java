package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.Age;
import com.slost_only1.slost_only1.enums.DolbomCategory;
import com.slost_only1.slost_only1.enums.Gender;
import com.slost_only1.slost_only1.enums.TeacherProfileStatus;
import com.slost_only1.slost_only1.model.AvailableAge;
import com.slost_only1.slost_only1.model.AvailableCategory;
import com.slost_only1.slost_only1.model.TeacherProfile;

import java.time.LocalDate;
import java.util.List;

public record TeacherProfileDetailRes(
        Long id,
        String name,
        Gender gender,
        String profileImageUrl,
        LocalDate birthday,
        String profileName,
        String introduce,
        String howBecameTeacher,
        TeacherProfileStatus status,
        List<DolbomCategory> availableCategory,
        List<Age> availableAge
) {
    @QueryProjection
    public TeacherProfileDetailRes {}


    public static TeacherProfileDetailRes from(TeacherProfile teacher) {
        List<DolbomCategory> categories = teacher.getAvailableCategories().isEmpty()
                ? null
                : teacher.getAvailableCategories().stream().map(AvailableCategory::getCategory).toList();
        List<Age> ages = teacher.getAvailableAges().isEmpty()
                ? null
                : teacher.getAvailableAges().stream().map(AvailableAge::getAge).toList();
        return new TeacherProfileDetailRes(teacher.getId(),
                teacher.getName(),
                teacher.getGender(),
                teacher.getProfileImageUrl(),
                teacher.getBirthday(),
                teacher.getProfileName(),
                teacher.getIntroduce(),
                teacher.getHowBecameTeacher(),
                teacher.getStatus(),
                categories, ages
        );
    }
}
