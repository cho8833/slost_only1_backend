package com.slost_only1.slost_only1.data.req;


import com.slost_only1.slost_only1.enums.Age;
import com.slost_only1.slost_only1.enums.DolbomCategory;

import java.util.List;

public record TeacherProfileEditReq(String introduce,
                                    String name,
                                    String howBecameTeacher,
                                    List<Age> availableAge,
                                    List<DolbomCategory> availableCategory
                                    ) {
}
