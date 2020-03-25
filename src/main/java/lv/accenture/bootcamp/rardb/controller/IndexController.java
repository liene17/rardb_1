package lv.accenture.bootcamp.rardb.controller;


import lv.accenture.bootcamp.rardb.model.Review;
import lv.accenture.bootcamp.rardb.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public ModelAndView toIndex() {

        ModelAndView modelAndView = new ModelAndView();
        List<Review> allSavedReviews = reviewRepository.findAll();
        Collections.sort(allSavedReviews, Collections.reverseOrder());
        modelAndView.addObject("allSavedReviews", allSavedReviews);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
