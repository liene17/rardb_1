package lv.accenture.bootcamp.rardb.controller;


import lv.accenture.bootcamp.rardb.model.Review;
import lv.accenture.bootcamp.rardb.repository.ReviewRepository;
import lv.accenture.bootcamp.rardb.service.TopReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


@Controller
public class IndexController {


    @Autowired
    private ReviewRepository reviewRepository;


    @GetMapping("/")
    public ModelAndView toIndex() {

        ModelAndView modelAndView = new ModelAndView();

        // TODO: Very ineffective. What if DB contains thousands, millions of reviews?
        // Much better way is to delegate it to DB (compute average, join tables, aggregate by reviewId, LIMIT 10)
        List<Review> allSavedReviews = reviewRepository.findAll();
        Collections.sort(allSavedReviews, Collections.reverseOrder());
        modelAndView.addObject("allSavedReviews", allSavedReviews);
        modelAndView.setViewName("index");
        return modelAndView;
    }

//    @GetMapping ("/")
//    public String toIndex(Model model) {
//        try {
//            model.addAttribute("review", TopReviewService.topReviews());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "/";
//    }


    }


