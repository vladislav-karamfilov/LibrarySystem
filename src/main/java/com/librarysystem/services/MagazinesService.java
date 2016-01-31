package com.librarysystem.services;

import com.librarysystem.data.models.Author;
import com.librarysystem.data.models.Magazine;
import com.librarysystem.data.models.PublicationWorkGenre;

public interface MagazinesService {
    Iterable<Magazine> getAll();

    Magazine getById(Long id);

    Iterable<Magazine> getPage(int page, int pageSize);

    Iterable<Magazine> getByAuthor(Author author, int page, int pageSize);

    Iterable<Magazine> getByGenre(PublicationWorkGenre genre, int page, int pageSize);

    Magazine update(Magazine magazine);

    Magazine save(Magazine magazine);

    void delete(Long id);
}


