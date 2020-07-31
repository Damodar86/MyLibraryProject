package com.libraryManagement.libraryArtifact.libraryModel;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.AccountStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDeletionModel {
    private Long userAccountId;
    private AccountStatus accountStatus;
    private Long librarianAccountId;
    private String password;

}
