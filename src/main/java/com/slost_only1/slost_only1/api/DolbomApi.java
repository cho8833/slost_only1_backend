package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.data.req.DolbomPostReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.service.DolbomService;
import com.slost_only1.slost_only1.service.TeacherProfileService;
import com.slost_only1.slost_only1.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dolbom")
@RequiredArgsConstructor
public class DolbomApi {

    private final DolbomService dolbomService;

    private final TeacherProfileService teacherProfileService;

    private final AuthUtil authUtil;

    @PostMapping
    public Response<?> postDolbom(@RequestBody DolbomPostReq req) {
        System.out.println(req);
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
    public Response<List<TeacherProfile>> getPendingTeacher(@RequestParam Long dolbomId) {
        return new Response<>(teacherProfileService.getDolbomPendingTeacher(dolbomId));
    }
}
