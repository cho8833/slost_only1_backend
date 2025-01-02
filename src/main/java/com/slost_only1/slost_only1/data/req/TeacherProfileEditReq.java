package com.slost_only1.slost_only1.data.req;


import com.slost_only1.slost_only1.enums.Age;
import com.slost_only1.slost_only1.enums.DolbomCategory;
import com.slost_only1.slost_only1.enums.Gender;
import java.time.LocalDate;

import java.util.List;

public record TeacherProfileEditReq(String introduce,
                                    String name,
                                    String profileName,
                                    String howBecameTeacher,
                                    LocalDate birthday,
                                    Gender gender,
                                    List<Age> availableAge,
                                    List<DolbomCategory> availableCategory
                                    ) {
}
