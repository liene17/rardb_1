package lv.accenture.bootcamp.rardb.controller;

import lv.accenture.bootcamp.rardb.network.ImdbAPIService;
import lv.accenture.bootcamp.rardb.network.ImdbMovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {

    @Autowired
    private ImdbAPIService imdbAPIService;

    @GetMapping(value = "/search/movie/{id}")
    public ModelAndView oneMovieInfo(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        ImdbMovieData oneMovie= imdbAPIService.getOneMovieOnly(id);
        modelAndView.addObject("oneMovie", oneMovie);
        modelAndView.setViewName("movie");
        return modelAndView;
    }
}
