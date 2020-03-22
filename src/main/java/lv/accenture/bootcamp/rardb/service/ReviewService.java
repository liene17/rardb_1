package lv.accenture.bootcamp.rardb.service;

import lv.accenture.bootcamp.rardb.model.Review;
import lv.accenture.bootcamp.rardb.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findByImdbID(String id){
        return reviewRepository.findByImdbID(id);
    }

    public Review saveReview(Review review){
        return reviewRepository.save(review);
    }
}
