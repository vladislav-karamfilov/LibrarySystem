package com.librarysystem.data.repositories;

import com.librarysystem.data.models.Author;
import com.librarysystem.data.models.Magazine;
import com.librarysystem.data.models.PublicationWorkGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazinesRepository extends PagingAndSortingRepository<Magazine, Long> {
    @Query("SELECT magazine FROM Magazine magazine WHERE (:author) MEMBER OF magazine.authors")
    Page<Magazine> getByAuthor(@Param("author") Author author, Pageable pageable);

    @Query("SELECT magazine FROM Magazine magazine WHERE magazine.genre = (:genre)")
    Page<Magazine> getByGenre(@Param("genre") PublicationWorkGenre genre, Pageable pageable);
}
