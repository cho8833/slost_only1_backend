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
import java.util.ArrayList;
import java.util.List;

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
    public void createDummyData1() {

        LocalDateTime now = LocalDateTime.now();
        Member member1 = new Member(
                "username1",
                "password",
                "01012345678"
        );
        Kid kid1 = new Kid(
                member1,
                "홍길동", now.minusYears(5), Gender.MALE, "귀여움", "행동이 서툼"
        );
        DolbomLocation dolbomLocation1 = new DolbomLocation(
                new Address("경상남도", "창원시", "장천동", "경상남도 창원시 행암로 25", "117동 405호"),
                member1
        );

        DolbomNotice dolbomNotice1 = new DolbomNotice(
                now, now.plusMonths(1), 100000L, member1, dolbomLocation1, kid1
        );

        memberRepository.save(member1);
        kidRepository.save(kid1);
        dolbomLocationRepository.save(dolbomLocation1);
        dolbomNoticeRepository.save(dolbomNotice1);
    }

    @Test
    public void createDummyData2() {
//        dolbomNoticeRepository.deleteAll();
//        dolbomLocationRepository.deleteAll();
//        kidRepository.deleteAll();
//        memberRepository.deleteAll();
        LocalDateTime now = LocalDateTime.now();

        List<Member> members = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            members.add(new Member(
                    "username" + i,
                    "password" + i,
                    "0101234567" + i
            ));
        }
        memberRepository.saveAll(members);

        List<Kid> kids = new ArrayList<>();
        kids.add(new Kid(members.get(0), "홍길동", now.minusYears(4), Gender.MALE, "귀여움", "행동이 서툼"));
        kids.add(new Kid(members.get(1), "김철수", now.minusYears(5), Gender.MALE, "말이 많음", "장난이 심함"));
        kids.add(new Kid(members.get(2), "이영희", now.minusYears(6), Gender.FEMALE, "웃음이 많음", "수줍음"));
        kids.add(new Kid(members.get(3), "박민수", now.minusYears(7), Gender.MALE, "활발함", "집중력 부족"));
        kids.add(new Kid(members.get(4), "최수지", now.minusYears(8), Gender.FEMALE, "상냥함", "순발력 부족"));
        kidRepository.saveAll(kids);

        List<DolbomLocation> dolbomLocations = new ArrayList<>();
        dolbomLocations.add(new DolbomLocation(new Address("경상남도", "창원시", "장천동", "경상남도 창원시 행암로 25", "117동 405호"), members.get(0)));
        dolbomLocations.add(new DolbomLocation(new Address("서울특별시", "강남구", "삼성동", "서울 강남구 테헤란로 45", "101동 205호"), members.get(1)));
        dolbomLocations.add(new DolbomLocation(new Address("부산광역시", "해운대구", "우동", "부산 해운대구 우동로 76", "3동 502호"), members.get(2)));
        dolbomLocations.add(new DolbomLocation(new Address("대구광역시", "수성구", "지산동", "대구 수성구 지산로 39", "202동 1101호"), members.get(3)));
        dolbomLocations.add(new DolbomLocation(new Address("광주광역시", "서구", "상무동", "광주 서구 상무대로 18", "5동 307호"), members.get(4)));
        dolbomLocationRepository.saveAll(dolbomLocations);

        List<DolbomNotice> dolbomNotices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dolbomNotices.add(new DolbomNotice(
                    now,
                    now.plusMonths(1),
                    100000L + (i * 5000),
                    members.get(i),
                    dolbomLocations.get(i),
                    kids.get(i)
            ));
        }
        dolbomNoticeRepository.saveAll(dolbomNotices);
    }
}
