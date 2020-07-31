package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookRenewalModel;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BookRenewalService {

    private BookCheckOutRepository bookCheckOutRepository;

    public BookRenewalService(BookCheckOutRepository bookCheckOutRepository) {
        this.bookCheckOutRepository = bookCheckOutRepository;
    }

    public String bookRenewalServiceMethod(BookRenewalModel bookrenewalmodel) throws LibraryException {
        Optional<BookCheckOutEntity> bookCheckOutEntity = bookCheckOutRepository.findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookrenewalmodel.getBarCode(), bookrenewalmodel.getAccountId());
        if (bookCheckOutEntity.isPresent()) {
            return null;
        } else {
            log.info("entered into is present else block ");
            throw new LibraryException(LibraryErrorMessages.INVALID_GIVEN_BORROWED_DATE);
        }
    }
}


