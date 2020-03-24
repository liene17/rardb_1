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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    private Integer ratingID;

    @Column(name = "stars")
    private float stars;

    @Column(name = "review_id")
    private Integer reviewID;

    public Integer getRatingID() {
        return ratingID;
    }

    public Rating(Integer ratingID, float stars, Integer reviewID) {
        this.ratingID = ratingID;
        this.stars = stars;
        this.reviewID = reviewID;
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
}


