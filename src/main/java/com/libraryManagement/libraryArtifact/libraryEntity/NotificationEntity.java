package com.libraryManagement.libraryArtifact.libraryEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "LIBRARY_NOTIFICATION_TABLE")
public class NotificationEntity {

    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Column(name = "BORROWED_DATE")
    private LocalDate borrowedDate;
    @Column(name = "RENEWAL_DATE")
    private LocalDate renewalDate;
    @Column(name = "NOTIFICATION_DATE")
    private LocalDate notificationDate;
    @Id
    @Column(name = "BOOK_BARCODE")
    private String bookBarCode ;
    @Column(name = "PERSON_EMAILID")
    private String personEmailId ;
    @Column(name = "MESSAGE")
    private String message;
}
