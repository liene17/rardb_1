package lv.accenture.bootcamp.rardb.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Integer reviewId;

    @Column(name = "imdbid")
    private String imdbID;

    @Column(name = "user_name")
    private String userName;


    @Column(name = "review_text")
    @Size(min = 1, max = 2500)
    private String reviewText;

    @Column(name = "date")
    private OffsetDateTime date;

    public java.lang.Float ratingForThisReview;

    public Review(Integer reviewId, String imdbID, String userName, String reviewText) {
        this.reviewId = reviewId;
        this.imdbID = imdbID;
        this.userName = userName;
        this.reviewText = reviewText;
        ratingForThisReview=0.0f;
    }

    public Review(String userName) {
        this.userName = userName;
        ratingForThisReview=0.0f;

    }
    public Review() {
        ratingForThisReview=0.0f;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public OffsetDateTime getDate() {
        return date;
    }
}

