package lv.accenture.bootcamp.rardb.repository;

import lv.accenture.bootcamp.rardb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByImdbID(String imdbID);
    Review findByReviewId(Integer reviewId);

    @Query(value = "SELECT movie_title, movie_poster, user_name, AVG(total_rating_sum/total_rating_count) as rating_average , review_text, Date FROM rardb1.reviews group by review_id order by tmp desc LIMIT 10", nativeQuery = true)
    List<Review> findTopTenReviews();
}
