package com.uan.ecommerce.controller;

import com.uan.ecommerce.model.Movie;
import com.uan.ecommerce.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public String home(Model model) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);

        return "administrator/home";
    }





}
