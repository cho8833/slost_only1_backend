package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.DolbomReviewRes;
import com.slost_only1.slost_only1.data.req.DolbomReviewCreateReq;
import com.slost_only1.slost_only1.data.req.ReviewReportReq;
import com.slost_only1.slost_only1.model.DolbomReview;
import com.slost_only1.slost_only1.service.DolbomReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dolbom-review")
@RequiredArgsConstructor
public class DolbomReviewApi {

    private final DolbomReviewService dolbomReviewService;

    @PutMapping
    public Response<DolbomReviewRes> create(@RequestBody DolbomReviewCreateReq req) {
        DolbomReview result =  dolbomReviewService.create(req);

        return new Response<>(DolbomReviewRes.from(result));
    }

    @PostMapping("/report")
    public Response<?> report(@RequestBody ReviewReportReq req) {
        dolbomReviewService.report(req);
        return Response.SUCCESS;
    }

    @GetMapping("/report")
    public Response<Page<DolbomReviewRes>> getReportedReview(@PageableDefault Pageable pageable) {
        Page<DolbomReview> result = dolbomReviewService.getReported(pageable);
        Page<DolbomReviewRes> res = result.map(DolbomReviewRes::from);
        return new Response<>(res);
    }

    @DeleteMapping("/{id}")
    public Response<?> delete(@PathVariable Long id) {
        dolbomReviewService.delete(id);
        return Response.SUCCESS;
    }
}
