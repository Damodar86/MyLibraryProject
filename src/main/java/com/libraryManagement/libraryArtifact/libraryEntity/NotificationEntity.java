package com.libraryManagement.libraryArtifact.libraryEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION_TABLE")
public class NotificationEntity {
    @Id
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
}
