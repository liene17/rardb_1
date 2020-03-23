package lv.accenture.bootcamp.rardb.controller;

import lv.accenture.bootcamp.rardb.model.Review;
import lv.accenture.bootcamp.rardb.network.ImdbAPIService;
import lv.accenture.bootcamp.rardb.network.ImdbMovieData;
import lv.accenture.bootcamp.rardb.repository.ReviewRepository;
import lv.accenture.bootcamp.rardb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ImdbAPIService imdbAPIService;

    @GetMapping(value = "/review/add/{id}")
    public ModelAndView reviewAdd(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        ImdbMovieData thisMovie = imdbAPIService.getOneMovieOnly(id);
        modelAndView.addObject("review", new Review());
        modelAndView.addObject("thisMovie", thisMovie);
        modelAndView.setViewName("add-review");
        return modelAndView;
    }

    @PostMapping(value = "/review/add-review/{id}")
    public ModelAndView addReview(@Valid Review reviewToAdd, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("add-review");
        } else {
            reviewService.saveReview(reviewToAdd);
            modelAndView.addObject("successMessage", "Review has been added successfully");
            modelAndView.setViewName("redirect:movie/"+reviewToAdd.getImdbID());

        }
        return modelAndView;

    }



//    @PostMapping (value = "/review/add-review")
//    public List generateReview(){
//    List<Review> generatedReviews = new ArrayList<>();
//    return generatedReviews;
//    }
}
