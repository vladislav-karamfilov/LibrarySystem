package com.librarysystem.services;

import com.librarysystem.data.models.Author;
import com.librarysystem.data.models.Magazine;
import com.librarysystem.data.models.PublicationWorkGenre;
import com.librarysystem.data.repositories.MagazinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MagazinesServiceImpl implements MagazinesService {
    @Autowired
    private MagazinesRepository magazinesRepository;

    @Override
    public Iterable<Magazine> getAll() {
        return this.magazinesRepository.findAll();
    }

    @Override
    public Magazine getById(Long id) {
        return this.magazinesRepository.findOne(id);
    }

    @Override
    public Iterable<Magazine> getPage(int page, int pageSize) {
        Pageable pageable = new PageRequest(0, pageSize, new Sort(Sort.Direction.DESC, "id"));
        return this.magazinesRepository.findAll(pageable).getContent();
    }

    @Override
    public Iterable<Magazine> getByAuthor(Author author, int page, int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
        return this.magazinesRepository.getByAuthor(author, pageable);
    }

    @Override
    public Iterable<Magazine> getByGenre(PublicationWorkGenre genre, int page, int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
        return this.magazinesRepository.getByGenre(genre, pageable);
    }

    @Override
    public Magazine update(Magazine magazine) {
        // TODO: Implement.
        return null;
    }

    @Override
    public Magazine save(Magazine magazine) {
        return this.magazinesRepository.save(magazine);
    }

    @Override
    public void delete(Long id) {
        this.magazinesRepository.delete(id);
    }
}
