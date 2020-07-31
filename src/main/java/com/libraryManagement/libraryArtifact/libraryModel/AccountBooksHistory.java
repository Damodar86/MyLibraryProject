package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AccountBooksHistory {
    private String bookBarCode;
    private Long borrowerAccountId;
    private LocalDateTime borrowedDate;
    private LocalDateTime renewalDate;
    private String issuedById;
    private LocalDateTime returnedDate;
    private int penalty;
    private Integer renewalCount;
}
