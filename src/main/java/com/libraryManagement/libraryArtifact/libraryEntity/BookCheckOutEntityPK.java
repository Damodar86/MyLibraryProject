package com.libraryManagement.libraryArtifact.libraryEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Embeddable
@Getter
@Setter
public class BookCheckOutEntityPK implements Serializable {
    @Column(name = "BOOK_BARCODE")
    private String bookBarCode;
    @Column(name = "BORRWOR_ACCOUNT_ID")
    private Long borrowerAccountId;
    @Column(name = "BORROWED_DATE")
    private LocalDate borrowedDate;
}
