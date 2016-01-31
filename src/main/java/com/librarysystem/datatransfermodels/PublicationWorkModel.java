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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Date getPublicationDate() {
        return publicationDate;
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

    public PublicationWorkType getType() {
        return type;
    }

    public void setType(PublicationWorkType type) {
        this.type = type;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public long getId() {
        return id;
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
