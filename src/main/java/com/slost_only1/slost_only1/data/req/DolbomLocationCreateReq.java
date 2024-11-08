package com.slost_only1.slost_only1.data.req;

import com.slost_only1.slost_only1.model.Address;
import lombok.Getter;

@Getter
public class DolbomLocationCreateReq {

    private Address address;

    private String name;
}
