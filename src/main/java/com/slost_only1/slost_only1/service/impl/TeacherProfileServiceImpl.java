package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.AreaReq;
import com.slost_only1.slost_only1.data.req.TeacherProfileEditReq;
import com.slost_only1.slost_only1.model.AvailableArea;
import com.slost_only1.slost_only1.model.DolbomReview;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.*;
import com.slost_only1.slost_only1.service.TeacherProfileService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherProfileServiceImpl implements TeacherProfileService {

    private final TeacherProfileRepository teacherProfileRepository;

    private final AvailableAreaRepository availableAreaRepository;

    private final DolbomReviewRepository dolbomReviewRepository;

    private final CloudFileRepository cloudFileRepository;

    private final AuthUtil authUtil;

    @Override
    public List<TeacherProfile> getDolbomPendingTeacher(Long dolbomId) {
        return teacherProfileRepository.findByDolbomId(dolbomId);
    }

    @Override
    public Page<TeacherProfile> getNearTeacher(AreaReq req, Pageable pageable) {
        return teacherProfileRepository.findBySigunguAndSido(req.sigungu(), req.sido(), pageable);
    }

    @Override
    public List<AvailableArea> getAvailableArea(Long teacherProfileId) {
        return availableAreaRepository.findByTeacherProfileId(teacherProfileId);
    }

    @Override
    public TeacherProfile getTeacherProfileById(Long id) {
        return teacherProfileRepository.findByMemberId(id).orElseThrow();
    }

    @Override
    public Page<TeacherProfile> getTeacherProfile(Pageable pageable) {
        return teacherProfileRepository.findAll(pageable);
    }

    @Override
    public TeacherProfile editTeacherProfile(Long id, TeacherProfileEditReq req) {
        TeacherProfile teacherProfile = teacherProfileRepository.findById(id).orElseThrow();

        if (req.howBecameTeacher() != null) {
            teacherProfile.setHowBecameTeacher(req.howBecameTeacher());
        }
        if (req.introduce() != null) {
            teacherProfile.setIntroduce(req.introduce());
        }

        teacherProfileRepository.save(teacherProfile);

        return teacherProfile;
    }

    @Override
    public TeacherProfile editTeacherProfileImage(Long id, MultipartFile file) {
        TeacherProfile teacherProfile = teacherProfileRepository.findById(id).orElseThrow();

        // 이미지 업로드
        String url;
        try {
            url = cloudFileRepository.saveFile(file);
        } catch (Exception e) {
            throw new CustomException(ResponseCode.FAIL_UPLOAD_FILE);
        }

        // 이미 프로필 이미지가 있다면 버킷에서 삭제
        if (teacherProfile.getProfileImageUrl() != null) {
            List<String> split = Arrays.stream(teacherProfile.getProfileImageUrl()
                    .split("/")).toList();
            String fileName = split.get(split.size()-1);
            cloudFileRepository.deleteFile(fileName);
        }

        teacherProfile.setProfileImageUrl(url);
        teacherProfileRepository.save(teacherProfile);

        return teacherProfile;

    }

    @Override
    public Page<DolbomReview> getTeacherReview(Pageable pageable, Long teacherId) {
        // 신고되지 않은 리뷰만 가져옴
        return dolbomReviewRepository.findByTeacherProfileIdAndReportReason(teacherId, null, pageable);
    }
}
