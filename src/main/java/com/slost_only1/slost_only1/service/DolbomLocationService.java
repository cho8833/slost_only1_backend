package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.req.DolbomLocationCreateReq;
import com.slost_only1.slost_only1.model.DolbomLocation;

import java.util.List;

public interface DolbomLocationService {
    List<DolbomLocation> getMyDolbomLocations();

    DolbomLocation create(DolbomLocationCreateReq req);

    void delete(Long id);
}
