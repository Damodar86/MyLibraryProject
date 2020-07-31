package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCancellationReturnModel {
    private Long accountId;
    private String accountStatus;
    private Long librarianAccountId;
}
