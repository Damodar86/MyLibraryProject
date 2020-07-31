package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.AccountStatus;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.LibraryAccountEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.UserCancellationModel;
import com.libraryManagement.libraryArtifact.libraryRepository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCancellationService {
    private AccountRepository accountRepository;

    public UserCancellationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UserCancellationModel userCancellationMethod(UserCancellationModel userCancellationModel) throws LibraryException {
        Optional<LibraryAccountEntity> libraryAccountEntity = accountRepository.findByAccountIdAndPassword(userCancellationModel.getLibrarianAccountId(), userCancellationModel.getPassword());

        if (libraryAccountEntity.isPresent()) {

            LibraryAccountEntity libraryAccountEntity2 = libraryAccountEntity.get();

            if (libraryAccountEntity2.getAccountType().equalsIgnoreCase("Librarian")) {

                Optional<LibraryAccountEntity> libraryAccountEntity3 = accountRepository.findByAccountIdAndAccountStatus(userCancellationModel.getMemberAccountId(), AccountStatus.Active.name());

                if (libraryAccountEntity3.isPresent()) {

                    LibraryAccountEntity libraryAccountEntity1 = libraryAccountEntity3.get();

                    libraryAccountEntity1.setAccountStatus(AccountStatus.Canceled.name());

                    accountRepository.save(libraryAccountEntity1);
                    userCancellationModel.setAccountStatus(AccountStatus.Canceled);
                    userCancellationModel.setMemberAccountId(libraryAccountEntity1.getAccountId());


                } else {
                    throw new LibraryException(LibraryErrorMessages.SORRY_THIS_IS_INVALID_MEMBER_ACCOUNT);

                }


            } else {
                throw new LibraryException(LibraryErrorMessages.SORRY_THIS_IS_NOT_LIBRARIAN_ACCOUNT);
            }
        } else {
            throw new LibraryException(LibraryErrorMessages.INVALID_ACCOUNT_ID);
        }


        return userCancellationModel;
    }
}
