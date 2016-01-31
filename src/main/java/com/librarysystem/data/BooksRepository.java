package com.librarysystem.data;

import com.librarysystem.data.models.Author;
import com.librarysystem.data.models.Book;
import com.librarysystem.data.models.PublicationWorkGenre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends PagingAndSortingRepository<Book, Long> {
    @Query("SELECT book FROM Book book WHERE (:author) MEMBER OF book.authors")
    List<Book> getByAuthor(@Param("author") Author author, Pageable pageable);

    @Query("SELECT book FROM Book book WHERE book.genre = (:genre)")
    List<Book> getByGenre(@Param("genre") PublicationWorkGenre genre, Pageable pageable);
}