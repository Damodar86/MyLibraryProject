package com.libraryManagement.libraryArtifact.libraryEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "BOOK_CHECK_OUT")
@IdClass(BookCheckOutEntityPK.class)
public class BookCheckOutEntity {
    @Id
    @Column(name = "BOOK_BARCODE")
    private String bookBarCode;
    @Id
    @Column(name = "BORRWOR_ACCOUNT_ID")
    private Long borrowerAccountId;
    @Id
    @Column(name = "BORROWED_DATE")
    private LocalDate borrowedDate;
    @Column(name = "RENUAL_DATE")
    private LocalDate renewalDate;
    @Column(name = "ISSUED_BY_ID")
    private String issuedById;
    @Column(name = "RETURNED_DATE")
    private LocalDate returnedDate;
    @Column(name = "PENALITY")
   private int penality;
    @Column(name = "RENEWAL_COUNT")
    private Integer renewalCount;
}
