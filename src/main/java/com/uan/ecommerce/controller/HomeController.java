package com.uan.ecommerce.controller;

import com.uan.ecommerce.model.Movie;
import com.uan.ecommerce.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("movies", movieService.findAll());
        return  "user/home";
    }

    @GetMapping("moviehome/{id}")
    public String movieHome(@PathVariable Integer id, Model model) {
        log.info("Id movie send with parameter {}", id);
        Movie movie = new Movie();
        Optional<Movie> movieOptional = movieService.get(id);
        movie = movieOptional.get();

        model.addAttribute("movie", movie);

        return "user/moviehome";
    }
}
