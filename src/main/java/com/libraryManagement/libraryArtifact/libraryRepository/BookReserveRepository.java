package com.libraryManagement.libraryArtifact.libraryRepository;

import com.libraryManagement.libraryArtifact.libraryEntity.BookReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReserveRepository extends JpaRepository<BookReserveEntity, String> {

}
