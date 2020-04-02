package lv.accenture.bootcamp.rardb.repository;

import lv.accenture.bootcamp.rardb.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
    List<Rating> findByReviewID(Integer reviewID);

    @Query(value = "SELECT r.rating_id, r.review_id, r.stars, r.user_name_of_rating FROM ratings r WHERE r.user_name_of_rating = :userNameOfRating and r.review_id = :reviewID", nativeQuery = true)
    Rating findByUserNameReviewId(@Param(value= "userNameOfRating") String userNameOfRating, @Param(value= "reviewID") Integer reviewID);
}
