package com.slost_only1.slost_only1.repository.impl;

import com.slost_only1.slost_only1.data.SendbirdCreateUserRes;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.SendbirdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
public class SendbirdRepositoryImpl implements SendbirdRepository {

    @Value("${sendbird.app-id}")
    private String applicationId;

    @Value("${sendbird.master-api-token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    @Override
    public SendbirdCreateUserRes createUser(Member member) {
        String url = "https://api-" + applicationId + ".sendbird.com/v3/users";

        HttpHeaders header = new HttpHeaders();
        header.set("Api-Token", apiToken);
        header.setContentType(MediaType.APPLICATION_JSON);

        SendbirdCreateUserReq reqBody = new SendbirdCreateUserReq(
                member.getId().toString(), member.getId().toString(), "", true);

        HttpEntity<SendbirdCreateUserReq> entity = new HttpEntity<>(reqBody, header);

        ResponseEntity<SendbirdCreateUserRes> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                SendbirdCreateUserRes.class
        );

        return response.getBody();
    }

    private record SendbirdCreateUserReq(
            String user_id,
            String nickname,
            String profile_url,
            Boolean issue_access_token
    ) {
    }
}
