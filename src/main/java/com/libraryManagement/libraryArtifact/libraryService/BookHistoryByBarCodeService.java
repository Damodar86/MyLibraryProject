package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookHistoryByBarCodeModel;
import com.libraryManagement.libraryArtifact.libraryModel.BookHistoryByBarCodeReturnModel;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookHistoryByBarCodeService {
    private BookCheckOutRepository bookCheckOutRepository;

    public BookHistoryByBarCodeService(BookCheckOutRepository bookCheckOutRepository) {
        this.bookCheckOutRepository = bookCheckOutRepository;
    }

    public List<BookHistoryByBarCodeReturnModel> bookHistoryByBarCodeMethod(BookHistoryByBarCodeModel bookHistoryByBarCodeModel) throws LibraryException {
        List<BookCheckOutEntity> bookCheckOutEntities = bookCheckOutRepository.findByBookBarCodeOrIssuedById(bookHistoryByBarCodeModel.getBarCode(), "");
        if (CollectionUtils.isNotEmpty(bookCheckOutEntities)) {
            List<BookHistoryByBarCodeReturnModel> bookHistoryByBarCodeReturnModels = new ArrayList<>();
            bookCheckOutEntities.forEach(bookCheckOutEntity -> {
                BookHistoryByBarCodeReturnModel bookHistoryByBarCodeReturnModel = new BookHistoryByBarCodeReturnModel();
                bookHistoryByBarCodeReturnModel.setAccountId(bookCheckOutEntity.getBorrowerAccountId());
                bookHistoryByBarCodeReturnModel.setBarCode(bookCheckOutEntity.getBookBarCode());
                bookHistoryByBarCodeReturnModel.setBarrowDate(bookCheckOutEntity.getBorrowedDate());
                bookHistoryByBarCodeReturnModel.setIssuedBy(bookCheckOutEntity.getIssuedById());
                bookHistoryByBarCodeReturnModel.setPenalty(bookCheckOutEntity.getPenality());
                bookHistoryByBarCodeReturnModel.setRenewalCount(bookCheckOutEntity.getRenewalCount());
                bookHistoryByBarCodeReturnModel.setReturnDate(bookCheckOutEntity.getReturnedDate());
                bookHistoryByBarCodeReturnModel.setRenewalDate(bookCheckOutEntity.getRenewalDate());
                bookHistoryByBarCodeReturnModels.add(bookHistoryByBarCodeReturnModel);
            });
            return bookHistoryByBarCodeReturnModels;

        } else {
            throw new LibraryException(LibraryErrorMessages.INVALID_GIVEN_BARCODE1);
        }

    }
}
