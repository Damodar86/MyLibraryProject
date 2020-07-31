package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationModel {
    private LocalDateTime bookReturnDate;
    private LocalDateTime renewalDate;

}
