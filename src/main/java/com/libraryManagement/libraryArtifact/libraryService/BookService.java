package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.BookEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.BookItemModel;
import com.libraryManagement.libraryArtifact.libraryModel.FindByTitle;
import com.libraryManagement.libraryArtifact.libraryRepository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String addNewBookItem(BookItemModel bookItemModel) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(bookItemModel.getIsbn());
        bookEntity.setTitle(bookItemModel.getTitle());
        bookEntity.setSubject(bookItemModel.getSubject());
        bookEntity.setPublisher(bookItemModel.getPublisher());
        bookEntity.setLanguage(bookItemModel.getLanguage());
        bookEntity.setNoOfPages(bookItemModel.getNoOfPages());
        bookEntity.setBarcode(bookItemModel.getBarcode());
        bookEntity.setIsReference(bookItemModel.getIsReference());
        bookEntity.setBorrowedDate(bookItemModel.getBorrowedDate());
        bookEntity.setDueDate(bookItemModel.getDueDate());
        bookEntity.setPrice(bookItemModel.getPrice());
        bookEntity.setBookFormat(bookItemModel.getBookFormat().toString());
        bookEntity.setBookStatus(bookItemModel.getBookStatus().toString());
        bookEntity.setDateOfPurchase(bookItemModel.getDateOfPurchase());
        bookEntity.setPublicationDate(bookItemModel.getPublicationDate());
        return bookRepository.save(bookEntity).getTitle();
    }

    public List<FindByTitle> findBooksByTitle(String bookTitle) throws LibraryException {

        List<BookEntity> bookEntities = bookRepository.findBySubject(bookTitle);

        List<FindByTitle> findByTitles = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(bookEntities)) {
            bookEntities.forEach(bookEntity -> {
                FindByTitle findByTitle = new FindByTitle();
                findByTitle.setPublisher(bookEntity.getPublisher());
                findByTitle.setBarCode(bookEntity.getBarcode());
                findByTitle.setNoOfPages(bookEntity.getNoOfPages());
                findByTitles.add(findByTitle);
            });
            log.info("Find by titles list - {}", findByTitles);

            return findByTitles;
        } else {
            throw new LibraryException(LibraryErrorMessages.INVALID_TITLE);
        }
    }

}
