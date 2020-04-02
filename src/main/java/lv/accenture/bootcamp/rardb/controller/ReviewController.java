package lv.accenture.bootcamp.rardb.controller;

import lv.accenture.bootcamp.rardb.model.Rating;
import lv.accenture.bootcamp.rardb.model.Review;
import lv.accenture.bootcamp.rardb.network.ImdbAPIService;
import lv.accenture.bootcamp.rardb.network.ImdbMovieData;
import lv.accenture.bootcamp.rardb.repository.ReviewRepository;
import lv.accenture.bootcamp.rardb.service.RatingService;
import lv.accenture.bootcamp.rardb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ImdbAPIService imdbAPIService;

    @Autowired
    private RatingService ratingService;

    @GetMapping(value = "/review/add/{id}")
    public ModelAndView getReviewView(@PathVariable String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        ImdbMovieData thisMovie = imdbAPIService.getOneMovieOnly(id);
        Principal principal = request.getUserPrincipal();
        String userName = principal.getName();
        modelAndView.addObject("review", new Review(userName));
        modelAndView.addObject("thisMovie", thisMovie);
        modelAndView.setViewName("add-review");
        return modelAndView;
    }

    @PostMapping(value = "/addreview")
    public ModelAndView addReview(@Valid Review reviewToAdd, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("add-review");
        } else {
            reviewToAdd.setDate(java.time.OffsetDateTime.now(ZoneId.of("Europe/Riga")));
            reviewService.saveReview(reviewToAdd);
            modelAndView.setViewName("redirect:search/movie/" + reviewToAdd.getImdbID());
        }
        return modelAndView;
    }

    @PostMapping(value = "/addrating")
    public ModelAndView addRating(@Valid Rating ratingToAdd, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Review thisReview = reviewService.findByReviewID(ratingToAdd.getReviewID());
        Principal principal = request.getUserPrincipal();
        String authorUserName = principal.getName();
        if(authorUserName.equals(thisReview.getUserName())){
            modelAndView.setViewName("redirect:read-review/" + ratingToAdd.getReviewID());
            return modelAndView;
        }

        if (!bindingResult.hasErrors()) {
            ratingService.saveRating(ratingToAdd);
        }
        Review reviewToUpdate = reviewService.findByReviewID(ratingToAdd.getReviewID());
        // Can merge this 2 IFs
        //done by Santa

        if (reviewToUpdate.totalRatingCount == null && reviewToUpdate.totalRatingSum == null) {
            reviewToUpdate.totalRatingCount = 1;
            reviewToUpdate.totalRatingSum = ratingToAdd.getStars();
        }
        else {
            reviewToUpdate.totalRatingCount++;
            reviewToUpdate.totalRatingSum += ratingToAdd.getStars();
        }
        reviewService.saveReview(reviewToUpdate);

        //TODO: serious problem here : querying external API is much more "expensive"
        // operation than querying own database. Info about each movie should be saved in DB
        // when somebody writes review on it!
        //done by Santa
        modelAndView.setViewName("redirect:read-review/" + ratingToAdd.getReviewID());
        return modelAndView;
    }

    @GetMapping(value = "/read-review/{id}")
    public ModelAndView readOneReview(@PathVariable Integer id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        List<Rating> ratingExists = ratingService.findByReviewID(id);
        Review thisReview = reviewService.findByReviewID(id);
        if (ratingExists.size() > 0) {
            Float ratingToUse = thisReview.getRatingForThisReview();
            DecimalFormat df = new DecimalFormat("#.#");
            modelAndView.addObject("ratingToUse", df.format(ratingToUse));
        } else {
            modelAndView.addObject("ratingToUse", "0.0");
        }
        modelAndView.addObject("newRating", new Rating());
        Review oneReview = reviewService.findByReviewID(id);
        modelAndView.addObject("oneReview", oneReview);
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            String authorUserName = principal.getName();
            modelAndView.addObject("authorUserName", authorUserName);
            boolean authorMadeRatingAlready = false;
            Rating testRating = ratingService.findByUserNameReviewId(authorUserName, thisReview.getReviewId());
            if(ratingService.findByUserNameReviewId(authorUserName, thisReview.getReviewId()) != null){
                authorMadeRatingAlready = true;
            }
            modelAndView.addObject("authorMadeRatingAlready", authorMadeRatingAlready);
        }
        modelAndView.setViewName("read-review");
        return modelAndView;
    }
}
