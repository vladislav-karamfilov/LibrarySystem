package com.librarysystem.services;

import com.librarysystem.data.models.Author;
import com.librarysystem.data.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AuthorsServiceImpl implements AuthorsService {
    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public Iterable<Author> getAll() {
        return this.authorsRepository.findAll();
    }

    @Override
    public Author getById(Long id) {
        return this.authorsRepository.findOne(id);
    }

    @Override
    public Author getByName(String name) {
        List<Author> authors = this.authorsRepository.getByName(name);

        if(authors.isEmpty()){
            return null;
        }

        return authors.get(0);
    }

    @Override
    public Collection<Author> getOrCreateAuthors(String mergedAuthorNames) {
        String[] splittedAuthorNames = mergedAuthorNames.split(",");

        List<Author> authors = new ArrayList<>();

        for (String authorName : splittedAuthorNames) {
            String trimmedAuthorName = authorName.trim();
            if (trimmedAuthorName.length() > 6) { // Valid names contain at least 6 characters
                Author author = this.getByName(trimmedAuthorName);

                if (author == null) {
                    author = new Author();
                    author.setName(trimmedAuthorName);

                    author = this.save(author);
                }

                authors.add(author);
            }
        }

        return authors;
    }

    @Override
    public Author save(Author author) {
        return this.authorsRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        this.authorsRepository.delete(id);
    }
}
