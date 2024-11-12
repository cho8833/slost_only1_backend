package com.slost_only1.slost_only1.data.req;

import com.slost_only1.slost_only1.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public final class TeacherProfileCreateReq {
    private final String name;
    private final Gender gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDate birthday;
    private final String profileName;

    public TeacherProfileCreateReq(String name, Gender gender, LocalDate birthday, String profileName) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.profileName = profileName;
    }




}
