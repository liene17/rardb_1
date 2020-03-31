package lv.accenture.bootcamp.rardb.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@Builder
@Entity
@Table(name = "ratings")
public class Rating {

    //TODO : in such approach any user can submit multiple times.
    // Think of adding userId and @Unique usage
    //in progress by Santa

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    private Integer ratingID;

    @Column(name = "stars")
    private float stars;

    @Column(name = "review_id")
    private Integer reviewID;

    @Column(name = "userNameOfRating")
    private Integer userIdOfRating;

    public Integer getRatingID() {
        return ratingID;
    }

    public Rating(Integer ratingID, float stars, Integer reviewID, Integer userIdOfRating) {
        this.ratingID = ratingID;
        this.stars = stars;
        this.reviewID = reviewID;
        this.userIdOfRating = userIdOfRating;
    }

    public Rating() {
    }

    public void setRatingID(Integer ratingID) {
        this.ratingID = ratingID;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getUserUserIdOfRating() {
        return userIdOfRating;
    }

    public void setUserIdOfRating(Integer userIdOfRating) {
        this.userIdOfRating = userIdOfRating;
    }
}
