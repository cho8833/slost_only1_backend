package com.slost_only1.slost_only1.data;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class AuthorizationTokenData {

    private String accessToken;

    private Long id;

    private String refreshToken;
}
