package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DolbomLocation extends BaseEntity {

    @Embedded
    private Address address;

}
