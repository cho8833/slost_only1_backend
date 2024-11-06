package com.slost_only1.slost_only1.data;

import com.slost_only1.slost_only1.model.DolbomLocation;
import lombok.Getter;

@Getter
public class DolbomLocationRes {
    private String sido;

    private String bname;

    private String sigungu;

    private String address;

    public DolbomLocationRes(String sido, String bname, String sigungu, String address) {
        this.sido = sido;
        this.bname = bname;
        this.sigungu = sigungu;
        this.address = address;
    }

    public static DolbomLocationRes of(DolbomLocation dolbomLocation) {
        return new DolbomLocationRes(
                dolbomLocation.getAddress().getSido(),
                dolbomLocation.getAddress().getBname(),
                dolbomLocation.getAddress().getSigungu(),
                dolbomLocation.getAddress().getAddress()
        );
    }

}
