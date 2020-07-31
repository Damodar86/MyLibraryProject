package com.libraryManagement.libraryArtifact.libraryRepository;

import com.libraryManagement.libraryArtifact.libraryEntity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity,String> {
    Optional<PersonEntity> findByPhoneNumber(String phoneNumber);
    Optional<PersonEntity> findByEmail(String email);
}
