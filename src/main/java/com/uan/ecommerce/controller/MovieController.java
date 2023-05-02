package com.uan.ecommerce.controller;

import com.uan.ecommerce.model.User;
import com.uan.ecommerce.model.Movie;
import com.uan.ecommerce.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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
        User u = new User(1, "", "", "", "");
        movie.setUser(u);
        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Movie movie = new Movie();
        Optional<Movie> optionalMovie = movieService.get(id);
        movie = optionalMovie.get();

        LOGGER.info("Movie search: {}",movie);
        model.addAttribute("movie", movie);

        return "movies/edit";
    }

    @PostMapping("/update")
    public String update(Movie movie) {
        movieService.update(movie);
        return "redirect:/movies";
    }
}
