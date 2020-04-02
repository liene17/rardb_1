package lv.accenture.bootcamp.rardb.controller;


import lv.accenture.bootcamp.rardb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    @Autowired
    private ReviewService reviewService;

    // TODO: Very ineffective. What if DB contains thousands, millions of reviews?
    // Much better way is to delegate it to DB (compute average, join tables, aggregate by reviewId, LIMIT 10)
    //done by Līva and Santa

    @GetMapping("/")
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reviewTop", reviewService.findTopTenReviews());
        modelAndView.setViewName("index");
        return modelAndView;
    }


}


