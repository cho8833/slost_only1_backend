package com.slost_only1.slost_only1.data;


import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.model.Certificate;

public record CertificateRes(Long id, String title) {

    @QueryProjection
    public CertificateRes {

    }

    public static CertificateRes from(Certificate certificate) {
        return new CertificateRes(certificate.getId(), certificate.getTitle());
    }
}
