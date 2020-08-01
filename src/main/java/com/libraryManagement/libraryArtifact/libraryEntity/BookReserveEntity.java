package com.libraryManagement.libraryArtifact.libraryEntity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "BOOK_RESERVATION_TABLE")
public class BookReserveEntity {
    @Id
    @Column(name = "ACCOUNT_ID")
    private Long account_Id;
    @Column(name = "BOOK_BARCODE")
    private String bookBarCode;
    @Column(name = "RESERVED_DATE")
    private LocalDate reserveDate;
    @Column(name = "RESERVED_STATUS")
    private String reservedStatus;


}
