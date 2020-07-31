package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.BookStatus;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.BookEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.LibraryAccountEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookCheckOutModel;
import com.libraryManagement.libraryArtifact.libraryModel.BookDetails;
import com.libraryManagement.libraryArtifact.libraryRepository.AccountRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CheckOutBookService {
    private AccountRepository accountRepository;
    private BookRepository bookRepository;
    private BookCheckOutRepository bookCheckOutRepository;

    public CheckOutBookService(AccountRepository accountRepository, BookRepository bookRepository, BookCheckOutRepository bookCheckOutRepository) {
        this.accountRepository = accountRepository;
        this.bookRepository = bookRepository;
        this.bookCheckOutRepository = bookCheckOutRepository;
    }

    @Transactional
    public String assignBookToUser(BookCheckOutModel bookCheckOutModel) throws LibraryException {
        Optional<LibraryAccountEntity> libraryAccountEntity = accountRepository.findById(bookCheckOutModel.getAccountId());
        libraryAccountEntity.orElseThrow(() -> new LibraryException(LibraryErrorMessages.INVALID_ACCOUNT_ID));
        if (bookCheckOutModel.getSelectedBooksList() != null) {
            for (BookDetails bookDetails : bookCheckOutModel.getSelectedBooksList()) {

                Optional<BookEntity> bookEntityList = bookRepository.findByBarcodeAndBookStatus(bookDetails.getBarCode(), BookStatus.Available.name());
                if (bookEntityList.isPresent()) {
                    BookEntity bookEntity = bookEntityList.get();
                    BookCheckOutEntity bookCheckOutEntity = new BookCheckOutEntity();
                    bookCheckOutEntity.setBookBarCode(bookEntity.getBarcode());
                    bookCheckOutEntity.setBorrowerAccountId(bookCheckOutModel.getAccountId());
                    bookCheckOutEntity.setBorrowedDate(LocalDateTime.now());
                    bookCheckOutEntity.setRenewalDate(LocalDateTime.now().plusDays(14));
                    bookCheckOutEntity.setIssuedById("ONLINE");
                    bookCheckOutEntity.setRenewalCount(0);
                    bookCheckOutRepository.save(bookCheckOutEntity);
                    bookEntity.setBookStatus(BookStatus.Loaned.name());
                    bookRepository.save(bookEntity);
                } else {
                    throw new LibraryException(LibraryErrorMessages.INVALID_TITLE_AND_INVALID_BARCODE);
                }
            }
            return "Successfully assigned all the books";
        } else {
            throw new LibraryException(LibraryErrorMessages.INVALID_SELECTED_BOOK_LIST);
        }

    }
}


