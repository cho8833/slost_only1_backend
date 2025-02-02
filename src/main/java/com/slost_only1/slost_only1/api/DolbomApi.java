package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.DolbomReviewRes;
import com.slost_only1.slost_only1.data.TeacherProfileRes;
import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.data.req.DolbomPostReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.DolbomReview;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.service.DolbomReviewService;
import com.slost_only1.slost_only1.service.DolbomService;
import com.slost_only1.slost_only1.service.TeacherProfileService;
import com.slost_only1.slost_only1.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dolbom")
@RequiredArgsConstructor
public class DolbomApi {

    private final DolbomService dolbomService;

    private final TeacherProfileService teacherProfileService;

    private final DolbomReviewService dolbomReviewService;

    private final AuthUtil authUtil;

    @PostMapping
    public Response<?> postDolbom(@RequestBody DolbomPostReq req) {
        dolbomService.postDolbom(req);

        return Response.SUCCESS;
    }

    @GetMapping("/me")
    public Response<Page<DolbomRes>> getMyDolbom(@RequestParam(required = false) DolbomStatus status,
                                                 @PageableDefault Pageable pageReq) {

        if (authUtil.getMemberRole() == MemberRole.PARENT) {
            return new Response<>(dolbomService.getParentDolbom(status, pageReq));
        } else {
            return new Response<>(dolbomService.getTeacherDolbom(status, pageReq));
        }
    }

    @GetMapping("/matching")
    public Response<Page<DolbomRes>> getMatchingDolbom(@PageableDefault Pageable pageable,
                                                       @ModelAttribute AddressListReq addressReq) {
        return new Response<>(dolbomService.getMatchingDolbom(addressReq, pageable));
    }

    @GetMapping("/pending-teacher")
    public Response<List<TeacherProfileRes>> getPendingTeacher(@RequestParam Long dolbomId) {
        List<TeacherProfile> fetch = teacherProfileService.getDolbomPendingTeacher(dolbomId);
        List<TeacherProfileRes> res = fetch.stream().map(TeacherProfileRes::from).toList();
        return new Response<>(res);
    }

    @GetMapping("/teacher/me")
    public Response<Page<DolbomRes>> getTeacherDolbom(@PageableDefault Pageable pageable, @RequestParam DolbomStatus status) {
        return new Response<>(dolbomService.getTeacherDolbom(status, pageable));
    }

    @GetMapping("/teacher/applied")
    public Response<Page<DolbomRes>> getTeacherAppliedDolbom(@PageableDefault Pageable pageable) {
        return new Response<>(dolbomService.getTeacherAppliedDolbom(pageable));
    }

    @PostMapping("/apply")
    public Response<?> apply(@RequestParam Long dolbomId) {
        dolbomService.apply(dolbomId);
        return Response.SUCCESS;
    }

    @GetMapping("/{dolbomId}/review")
    public Response<?> getReviewByDolbomId(@PathVariable Long dolbomId) throws Throwable {
        Optional<DolbomReview> fetch = dolbomReviewService.getReviewByDolbomId(dolbomId);
        if (fetch.isEmpty()) {
            return Response.SUCCESS;
        } else {
            return new Response<>(DolbomReviewRes.from(fetch.get()));
        }
    }

    @PostMapping("/{dolbomId}/match")
    public Response<?> matchDolbom(@PathVariable Long dolbomId, @RequestParam Long teacherProfileId) {
        dolbomService.match(dolbomId, teacherProfileId);
        return Response.SUCCESS;
    }

    @GetMapping("/{dolbomId}/teacher")
    public Response<TeacherProfileRes> getDolbomTeacher(@PathVariable Long dolbomId) {
        TeacherProfile teacherProfile = teacherProfileService.getByDolbomId(dolbomId);
        return new Response<>(TeacherProfileRes.from(teacherProfile));
    }
    @PostMapping("/{dolbomId}/finish")
    public Response<?> finishDolbom(@PathVariable Long dolbomId) {
        dolbomService.finishDolbom(dolbomId);
        return Response.SUCCESS;
    }
}
