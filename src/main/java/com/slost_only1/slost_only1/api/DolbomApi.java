package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.data.req.DolbomPostReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.service.DolbomService;
import com.slost_only1.slost_only1.service.TeacherProfileService;
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

    @PostMapping
    public Response<?> postDolbom(@RequestBody DolbomPostReq req) {
        System.out.println(req);
        dolbomService.postDolbom(req);

        return Response.SUCCESS;
    }

    @GetMapping("/me")
    public Response<Page<DolbomRes>> getMyDolbom(@ModelAttribute AddressListReq addressListReq,
                                                 @RequestParam(required = false) DolbomStatus status,
                                                 @PageableDefault Pageable pageReq) {
        return new Response<>(dolbomService.getMyDolbom(addressListReq, status, pageReq));
    }

    @GetMapping("/pending-teacher")
    public Response<List<TeacherProfile>> getPendingTeacher(@RequestParam Long dolbomId) {
        return new Response<>(teacherProfileService.getDolbomPendingTeacher(dolbomId));
    }
}
