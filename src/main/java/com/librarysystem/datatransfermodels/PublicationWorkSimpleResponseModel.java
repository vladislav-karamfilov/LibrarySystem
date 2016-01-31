package com.librarysystem.datatransfermodels;

import com.librarysystem.data.models.*;

public class PublicationWorkSimpleResponseModel {
    protected long id;

    protected String title;

    protected String authors;

    protected Double rating;

    protected int issue;

    protected PublicationWorkGenre genre;

    protected PublicationWorkType type;

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthors() {
        return this.authors;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getIssue() {
        return this.issue;
    }

    public PublicationWorkGenre getGenre() {
        return this.genre;
    }

    public PublicationWorkType getType() {
        return this.type;
    }

    public static PublicationWorkSimpleResponseModel fromBook(Book book) {
        PublicationWorkSimpleResponseModel publicationWork = fromPublicationWorkEntityModel(book);

        publicationWork.type = PublicationWorkType.BOOK;

        return publicationWork;
    }

    public static PublicationWorkSimpleResponseModel fromMagazine(Magazine magazine) {
        PublicationWorkSimpleResponseModel publicationWork = fromPublicationWorkEntityModel(magazine);

        publicationWork.issue = magazine.getIssue();
        publicationWork.type = PublicationWorkType.MAGAZINE;

        return publicationWork;
    }

    protected static String getAuthorsMerged(Iterable<Author> authors) {
        StringBuilder result = new StringBuilder();

        for (Author author: authors) {
            result.append(author.getName());
            result.append(", ");
        }

        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }

    private static PublicationWorkSimpleResponseModel fromPublicationWorkEntityModel(PublicationWork pubWork) {
        PublicationWorkSimpleResponseModel publicationWork = new PublicationWorkSimpleResponseModel();

        publicationWork.title = pubWork.getTitle();
        publicationWork.id = pubWork.getId();
        publicationWork.genre = pubWork.getGenre();
        publicationWork.authors = getAuthorsMerged(pubWork.getAuthors());

        return publicationWork;
    }
}
