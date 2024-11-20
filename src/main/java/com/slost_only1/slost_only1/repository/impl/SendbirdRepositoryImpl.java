package com.slost_only1.slost_only1.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class SendbirdRepositoryImpl {

    @Value("${sendbird.app-id}")
    private String applicationId;

    @Value("${sendbird.master-api-token}")
    private String apiToken;


}
