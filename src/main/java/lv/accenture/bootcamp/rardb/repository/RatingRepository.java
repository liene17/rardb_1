package lv.accenture.bootcamp.rardb.repository;

import lv.accenture.bootcamp.rardb.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
    List<Rating> findByReviewID(Integer reviewID);
}
