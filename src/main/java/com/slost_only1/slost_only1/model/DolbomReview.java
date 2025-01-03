package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.ReviewReportReason;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE dolbom_review SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class DolbomReview extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Dolbom dolbom;

    @Column
    private String content;

    @Column
    private Long star;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private TeacherProfile teacherProfile;

    @Column
    private String reportContent;

    @Column
    private ReviewReportReason reportReason;
}
