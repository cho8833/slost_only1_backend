package com.slost_only1.slost_only1;

import com.slost_only1.slost_only1.enums.Gender;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.enums.TeacherProfileStatus;
import com.slost_only1.slost_only1.model.*;
import com.slost_only1.slost_only1.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
    private MemberRepository memberRepository;

    @Autowired
    private DolbomRepository dolbomRepository;

    @Autowired
    private TeacherProfileRepository teacherProfileRepository;

    @Autowired
    private DolbomAppliedTeacherRepository dolbomAppliedTeacherRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private AvailableAreaRepository availableAreaRepository;

    @Test
    public void createParentDummy() {

        LocalDateTime now = LocalDateTime.now();
        Member member1 = new Member(
                "username3",
                "password",
                "01034567890",
                MemberRole.PARENT
        );
        Kid kid1 = new Kid(
                member1,
                "3번아이", now.minusYears(5), Gender.MALE, "귀여움", "행동이 서툼"
        );
        DolbomLocation dolbomLocation1 = new DolbomLocation(
                new Address("경남", "창원시 마산합포구", "장천동", "경상남도 창원시 행암로 25", "115동 404호"),
                member1, "우리집"
        );

        memberRepository.save(member1);
        kidRepository.save(kid1);
        dolbomLocationRepository.save(dolbomLocation1);
    }

    @Test
    public void createTeacher() {
        Member member = new Member(
                "username2",
                "password",
                "01023456789",
                MemberRole.TEACHER
        );
        memberRepository.save(member);

        TeacherProfile teacherProfile = new TeacherProfile(
            "선생님1",
                Gender.FEMALE,
                LocalDate.of(2000, 8, 13),
                "좋은 선생님",
                "https://s.pstatic.net/static/www/mobile/edit/20240112_1095/upload_1705057885416AaxUM.png",
                TeacherProfileStatus.APPROVED,
                member
        );
        teacherProfileRepository.save(teacherProfile);

        Certificate certificate1 = new Certificate("9급 자격증", member);
        Certificate certificate2 = new Certificate("8급 자격증", member);

        certificateRepository.saveAll(List.of(certificate1, certificate2));

        AvailableArea area1 = new AvailableArea(
                "경남", "창원시 진해구", teacherProfile
        );
        AvailableArea area2 = new AvailableArea(
                "경남", "창원시 마산합포구", teacherProfile
        );
        availableAreaRepository.saveAll(List.of(area1, area2));
    }

    @Test
    public void createDummyData2() {
        LocalDateTime now = LocalDateTime.now();

        List<Member> members = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            members.add(new Member(
                    "username" + i,
                    "password" + i,
                    "0101234567" + i,
                    MemberRole.PARENT
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
        dolbomLocations.add(new DolbomLocation(
                new Address("경상남도", "창원시", "장천동", "경상남도 창원시 행암로 25", "117동 405호"), members.get(0), "집1"));
        dolbomLocations.add(new DolbomLocation(new Address("서울특별시", "강남구", "삼성동", "서울 강남구 테헤란로 45", "101동 205호"), members.get(1), "집2"));
        dolbomLocations.add(new DolbomLocation(new Address("부산광역시", "해운대구", "우동", "부산 해운대구 우동로 76", "3동 502호"), members.get(2), "집3"));
        dolbomLocations.add(new DolbomLocation(new Address("대구광역시", "수성구", "지산동", "대구 수성구 지산로 39", "202동 1101호"), members.get(3), "집4"));
        dolbomLocations.add(new DolbomLocation(new Address("광주광역시", "서구", "상무동", "광주 서구 상무대로 18", "5동 307호"), members.get(4), "집5"));
        dolbomLocationRepository.saveAll(dolbomLocations);
    }
}
