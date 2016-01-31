package com.librarysystem.datatransfermodels;

import com.librarysystem.data.models.Book;
import com.librarysystem.data.models.Magazine;
import com.librarysystem.data.models.PublicationWorkGenre;

import java.util.Date;

public class PublicationWorkModel {
    private long id;

    private String title;

    private String authors;

    private int pages;

    private Date publicationDate;

    private PublicationWorkGenre genre;

    private PublicationWorkType type;

    private int issue;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return this.authors;
    }

    public void setAuthors(String authors) {
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
        return this.genre;
    }

    public void setGenre(PublicationWorkGenre genre) {
        this.genre = genre;
    }

    public PublicationWorkType getType() {
        return this.type;
    }

    public void setType(PublicationWorkType type) {
        this.type = type;
    }

    public int getIssue() {
        return this.issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book toBook(){
        Book book = new Book();

        book.setGenre(this.getGenre());
        book.setPages(this.getPages());
        book.setTitle(this.getTitle());
        book.setPublicationDate(this.getPublicationDate());
        book.setGenre(this.getGenre());

        return book;
    }

    public Magazine toMagazine() {
        Magazine magazine = new Magazine();

        magazine.setGenre(this.getGenre());
        magazine.setPages(this.getPages());
        magazine.setTitle(this.getTitle());
        magazine.setPublicationDate(this.getPublicationDate());
        magazine.setGenre(this.getGenre());
        magazine.setIssue(this.getIssue());

        return magazine;
    }
}
