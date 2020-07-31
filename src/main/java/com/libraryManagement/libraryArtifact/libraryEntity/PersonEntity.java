package com.libraryManagement.libraryArtifact.libraryEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "LIB_PERSON")
public class PersonEntity {
    @Id
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NO")
    private String phoneNumber;

    @Column(name = "ADDRESS_ID")
    private Long addressId;

}
