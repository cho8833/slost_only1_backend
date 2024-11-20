package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.TeacherProfileRepository;
import com.slost_only1.slost_only1.service.MyTeacherProfileService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyTeacherProfileServiceImpl implements MyTeacherProfileService {

    private final TeacherProfileRepository teacherProfileRepository;

    private final AuthUtil authUtil;

    private final EntityManager entityManager;


    @Override
    public TeacherProfile getMyTeacherProfile() {
        Optional<TeacherProfile> myProfile = teacherProfileRepository.findByMemberId(authUtil.getLoginMemberId());
        if (myProfile.isEmpty()) {
            TeacherProfile teacherProfile = new TeacherProfile();
            Member member = entityManager.getReference(Member.class, authUtil.getLoginMemberId());
            teacherProfile.setMember(member);

            teacherProfileRepository.save(teacherProfile);

            return teacherProfile;
        } else {
            return myProfile.get();
        }
    }

}
