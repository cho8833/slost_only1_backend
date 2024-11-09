package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.model.Address;
import com.slost_only1.slost_only1.model.DolbomLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DolbomLocationRes {

    private Long id;

    private Address address;

    private String name;

    @QueryProjection
    public DolbomLocationRes(Long id, Address address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    public static DolbomLocationRes of(DolbomLocation dolbomLocation) {
        return new DolbomLocationRes(
                dolbomLocation.getId(),
                dolbomLocation.getAddress(),
                dolbomLocation.getName()
        );
    }

}
