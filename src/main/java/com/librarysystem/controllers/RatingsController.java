package com.librarysystem.controllers;

import com.librarysystem.datatransfermodels.RatingRequestModel;
import com.librarysystem.data.models.PublicationWork;
import com.librarysystem.data.models.Rating;
import com.librarysystem.services.BooksService;
import com.librarysystem.services.IpAddressProvider;
import com.librarysystem.services.MagazinesService;
import com.librarysystem.services.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RatingsController {
    @Autowired
    private RatingsService ratingsService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private MagazinesService magazinesService;

    @Autowired
    private IpAddressProvider ipAddressProvider;

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    private double ratePublicationWork(@RequestBody RatingRequestModel rating, HttpServletRequest request)
            throws Exception {
        String ipAddress = this.ipAddressProvider.getRequestIpAddress(request);

        long publicationWorkId = rating.getPublicationWorkId();
        boolean publicationWorkHasBeenRatedByIp =
                this.ratingsService.publicationWorkHasBeenRatedByIp(publicationWorkId, ipAddress);

        if (publicationWorkHasBeenRatedByIp) {
            throw new Exception("The publication work has already been rated by your IP!");
        }

        PublicationWork publicationWork = this.booksService.getById(publicationWorkId);
        if (publicationWork == null) {
            publicationWork = this.magazinesService.getById(publicationWorkId);
        }

        if (publicationWork == null) {
            throw new Exception("Publication work not found!");
        }

        Rating newRating = new Rating();
        newRating.setPublicationWork(publicationWork);
        newRating.setValue(rating.getValue());
        newRating.setIp(ipAddress);

        this.ratingsService.save(newRating);

        return this.ratingsService.getAverageRatingOfPublicationWork(publicationWorkId);
    }
}
