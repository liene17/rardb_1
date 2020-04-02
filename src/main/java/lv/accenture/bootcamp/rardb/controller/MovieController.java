package lv.accenture.bootcamp.rardb.controller;

import lv.accenture.bootcamp.rardb.model.Rating;
import lv.accenture.bootcamp.rardb.model.Review;
import lv.accenture.bootcamp.rardb.network.ImdbAPIService;
import lv.accenture.bootcamp.rardb.network.ImdbMovieData;
import lv.accenture.bootcamp.rardb.service.RatingService;
import lv.accenture.bootcamp.rardb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private ImdbAPIService imdbAPIService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RatingService ratingService;

    @GetMapping(value = "/search/movie/{id}")
    public ModelAndView oneMovieInfo(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        ImdbMovieData oneMovie = imdbAPIService.getOneMovieOnly(id);
        List<Review> reviewsForThisMovie = reviewService.findByImdbID(id);
        for (Review reviewInList : reviewsForThisMovie) {
            Float ratingToUse = reviewInList.getRatingForThisReview();
            if (ratingToUse == null) {
                reviewInList.ratingForThisReview = "0.0";
            } else {
                DecimalFormat df = new DecimalFormat("#.#");
                reviewInList.ratingForThisReview = df.format(ratingToUse);
            }
        }

        modelAndView.addObject("oneMovie", oneMovie);
        modelAndView.addObject("reviewsForThisMovie", reviewsForThisMovie);
        modelAndView.addObject("rating", new Rating());
        modelAndView.setViewName("movie");
        return modelAndView;
    }
}
