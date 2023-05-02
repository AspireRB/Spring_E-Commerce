package com.uan.ecommerce.controller;

import com.uan.ecommerce.model.Detail;
import com.uan.ecommerce.model.Movie;
import com.uan.ecommerce.model.Order;
import com.uan.ecommerce.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private MovieService movieService;

    List<Detail> details = new ArrayList<Detail>();

    Order order = new Order();

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

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer amount, Model model) {
        Detail detail = new Detail();
        Movie movie = new Movie();
        double totalAmount = 0;

        Optional<Movie> optionalMovie = movieService.get(id);
        log.info("Add movie: {}", optionalMovie.get());
        log.info("Amount: {}", amount);
        movie = optionalMovie.get();

        detail.setAmount(amount);
        detail.setPrice(movie.getPrice());
        detail.setName(movie.getName());
        detail.setPay(movie.getPrice()*amount);
        detail.setMovie(movie);

        details.add(detail);

        totalAmount = details.stream().mapToDouble(dt->dt.getPay()).sum();

        order.setPay(totalAmount);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        return "user/cart";
    }
}
