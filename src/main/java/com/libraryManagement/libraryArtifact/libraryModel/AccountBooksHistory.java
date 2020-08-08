package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AccountBooksHistory {
    private String bookBarCode;
    private Long borrowerAccountId;
    private LocalDate borrowedDate;
    private LocalDate renewalDate;
    private String issuedById;
    private LocalDate returnedDate;
    private int penalty;
    private Integer renewalCount;
}
