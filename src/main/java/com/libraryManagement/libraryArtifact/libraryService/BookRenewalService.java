package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookRenewalModel;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            log.info("entered into bookCheckOutEntity is Present block");
            BookCheckOutEntity bookCheckOutEntity1 = bookCheckOutEntity.get();
            if (bookCheckOutEntity1.getRenewalCount() < 3) {
                log.info("entered into Renewal Count check Condition block");
                bookCheckOutEntity1.setRenewalDate(LocalDate.now().plusDays(13));
                bookCheckOutEntity1.setIssuedById("online");
                bookCheckOutEntity1.setRenewalCount(bookCheckOutEntity1.getRenewalCount() + 1);
                bookCheckOutRepository.save(bookCheckOutEntity1);
            } else {
                log.info(" entered in to renewal count check exception block ");
                throw new LibraryException(LibraryErrorMessages.RENEWAL_LIMIT_EXCEEDED);
            }

        } else {
            log.info("entered into is present else block ");
            throw new LibraryException(LibraryErrorMessages.INVALID_GIVEN_BARCODE_AND_ACCOUNT_ID);

        }
        return "Successfully renewed your books" + bookCheckOutEntity.get().getBookBarCode() + " borrower_Id" + bookCheckOutEntity.get().getBorrowerAccountId();
    }
}


