package com.libraryManagement.libraryArtifact.libraryController;

import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookRe_ReturnModel;
import com.libraryManagement.libraryArtifact.libraryModel.BookReturnModel;
import com.libraryManagement.libraryArtifact.libraryService.BookReturnService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookReturnController {
    private BookReturnService bookReturnService;

    public BookReturnController(BookReturnService bookReturnService) {
        this.bookReturnService = bookReturnService;
    }
    @PostMapping(value = "/returnBook")
    public BookRe_ReturnModel returnBookToLibrary(@RequestBody BookReturnModel bookReturnModel) throws LibraryException {
        return bookReturnService.bookReturnMethod(bookReturnModel);
    }
}
