package com.slost_only1.slost_only1.model;


import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.Gender;
import com.slost_only1.slost_only1.enums.TeacherProfileStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@Getter
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

    @Column
    private String profileImageUrl;

    @Column
    private TeacherProfileStatus status;

    @ManyToOne
    private Member member;

}
