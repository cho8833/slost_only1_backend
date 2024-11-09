package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.DolbomNoticeRes;
import com.slost_only1.slost_only1.data.req.DolbomNoticeCreateReq;
import com.slost_only1.slost_only1.data.req.DolbomNoticeListReq;
import com.slost_only1.slost_only1.model.DolbomNotice;
import com.slost_only1.slost_only1.service.DolbomNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dolbom-notice")
@RequiredArgsConstructor
public class DolbomNoticeApi {

    private final DolbomNoticeService service;


    @GetMapping
    public Response<Page<DolbomNoticeRes>> getList(Pageable pageable, DolbomNoticeListReq req) {
        Page<DolbomNoticeRes> res = service.findByAddress(pageable, req);;

        return new Response<>(res);
    }

    @PutMapping
    public Response<DolbomNoticeRes> create(@RequestBody DolbomNoticeCreateReq req) {
        return null;
    }
}
