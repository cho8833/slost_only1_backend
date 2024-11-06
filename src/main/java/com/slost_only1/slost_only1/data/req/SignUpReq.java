package com.slost_only1.slost_only1.data.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpReq {

    private String username;

    private String password;

    private String phoneNumber;
}
