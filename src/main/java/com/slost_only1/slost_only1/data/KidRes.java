package com.slost_only1.slost_only1.data;


import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.Gender;
import com.slost_only1.slost_only1.model.Kid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

@Getter
public class KidRes {

    private Long id;

    private String name;

    private LocalDateTime birthday;

    private Gender gender;

    private String tendency;

    private String remark;

    @QueryProjection
    public KidRes(Long id, String name, LocalDateTime birthday, Gender gender, String tendency, String remark) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.tendency = tendency;
        this.remark = remark;
    }

    public static KidRes of(Kid kid) {
        return new KidRes(
                kid.getId(),
                kid.getName(),
                kid.getBirthday(),
                kid.getGender(),
                kid.getTendency(),
                kid.getRemark()
        );
    }

}
