package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BookHistoryByBarCodeReturnModel {
    private String barCode;
    private Long accountId;
    private LocalDateTime barrowDate;
    private LocalDateTime renewalDate;
    private String issuedBy;
    private int penalty;
    private int renewalCount;
    private LocalDateTime returnDate;


}
