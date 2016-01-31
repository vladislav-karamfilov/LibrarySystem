package com.librarysystem.services;

import com.librarysystem.data.models.Rating;

public interface RatingsService {
    Double getAverageRatingOfPublicationWork(long publicationWorkId);

    void save(Rating rating);

    Byte getRatingForPublicationWorkByIp(long publicationWorkId, String ip);
}
