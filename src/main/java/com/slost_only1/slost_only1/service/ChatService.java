package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.ChatRoom;
import com.slost_only1.slost_only1.model.Member;

public interface ChatService {

    ChatRoom askTeacher(Long teacherProfileId);

    ChatRoom askDolbom(Long dolbomId);

    Member createUser(Long memberId, MemberRole memberRole);
}
