package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class BookCheckOutModel {

    private Long accountId;
    private List <BookDetails> selectedBooksList;


}
