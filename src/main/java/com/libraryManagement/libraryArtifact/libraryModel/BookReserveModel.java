package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BookReserveModel {

    private Long memberAccountId;
    private String password;
    private String bookBarCode;
}
