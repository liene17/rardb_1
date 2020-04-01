package lv.accenture.bootcamp.rardb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class TopReviewService {

    public static ResultSet topReviews() throws SQLException {


        ModelAndView modelAndView = new ModelAndView();
        Connection connection = DBUtilService.acquireConnection();
        Statement statement = connection.createStatement();
        String topReviewsSQL = "select AVG(total_rating_sum/total_rating_count) as RatingAverage from reviews group by Review_id LIMIT 10";
        ResultSet topReviews = statement.executeQuery(topReviewsSQL);
        return topReviews;
    }
}
