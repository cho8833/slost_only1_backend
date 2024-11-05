package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthApi {
    @GetMapping
    public Response<?> health() {
        return Response.SUCCESS;
    }
}
