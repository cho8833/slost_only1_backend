package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.AuthProvider;
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
@SQLDelete(sql = "UPDATE oauth SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class OAuth extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    Member member;

    @Column
    String userId;

    @Column
    AuthProvider authProvider;
}
