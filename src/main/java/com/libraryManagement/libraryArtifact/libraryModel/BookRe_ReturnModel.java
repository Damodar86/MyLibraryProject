package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRe_ReturnModel {
    private String barCode;
    private Long accountId;
    private String bookTitle;
}
