package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.AccountStatus;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.LibraryAccountEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.UserDeletionModel;
import com.libraryManagement.libraryArtifact.libraryRepository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeletionService {
    private AccountRepository accountRepository;

    public UserDeletionService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UserDeletionModel userDeletionByIdMethod(UserDeletionModel userDeletionModel) throws LibraryException {
        Optional<LibraryAccountEntity> libraryAccountEntity = accountRepository.findByAccountIdAndPassword(userDeletionModel.getLibrarianAccountId(), userDeletionModel.getPassword());
        if (libraryAccountEntity.isPresent()) {
            LibraryAccountEntity libraryAccountEntity1 = libraryAccountEntity.get();
            libraryAccountEntity1.setAccountStatus(AccountStatus.Closed.name());
            accountRepository.save(libraryAccountEntity1);
            userDeletionModel.setUserAccountId(libraryAccountEntity1.getAccountId());
            userDeletionModel.setAccountStatus(AccountStatus.valueOf(libraryAccountEntity1.getAccountStatus()));

        } else {
            throw new LibraryException(LibraryErrorMessages.INVALID_GIVEN_BARCODE_AND_ACCOUNT_ID);
        }
        return userDeletionModel;
    }
}