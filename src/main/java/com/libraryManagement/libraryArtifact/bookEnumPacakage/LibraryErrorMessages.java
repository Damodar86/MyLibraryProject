package com.libraryManagement.libraryArtifact.bookEnumPacakage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum LibraryErrorMessages {
    INVALID_EMAIL_PSWD(101, "Invalid email and password.", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_NUM_PSWD(102, "Invalid phone number and password.", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS(103, "Please provide either email/phone number and password to login.", HttpStatus.BAD_REQUEST),
    INVALID_TITLE(105,"entered subject is Invalid plz check",HttpStatus.BAD_REQUEST ),
    USER_ALREADY_EXITS(104, "You're entered email address is alredy existing, plz try with new email.id.",HttpStatus.BAD_REQUEST),
    INVALID_ACCOUNT_ID(106,"Given Account Id is Invalid",HttpStatus.BAD_REQUEST),
    INVALID_TITLE_AND_INVALID_BARCODE(107,"Please provide valid book title or barcode",HttpStatus.BAD_REQUEST),
    INVALID_SELECTED_BOOK_LIST(108,"Please provide atleast 1 book to checkout",HttpStatus.BAD_REQUEST),
    INVALID_GIVEN_ACCOUNT_ID_OR_BARCODE_OR_BOOK_ISSUED_DATE(109,"Please provide correct account_Id and barCode and issudeDate",HttpStatus.BAD_REQUEST),
    INVALID_GIVEN_BORROWED_DATE(110,"You're not valid for renewal book, for renewal book you need to come before day of returning book",HttpStatus.BAD_REQUEST),
    INVALID_GIVEN_BARCODE(111,"The given barCode is already returned by",HttpStatus.BAD_REQUEST),
    INVALID_GIVEN_BARCODE_AND_ACCOUNT_ID(112,"The given barCode and Account Id is Invalid",HttpStatus.BAD_REQUEST),
    RENEWAL_LIMIT_EXCEEDED(113,"Your Book Renewal Reached Maximum Count",HttpStatus.BAD_REQUEST),
    INVALID_GIVEN_BARCODE1(114,"There is Books History on Given BarCode please Check once",HttpStatus.BAD_REQUEST),
    SORRY_GIVEN_ACCOUNT_ID_WAS_CLOSED_ALREADY(115,"Soryy your given accountId was closed already ", HttpStatus.BAD_REQUEST),
    SORRY_THIS_IS_NOT_LIBRARIAN_ACCOUNT(116,"SORRY THIS IS NOT LIBRARIAN ACCOUNT",HttpStatus.BAD_REQUEST),
    SORRY_THIS_IS_INVALID_MEMBER_ACCOUNT(117,"your given member accountId is already cancelled from our DataBase",HttpStatus.BAD_REQUEST),
    SORRY_GIVEN_ACCOUNTID_PASSWORD_INVALID(118,"GIVEN ACCOUNT ID AND PASSWORD ARE INVALID PLZ CHECK",HttpStatus.BAD_REQUEST),
    SORRY_NO_DUE_BOOKS(119,"SORRY THERE ARE MORE DUE BOOKS FOR THIS DATE",HttpStatus.BAD_REQUEST),
    SORRY_THERE_ARE_NO_BOOKS(120,"Sorry There Are NO Books CheckOuts for this Given Date",HttpStatus.BAD_REQUEST);

    int code;
    String errorDescription;
    HttpStatus httpStatus;
}
