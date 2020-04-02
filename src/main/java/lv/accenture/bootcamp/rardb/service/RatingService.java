package lv.accenture.bootcamp.rardb.service;

import lv.accenture.bootcamp.rardb.model.Rating;
import lv.accenture.bootcamp.rardb.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findByReviewID(Integer reviewID) {
        return ratingRepository.findByReviewID(reviewID);
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating findByUserNameReviewId(String userNameOfRating, Integer reviewID) {
        return ratingRepository.findByUserNameReviewId(userNameOfRating, reviewID);
    };



}
