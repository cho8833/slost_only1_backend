package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.model.ChatRoom;

public interface ChatService {

    ChatRoom askTeacher(Long teacherProfileId);
    ChatRoom askDolbom(Long dolbomId);
}
