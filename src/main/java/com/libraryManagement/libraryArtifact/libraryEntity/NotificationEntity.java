package com.libraryManagement.libraryArtifact.libraryEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "LIBRARY_NOTIFICATION_TABLE")
public class NotificationEntity {

    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Column(name = "BORROWED_DATE")
    private LocalDateTime borrowedDate;
    @Column(name = "RENEWAL_DATE")
    private LocalDateTime renewalDate;
    @Column(name = "NOTIFICATION_DATE")
    private LocalDateTime notificationDate;
    @Id
    @Column(name = "BOOK_BARCODE")
    private String bookBarCode ;
    @Column(name = "PERSON_EMAILID")
    private String personEmailId ;
    @Column(name = "MESSAGE")
    private String message;
}
