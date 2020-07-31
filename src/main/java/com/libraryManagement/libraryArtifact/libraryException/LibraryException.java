package com.libraryManagement.libraryArtifact.libraryException;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import lombok.Getter;

@Getter
public class LibraryException extends Exception{
    LibraryErrorMessages libraryErrorMessages;

    public LibraryException(LibraryErrorMessages libraryErrorMessages) {
        super(libraryErrorMessages.getErrorDescription());
        this.libraryErrorMessages = libraryErrorMessages;
    }
}
