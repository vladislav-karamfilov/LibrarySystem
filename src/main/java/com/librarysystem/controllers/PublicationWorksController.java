package com.librarysystem.controllers;

import com.librarysystem.datatransfermodels.*;
import com.librarysystem.data.models.Author;
import com.librarysystem.data.models.Book;
import com.librarysystem.data.models.Magazine;
import com.librarysystem.data.models.PublicationWorkGenre;
import com.librarysystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class PublicationWorksController {
    private static final int MAX_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private BooksService booksService;

    @Autowired
    private MagazinesService magazinesService;

    @Autowired
    private AuthorsService authorsService;

    @Autowired
    private RatingsService ratingsService;

    @Autowired
    private IpAddressProvider ipAddressProvider;

    @RequestMapping(value = "/publicationworks", method = RequestMethod.GET)
    public PublicationWorkDetailsResponseModel get(@RequestParam long id, HttpServletRequest request)
            throws Exception {
        PublicationWorkDetailsResponseModel publicationWork = null;

        Book book = this.booksService.getById(id);

        if (book == null) {
            Magazine magazine = this.magazinesService.getById(id);
            if (magazine != null) {
                publicationWork = PublicationWorkDetailsResponseModel.fromMagazine(magazine);
            }
        } else {
            publicationWork = PublicationWorkDetailsResponseModel.fromBook(book);
        }

        if (publicationWork == null) {
            throw new Exception("Publication work not found!");
        }

        publicationWork.setRating(this.ratingsService.getAverageRatingOfPublicationWork(id));

        String ipAddress = this.ipAddressProvider.getRequestIpAddress(request);

        publicationWork.setHasBeenRatedByIp(this.ratingsService.publicationWorkHasBeenRatedByIp(id, ipAddress));

        return publicationWork;
    }

    @RequestMapping(value = "/books/last", method = RequestMethod.GET)
    public Iterable<PublicationWorkSimpleResponseModel> getLastBooks(@RequestParam int count) {
        int pageSize = Math.min(count, MAX_PAGE_SIZE);

        Iterable<Book> books = this.booksService.getPage(0, pageSize);

        List<PublicationWorkSimpleResponseModel> result = new ArrayList<>();
        for (Book book : books) {
            PublicationWorkSimpleResponseModel publicationWork = PublicationWorkSimpleResponseModel.fromBook(book);
            publicationWork.setRating(this.ratingsService.getAverageRatingOfPublicationWork(book.getId()));
            result.add(publicationWork);
        }

        return result;
    }

    @RequestMapping(value = "/magazines/last", method = RequestMethod.GET)
    public Iterable<PublicationWorkSimpleResponseModel> getLastMagazines(@RequestParam int count) {
        int pageSize = Math.min(count, MAX_PAGE_SIZE);

        Iterable<Magazine> magazines = this.magazinesService.getPage(0, pageSize);

        List<PublicationWorkSimpleResponseModel> result = new ArrayList<>();
        for (Magazine magazine : magazines) {
            PublicationWorkSimpleResponseModel publicationWork = PublicationWorkSimpleResponseModel.fromMagazine(magazine);
            publicationWork.setRating(this.ratingsService.getAverageRatingOfPublicationWork(magazine.getId()));
            result.add(publicationWork);
        }

        return result;
    }

    @RequestMapping(value = "/publicationworks", method = RequestMethod.POST)
    public PublicationWorkModel create(@RequestBody PublicationWorkModel publicationWork) {
        if (publicationWork.getType() == PublicationWorkType.BOOK) {
            Book newBook = publicationWork.toBook();
            newBook.setAuthors(new HashSet<>(this.authorsService.getOrCreateAuthors(publicationWork.getAuthors())));

            newBook = booksService.save(newBook);

            publicationWork.setId(newBook.getId());
        } else {
            Magazine newMagazine = publicationWork.toMagazine();
            newMagazine.setAuthors(new HashSet<>(this.authorsService.getOrCreateAuthors(publicationWork.getAuthors())));

            newMagazine = magazinesService.save(newMagazine);

            publicationWork.setId(newMagazine.getId());
        }

        return publicationWork;
    }

    @RequestMapping(value = "/publicationworks", method = RequestMethod.PUT)
    public PublicationWorkModel update(@RequestBody PublicationWorkModel publicationWork) {
        if (publicationWork.getType() == PublicationWorkType.BOOK) {
            Book book = publicationWork.toBook();
            book.setAuthors(new HashSet<>(this.authorsService.getOrCreateAuthors(publicationWork.getAuthors())));

            booksService.updateBook(book);
        } else {
            Magazine magazine = publicationWork.toMagazine();
            magazine.setAuthors(new HashSet<>(this.authorsService.getOrCreateAuthors(publicationWork.getAuthors())));

            magazinesService.update(magazine);
        }

        return publicationWork;
    }

    @RequestMapping(value = "/publicationworks/filter")
    public FilteredPublicationWorksResponseModel filter(
            @RequestParam PublicationWorksFilterType type,
            @RequestParam String value,
            @RequestParam int page) throws Exception {
        FilteredPublicationWorksResponseModel result = new FilteredPublicationWorksResponseModel();

        Iterable<Book> books = null;
        Iterable<Magazine> magazines = null;

        switch (type) {
            case AUTHOR:
                Author author = this.authorsService.getByName(value);
                if (author != null) {
                    books = this.booksService.getByAuthor(author, page, DEFAULT_PAGE_SIZE);
                    magazines = this.magazinesService.getByAuthor(author, page, DEFAULT_PAGE_SIZE);
                }

                break;
            case GENRE:
                books = this.booksService.getByGenre(PublicationWorkGenre.valueOf(value), page, DEFAULT_PAGE_SIZE);
                magazines = this.magazinesService.getByGenre(PublicationWorkGenre.valueOf(value), page, DEFAULT_PAGE_SIZE);
                break;
            default:
                throw new Exception("Invalid filter type!");
        }

        if (books != null) {
            List<PublicationWorkSimpleResponseModel> bookResponseModels = new ArrayList<>();
            for (Book book : books) {
                PublicationWorkSimpleResponseModel publicationWork = PublicationWorkSimpleResponseModel.fromBook(book);
                publicationWork.setRating(this.ratingsService.getAverageRatingOfPublicationWork(book.getId()));
                bookResponseModels.add(publicationWork);
            }

            result.setBooks(bookResponseModels);
        }

        if (magazines != null) {
            List<PublicationWorkSimpleResponseModel> magazineResponseModels = new ArrayList<>();
            for (Magazine magazine : magazines) {
                PublicationWorkSimpleResponseModel publicationWork = PublicationWorkSimpleResponseModel.fromMagazine(magazine);
                publicationWork.setRating(this.ratingsService.getAverageRatingOfPublicationWork(magazine.getId()));
                magazineResponseModels.add(publicationWork);
            }

            result.setMagazines(magazineResponseModels);
        }

        return result;
    }
}
