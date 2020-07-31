package com.libraryManagement.libraryArtifact.libraryModel;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.BookFormat;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.BookStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class BookItemModel extends Book {
    private String barcode;
    private String isReference;
    private Date borrowedDate;
    private Date dueDate;
    private int price;
    private BookFormat bookFormat;
    private BookStatus bookStatus;
    private Date dateOfPurchase;
    private Date publicationDate;


}
