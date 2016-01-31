package com.librarysystem.services;

import com.librarysystem.data.models.Author;
import com.librarysystem.data.models.Book;
import com.librarysystem.data.models.PublicationWorkGenre;

public interface BooksService {
    Iterable<Book> getAll();

    Book getById(Long id);

    Iterable<Book> getPage(int page, int pageSize);

    Iterable<Book> getByAuthor(Author author, int page, int pageSize);

    Iterable<Book> getByGenre(PublicationWorkGenre genre, int page, int pageSize);

    Book updateBook(Book book);

    Book save(Book book);

    void deleteBook(Long id);
}
