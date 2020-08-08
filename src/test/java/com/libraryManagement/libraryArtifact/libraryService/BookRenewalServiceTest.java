package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookRenewalModel;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookRenewalServiceTest {

    @InjectMocks
    private BookRenewalService bookRenewalService;
    @Mock
    private BookCheckOutRepository mockBookCheckOutRepository;

    @Test
    public void bookRenewalServiceMethod_shouldThrow_Exception_WhenBookIsNotCheckedOut() {
        BookRenewalModel bookRenewalModel = BookRenewalModel.builder()
                .accountId(1005l)
                .barCode("1002")
                .build();

        when(mockBookCheckOutRepository
                .findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId()))
                .thenReturn(Optional.empty());

        LibraryException libraryException = (LibraryException) catchThrowable(() -> bookRenewalService.bookRenewalServiceMethod(bookRenewalModel));

        verify(mockBookCheckOutRepository, times(1))
                .findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId());

        assertThat(libraryException).isNotNull();
        assertThat(libraryException.getLibraryErrorMessages()).isEqualTo(LibraryErrorMessages.INVALID_GIVEN_BARCODE_AND_ACCOUNT_ID);

    }

    @Test
    public void bookRenewalServiceMethod_ShouldSuccessfully_renew_book_WhenRenewalCount_is_0() throws LibraryException {
        BookRenewalModel bookRenewalModel = BookRenewalModel.builder()
                .accountId(1005l)
                .barCode("1002")
                .build();

        BookCheckOutEntity mockBookCheckOutEntity = new BookCheckOutEntity();
        mockBookCheckOutEntity.setRenewalCount(0);
        Optional<BookCheckOutEntity> optionalBookCheckOutEntity = Optional.of(mockBookCheckOutEntity);
        when(mockBookCheckOutRepository
                .findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId()))
                .thenReturn(optionalBookCheckOutEntity);
        String actualMessage = bookRenewalService.bookRenewalServiceMethod(bookRenewalModel);

        ArgumentCaptor<BookCheckOutEntity> bookCheckOutEntityArgumentCaptor = ArgumentCaptor.forClass(BookCheckOutEntity.class);
        verify(mockBookCheckOutRepository, times(1)).save(bookCheckOutEntityArgumentCaptor.capture());

        BookCheckOutEntity savedBookCheckoutEntity = bookCheckOutEntityArgumentCaptor.getValue();
        assertThat(savedBookCheckoutEntity).isNotNull();
        assertThat(savedBookCheckoutEntity.getRenewalDate()).isEqualTo(LocalDate.now().plusDays(13));
        assertThat(savedBookCheckoutEntity.getIssuedById()).isEqualTo("online");
        assertThat(savedBookCheckoutEntity.getRenewalCount()).isEqualTo(1);

        assertThat(actualMessage).isEqualTo("Successfully renewed your books " + bookRenewalModel.getBarCode() + " borrower_Id " + bookRenewalModel.getAccountId());
    }

    @Test
    public void bookRenewalServiceMethod_ShouldSuccessfully_renew_book_WhenRenewalCount_is_1() throws LibraryException {
        BookRenewalModel bookRenewalModel = BookRenewalModel.builder()
                .accountId(1005l)
                .barCode("1002")
                .build();

        BookCheckOutEntity mockBookCheckOutEntity = new BookCheckOutEntity();
        mockBookCheckOutEntity.setRenewalCount(1);
        Optional<BookCheckOutEntity> optionalBookCheckOutEntity = Optional.of(mockBookCheckOutEntity);
        when(mockBookCheckOutRepository
                .findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId()))
                .thenReturn(optionalBookCheckOutEntity);
        String actualMessage = bookRenewalService.bookRenewalServiceMethod(bookRenewalModel);

        ArgumentCaptor<BookCheckOutEntity> bookCheckOutEntityArgumentCaptor = ArgumentCaptor.forClass(BookCheckOutEntity.class);
        verify(mockBookCheckOutRepository, times(1)).save(bookCheckOutEntityArgumentCaptor.capture());

        BookCheckOutEntity savedBookCheckoutEntity = bookCheckOutEntityArgumentCaptor.getValue();
        assertThat(savedBookCheckoutEntity).isNotNull();
        assertThat(savedBookCheckoutEntity.getRenewalDate()).isEqualTo(LocalDate.now().plusDays(13));
        assertThat(savedBookCheckoutEntity.getIssuedById()).isEqualTo("online");
        assertThat(savedBookCheckoutEntity.getRenewalCount()).isEqualTo(2);

        assertThat(actualMessage).isEqualTo("Successfully renewed your books " + bookRenewalModel.getBarCode() + " borrower_Id " + bookRenewalModel.getAccountId());
    }

    @Test
    public void bookRenewalServiceMethod_ShouldSuccessfully_renew_book_WhenRenewalCount_is_2() throws LibraryException {
        BookRenewalModel bookRenewalModel = BookRenewalModel.builder()
                .accountId(1005l)
                .barCode("1002")
                .build();

        BookCheckOutEntity mockBookCheckOutEntity = new BookCheckOutEntity();
        mockBookCheckOutEntity.setRenewalCount(2);
        Optional<BookCheckOutEntity> optionalBookCheckOutEntity = Optional.of(mockBookCheckOutEntity);
        when(mockBookCheckOutRepository
                .findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId()))
                .thenReturn(optionalBookCheckOutEntity);
        String actualMessage = bookRenewalService.bookRenewalServiceMethod(bookRenewalModel);

        ArgumentCaptor<BookCheckOutEntity> bookCheckOutEntityArgumentCaptor = ArgumentCaptor.forClass(BookCheckOutEntity.class);
        verify(mockBookCheckOutRepository, times(1)).save(bookCheckOutEntityArgumentCaptor.capture());

        BookCheckOutEntity savedBookCheckoutEntity = bookCheckOutEntityArgumentCaptor.getValue();
        assertThat(savedBookCheckoutEntity).isNotNull();
        assertThat(savedBookCheckoutEntity.getRenewalDate()).isEqualTo(LocalDate.now().plusDays(13));
        assertThat(savedBookCheckoutEntity.getIssuedById()).isEqualTo("online");
        assertThat(savedBookCheckoutEntity.getRenewalCount()).isEqualTo(3);

        assertThat(actualMessage).isEqualTo("Successfully renewed your books " + bookRenewalModel.getBarCode() + " borrower_Id " + bookRenewalModel.getAccountId());
    }

    @Test
    public void bookRenewalServiceMethod_ShouldThrowException_WhenAllRenewalReached_to_3() {
        BookRenewalModel bookRenewalModel = BookRenewalModel.builder()
                .accountId(1005l)
                .barCode("1002")
                .build();

        BookCheckOutEntity bookCheckOutEntity = new BookCheckOutEntity();
        bookCheckOutEntity.setRenewalCount(3);
        Optional<BookCheckOutEntity> optionalBookCheckOutEntity = Optional.of(bookCheckOutEntity);
        when(mockBookCheckOutRepository
                .findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId()))
                .thenReturn(optionalBookCheckOutEntity);

        LibraryException libraryException = (LibraryException) catchThrowable(() -> bookRenewalService.bookRenewalServiceMethod(bookRenewalModel));

        verify(mockBookCheckOutRepository, times(1))
                .findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId());

        assertThat(libraryException).isNotNull();
        assertThat(libraryException.getLibraryErrorMessages()).isEqualTo(LibraryErrorMessages.RENEWAL_LIMIT_EXCEEDED);

    }
}
