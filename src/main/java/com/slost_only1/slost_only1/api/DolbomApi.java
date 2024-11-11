package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.data.req.DolbomListReq;
import com.slost_only1.slost_only1.data.req.DolbomPostReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import com.slost_only1.slost_only1.model.Dolbom;
import com.slost_only1.slost_only1.service.DolbomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dolbom")
@RequiredArgsConstructor
public class DolbomApi {

    private final DolbomService dolbomService;

    @PostMapping
    public Response<?> postDolbom(@RequestBody DolbomPostReq req) {
        System.out.println(req);
        dolbomService.postDolbom(req);

        return Response.SUCCESS;
    }

    @GetMapping
    public Response<Page<DolbomRes>> getMyDolbom(@ModelAttribute AddressListReq addressListReq,
                                                 @RequestParam(required = false) DolbomStatus status,
                                                 @PageableDefault Pageable pageReq) {
        return new Response<>(dolbomService.getMyDolbom(addressListReq, status, pageReq));
    }
}
