package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.AreaReq;
import com.slost_only1.slost_only1.data.AvailableAreaRes;
import com.slost_only1.slost_only1.data.DolbomReviewRes;
import com.slost_only1.slost_only1.data.TeacherProfileRes;
import com.slost_only1.slost_only1.data.req.TeacherProfileEditReq;
import com.slost_only1.slost_only1.model.DolbomReview;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.service.MyTeacherProfileService;
import com.slost_only1.slost_only1.service.TeacherProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherProfileApi {

    private final TeacherProfileService service;

    private final MyTeacherProfileService myTeacherProfileService;

    @GetMapping("/near")
    public Response<Page<TeacherProfileRes>> getNearTeacher(@PageableDefault Pageable pageable,
                                                            @ModelAttribute AreaReq req) {
        Page<TeacherProfile> fetch = service.getNearTeacher(req, pageable);
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
        return new Response<>(TeacherProfileRes.from(myTeacherProfileService.getMyTeacherProfile()));
    }

    @GetMapping("/me/review")
    public Response<Page<DolbomReviewRes>> getMyReviews(@PageableDefault Pageable pageable) {
        TeacherProfile myProfile = myTeacherProfileService.getMyTeacherProfile();
        Page<DolbomReview> reviews = service.getTeacherReview(pageable, myProfile.getId(), true);

        Page<DolbomReviewRes> res = reviews.map(DolbomReviewRes::from);

        return new Response<>(res);
    }

    @GetMapping("/{id}")
    public Response<TeacherProfileRes> getTeacherProfileById(@PathVariable Long id) {
        return new Response<>(TeacherProfileRes.from(service.getTeacherProfileById(id)));
    }

    @PatchMapping("/{id}")
    public Response<TeacherProfileRes> editTeacherProfile(@PathVariable Long id, @RequestBody TeacherProfileEditReq req) {
        return new Response<>(TeacherProfileRes.from(service.editTeacherProfile(id, req)));
    }

    @PostMapping("/{id}/profile-image")
    public Response<TeacherProfileRes> editTeacherProfileImage(@PathVariable Long id, @RequestPart("profileImg") MultipartFile profileImg) {
        return new Response<>(TeacherProfileRes.from(service.editTeacherProfileImage(id, profileImg)));
    }

    @GetMapping
    public Response<Page<TeacherProfileRes>> getTeacherProfiles(@PageableDefault Pageable pageable) {
        Page<TeacherProfile> fetch = service.getTeacherProfile(pageable);
        Page<TeacherProfileRes> result = fetch.map(TeacherProfileRes::from);
        return new Response<>(result);
    }

    @GetMapping("/{id}/review")
    public Response<Page<DolbomReviewRes>> getTeacherReview(@PageableDefault Pageable pageable, @PathVariable Long id) {
        Page<DolbomReview> reviews = service.getTeacherReview(pageable, id, false);
        Page<DolbomReviewRes> res = reviews.map(DolbomReviewRes::from);
        return new Response<>(res);
    }
}
