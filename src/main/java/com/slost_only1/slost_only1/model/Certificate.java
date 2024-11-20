package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.data.req.CertificateCreateReq;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE certificate SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class Certificate extends BaseEntity {
    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private TeacherProfile teacherProfile;

    public static Certificate of(CertificateCreateReq req, TeacherProfile teacherProfile) {
        return new Certificate(req.getTitle(), teacherProfile);
    }

}
