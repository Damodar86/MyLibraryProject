package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.AccountBooksHistory;
import com.libraryManagement.libraryArtifact.libraryModel.BookHistoryByAccount_Id_Model;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookHistoryByAccount_Id_Service {

    private BookCheckOutRepository bookCheckOutRepository;

    public BookHistoryByAccount_Id_Service(BookCheckOutRepository bookCheckOutRepository) {
        this.bookCheckOutRepository = bookCheckOutRepository;
    }

    public List<AccountBooksHistory> bookHistoryByAccount_Id_Method(BookHistoryByAccount_Id_Model bookHistoryByAccountIdModel) throws LibraryException {
        List<BookCheckOutEntity> bookCheckOutEntities = bookCheckOutRepository.findByBorrowerAccountId(bookHistoryByAccountIdModel.getAccountId());
        if (CollectionUtils.isNotEmpty(bookCheckOutEntities)) {
            List<AccountBooksHistory> accountBooksHistories = new ArrayList<>();
            bookCheckOutEntities.forEach(bookCheckOutEntity -> {
                AccountBooksHistory accountBooksHistory = new AccountBooksHistory();
                accountBooksHistory.setBookBarCode(bookCheckOutEntity.getBookBarCode());
                accountBooksHistory.setBorrowerAccountId(bookCheckOutEntity.getBorrowerAccountId());
                accountBooksHistory.setBorrowedDate(bookCheckOutEntity.getBorrowedDate());
                accountBooksHistory.setIssuedById(bookCheckOutEntity.getIssuedById());
                accountBooksHistory.setRenewalDate(bookCheckOutEntity.getRenewalDate());
                accountBooksHistory.setRenewalCount(bookCheckOutEntity.getRenewalCount());
                accountBooksHistory.setPenalty(bookCheckOutEntity.getPenality());
                accountBooksHistories.add(accountBooksHistory);
            });

            return accountBooksHistories;

        } else {
            log.info("sorry history is not found for the given account_Id ");
            throw new LibraryException(LibraryErrorMessages.INVALID_ACCOUNT_ID);
        }

    }
}
