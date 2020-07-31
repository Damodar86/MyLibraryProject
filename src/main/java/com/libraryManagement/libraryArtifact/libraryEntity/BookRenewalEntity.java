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
@Table(name = "BOOKRENEWALTABLE")
public class BookRenewalEntity {
    @Column(name = "ACCOUNT_ID")
    @Id
    private Long account_Id;
    @Column(name = "BOOKBARCODE")
    private String barCode;
    @Column(name = "RENEWALDATE")
    private LocalDateTime renewalDate;
    @Column(name = "NEXTRENEWALDATE")
    private LocalDateTime nextRenewalDate;
}
