package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class BookHistoryByBarCodeReturnModel {
    private String barCode;
    private Long accountId;
    private LocalDate barrowDate;
    private LocalDate renewalDate;
    private String issuedBy;
    private int penalty;
    private int renewalCount;
    private LocalDate returnDate;


}
