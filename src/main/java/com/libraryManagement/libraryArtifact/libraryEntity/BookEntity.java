package com.libraryManagement.libraryArtifact.libraryEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BOOKTABLE")
public class BookEntity {
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "PUBLISHER")
    private String publisher;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "NOOFPAGES")
    private int noOfPages;
    @Id
    @Column(name = "BARCODE")
    private String barcode;
    @Column(name = "ISREFERENCE")
    private String isReference;
    @Column(name = "BORROWEDDATE")
    private Date borrowedDate;
    @Column(name = "DUEDATE")
    private Date dueDate;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "FORMAT")
    private String bookFormat;
    @Column(name = "STATUS")
    private String bookStatus;
    @Column(name = "DATEOFPURCHASE")
    private Date dateOfPurchase;
    @Column(name = "PUBLICATIONDATE")
    private Date publicationDate;



}
