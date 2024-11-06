package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.DolbomNoticeRes;
import com.slost_only1.slost_only1.data.req.DolbomNoticeReq;
import com.slost_only1.slost_only1.model.DolbomNotice;
import com.slost_only1.slost_only1.service.DolbomNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dolbom-notice")
@RequiredArgsConstructor
public class DolbomNoticeApi {

    private final DolbomNoticeService service;


    @GetMapping
    public Response<List<DolbomNoticeRes>> getList(DolbomNoticeReq req) {
        List<DolbomNotice> result = service.findByAddress(req);

        List<DolbomNoticeRes> response = result.stream().map(DolbomNoticeRes::of).toList();
        
        return new Response<>(response);
    }
}
