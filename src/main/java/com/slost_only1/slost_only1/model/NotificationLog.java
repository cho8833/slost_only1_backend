package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@SQLDelete(sql = "UPDATE notification_log SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class NotificationLog extends BaseEntity {

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private LocalDateTime sentAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member to;

}
