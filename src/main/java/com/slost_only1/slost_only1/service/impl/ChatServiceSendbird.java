package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.SendbirdCreateGroupChannelRes;
import com.slost_only1.slost_only1.data.SendbirdCreateUserRes;
import com.slost_only1.slost_only1.data.req.SendbirdCreateGroupChannelReq;
import com.slost_only1.slost_only1.data.req.SendbirdCreateUserReq;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.ChatRoom;
import com.slost_only1.slost_only1.model.Dolbom;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.*;
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

    private final MemberRepository memberRepository;

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

    @Override
    public Member createUser(Long memberId, MemberRole memberRole) {

        String profileUrl = "";
        String nickName = memberRole == MemberRole.PARENT ? "부모님" : "선생님";
        if (memberRole == MemberRole.TEACHER) {
            TeacherProfile teacherProfile = teacherProfileRepository.findByMemberId(memberId).orElseThrow();
            nickName = teacherProfile.getName() != null ? teacherProfile.getName() : "선생님";
            profileUrl = teacherProfile.getProfileImageUrl() != null ? teacherProfile.getProfileImageUrl() : "";
        }

        SendbirdCreateUserReq req = new SendbirdCreateUserReq(
                Member.getSendbirdId(memberRole, memberId), nickName, profileUrl, true
        );
        SendbirdCreateUserRes res =  sendbirdRepository.createUser(req);

        Member member = memberRepository.findById(memberId).orElseThrow();
        member.setSendbirdAccessToken(res.access_token());
        memberRepository.save(member);

        return member;
    }

    private ChatRoom createChatRoom(Long teacherId, Long parentId) {
        String channelUrl = generateChannelUrl(teacherId, parentId);
        String teacherSendbirdId = Member.getSendbirdId(MemberRole.TEACHER, teacherId);
        String parentSendbirdId = Member.getSendbirdId(MemberRole.PARENT, parentId);

        SendbirdCreateGroupChannelReq req = new SendbirdCreateGroupChannelReq(
                List.of(teacherSendbirdId, parentSendbirdId), List.of(teacherSendbirdId, parentSendbirdId),
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
