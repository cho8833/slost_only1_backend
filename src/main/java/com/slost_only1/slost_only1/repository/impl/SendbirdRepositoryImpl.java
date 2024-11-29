package com.slost_only1.slost_only1.repository.impl;

import com.slost_only1.slost_only1.data.SendbirdCreateGroupChannelRes;
import com.slost_only1.slost_only1.data.SendbirdCreateUserRes;
import com.slost_only1.slost_only1.data.req.SendbirdCreateGroupChannelReq;
import com.slost_only1.slost_only1.data.req.SendbirdCreateUserReq;
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
    public SendbirdCreateUserRes createUser(SendbirdCreateUserReq req) {
        String url = "https://api-" + applicationId + ".sendbird.com/v3/users";

        HttpHeaders header = new HttpHeaders();
        header.set("Api-Token", apiToken);
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SendbirdCreateUserReq> entity = new HttpEntity<>(req, header);

        ResponseEntity<SendbirdCreateUserRes> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                SendbirdCreateUserRes.class
        );

        return response.getBody();
    }

    @Override
    public SendbirdCreateGroupChannelRes createGroupChannel(SendbirdCreateGroupChannelReq req) {
        String url = "https://api-" + applicationId + ".sendbird.com/v3/group_channels";

        HttpHeaders header = new HttpHeaders();
        header.set("Api-Token", apiToken);
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SendbirdCreateGroupChannelReq> entity = new HttpEntity<>(req, header);

        ResponseEntity<SendbirdCreateGroupChannelRes> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                SendbirdCreateGroupChannelRes.class
        );

        return response.getBody();
    }
}
