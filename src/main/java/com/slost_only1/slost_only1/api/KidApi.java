package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.KidRes;
import com.slost_only1.slost_only1.data.req.KidCreateReq;
import com.slost_only1.slost_only1.data.req.KidUpdateReq;
import com.slost_only1.slost_only1.model.Kid;
import com.slost_only1.slost_only1.service.KidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kid")
@RequiredArgsConstructor
public class KidApi {

    private final KidService service;

    @GetMapping
    public Response<List<KidRes>> getMyKidList() {
        List<Kid> result = service.getMyKids();
        return new Response<>(result.stream().map(KidRes::of).toList());
    }

    @PutMapping
    public Response<KidRes> createKid(@RequestBody KidCreateReq req) {
        Kid kid = service.createKid(req);

        return new Response<>(KidRes.of(kid));
    }

    @PostMapping
    public Response<KidRes> updateKid(@RequestBody KidUpdateReq req) {

        Kid updatedKid = service.updateKid(req);

        return new Response<>(KidRes.of(updatedKid));
    }

    @DeleteMapping
    public Response<?> deleteKid(@RequestParam Long id) {
        service.deleteKid(id);

        return Response.SUCCESS;
    }
}
