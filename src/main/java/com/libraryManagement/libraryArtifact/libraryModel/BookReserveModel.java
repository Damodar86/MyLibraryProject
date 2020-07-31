package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookReserveModel {

    private Long memberAccountId;
    private String password;
    private String bookBarCode;
}
