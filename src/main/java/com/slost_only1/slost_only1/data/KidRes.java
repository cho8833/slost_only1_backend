package com.slost_only1.slost_only1.data;


import com.slost_only1.slost_only1.enums.Gender;
import com.slost_only1.slost_only1.model.Kid;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KidRes {

    private Long id;

    private String name;

    private Integer age;

    private Gender gender;

    private String tendency;

    private String remark;


    public static KidRes of(Kid kid) {
        return new KidRes(
                kid.getId(),
                kid.getName(),
                kid.getAge(),
                kid.getGender(),
                kid.getTendency(),
                kid.getRemark()
        );
    }

}
