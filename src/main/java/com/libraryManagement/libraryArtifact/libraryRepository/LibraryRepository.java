package com.libraryManagement.libraryArtifact.libraryRepository;

import com.libraryManagement.libraryArtifact.libraryEntity.LibraryMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends JpaRepository<LibraryMemberEntity,Long> {
   List<LibraryMemberEntity> findByMobile(String mobileNumber);
}
