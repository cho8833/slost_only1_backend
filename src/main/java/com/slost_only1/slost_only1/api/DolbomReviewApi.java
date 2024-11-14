package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.DolbomReviewRes;
import com.slost_only1.slost_only1.service.DolbomReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dolbom-review")
@RequiredArgsConstructor
public class DolbomReviewApi {

    private final DolbomReviewService dolbomReviewService;

    @PutMapping
    public Response<DolbomReviewRes> create(@RequestParam Long dolbomId) {

    }
}
