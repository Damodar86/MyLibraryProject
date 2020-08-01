package com.libraryManagement.libraryArtifact.libraryRepository;

import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.BookCheckOutEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookCheckOutRepository extends JpaRepository<BookCheckOutEntity, BookCheckOutEntityPK>{
    Optional <BookCheckOutEntity>findByBookBarCodeAndBorrowerAccountIdAndReturnedDateIsNull(String barCode, Long borrowerAccountId);
    Optional<BookCheckOutEntity>findByBookBarCodeAndBorrowerAccountId(String barCode, String accountId);
    List<BookCheckOutEntity> findByBorrowerAccountId(Long account_Id);
    Optional<BookCheckOutEntity>findByBookBarCode(String barCode);
    List<BookCheckOutEntity>findByBookBarCodeOrIssuedById(String barCode,String issuedBy);
    List<BookCheckOutEntity>findByBorrowedDateAndReturnedDateIsNull(LocalDate borrowedDate);
}
