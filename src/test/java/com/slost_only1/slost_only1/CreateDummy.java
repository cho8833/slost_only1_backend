package com.slost_only1.slost_only1;

import com.slost_only1.slost_only1.enums.Gender;
import com.slost_only1.slost_only1.model.*;
import com.slost_only1.slost_only1.repository.DolbomLocationRepository;
import com.slost_only1.slost_only1.repository.DolbomNoticeRepository;
import com.slost_only1.slost_only1.repository.KidRepository;
import com.slost_only1.slost_only1.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CreateDummy {

    @Autowired
    private DolbomLocationRepository dolbomLocationRepository;

    @Autowired
    private KidRepository kidRepository;

    @Autowired
    private DolbomNoticeRepository dolbomNoticeRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void createDummyData() {

        LocalDateTime now = LocalDateTime.now();
        Member member1 = new Member(
                "username1",
                "password",
                "01012345678"
        );
        Kid kid1 = new Kid(
                "홍길동", 9, Gender.MALE, "귀여움", "행동이 서툼"
        );
        DolbomLocation dolbomLocation1 = new DolbomLocation(
                new Address("경상남도", "창원시", "장천동", "경상남도 창원시 행암로 25", "117동 405호")
        );

        DolbomNotice dolbomNotice1 = new DolbomNotice(
                now, now.plusMonths(1), 100000L, member1, dolbomLocation1, kid1
        );

        memberRepository.save(member1);
        kidRepository.save(kid1);
        dolbomLocationRepository.save(dolbomLocation1);
        dolbomNoticeRepository.save(dolbomNotice1);
    }
}
