package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.BookEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookRe_ReturnModel;
import com.libraryManagement.libraryArtifact.libraryModel.BookReturnModel;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookReturnService {
    private BookCheckOutRepository bookCheckOutRepository;
    private BookRepository bookRepository;

    public BookReturnService(BookCheckOutRepository bookCheckOutRepository, BookRepository bookRepository) {
        this.bookCheckOutRepository = bookCheckOutRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookRe_ReturnModel bookReturnMethod(BookReturnModel bookReturnModel) throws LibraryException {

        Optional<BookCheckOutEntity> bookCheckOutEntity = bookCheckOutRepository.findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookReturnModel.getBarCode(), bookReturnModel.getAccountId());
        BookRe_ReturnModel bookRe_returnModel = new BookRe_ReturnModel();
        if (bookCheckOutEntity.isPresent()) {
            BookCheckOutEntity bookCheckOutEntity1 = bookCheckOutEntity.get();
            bookCheckOutEntity1.setReturnedDate(LocalDate.now());
            int daysPassedRenewalDate = LocalDate.now().compareTo(bookCheckOutEntity1.getRenewalDate());
            if (daysPassedRenewalDate > 0) {

                bookCheckOutEntity1.setPenality(daysPassedRenewalDate * 10);
            }
            bookCheckOutRepository.save(bookCheckOutEntity1);
            Optional<BookEntity> bookEntity = bookRepository.findById(bookReturnModel.getBarCode());
            BookEntity bookEntity1 = bookEntity.get();
            bookEntity1.setBookStatus("Available");
            bookRepository.save(bookEntity1);

            bookRe_returnModel.setAccountId(bookCheckOutEntity1.getBorrowerAccountId());
            bookRe_returnModel.setBarCode(bookCheckOutEntity1.getBookBarCode());
            bookRe_returnModel.setBookTitle(bookEntity1.getTitle());
        } else {
            throw new LibraryException(LibraryErrorMessages.INVALID_GIVEN_BARCODE_AND_ACCOUNT_ID);
        }
        return bookRe_returnModel;
    }
}
