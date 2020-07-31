package com.libraryManagement.libraryArtifact.libraryController;

import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookCheckOutModel;
import com.libraryManagement.libraryArtifact.libraryService.CheckOutBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookCheckOutController {
    private CheckOutBookService checkOutBookService;

    public BookCheckOutController(CheckOutBookService checkOutBookService) {
        this.checkOutBookService = checkOutBookService;
    }

    @PostMapping(value = "/bookCheckOut")
    public String checkOutBook(@RequestBody BookCheckOutModel bookCheckOutModel) throws LibraryException {
        return checkOutBookService.assignBookToUser(bookCheckOutModel);

    }
}
