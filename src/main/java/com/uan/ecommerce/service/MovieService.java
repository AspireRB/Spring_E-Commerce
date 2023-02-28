package com.uan.ecommerce.service;

import com.uan.ecommerce.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public Movie save(Movie movie);
    public Optional<Movie> get(Integer id);
    public void update(Movie movie);
    public void delete(Integer id);
    public List<Movie> findAll();

}
