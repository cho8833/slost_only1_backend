package com.slost_only1.slost_only1.repository.custom;

import com.slost_only1.slost_only1.model.Kid;

import java.util.List;
import java.util.Map;

public interface KidRepositoryCustom {
    Map<Long, List<Kid>> findByDolbomIdsIn(List<Long> dolbomIds);
}
