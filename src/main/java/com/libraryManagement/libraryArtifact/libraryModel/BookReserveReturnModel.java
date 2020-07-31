package com.libraryManagement.libraryArtifact.libraryModel;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.BookStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookReserveReturnModel {
private String bookBarCode;
private BookStatus bookStatus;

}
