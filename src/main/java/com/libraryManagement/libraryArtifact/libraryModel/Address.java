package com.libraryManagement.libraryArtifact.libraryModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
