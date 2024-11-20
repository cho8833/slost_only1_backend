package com.slost_only1.slost_only1.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoOAuthToken(String accessToken,
                              LocalDateTime expiresAt,
                              String refreshToken,
                              LocalDateTime refreshTokenExpiresAt,
                              List<String> scopes,
                              String idToken) {
}
