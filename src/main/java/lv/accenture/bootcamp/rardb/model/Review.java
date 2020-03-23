package lv.accenture.bootcamp.rardb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Review_id")
    private Integer id;

    @Column(name = "imdbid")
    private String imdbID;

    @Column(name = "UserName")
    private String userName;


    @Column(name = "Review")
    @Size(min = 30, max = 2500)
    private String review;

    @Column(name = "Date")
    private String date;

    public Review(Integer id, String imdbID, String userName, String review) {
        this.id = id;
        this.imdbID = imdbID;
        this.userName = userName;
        this.review = review;
    }

    public Review(String userName) {
        this.userName = userName;

    }
    public Review() {

    }

//    public Review() {
//    }

    public Integer getId() {
        return id;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDate() {
        return date;
    }
}


