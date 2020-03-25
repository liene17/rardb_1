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
import java.math.RoundingMode;
import java.security.Principal;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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
        String user = principal.getName();
        modelAndView.addObject("review", new Review(user));
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
        if (!bindingResult.hasErrors()) {
            ratingService.saveRating(ratingToAdd);
        }
        Review reviewToUpdate = reviewService.findByReviewID(ratingToAdd.getReviewID());
        if (reviewToUpdate.totalRatingCount == null)
            reviewToUpdate.totalRatingCount = 1;
        else {
            reviewToUpdate.totalRatingCount++;
        }
        if (reviewToUpdate.totalRatingSum == null)
            reviewToUpdate.totalRatingSum = ratingToAdd.getStars();
        else {
            reviewToUpdate.totalRatingSum += ratingToAdd.getStars();
        }
        reviewService.saveReview(reviewToUpdate);
        String imdbIdOfReview = reviewService.findByReviewID(ratingToAdd.getReviewID()).getImdbID();
        modelAndView.setViewName("redirect:read-review/" + ratingToAdd.getReviewID() + "/" + imdbIdOfReview);
        return modelAndView;
    }

    @GetMapping(value = "/read-review/{id}/{imdbID}")
    public ModelAndView readOneReview(@PathVariable Integer id, @PathVariable String imdbID) {
        ModelAndView modelAndView = new ModelAndView();
        List<Rating> ratingExists = ratingService.findByReviewID(id);
        Review thisReview = reviewService.findByReviewID(id);
        if (ratingExists.size() > 0) {
            Float ratingToUse = thisReview.getRatingForThisReview();
            DecimalFormat df = new DecimalFormat("#.#");
            df.setRoundingMode(RoundingMode.CEILING);
            modelAndView.addObject("ratingToUse", df.format(ratingToUse));
        } else {
            modelAndView.addObject("ratingToUse", 0.0f);
        }
        modelAndView.addObject("newRating", new Rating());
        Review oneReview = reviewService.findByReviewID(id);
        modelAndView.addObject("oneReview", oneReview);
        ImdbMovieData oneMovie = imdbAPIService.getOneMovieOnly(imdbID);
        modelAndView.addObject("oneMovie", oneMovie);
        modelAndView.setViewName("read-review");
        return modelAndView;
    }

    @GetMapping(value = "/home")
    public ModelAndView seeHighestReviewRatings() {
        ModelAndView modelAndView = new ModelAndView();
        List<Review> allSavedReviews = reviewRepository.findAll();
        Collections.sort(allSavedReviews, Collections.reverseOrder());
        modelAndView.addObject("allSavedReviews", allSavedReviews);
        List<String> movieIds = allSavedReviews.stream().map(x -> x.getImdbID()).collect(Collectors.toList());
        Set<String> idsWithoutDuplicates = new HashSet<>(movieIds);
        List<String> movieTitlesFromSet = new ArrayList<>(idsWithoutDuplicates);
        List<ImdbMovieData> finalMovies = new ArrayList<>();
        for (int i = 0; i < movieTitlesFromSet.size(); i++) {
           finalMovies.add(imdbAPIService.getOneMovieOnly(movieTitlesFromSet.get(i)));
        }
        modelAndView.addObject("finalMovies", finalMovies);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
