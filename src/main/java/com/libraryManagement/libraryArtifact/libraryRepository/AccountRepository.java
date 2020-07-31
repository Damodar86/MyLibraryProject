package com.libraryManagement.libraryArtifact.libraryRepository;

import com.libraryManagement.libraryArtifact.libraryEntity.LibraryAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<LibraryAccountEntity, Long> {
    Optional<LibraryAccountEntity> findByPersonEmail(String email);
    Optional<LibraryAccountEntity> findByPersonEmailAndPassword(String email, String password);
    Optional<LibraryAccountEntity> findByPhoneNumberAndPassword(String email, String password);
    Optional<LibraryAccountEntity>findByAccountIdAndPassword(Long accountId, String password);
    Optional<LibraryAccountEntity>findByAccountIdAndAccountStatus(Long accountId,String status);
}
