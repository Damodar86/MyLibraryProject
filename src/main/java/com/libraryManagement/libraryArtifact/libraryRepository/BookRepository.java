package com.libraryManagement.libraryArtifact.libraryRepository;

import com.libraryManagement.libraryArtifact.libraryEntity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity,String> {
    List<BookEntity>findBySubjectOrBarcode(String title, String barCode);
    List<BookEntity>findBySubject(String title);
    Optional<BookEntity> findByBarcodeAndBookStatus(String barCode, String bookStatus);

}
