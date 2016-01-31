package com.librarysystem.services;

import com.librarysystem.data.models.Author;

import java.util.Collection;

public interface AuthorsService {
    Iterable<Author> getAll();

    Author getById(Long id);

    Author getByName(String name);

    Collection<Author> getOrCreateAuthors(String mergedAuthorNames);

    Author save(Author author);

    void delete(Long id);
}
