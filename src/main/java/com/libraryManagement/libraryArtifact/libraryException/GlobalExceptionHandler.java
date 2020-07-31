package com.libraryManagement.libraryArtifact.libraryException;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LibraryException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LibraryErrorMessages handleLibraryException(LibraryException e){
        return e.getLibraryErrorMessages();
    }
}
