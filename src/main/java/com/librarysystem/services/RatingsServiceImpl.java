package com.librarysystem.services;

import com.librarysystem.data.models.Rating;
import com.librarysystem.data.repositories.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RatingsServiceImpl implements RatingsService {
    @Autowired
    private RatingsRepository ratingsRepository;

    @Override
    public Double getAverageRatingOfPublicationWork(long id) {
        List<Double> result = this.ratingsRepository.getAverageRatingOfPublicationWork(id);

        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

    @Override
    public void save(Rating rating) {
        this.ratingsRepository.save(rating);
    }

    @Override
    public Byte getRatingForPublicationWorkByIp(long publicationWorkId, String ip) {
        List<Byte> ratingsForPublicationWorkByIp =
            this.ratingsRepository.getRatingForPublicationWorkByIp(publicationWorkId, ip);

        if (ratingsForPublicationWorkByIp.isEmpty()) {
            return null;
        }

        return ratingsForPublicationWorkByIp.get(0);
    }
}
