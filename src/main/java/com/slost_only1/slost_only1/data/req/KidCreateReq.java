package com.slost_only1.slost_only1.data.req;

import com.slost_only1.slost_only1.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KidCreateReq {

    private String name;

    private LocalDateTime birthday;

    private Gender gender;

    private String tendency;

    private String remark;

}
