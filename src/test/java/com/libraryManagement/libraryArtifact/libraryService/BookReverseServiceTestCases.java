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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookReverseServiceTestCases {
    @InjectMocks
    BookReserveService bookReserveService;
    @Mock
    BookCheckOutRepository mockBookCheckOutRepository;
    @Mock
    AccountRepository mockAccountRepository;
    @Mock
    BookRepository mockBookRepository;

    @Mock
    BookReserveRepository bookReserveRepository;

    @Test
    public void bookReverseMethodShouldThrowException_IfThereIsNoBooKInBookCheckOutRepository() {
        BookReserveModel bookReserveModel1 = BookReserveModel.builder()
                .memberAccountId(1005l)
                .password("password")
                .bookBarCode("1001")
                .build();
        when(mockAccountRepository.findByAccountIdAndPassword(bookReserveModel1.getMemberAccountId(), bookReserveModel1.getPassword()))
                .thenReturn(Optional.empty());
        LibraryException libraryException = (LibraryException) catchThrowable(() -> bookReserveService.bookReserveMethod(bookReserveModel1));
        verify(mockAccountRepository, times(1))
                .findByAccountIdAndPassword(bookReserveModel1.getMemberAccountId(), bookReserveModel1.getPassword());
        assertThat(libraryException).isNotNull();
        assertThat(libraryException.getLibraryErrorMessages()).isEqualTo(LibraryErrorMessages.SORRY_GIVEN_ACCOUNTID_PASSWORD_INVALID);

    }

    @Test
    public void bookReserveServiceShouldSuccessWithParameters() throws LibraryException {
        //request model that we pass to the actual service method//
        BookReserveModel bookReserveModel = BookReserveModel.builder()
                .memberAccountId(1005l)
                .password("password")
                .bookBarCode("1001")
                .build();

        //account repository mock database values started//
        LibraryAccountEntity libraryAccountEntity = new LibraryAccountEntity();
        libraryAccountEntity.setAccountId(bookReserveModel.getMemberAccountId());
        libraryAccountEntity.setPassword(bookReserveModel.getPassword());
        libraryAccountEntity.setAccountStatus(AccountStatus.Active.name());
        Optional<LibraryAccountEntity> mockOptionalLibraryAccountEntity = Optional.of(libraryAccountEntity);

        when(mockAccountRepository.findByAccountIdAndPassword(bookReserveModel.getMemberAccountId(), bookReserveModel.getPassword()))
                .thenReturn(mockOptionalLibraryAccountEntity);
        //account repository mock database values end//

        //book repository mock database values started//
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookStatus(BookStatus.Loaned.name());
        bookEntity.setBarcode(bookReserveModel.getBookBarCode());
        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);
        //bookReserveReturnModel//
        BookReserveReturnModel bookReserveReturnModel = new BookReserveReturnModel();
        bookReserveReturnModel.setBookStatus(BookStatus.Reserved);
        bookReserveReturnModel.setBookBarCode(bookReserveModel.getBookBarCode());
        when(mockBookRepository.findByBarcodeAndBookStatus(bookReserveModel.getBookBarCode(), BookStatus.Loaned.name()))
                .thenReturn(optionalBookEntity);
        //book repository mock database values end//

        bookReserveService.bookReserveMethod(bookReserveModel);

        verify(mockAccountRepository, times(1)).findByAccountIdAndPassword(bookReserveModel.getMemberAccountId(), bookReserveModel.getPassword());
        verify(mockBookRepository, times(1)).findByBarcodeAndBookStatus(bookReserveModel.getBookBarCode(), BookStatus.Loaned.name());

        ArgumentCaptor<BookReserveEntity> bookReserveEntityArgumentCaptor = ArgumentCaptor.forClass(BookReserveEntity.class);
        verify(bookReserveRepository, times(1)).save(bookReserveEntityArgumentCaptor.capture());
        BookReserveEntity savedBookReserveEntity = bookReserveEntityArgumentCaptor.getValue();
        assertThat(savedBookReserveEntity.getAccount_Id()).isEqualTo(bookReserveModel.getMemberAccountId());
        assertThat(savedBookReserveEntity.getBookBarCode()).isEqualTo(bookReserveModel.getBookBarCode());
        assertThat(savedBookReserveEntity.getReserveDate()).isEqualTo(LocalDate.now());
        assertThat(savedBookReserveEntity.getReservedStatus()).isEqualTo(Constants.RESERVED_STATUS);
        assertThat(bookReserveReturnModel.getBookBarCode()).isEqualTo(bookReserveModel.getBookBarCode());
        assertThat(bookReserveReturnModel.getBookStatus()).isEqualTo(BookStatus.Reserved);

        ArgumentCaptor<BookEntity> bookEntityArgumentCaptor = ArgumentCaptor.forClass(BookEntity.class);
        verify(mockBookRepository, times(1)).save(bookEntityArgumentCaptor.capture());
        BookEntity savedBookEntity = bookEntityArgumentCaptor.getValue();
        assertThat(savedBookEntity.getBookStatus()).isEqualTo(Constants.RESERVED_STATUS);
        assertThat(bookReserveReturnModel.getBookStatus()).isEqualTo(BookStatus.Reserved);
        assertThat(bookReserveReturnModel.getBookBarCode()).isEqualTo(bookReserveModel.getBookBarCode());

    }

    @Test
    public void bookReserveServiceThrowsException_whenAccountStatusIsNotActive() {
        //request model that we pass to the actual service method//
        BookReserveModel bookReserveModel = BookReserveModel.builder()
                .memberAccountId(1005l)
                .password("password")
                .bookBarCode("1001")
                .build();
        LibraryAccountEntity libraryAccountEntity = new LibraryAccountEntity();
        libraryAccountEntity.setAccountStatus(AccountStatus.Closed.name());
        Optional<LibraryAccountEntity> mockOptionalLibraryAccountEntity = Optional.of(libraryAccountEntity);
        when(mockAccountRepository.findByAccountIdAndPassword(bookReserveModel.getMemberAccountId(), bookReserveModel.getPassword()))
                .thenReturn(mockOptionalLibraryAccountEntity);
        LibraryException libraryException = (LibraryException) catchThrowable(() -> bookReserveService.bookReserveMethod(bookReserveModel));
        verify(mockAccountRepository, times(1))
                .findByAccountIdAndPassword(bookReserveModel.getMemberAccountId(), bookReserveModel.getPassword());
        assertThat(libraryException).isNotNull();
        assertThat(libraryException.getLibraryErrorMessages()).isEqualTo(LibraryErrorMessages.SORRY_GIVEN_ACCOUNT_ID_WAS_CLOSED_ALREADY);
    }

    @Test
    public void bookReserveServiceThrowsExceptionWhenThereIsNoLoanedBook() {
        BookReserveModel bookReserveModel = BookReserveModel.builder()
                .memberAccountId(1005l)
                .password("password")
                .bookBarCode("1001")
                .build();
// preparing LibraryAccount Entity for returning values to the calling method
        // and cheking user details in DB if present or not
        LibraryAccountEntity libraryAccountEntity = new LibraryAccountEntity();
        libraryAccountEntity.setAccountId(bookReserveModel.getMemberAccountId());
        libraryAccountEntity.setPassword(bookReserveModel.getPassword());
        libraryAccountEntity.setAccountStatus(AccountStatus.Active.name());
        Optional<LibraryAccountEntity> mockOptionalLibraryAccountEntity = Optional.of(libraryAccountEntity);

        when(mockAccountRepository.findByAccountIdAndPassword(bookReserveModel.getMemberAccountId(), bookReserveModel.getPassword()))
                .thenReturn(mockOptionalLibraryAccountEntity);
        //account repository mock database values end//
        // preparing the bookEntity for checking whether book status is available or loaned
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookStatus(BookStatus.Available.name());
        Optional<BookEntity> mocBookEntity = Optional.of(bookEntity);
        /*when(mockBookRepository.findByBarcodeAndBookStatus(bookReserveModel.getBookBarCode(),
                BookStatus.Available.name())).thenReturn(mocBookEntity);*/
        LibraryException libraryException = (LibraryException) catchThrowable(() -> bookReserveService.bookReserveMethod(bookReserveModel));
     /* verify(mockBookRepository,times(1))
              .findByBarcodeAndBookStatus(bookReserveModel.getBookBarCode(),BookStatus.Available.name());*/
        assertThat(libraryException).isNotNull();
        assertThat(libraryException.getLibraryErrorMessages()).isEqualTo(LibraryErrorMessages.INVALID_GIVEN_BARCODE);

    }
}
