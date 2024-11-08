package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.DolbomLocationRes;
import com.slost_only1.slost_only1.data.req.DolbomLocationCreateReq;
import com.slost_only1.slost_only1.service.DolbomLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dolbom-location")
public class DolbomLocationApi {

    private final DolbomLocationService service;

    @GetMapping
    public Response<List<DolbomLocationRes>> getList() {
        return new Response<>(service.getMyDolbomLocations().stream().map(DolbomLocationRes::of).toList());
    }

    @PutMapping
    public Response<DolbomLocationRes> create(@RequestBody DolbomLocationCreateReq req) {
        return new Response<>(DolbomLocationRes.of(service.create(req)));
    }

    @DeleteMapping
    public Response<?> delete(@RequestParam Long id) {
        service.delete(id);

        return Response.SUCCESS;
    }
}
