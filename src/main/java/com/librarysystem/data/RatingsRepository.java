package com.librarysystem.data;

import com.librarysystem.data.models.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends CrudRepository<Rating, Long> {
    @Query("SELECT AVG(rating.value) FROM Rating rating WHERE rating.publicationWork.id = (:id)")
    List<Double> getAverageRatingOfPublicationWork(@Param("id") long id);

    @Query("SELECT COUNT(rating.id) FROM Rating rating WHERE rating.publicationWork.id = (:id) AND rating.ip = (:ip)")
    List<Long> getRatingsCountForPublicationWorkByIp(@Param("id") long id, @Param("ip") String ip);
}
