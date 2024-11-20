package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.data.KakaoUser;

public interface KakaoAuthRepository {
    KakaoUser getUserInfo(String accessToken);
}
