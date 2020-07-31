package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
public class NotificationReturnModel {
    private Long accountId;
    private LocalDateTime bookBorrowDate;
    private String message;
    private LocalDateTime bookRenewalDate;


}
