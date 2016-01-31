package com.librarysystem.services;

import com.librarysystem.data.models.Author;
import com.librarysystem.data.models.Book;
import com.librarysystem.data.models.PublicationWorkGenre;
import com.librarysystem.data.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksRepository booksRepository;

    @Override
    public Iterable<Book> getAll() {
        return this.booksRepository.findAll();
    }

    @Override
    public Book getById(Long id) {
        return this.booksRepository.findOne(id);
    }

    @Override
    public Iterable<Book> getPage(int page, int pageSize) {
        Pageable pageable = new PageRequest(0, pageSize, new Sort(Sort.Direction.DESC, "id"));
        return this.booksRepository.findAll(pageable).getContent();
    }

    @Override
    public Iterable<Book> getByAuthor(Author author, int page, int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
        return this.booksRepository.getByAuthor(author, pageable);
    }

    @Override
    public Iterable<Book> getByGenre(PublicationWorkGenre genre, int page, int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
        return this.booksRepository.getByGenre(genre, pageable);
    }

    @Override
    public Book updateBook(Book book) {
        // TODO: Implement.
        return null;
    }

    @Override
    public Book save(Book book) {
        return this.booksRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        this.booksRepository.delete(id);
    }
}
