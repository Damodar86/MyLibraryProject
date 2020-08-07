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

    public String bookRenewalServiceMethod(BookRenewalModel bookRenewalModel) throws LibraryException{
        Optional<BookCheckOutEntity> optionalBookCheckOutEntity = bookCheckOutRepository
                .findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId());
        if(optionalBookCheckOutEntity.isPresent()){
            BookCheckOutEntity bookCheckOutEntity = optionalBookCheckOutEntity.get();
            if(bookCheckOutEntity.getRenewalCount() < 3){
                log.info("entered into Renewal Count check Condition block");
                bookCheckOutEntity.setRenewalDate(LocalDate.now().plusDays(13));
                bookCheckOutEntity.setIssuedById("online");
                bookCheckOutEntity.setRenewalCount(bookCheckOutEntity.getRenewalCount() + 1);
                bookCheckOutRepository.save(bookCheckOutEntity);
                return "Successfully renewed your books " + bookRenewalModel.getBarCode() + " borrower_Id " + bookRenewalModel.getAccountId();
            }else{
                log.info("Renewal limit exceeded for book with barcode {}, accountId {} ", bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId());
                throw new LibraryException(LibraryErrorMessages.RENEWAL_LIMIT_EXCEEDED);
            }
        }else{
            log.info("There is no checkout entry for barcode {}, accountId {} ", bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId());
            throw new LibraryException(LibraryErrorMessages.INVALID_GIVEN_BARCODE_AND_ACCOUNT_ID);
        }
    }


   /* public String bookRenewalServiceMethod(BookRenewalModel bookrenewalmodel) throws LibraryException {
     return null;
    }*/
}



