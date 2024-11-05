package com.slost_only1.slost_only1.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String sido;

    private String sigungu;

    private String bname;

    private String address;
}
