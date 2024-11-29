package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.data.req.DolbomPostReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import com.slost_only1.slost_only1.model.*;
import com.slost_only1.slost_only1.repository.*;
import com.slost_only1.slost_only1.service.ChatService;
import com.slost_only1.slost_only1.service.DolbomService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DolbomServiceImpl implements DolbomService {

    private final DolbomRepository dolbomRepository;

    private final DolbomDowRepository dolbomDowRepository;

    private final KidDolbomRepository kidDolbomRepository;

    private final DolbomTimeSlotRepository dolbomTimeSlotRepository;

    private final DolbomAppliedTeacherRepository dolbomAppliedTeacherRepository;

    private final TeacherProfileRepository teacherProfileRepository;

    private final ChatService chatService;

    private final EntityManager entityManager;

    private final AuthUtil authUtil;

    @Override
    @Transactional
    public void postDolbom(DolbomPostReq req) {
        DolbomLocation dolbomLocation = entityManager.getReference(DolbomLocation.class, req.getDolbomLocationId());
        Member member = entityManager.getReference(Member.class, authUtil.getLoginMemberId());
        DolbomStatus status = DolbomStatus.MATCHING;

        Dolbom dolbom = Dolbom.builder()
                .dolbomLocation(dolbomLocation)
                .category(req.getCategory())
                .pay(req.getPay())
                .member(member)
                .status(status)
                .weeklyRepeat(req.getWeeklyRepeat())
                .setSeveralTime(req.getSetSeveralTime())
                .startTime(req.getStartTime() != null ? Time.valueOf(req.getStartTime().toLocalTime()) : null)
                .endTime(req.getEndTime() != null ? Time.valueOf(req.getEndTime().toLocalTime()) : null)
                .repeatStartDate(req.getRepeatStartDate() != null ? req.getRepeatStartDate().toLocalDate() : null)
                .repeatEndDate(req.getRepeatEndDate() != null ? req.getRepeatEndDate().toLocalDate() : null)
                .build();
        dolbomRepository.save(dolbom);

        List<Kid> kids = req.getKidIds().stream().map((id) -> entityManager.getReference(Kid.class, id)).toList();
        List<KidDolbom> kidDolboms = kids.stream().map((kid) -> new KidDolbom(dolbom, kid)).toList();
        kidDolbomRepository.saveAll(kidDolboms);

        List<DolbomTimeSlot> timeSlots = req.getTimeSlots().stream()
                .map((slot) -> DolbomTimeSlot.from(slot, dolbom)).toList();
        dolbomTimeSlotRepository.saveAll(timeSlots);

        if (req.getWeeklyRepeat()) {
            List<DolbomDow> dows = req.getDows().stream().map(dow -> new DolbomDow(dolbom, dow)).toList();
            dolbomDowRepository.saveAll(dows);
        }
    }

    @Override
    public Page<DolbomRes> getParentDolbom(DolbomStatus status, Pageable pageable) {
        Long memberId = authUtil.getLoginMemberId();

        return dolbomRepository.findByMemberIdAndAddressAndStatus(
                memberId,
                new AddressListReq(),
                status,
                pageable
        );
    }

    @Override
    public Page<DolbomRes> getMatchingDolbom(AddressListReq addressReq, Pageable pageable) {
        return dolbomRepository.findByMemberIdAndAddressAndStatus(null,
                addressReq,
                DolbomStatus.MATCHING,
                pageable);

    }

    @Override
    public Page<DolbomRes> getTeacherDolbom(DolbomStatus status, Pageable pageReq) {
        return dolbomRepository.findByTeacherIdAndStatus(authUtil.getLoginMemberId(), status, pageReq);
    }

    @Override
    public Page<DolbomRes> getTeacherAppliedDolbom(Pageable pageReq) {
        return dolbomRepository.findByAppliedTeacherId(authUtil.getLoginMemberId(), pageReq);
    }

    @Override
    @Transactional
    public void apply(Long dolbomId) {
        Long teacherId = authUtil.getLoginMemberId();
        Optional<TeacherProfile> teacherProfile = teacherProfileRepository.findByMemberId(teacherId);
        if (teacherProfile.isEmpty()) {
            throw new CustomException("프로필을 등록해주세요");
        }
        Optional<DolbomAppliedTeacher> appliedTeacher = dolbomAppliedTeacherRepository.findByDolbomIdAndTeacherProfileId(dolbomId, teacherProfile.get().getId());
        if (appliedTeacher.isPresent()) {
            throw new CustomException("이미 지원한 돌봄입니다");
        }
        DolbomAppliedTeacher dolbomAppliedTeacher = new DolbomAppliedTeacher(
                entityManager.getReference(Dolbom.class, dolbomId),
                teacherProfile.get()
        );
        dolbomAppliedTeacherRepository.save(dolbomAppliedTeacher);

        chatService.askDolbom(dolbomId);
    }
}
