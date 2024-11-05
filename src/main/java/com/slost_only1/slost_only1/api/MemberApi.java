package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.MemberReq;
import com.slost_only1.slost_only1.data.MemberRes;
import com.slost_only1.slost_only1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberService memberService;

    @GetMapping
    public Response<Page<MemberRes>> list(SearchRequest request) {
        return new Response<>(memberService.list(request));
    }

    @PostMapping
    public Response<MemberRes> update(@RequestParam Long id, @RequestBody MemberReq req) {
        return new Response<>(memberService.update(id, req));
    }


    public static class SearchRequest extends PageRequest {
        protected SearchRequest(Integer pageNumber, Integer pageSize, Sort sort) {
            super(pageNumber == null ? 0 : pageNumber,
                    pageSize == null ? 10 : pageSize,
                    sort == null ? Sort.by(Sort.Direction.DESC, "id") : sort);
        }
    }
}
