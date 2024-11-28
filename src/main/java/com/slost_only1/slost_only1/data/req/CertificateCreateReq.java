package com.slost_only1.slost_only1.data.req;

import org.springframework.web.multipart.MultipartFile;

public record CertificateCreateReq(
        String title,
        MultipartFile pdf) {
}
