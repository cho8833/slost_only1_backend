package com.slost_only1.slost_only1.data.req;

import com.slost_only1.slost_only1.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class TeacherProfileCreateReq {
    private String name;
    private Gender gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate birthday;
    private String profileName;
    private List<AvailableAreaCreateReq> availableArea;


}
