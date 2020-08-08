/*
package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.NotificationEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationModel;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationReturnModel;
import com.libraryManagement.libraryArtifact.libraryRepository.AccountRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.NotificationRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {
    private NotificationRepository notificationRepository;
    private BookCheckOutRepository bookCheckOutRepository;

    public NotificationService(NotificationRepository notificationRepository, BookCheckOutRepository bookCheckOutRepository, AccountRepository accountRepository) {
        this.notificationRepository = notificationRepository;
        this.bookCheckOutRepository = bookCheckOutRepository;
    }


    @Transactional
    public NotificationReturnModel notificationMethod(NotificationModel notificationModel) throws LibraryException {
        List<BookCheckOutEntity> bookCheckOutEntities = bookCheckOutRepository.findByBorrowedDateAndReturnedDateIsNull(notificationModel.getBorrowedDate());

        NotificationReturnModel notificationReturnModel = new NotificationReturnModel();
        if (CollectionUtils.isNotEmpty(bookCheckOutEntities)) {
            bookCheckOutEntities.forEach(bookCheckOutEntity -> {

                notificationReturnModel.setAccountId(bookCheckOutEntity.getBorrowerAccountId());
                notificationReturnModel.setBookBorrowDate(bookCheckOutEntity.getBorrowedDate());
                notificationReturnModel.setBookRenewalDate(bookCheckOutEntity.getRenewalDate());
                notificationReturnModel.setMessage("your book due date is tomorrow plz submit the book tomorrow morning in library");
                notificationReturnModel.setBookBarCode(bookCheckOutEntity.getBookBarCode());
            });
                int daysToNotification = LocalDate.now().compareTo(notificationReturnModel.getBookRenewalDate());
                if (daysToNotification==-1) {
                   */
/* NotificationEntity notificationEntity = new NotificationEntity();
                    notificationEntity.setAccountId(notificationReturnModel.getAccountId());
                    notificationEntity.setBookBarCode(notificationReturnModel.getBookBarCode());
                    notificationEntity.setBorrowedDate(notificationReturnModel.getBookBorrowDate());
                    notificationEntity.setRenewalDate(notificationReturnModel.getBookRenewalDate());
                    notificationEntity.setNotificationDate(LocalDate.now());
                    notificationEntity.setMessage(notificationReturnModel.getMessage());

                    notificationRepository.save(notificationEntity);*//*

                } else{
                    throw new LibraryException(LibraryErrorMessages.SORRY_THERE_ARE_NO_BOOKS);
                }




        } else {
            throw new LibraryException(LibraryErrorMessages.SORRY_THERE_ARE_NO_BOOKS);
        }

        return notificationReturnModel;
    }
}
*/
