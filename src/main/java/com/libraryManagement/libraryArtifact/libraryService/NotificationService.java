package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationModel;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationReturnModel;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.NotificationRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private NotificationRepository notificationRepository;
    private BookCheckOutRepository bookCheckOutRepository;

    public NotificationService(NotificationRepository notificationRepository, BookCheckOutRepository bookCheckOutRepository) {
        this.notificationRepository = notificationRepository;
        this.bookCheckOutRepository = bookCheckOutRepository;
    }

    NotificationReturnModel notificationReturnModel = new NotificationReturnModel();
    public NotificationReturnModel notificationMethod(NotificationModel notificationModel) throws LibraryException {
        List<BookCheckOutEntity> bookCheckOutEntities = bookCheckOutRepository.findByBorrowedDateAndReturnedDateIsNull(notificationModel.getBookReturnDate());

        if (CollectionUtils.isNotEmpty(bookCheckOutEntities)) {
            List<NotificationReturnModel> notificationReturnModels = new ArrayList<>();
            bookCheckOutEntities.forEach(bookCheckOutEntity -> {

                notificationReturnModel.setAccountId(bookCheckOutEntity.getBorrowerAccountId());
                notificationReturnModel.setBookBorrowDate(bookCheckOutEntity.getBorrowedDate());
                notificationReturnModel.setBookRenewalDate(bookCheckOutEntity.getRenewalDate());
                notificationReturnModel.setMessage("your book due date is tomorrow plz submit the book tomorrow morning in college library otherwise penalty will there  ");

                int daysToNotification = LocalDateTime.now().compareTo(notificationReturnModel.getBookRenewalDate());
                if (daysToNotification == -1) {



                }

            });


        }else{
            throw new LibraryException(LibraryErrorMessages.SORRY_NO_DUE_BOOKS);
        }

return notificationReturnModel;
    }
}
