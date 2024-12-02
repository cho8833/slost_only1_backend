package com.slost_only1.slost_only1.model;


import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.Gender;
import com.slost_only1.slost_only1.enums.TeacherProfileStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE teacher_profile SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class TeacherProfile extends BaseEntity {

    @Column
    private String name;

    @Column
    private Gender gender;

    @Column
    private LocalDate birthday;

    @Column
    private String profileName;

    @Column(length = 1024)
    private String profileImageUrl;

    @Column
    private TeacherProfileStatus status;

    @Column
    private String introduce;

    @Column
    private String howBecameTeacher;

    @ManyToOne
    private Member member;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="teacher_profile_id")
    private List<AvailableAge> availableAges;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="teacher_profile_id")
    private List<AvailableCategory> availableCategories;

    public TeacherProfile(Member member) {
        this.member = member;
    }

    public Boolean isApproved() {
        return TeacherProfileStatus.APPROVED.equals(this.status);
    }
}
