package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.req.CertificateCreateReq;
import com.slost_only1.slost_only1.model.Certificate;

import java.io.IOException;
import java.util.List;

public interface CertificateService {
    List<Certificate> getMyCertificates();

    Certificate createCertificate(CertificateCreateReq req) throws IOException;

    Certificate editCertificate(Long id, CertificateCreateReq req) throws IOException;
}
