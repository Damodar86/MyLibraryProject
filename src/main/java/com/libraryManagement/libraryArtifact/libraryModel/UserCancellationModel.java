package com.libraryManagement.libraryArtifact.libraryModel;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.AccountStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCancellationModel{
    private Long librarianAccountId;
    private String password;
    private Long memberAccountId;
    private AccountStatus accountStatus;

}
