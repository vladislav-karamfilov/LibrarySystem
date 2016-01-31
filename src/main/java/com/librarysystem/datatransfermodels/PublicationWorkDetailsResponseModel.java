package com.librarysystem.datatransfermodels;

import com.librarysystem.data.models.Book;
import com.librarysystem.data.models.Magazine;
import com.librarysystem.data.models.PublicationWork;

import java.util.Date;

public class PublicationWorkDetailsResponseModel extends PublicationWorkSimpleResponseModel {
    private Date publicationDate;

    private int pages;

    private Byte ratingByIp;

    //private boolean hasBeenRatedByIp;

    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public int getPages() {
        return this.pages;
    }

    public Byte getRatingByIp() {
        return this.ratingByIp;
    }

    public void setRatingByIp(Byte ratingByIp) {
        this.ratingByIp = ratingByIp;
    }

    public boolean getHasBeenRatedByIp() {
        return this.ratingByIp != null;
    }

    public static PublicationWorkDetailsResponseModel fromBook(Book book) {
        PublicationWorkDetailsResponseModel publicationWork = fromPublicationWorkEntityModel(book);

        publicationWork.type = PublicationWorkType.BOOK;

        return publicationWork;
    }

    public static PublicationWorkDetailsResponseModel fromMagazine(Magazine magazine) {
        PublicationWorkDetailsResponseModel publicationWork = fromPublicationWorkEntityModel(magazine);

        publicationWork.type = PublicationWorkType.MAGAZINE;
        publicationWork.issue = magazine.getIssue();

        return publicationWork;
    }

    private static PublicationWorkDetailsResponseModel fromPublicationWorkEntityModel(PublicationWork pubWork) {
        PublicationWorkDetailsResponseModel publicationWork = new PublicationWorkDetailsResponseModel();

        publicationWork.title = pubWork.getTitle();
        publicationWork.id = pubWork.getId();
        publicationWork.genre = pubWork.getGenre();
        publicationWork.pages = pubWork.getPages();
        publicationWork.publicationDate = pubWork.getPublicationDate();
        publicationWork.authors = getAuthorsMerged(pubWork.getAuthors());

        return publicationWork;
    }
}
