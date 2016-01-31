package com.librarysystem.datatransfermodels;

public class FilteredPublicationWorksResponseModel {
    private Iterable<PublicationWorkSimpleResponseModel> books;

    private Iterable<PublicationWorkSimpleResponseModel> magazines;

    public Iterable<PublicationWorkSimpleResponseModel> getBooks() {
        return books;
    }

    public void setBooks(Iterable<PublicationWorkSimpleResponseModel> books) {
        this.books = books;
    }

    public Iterable<PublicationWorkSimpleResponseModel> getMagazines() {
        return magazines;
    }

    public void setMagazines(Iterable<PublicationWorkSimpleResponseModel> magazines) {
        this.magazines = magazines;
    }
}
