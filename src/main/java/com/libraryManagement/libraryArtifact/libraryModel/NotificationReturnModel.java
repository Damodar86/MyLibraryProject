package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
public class NotificationReturnModel {
    private Long accountId;
    private LocalDate bookBorrowDate;
    private String message;
    private LocalDate bookRenewalDate;
    private String bookBarCode;


}
