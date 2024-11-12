package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.TeacherProfileRes;
import com.slost_only1.slost_only1.data.req.TeacherProfileCreateReq;
import com.slost_only1.slost_only1.service.TeacherProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherProfileApi {

    private final TeacherProfileService service;

    @PutMapping
    public Response<TeacherProfileRes> createTeacherProfile(@ModelAttribute TeacherProfileCreateReq req, @RequestPart MultipartFile profileImg) {
        return null;
    }


}
