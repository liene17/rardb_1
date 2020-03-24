package lv.accenture.bootcamp.rardb.repository;

import lv.accenture.bootcamp.rardb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByImdbID(String imdbID);
    Review findByReviewId(Integer reviewId);
}
