package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FindByTitle {

    private String publisher;
    private int  noOfPages;
    private String barCode;
}
