package lv.accenture.bootcamp.rardb.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review implements Comparable< Review >{

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

    @Column(name = "movie_title")
    private String movieTitle;

    @Column(name = "movie_poster")
    private String moviePoster;

    public java.lang.Float totalRatingSum;

    public Integer totalRatingCount;

    public java.lang.Float ratingForThisReview;

    public Review(String userName) {
        this.userName = userName;
        totalRatingSum=0.0f;
        totalRatingCount =0;

    }
    public Review() {
        totalRatingSum=0.0f;
        totalRatingCount =0;
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

    public Float getTotalRatingSum() {
        return totalRatingSum;
    }

    public void setTotalRatingSum(Float totalRatingSum) {
        this.totalRatingSum = totalRatingSum;
    }

    public Integer getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(Integer totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public Float getRatingForThisReview() {
        Float result = 0.0f;
        if(totalRatingCount != null && totalRatingCount != 0){
            result = totalRatingSum/totalRatingCount;
        }
        if(result == null){
            return 0.0f;
        } else {
            DecimalFormat df = new DecimalFormat("#.#");
            return Float.valueOf(df.format(result));
        }
    }

    public void setRatingForThisReview(Float ratingForThisReview) {
        this.ratingForThisReview = ratingForThisReview;
    }

    @Override
    public int compareTo(Review o) {
        return this.getRatingForThisReview().compareTo(o.getRatingForThisReview());
    }
}

