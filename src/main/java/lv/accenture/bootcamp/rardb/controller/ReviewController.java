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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ImdbAPIService imdbAPIService;

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
        System.out.println(reviewToAdd.getReviewText());
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("add-review");
        } else {
            reviewService.saveReview(reviewToAdd);
            modelAndView.setViewName("redirect:search/movie/"+ reviewToAdd.getImdbID());
        }
        return modelAndView;
    }
}
