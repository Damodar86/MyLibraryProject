package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.NotificationEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationReturnModel;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.NotificationRepository;
//import jdk.internal.org.jline.utils.Log;
import com.libraryManagement.libraryArtifact.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NotificationService2 {
    private BookCheckOutRepository bookCheckOutRepository;
    private NotificationRepository notificationRepository;

    public NotificationService2(BookCheckOutRepository bookCheckOutRepository, NotificationRepository notificationRepository) {
        this.bookCheckOutRepository = bookCheckOutRepository;
        this.notificationRepository = notificationRepository;
    }


    @Transactional
    public List<NotificationReturnModel> notificationMethod() throws LibraryException {
        List<BookCheckOutEntity> bookCheckOutEntities = bookCheckOutRepository.findByRenewalDateIsBeforeAndReturnedDateIsNull(LocalDate.now());
        List<NotificationReturnModel> notificationEntities = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(bookCheckOutEntities)) {
            log.info("entered into the if block");
            bookCheckOutEntities.forEach(bookCheckOutEntity -> {
                NotificationReturnModel notificationReturnModel = new NotificationReturnModel();
                notificationReturnModel.setAccountId(bookCheckOutEntity.getBorrowerAccountId());
                notificationReturnModel.setBookBarCode(bookCheckOutEntity.getBookBarCode());
                notificationReturnModel.setBookBorrowDate(bookCheckOutEntity.getBorrowedDate());
                notificationReturnModel.setBookRenewalDate(bookCheckOutEntity.getRenewalDate());
                notificationReturnModel.setMessage(Constants.NOTIFICATION_MESSAGE);
                notificationEntities.add(notificationReturnModel);

                NotificationEntity notificationEntity = new NotificationEntity();
                notificationEntity.setAccountId(bookCheckOutEntity.getBorrowerAccountId());
                notificationEntity.setBookBarCode(bookCheckOutEntity.getBookBarCode());
                notificationEntity.setBorrowedDate(bookCheckOutEntity.getBorrowedDate());
                notificationEntity.setRenewalDate(bookCheckOutEntity.getRenewalDate());
                notificationEntity.setNotificationDate(LocalDate.now());
                notificationEntity.setMessage(Constants.NOTIFICATION_MESSAGE);

                notificationRepository.save(notificationEntity);
            });
            return notificationEntities;
        } else {
            throw new LibraryException(LibraryErrorMessages.SORRY_THERE_ARE_NO_BOOKS);
        }
    }
}
