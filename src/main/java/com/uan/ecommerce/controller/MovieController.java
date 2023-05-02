package com.uan.ecommerce.controller;

import com.uan.ecommerce.model.User;
import com.uan.ecommerce.model.Movie;
import com.uan.ecommerce.service.MovieService;
import com.uan.ecommerce.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private UploadFileService upload;

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
    public String save(Movie movie, @RequestParam("img") MultipartFile file) throws IOException {
        LOGGER.info("This is the object movie {}",movie);
        User u = new User(1, "", "", "", "");
        movie.setUser(u);

        //image
        if (movie.getId()==null) { // when create a movie
            String nameImage = upload.saveImage(file);
            movie.setImage(nameImage);
        } else {

        }

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
    public String update(Movie movie, @RequestParam("img") MultipartFile file) throws IOException {
        Movie m = new Movie();
        m = movieService.get(movie.getId()).get();

        if (file.isEmpty()) { //edit movie but i dont change image
            movie.setImage(m.getImage());
        } else {
            //delete when it dont default image
            if (!m.getImage().equals("default.jpg")) {
                upload.deleteImage(m.getImage());
            }

            String nameImage = upload.saveImage(file);
            movie.setImage(nameImage);
        }
        movie.setUser(m.getUser());
        movieService.update(movie);
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Movie m = new Movie();
        m = movieService.get(id).get();

        //delete when it dont default image
        if (!m.getImage().equals("default.jpg")) {
            upload.deleteImage(m.getImage());
        }

        movieService.delete(id);
        return "redirect:/movies";
    }
}
