package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookRenewalModel;
import com.libraryManagement.libraryArtifact.libraryRepository.BookCheckOutRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookRenewalServiceTest {

    @InjectMocks
    private BookRenewalService bookRenewalService;
     @Mock
    private BookCheckOutRepository bookCheckOutRepository;

    //@Before
    public void setup() {
        bookCheckOutRepository = mock(BookCheckOutRepository.class);
        bookRenewalService = new BookRenewalService(bookCheckOutRepository);
    }

    @Test
    public void bookRenewalServiceMethod_shouldThrow_Exception_WhenBookIsNotCheckedOut() {
/*BookRenewalModel bookRenewalModel=new BookRenewalModel();
        bookRenewalModel.setBarCode("1002");
        bookRenewalModel.setAccountId(1005l);*/
        //same below code
        BookRenewalModel bookRenewalModel = BookRenewalModel.builder()
                .accountId(1005l)
                .barCode("1002")
                .build();
        when(bookCheckOutRepository.findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(bookRenewalModel.getBarCode(), bookRenewalModel.getAccountId())).thenReturn(Optional.empty());
        LibraryException libraryException = (LibraryException) catchThrowable(() -> bookRenewalService.bookRenewalServiceMethod(bookRenewalModel));
        assertThat(libraryException).isNotNull();
        assertThat(libraryException.getLibraryErrorMessages()).isEqualTo(LibraryErrorMessages.INVALID_GIVEN_BARCODE_AND_ACCOUNT_ID);

    }
}
