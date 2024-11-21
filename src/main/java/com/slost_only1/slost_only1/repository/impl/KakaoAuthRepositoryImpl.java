package com.slost_only1.slost_only1.repository.impl;

import com.slost_only1.slost_only1.data.KakaoUser;
import com.slost_only1.slost_only1.repository.KakaoAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
public class KakaoAuthRepositoryImpl implements KakaoAuthRepository {

    private final RestTemplate restTemplate;


    @Override
    public KakaoUser getUserInfo(String accessToken) {
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded;charset=utf-8"));
        headers.setBearerAuth(accessToken);
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoUser> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                KakaoUser.class
        );

        return response.getBody();
    }
}
