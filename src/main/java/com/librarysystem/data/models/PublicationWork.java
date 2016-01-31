package com.librarysystem.data.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public abstract class PublicationWork {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Length(min = 3, max = 200)
    private String title;

    @ManyToMany
    private Set<Author> authors;

    @NotNull
    @Min(1)
    private int pages;

    @NotNull
    private Date publicationDate;

    @NotNull
    private PublicationWorkGenre genre;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Rating> ratings;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public PublicationWorkGenre getGenre() {
        return genre;
    }

    public void setGenre(PublicationWorkGenre genre) {
        this.genre = genre;
    }

    public Set<Rating> getRatings() {
        return this.ratings;
    }

    public void setRating(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
