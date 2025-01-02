package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.DolbomLocationRes;
import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.DolbomTimeSlotRes;
import com.slost_only1.slost_only1.data.KidRes;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

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

    private final KidRepository kidRepository;

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

        Page<Dolbom> dolboms = dolbomRepository.findByMemberIdAndAddressAndStatus(
                memberId,
                new AddressListReq(),
                status,
                pageable
        );

        List<DolbomRes> dolbomRes = fetchDolbomDetails(dolboms.getContent());

        Page<DolbomRes> dolbomResPage = new PageImpl<>(
                dolbomRes,
                dolboms.getPageable(),
                dolboms.getTotalElements()
        );

        return dolbomResPage;
    }

    @Override
    public Page<DolbomRes> getMatchingDolbom(AddressListReq addressReq, Pageable pageable) {
        Page<Dolbom> dolboms = dolbomRepository.findByMemberIdAndAddressAndStatus(null,
                addressReq,
                DolbomStatus.MATCHING,
                pageable);

        List<DolbomRes> dolbomRes = fetchDolbomDetails(dolboms.getContent());

        Page<DolbomRes> dolbomResPage = new PageImpl<>(
                dolbomRes,
                dolboms.getPageable(),
                dolboms.getTotalElements()
        );

        return dolbomResPage;
    }

    @Override
    public Page<DolbomRes> getTeacherDolbom(DolbomStatus status, Pageable pageReq) {
        Page<Dolbom> dolboms = dolbomRepository.findByTeacherIdAndStatus(
                authUtil.getLoginMemberId(),
                status,
                pageReq
        );

        List<DolbomRes> dolbomRes = fetchDolbomDetails(dolboms.getContent());

        Page<DolbomRes> dolbomResPage = new PageImpl<>(
                dolbomRes,
                dolboms.getPageable(),
                dolboms.getTotalElements()
        );

        return dolbomResPage;
    }

    @Override
    public Page<DolbomRes> getTeacherAppliedDolbom(Pageable pageReq) {
        Page<Dolbom> dolboms = dolbomRepository.findByAppliedTeacherId(
                authUtil.getLoginMemberId(),
                pageReq
        );

        List<DolbomRes> dolbomRes = fetchDolbomDetails(dolboms.getContent());

        Page<DolbomRes> dolbomResPage = new PageImpl<>(
                dolbomRes,
                dolboms.getPageable(),
                dolboms.getTotalElements()
        );

        return dolbomResPage;
    }

    @Override
    @Transactional
    public void apply(Long dolbomId) {
        Long teacherId = authUtil.getLoginMemberId();
        TeacherProfile teacherProfile = teacherProfileRepository.findByMemberId(teacherId).orElseThrow();
        if (!teacherProfile.isApproved()) {
            throw new CustomException(ResponseCode.NOT_APPROVED_TEACHER);
        }
        Optional<DolbomAppliedTeacher> appliedTeacher
                = dolbomAppliedTeacherRepository.findByDolbomIdAndTeacherProfileId(dolbomId, teacherProfile.getId());
        if (appliedTeacher.isPresent()) {
            throw new CustomException("이미 지원한 돌봄입니다");
        }
        DolbomAppliedTeacher dolbomAppliedTeacher = new DolbomAppliedTeacher(
                entityManager.getReference(Dolbom.class, dolbomId),
                teacherProfile
        );
        dolbomAppliedTeacherRepository.save(dolbomAppliedTeacher);

        chatService.askDolbom(dolbomId);
    }

    @Override
    public void match(Long dolbomId, Long teacherProfileId) {
        Dolbom dolbom = dolbomRepository.findById(dolbomId).orElseThrow();
        TeacherProfile teacherProfile = entityManager.getReference(TeacherProfile.class, teacherProfileId);

        dolbom.setTeacherProfile(teacherProfile);
        dolbom.setStatus(DolbomStatus.RESERVED);

        dolbomRepository.save(dolbom);
    }

    private List<DolbomRes> fetchDolbomDetails(List<Dolbom> dolboms) {
        List<Long> dolbomIds = dolboms.stream().map(Dolbom::getId).toList();
        Map<Long, List<DolbomDow>> dows = dolbomDowRepository.findByDolbom_IdIn(dolbomIds).stream()
                .collect(Collectors.groupingBy(dow -> dow.getDolbom().getId()));

        Map<Long, List<Kid>> kids = kidRepository.findByDolbomIdsIn(dolbomIds);

        Map<Long, List<DolbomTimeSlot>> timeSlots = dolbomTimeSlotRepository.findByDolbom_IdIn(dolbomIds).stream()
                .collect(Collectors.groupingBy(timeSlot -> timeSlot.getDolbom().getId()));

        return dolboms.stream()
                .map(dolbom -> {
                    DolbomRes dto = new DolbomRes(
                            dolbom.getId(),
                            kids.getOrDefault(dolbom.getId(), Collections.emptyList()).stream().map(KidRes::of).toList(),
                            timeSlots.getOrDefault(dolbom.getId(), Collections.emptyList()).stream().map(DolbomTimeSlotRes::from).toList(),
                            dows.getOrDefault(dolbom.getId(), Collections.emptyList()).stream().map(DolbomDow::getDayOfWeek).toList(),
                            DolbomLocationRes.of(dolbom.getDolbomLocation()),
                            dolbom.getTeacherProfile() != null ? dolbom.getTeacherProfile().getId() : null,
                            dolbom.getStartTime(),
                            dolbom.getEndTime(),
                            dolbom.getStatus(),
                            dolbom.getCategory(),
                            dolbom.getWeeklyRepeat(),
                            dolbom.getSetSeveralTime(),
                            dolbom.getRepeatStartDate(),
                            dolbom.getRepeatEndDate(),
                            dolbom.getPay()
                    );
                    return dto;
                })
                .toList();
    }
}
