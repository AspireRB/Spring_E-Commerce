package com.uan.ecommerce.controller;

import com.uan.ecommerce.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("movies", movieService.findAll());
        return  "user/home";
    }
}
