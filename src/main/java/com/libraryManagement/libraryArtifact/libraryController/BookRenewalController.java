package com.libraryManagement.libraryArtifact.libraryController;

import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookRenewalModel;
import com.libraryManagement.libraryArtifact.libraryService.BookRenewalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRenewalController {
    private BookRenewalService bookRenewalService;

    public BookRenewalController(BookRenewalService bookRenewalService) {
        this.bookRenewalService = bookRenewalService;
    }

    @PostMapping(value = "/renewalBook")
        public String bookRenewal(@RequestBody BookRenewalModel bookrenewalmodel) throws LibraryException {
        return bookRenewalService.bookRenewalServiceMethod(bookrenewalmodel);
    }
}
