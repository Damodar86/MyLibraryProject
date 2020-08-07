package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.NotificationEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationModel;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationReturnModel;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.NotificationRepository;
import com.libraryManagement.libraryArtifact.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookNotificationServiceTest {
    @InjectMocks
    private NotificationService2 notificationService2;
    @Mock
    private BookCheckOutRepository mocBookCheckOutRepository;

    @Mock
    private NotificationRepository notificationRepository;

    @Test
    public void bookNotificationServiceMethod_shouldThrow_Exception_WhenBookIsNotCheckedOut() {
       /* NotificationModel notificationModel= NotificationModel.builder()
                .renewalDate(2020,8,04);
*/
        List<BookCheckOutEntity> bookCheckOutEntities=new ArrayList<>();
        when(mocBookCheckOutRepository
                .findByRenewalDateIsBeforeAndReturnedDateIsNull(LocalDate.now()))
                .thenReturn(bookCheckOutEntities);
        LibraryException libraryException = (LibraryException) catchThrowable(() -> notificationService2.notificationMethod());

       verify(mocBookCheckOutRepository, times(1))
                .findByRenewalDateIsBeforeAndReturnedDateIsNull(LocalDate.now());
        assertThat(libraryException).isNotNull();
        assertThat(libraryException.getLibraryErrorMessages()).isEqualTo(LibraryErrorMessages.SORRY_THERE_ARE_NO_BOOKS);
    }
    @Test
    public void bookNotificationServiceSuccessFullMethod() throws LibraryException {
        BookCheckOutEntity mockBookCheckOutEntity1 = new BookCheckOutEntity();
        mockBookCheckOutEntity1.setBorrowerAccountId(89747L);
        mockBookCheckOutEntity1.setBookBarCode("jasdjkflja");
        mockBookCheckOutEntity1.setBorrowedDate(LocalDate.now());
        mockBookCheckOutEntity1.setRenewalDate(LocalDate.now());

        BookCheckOutEntity mockBookCheckOutEntity2 = new BookCheckOutEntity();
        mockBookCheckOutEntity2.setBorrowerAccountId(5874L);
        mockBookCheckOutEntity2.setBookBarCode("asldjflkjasld");
        mockBookCheckOutEntity2.setBorrowedDate(LocalDate.now());
        mockBookCheckOutEntity2.setRenewalDate(LocalDate.now());

        List<BookCheckOutEntity> mockBookCheckOutEntities=new ArrayList<>();
        mockBookCheckOutEntities.add(mockBookCheckOutEntity1);
        mockBookCheckOutEntities.add(mockBookCheckOutEntity2);

        when(mocBookCheckOutRepository
                .findByRenewalDateIsBeforeAndReturnedDateIsNull(LocalDate.now()))
                .thenReturn(mockBookCheckOutEntities);

        List<NotificationReturnModel> actualNotificationReturnModels = notificationService2.notificationMethod();

        verify(mocBookCheckOutRepository, times(1))
                .findByRenewalDateIsBeforeAndReturnedDateIsNull(LocalDate.now());
        assertThat(actualNotificationReturnModels.size()).isEqualTo(2);
        NotificationReturnModel notificationReturnModel1 = actualNotificationReturnModels.get(0);
        assertThat(notificationReturnModel1.getAccountId()).isEqualTo(mockBookCheckOutEntity1.getBorrowerAccountId());
        assertThat(notificationReturnModel1.getBookBarCode()).isEqualTo(mockBookCheckOutEntity1.getBookBarCode());
        assertThat(notificationReturnModel1.getBookBorrowDate()).isEqualTo(mockBookCheckOutEntity1.getBorrowedDate());
        assertThat(notificationReturnModel1.getBookRenewalDate()).isEqualTo(mockBookCheckOutEntity1.getRenewalDate());
        assertThat(notificationReturnModel1.getMessage()).isEqualTo(Constants.NOTIFICATION_MESSAGE);

        NotificationReturnModel notificationReturnModel2 = actualNotificationReturnModels.get(1);
        assertThat(notificationReturnModel2.getAccountId()).isEqualTo(mockBookCheckOutEntity2.getBorrowerAccountId());
        assertThat(notificationReturnModel2.getBookBarCode()).isEqualTo(mockBookCheckOutEntity2.getBookBarCode());
        assertThat(notificationReturnModel2.getBookBorrowDate()).isEqualTo(mockBookCheckOutEntity2.getBorrowedDate());
        assertThat(notificationReturnModel2.getBookRenewalDate()).isEqualTo(mockBookCheckOutEntity2.getRenewalDate());
        assertThat(notificationReturnModel2.getMessage()).isEqualTo(Constants.NOTIFICATION_MESSAGE);

        ArgumentCaptor<NotificationEntity> notificationEntityArgumentCaptor = ArgumentCaptor.forClass(NotificationEntity.class);
        verify(notificationRepository, times(2)).save(notificationEntityArgumentCaptor.capture());

        List<NotificationEntity> actualSavedNotificationEntities = notificationEntityArgumentCaptor.getAllValues();
        NotificationEntity notificationEntity1 = actualSavedNotificationEntities.get(0);
        assertThat(notificationEntity1.getAccountId()).isEqualTo(mockBookCheckOutEntity1.getBorrowerAccountId());
        assertThat(notificationEntity1.getBookBarCode()).isEqualTo(mockBookCheckOutEntity1.getBookBarCode());
        assertThat(notificationEntity1.getBorrowedDate()).isEqualTo(mockBookCheckOutEntity1.getBorrowedDate());
        assertThat(notificationEntity1.getRenewalDate()).isEqualTo(mockBookCheckOutEntity1.getRenewalDate());
        assertThat(notificationEntity1.getMessage()).isEqualTo(Constants.NOTIFICATION_MESSAGE);
        assertThat(notificationEntity1.getNotificationDate()).isEqualTo(LocalDate.now());

        NotificationEntity notificationEntity2 = actualSavedNotificationEntities.get(1);
        assertThat(notificationEntity2.getAccountId()).isEqualTo(mockBookCheckOutEntity2.getBorrowerAccountId());
        assertThat(notificationEntity2.getBookBarCode()).isEqualTo(mockBookCheckOutEntity2.getBookBarCode());
        assertThat(notificationEntity2.getBorrowedDate()).isEqualTo(mockBookCheckOutEntity2.getBorrowedDate());
        assertThat(notificationEntity2.getRenewalDate()).isEqualTo(mockBookCheckOutEntity2.getRenewalDate());
        assertThat(notificationEntity2.getMessage()).isEqualTo(Constants.NOTIFICATION_MESSAGE);
        assertThat(notificationEntity2.getNotificationDate()).isEqualTo(LocalDate.now());
    }
}
