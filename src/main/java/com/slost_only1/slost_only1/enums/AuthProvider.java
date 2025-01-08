package com.slost_only1.slost_only1.enums;

import com.slost_only1.slost_only1.base.BaseEnum;

public enum AuthProvider implements BaseEnum {

    KAKAO("kakao", "카카오", "https://kauth.kakao.com/.well-known/jwks.json"),
    APPLE("apple", "애플", "https://appleid.apple.com/auth/keys");

    private final String name;

    private final String label;

    private final String publicKeyUrl;

    AuthProvider(String name, String label, String publicKeyUrl) {
        this.name = name;
        this.label = label;
        this.publicKeyUrl = publicKeyUrl;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getDescr() {
        return null;
    }

    public String getPublicKeyUrl() {
        return publicKeyUrl;
    }
}
