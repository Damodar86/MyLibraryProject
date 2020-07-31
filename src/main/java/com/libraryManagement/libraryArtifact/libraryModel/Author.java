package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;

@Getter

public class Author {
    private String authorName;
    private String description;
    public String getName(){

        return authorName;
    }
}
