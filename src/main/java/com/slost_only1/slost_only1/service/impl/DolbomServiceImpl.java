package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.data.req.DolbomListReq;
import com.slost_only1.slost_only1.data.req.DolbomPostReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import com.slost_only1.slost_only1.model.*;
import com.slost_only1.slost_only1.repository.DolbomDowRepository;
import com.slost_only1.slost_only1.repository.DolbomRepository;
import com.slost_only1.slost_only1.repository.DolbomTimeSlotRepository;
import com.slost_only1.slost_only1.repository.KidDolbomRepository;
import com.slost_only1.slost_only1.service.DolbomService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.Time;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DolbomServiceImpl implements DolbomService {

    private final DolbomRepository dolbomRepository;

    private final DolbomDowRepository dolbomDowRepository;

    private final KidDolbomRepository kidDolbomRepository;

    private final DolbomTimeSlotRepository dolbomTimeSlotRepository;

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
    public Page<DolbomRes> getMyDolbom(AddressListReq addressListReq, DolbomStatus status, Pageable pageable) {
        Long memberId = authUtil.getLoginMemberId();

        return dolbomRepository.findByMemberIdAndAddressAndStatus(
                memberId,
                addressListReq,
                status,
                pageable
        );
    }
}
