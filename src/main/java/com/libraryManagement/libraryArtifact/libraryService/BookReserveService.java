package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.AccountStatus;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.BookStatus;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
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
import com.libraryManagement.libraryArtifact.util.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public BookReserveReturnModel bookReserveMethod(BookReserveModel bookReserveModel) throws LibraryException {
        Optional<LibraryAccountEntity> optLibraryAccountEntity = accountRepository.findByAccountIdAndPassword(bookReserveModel.getMemberAccountId(), bookReserveModel.getPassword());
        if (optLibraryAccountEntity.isPresent()){
            LibraryAccountEntity libraryAccountEntity = optLibraryAccountEntity.get();
            if(libraryAccountEntity.getAccountStatus().equalsIgnoreCase(AccountStatus.Active.name())){
                Optional<BookEntity> optionalBookEntity = bookRepository.findByBarcodeAndBookStatus(bookReserveModel.getBookBarCode(), BookStatus.Loaned.name());
                if(optionalBookEntity.isPresent()){
                    BookReserveEntity bookReserveEntity = new BookReserveEntity();
                    bookReserveEntity.setAccount_Id(bookReserveModel.getMemberAccountId());
                    bookReserveEntity.setBookBarCode(bookReserveModel.getBookBarCode());
                    bookReserveEntity.setReserveDate(LocalDate.now());
                    bookReserveEntity.setReservedStatus(Constants.RESERVED_STATUS);
                    bookReserveRepository.save(bookReserveEntity);

                    BookEntity bookEntity = optionalBookEntity.get();
                    bookEntity.setBookStatus(Constants.RESERVED_STATUS);
                    bookRepository.save(bookEntity);
                    BookReserveReturnModel bookReserveReturnModel=new BookReserveReturnModel();
                    bookReserveReturnModel.setBookBarCode(bookReserveModel.getBookBarCode());
                    bookReserveReturnModel.setBookStatus(BookStatus.Reserved);
                }else{
                    throw new LibraryException(LibraryErrorMessages.INVALID_GIVEN_BARCODE);
                }
            }else{
                throw  new LibraryException(LibraryErrorMessages.SORRY_GIVEN_ACCOUNT_ID_WAS_CLOSED_ALREADY);
            }

        }else{
            throw new LibraryException(LibraryErrorMessages.SORRY_GIVEN_ACCOUNTID_PASSWORD_INVALID);
        }

return null;
    }
}

