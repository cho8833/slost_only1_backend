package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.SendbirdCreateGroupChannelRes;
import com.slost_only1.slost_only1.data.req.SendbirdCreateGroupChannelReq;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.ChatRoom;
import com.slost_only1.slost_only1.model.Dolbom;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.ChatRoomRepository;
import com.slost_only1.slost_only1.repository.DolbomRepository;
import com.slost_only1.slost_only1.repository.SendbirdRepository;
import com.slost_only1.slost_only1.repository.TeacherProfileRepository;
import com.slost_only1.slost_only1.service.ChatService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceSendbird implements ChatService {

    private final SendbirdRepository sendbirdRepository;

    private final AuthUtil authUtil;

    private final DolbomRepository dolbomRepository;

    private final TeacherProfileRepository teacherProfileRepository;

    private final EntityManager entityManager;

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom askTeacher(Long teacherProfileId) {
        // 부모님만 문의 가능
        MemberRole role = authUtil.getMemberRole();
        if (role != MemberRole.PARENT) {
            throw new CustomException(ResponseCode.FORBIDDEN);
        }

        Long parentId = authUtil.getLoginMemberId();

        TeacherProfile teacherProfile = teacherProfileRepository.findById(teacherProfileId).orElseThrow();
        Long teacherId = teacherProfile.getMember().getId();

        return createChatRoom(teacherId, parentId);
    }

    @Override
    public ChatRoom askDolbom(Long dolbomId) {
        // 선생님만 문의 가능
        MemberRole role = authUtil.getMemberRole();
        if (role != MemberRole.TEACHER) {
            throw new CustomException(ResponseCode.FORBIDDEN);
        }

        Long teacherId = authUtil.getLoginMemberId();

        Dolbom dolbom = dolbomRepository.findById(dolbomId).orElseThrow();
        Long parentId = dolbom.getMember().getId();

        return createChatRoom(teacherId, parentId);
    }

    private ChatRoom createChatRoom(Long teacherId, Long parentId) {
        String channelUrl = generateChannelUrl(teacherId, parentId);
        SendbirdCreateGroupChannelReq req = new SendbirdCreateGroupChannelReq(
                List.of(teacherId, parentId), List.of(teacherId, parentId),
                generateChannelUrl(teacherId, parentId), true
        );
        SendbirdCreateGroupChannelRes res = sendbirdRepository.createGroupChannel(req);

        Member teacher = entityManager.getReference(Member.class, teacherId);
        Member parent = entityManager.getReference(Member.class, parentId);

        ChatRoom chatRoom;

        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findByParent_IdAndTeacher_Id(parentId, teacherId);
        if (existingChatRoom.isEmpty()) {
            chatRoom = new ChatRoom(
                    channelUrl, parent, teacher
            );
            chatRoomRepository.save(chatRoom);
        } else {
            chatRoom = existingChatRoom.get();
        }
        return chatRoom;
    }


    private static String generateChannelUrl(Long teacherId, Long parentId) {
        return "teacher"+teacherId+"parent"+parentId;
    }
}
