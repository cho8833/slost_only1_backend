package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.req.KidCreateReq;
import com.slost_only1.slost_only1.data.req.KidUpdateReq;
import com.slost_only1.slost_only1.model.Kid;

import java.util.List;

public interface KidService {
    List<Kid> getMyKids();

    Kid createKid(KidCreateReq req);

    void deleteKid(Long id);

    Kid updateKid(KidUpdateReq req);
}
