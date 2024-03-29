package com.uan.ecommerce.controller;

import com.uan.ecommerce.model.Detail;
import com.uan.ecommerce.model.Movie;
import com.uan.ecommerce.model.Order;
import com.uan.ecommerce.model.User;
import com.uan.ecommerce.service.DetailService;
import com.uan.ecommerce.service.MovieService;
import com.uan.ecommerce.service.OrderService;
import com.uan.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DetailService detailService;

    List<Detail> details = new ArrayList<Detail>();

    Order order = new Order();

    @GetMapping("")
    public String home(Model model, HttpSession session) {
        log.info("Sesion del usuario: {}", session.getAttribute("idUser"));
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

        //Validate that the movie is not added 2 times
        Integer idMovie = movie.getId();
        boolean joined = details.stream().anyMatch(m -> m.getMovie().getId()==idMovie);

        if (!joined) {
            details.add(detail);
        }

        totalAmount = details.stream().mapToDouble(dt->dt.getPay()).sum();

        order.setPay(totalAmount);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        return "user/cart";
    }

    //Remove a movie of cart
    @GetMapping("/delete/cart/{i}")
    public String deleteMovieCart(@PathVariable Integer id, Model model) {
        // List new of movies
        List<Detail> ordersNew = new ArrayList<Detail>();

        for(Detail detail: details) {
            if(detail.getMovie().getId()!=id) {
                ordersNew.add(detail);
            }
        }

        //Put new list with movies remaining
        details = ordersNew;

        double totalAmount = 0;
        totalAmount = details.stream().mapToDouble(dt->dt.getPay()).sum();

        order.setPay(totalAmount);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        return "user/cart";
    }

    @GetMapping("/getCart")
    public String getCart(Model model) {

        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        return "/user/cart";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        User user = userService.findById(Integer.parseInt(session.getAttribute("idUser").toString())).get();

        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        return "user/summaryorder";
    }

    //Save the order
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session) {
        Date startDate = new Date();
        order.setStartDate(startDate);
        order.setNumber(orderService.generateOrderNumber());

        //User
        User user = userService.findById(Integer.parseInt(session.getAttribute("idUser").toString())).get();

        order.setUser(user);
        orderService.save(order);

        //Save details
        for (Detail dt:details) {
            dt.setOrder(order);
            detailService.save(dt);
        }

        //Clean list and order
        order = new Order();
        details.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchMovie(@RequestParam String name, Model model) {
        log.info("Name movie: {}", name);
        List<Movie> movies = movieService.findAll().stream().filter(m -> m.getName().contains(name)).collect(Collectors.toList());
        model.addAttribute("movies", movies);
        return "user/home";
    }
}
