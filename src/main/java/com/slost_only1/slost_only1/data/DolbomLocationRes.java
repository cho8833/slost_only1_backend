package com.slost_only1.slost_only1.data;

import com.slost_only1.slost_only1.model.Address;
import com.slost_only1.slost_only1.model.DolbomLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DolbomLocationRes {

    private Long id;

    private Address address;


    public static DolbomLocationRes of(DolbomLocation dolbomLocation) {
        return new DolbomLocationRes(
                dolbomLocation.getId(),
                dolbomLocation.getAddress()
        );
    }

}
