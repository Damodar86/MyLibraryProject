package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;
    private String password;
    private String accountType;

}
