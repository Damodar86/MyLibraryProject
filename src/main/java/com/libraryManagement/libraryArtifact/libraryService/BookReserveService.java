package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.BookStatus;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.BookEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.BookReserveEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.LibraryAccountEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookReserveModel;
import com.libraryManagement.libraryArtifact.libraryModel.BookReserveReturnModel;
import com.libraryManagement.libraryArtifact.libraryRepository.AccountRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.BookRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.BookReserveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookReserveService {

    private BookCheckOutRepository bookCheckOutRepository;
    private AccountRepository accountRepository;
    private BookRepository bookRepository;
    private BookReserveRepository bookReserveRepository;

    public BookReserveService(BookCheckOutRepository bookCheckOutRepository, AccountRepository accountRepository, BookRepository bookRepository, BookReserveRepository bookReserveRepository) {
        this.bookCheckOutRepository = bookCheckOutRepository;
        this.accountRepository = accountRepository;
        this.bookRepository = bookRepository;
        this.bookReserveRepository = bookReserveRepository;
    }

    BookReserveReturnModel bookReserveReturnModel = new BookReserveReturnModel();

    public BookReserveReturnModel bookReserveMethod(BookReserveModel bookReserveModel) throws LibraryException {

        Optional<LibraryAccountEntity> libraryAccountEntity = accountRepository.findByAccountIdAndPassword(bookReserveModel.getMemberAccountId(), bookReserveModel.getPassword());

        if (libraryAccountEntity.isPresent()) {

            LibraryAccountEntity libraryAccountEntity1 = libraryAccountEntity.get();

            if (libraryAccountEntity1.getAccountStatus().equalsIgnoreCase("Active")) {

                Optional<BookCheckOutEntity> bookCheckOutEntities = bookCheckOutRepository.findByBookBarCode(bookReserveModel.getBookBarCode());
                Optional<BookEntity> bookEntity = bookRepository.findByBarcodeAndBookStatus(bookReserveModel.getBookBarCode(), BookStatus.Loaned.name());

                if (bookCheckOutEntities.isPresent() && bookEntity.isPresent()) {

                    BookEntity bookEntity2 = bookEntity.get();

                    BookCheckOutEntity bookCheckOutEntity = bookCheckOutEntities.get();

                    int reserveDays = LocalDateTime.now().compareTo(bookCheckOutEntity.getRenewalDate());

                    if (reserveDays <= 0) {

                        bookEntity2.setBookStatus(BookStatus.Reserved.name());
                        bookRepository.save(bookEntity2);
                        BookReserveEntity bookReserveEntity = new BookReserveEntity();
                        bookReserveEntity.setAccount_Id(bookReserveModel.getMemberAccountId());
                        bookReserveEntity.setBookBarCode(bookReserveModel.getBookBarCode());
                        bookReserveEntity.setReserveDate(LocalDateTime.now());
                        bookReserveEntity.setReservedStatus("Still Reserved");
                        bookReserveRepository.save(bookReserveEntity);

                        bookReserveReturnModel.setBookBarCode(bookReserveModel.getBookBarCode());
                        bookReserveReturnModel.setBookStatus(BookStatus.Reserved);


                    }


                } else {
                    throw new LibraryException(LibraryErrorMessages.INVALID_GIVEN_BARCODE);
                }

            } else {
                throw new LibraryException(LibraryErrorMessages.SORRY_GIVEN_ACCOUNT_ID_WAS_CLOSED_ALREADY);
            }

        } else {
            throw new LibraryException(LibraryErrorMessages.SORRY_GIVEN_ACCOUNTID_PASSWORD_INVALID);
        }

        return bookReserveReturnModel;
    }


}

