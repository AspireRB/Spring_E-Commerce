package com.uan.ecommerce.controller;

import com.uan.ecommerce.model.Client;
import com.uan.ecommerce.model.Movie;
import com.uan.ecommerce.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("movies", movieService.findAll());
        return "movies/show";
    }

    @GetMapping("/create")
    public String create() {
        return "movies/create";
    }

    @PostMapping("/save")
    public String save(Movie movie) {
        LOGGER.info("This is the object movie {}",movie);
        Client c = new Client(1, "", "", "", "");
        movie.setClient(c);
        movieService.save(movie);
        return "redirect:/movies";
    }

}
