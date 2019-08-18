package com.example.movie.controller;

import com.example.movie.model.Movie;
import com.example.movie.repo.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieCatalogResource {

    @Autowired
    MovieRepository movieRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public Optional<Movie> get(@PathVariable int movieId){
         return movieRepo.findById(movieId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Movie> all(){
        List<Movie> list = new ArrayList<>();
        movieRepo.findAll().forEach(list::add);
        return list;
    }
}
