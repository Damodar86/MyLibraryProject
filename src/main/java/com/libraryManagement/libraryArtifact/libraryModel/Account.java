package com.libraryManagement.libraryArtifact.libraryModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.AccountStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    private Long id;
    private AccountStatus status;
    private String accountType;
    private String password;
}
