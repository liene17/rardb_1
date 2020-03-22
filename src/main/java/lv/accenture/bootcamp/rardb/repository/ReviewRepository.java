package lv.accenture.bootcamp.rardb.repository;

import lv.accenture.bootcamp.rardb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
//    @Query("SELECT r FROM reviews r WHERE r.imdbID =:imdbID")
//    List<Review> findByImdbID(@Param(value= "imdbID") String imdbID);

    List<Review> findByImdbID(String imdbID);
}
