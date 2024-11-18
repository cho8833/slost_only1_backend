package com.slost_only1.slost_only1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.AvailableAreaRes;
import com.slost_only1.slost_only1.data.TeacherProfileRes;
import com.slost_only1.slost_only1.data.req.TeacherProfileCreateReq;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.service.TeacherProfileService;
import com.slost_only1.slost_only1.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherProfileApi {

    private final TeacherProfileService service;

    private final AuthUtil authUtil;

    @PostMapping
    public Response<TeacherProfileRes> createTeacherProfile(@RequestPart("profile") String req, @RequestPart("profileImg") MultipartFile profileImg) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        TeacherProfileCreateReq profile;
        try {
            profile = objectMapper.readValue(req, TeacherProfileCreateReq.class);
            return new Response<>(service.createProfile(profile, profileImg));
        } catch (Exception e) {
            throw new CustomException(ResponseCode.WRONG_REQUEST);
        }
    }

    @GetMapping("/near")
    public Response<Page<TeacherProfileRes>> getNearTeacher(@PageableDefault Pageable pageable,
                                                            @RequestParam(required = false) String sigungu) {
        Page<TeacherProfile> fetch = service.getNearTeacher(sigungu, pageable);
        Page<TeacherProfileRes> result = fetch.map(TeacherProfileRes::from);

        return new Response<>(result);
    }

    @GetMapping("/available-area")
    public Response<List<AvailableAreaRes>> getAvailableArea(@RequestParam Long teacherProfileId) {
        return new Response<>(
                service.getAvailableArea(teacherProfileId).stream().map(AvailableAreaRes::from).toList()
        );
    }

    @GetMapping("/me")
    public Response<TeacherProfileRes> getMyTeacherProfile() {
        try {
            return new Response<>(TeacherProfileRes.from(
                    service.getTeacherProfileById(authUtil.getLoginMemberId()))
            );
        } catch (NoSuchElementException e) {
            throw new CustomException(ResponseCode.DATA_NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public Response<TeacherProfileRes> getTeacherProfileById(@PathVariable Long id) {
        return new Response<>(TeacherProfileRes.from(service.getTeacherProfileById(id)));
    }

    @GetMapping
    public Response<Page<TeacherProfileRes>> getTeacherProfiles(@PageableDefault Pageable pageable) {
        Page<TeacherProfile> fetch = service.getTeacherProfile(pageable);
        Page<TeacherProfileRes> result = fetch.map(TeacherProfileRes::from);
        return new Response<>(result);
    }
}
