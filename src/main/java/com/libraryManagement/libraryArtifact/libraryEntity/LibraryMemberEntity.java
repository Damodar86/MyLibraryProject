package com.libraryManagement.libraryArtifact.libraryEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "LIBRARYMANAGMENT")
public class LibraryMemberEntity {
    @Column(name = "MEMBERNAME")
    private String memberName;
    @Column(name = "AGE")
    private String age;
    @Column(name = "FATHER")
    private String fatherName;
    @Column(name = "MOTHER")
    private String motherName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "ROLLNO")
    private String rollNo;
    @Column(name = "MOBILENO")
    private String mobile;
    @Column(name = "COURSE")
    private String course;

    @Column(name = "IDNUMBER")
    @Id
    private Long idNumber;

}
