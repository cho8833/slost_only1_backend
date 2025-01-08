package com.slost_only1.slost_only1.data;

public record AppleAuthorizationCredential(
        String userIdentifier,
        String givenName,
        String familyName,
        String authorizationCode,
        String email,
        String identityToken,
        String state) {
}