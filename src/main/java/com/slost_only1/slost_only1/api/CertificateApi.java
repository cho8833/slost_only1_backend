package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.CertificateRes;
import com.slost_only1.slost_only1.data.req.CertificateCreateReq;
import com.slost_only1.slost_only1.model.Certificate;
import com.slost_only1.slost_only1.repository.TeacherProfileRepository;
import com.slost_only1.slost_only1.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificate")
@RequiredArgsConstructor
public class CertificateApi {

    private final CertificateService service;

    @GetMapping("/me")
    public Response<List<CertificateRes>> getMyCertificate() {
        List<Certificate> data = service.getMyCertificates();

        return new Response<>(data.stream().map(CertificateRes::from).toList());
    }

    @PutMapping
    public Response<CertificateRes> createCertificate(@RequestBody CertificateCreateReq req) {
        return new Response<>(CertificateRes.from(service.createCertificate(req)));
    }

}
