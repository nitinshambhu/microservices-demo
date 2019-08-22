package com.example.movie.controller;

import com.example.movie.model.Movie;
import com.example.movie.model.MovieDTO;
import com.example.movie.repo.MovieRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/movie")
public class MovieCatalogController {

    @Autowired
    MovieRepository repository;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public MovieDTO get(@PathVariable int movieId){
        Optional<Movie> optionalMovie = repository.findById(movieId);
        if(optionalMovie.isPresent()){
            return mapper.map(optionalMovie.get(), MovieDTO.class);
        } else {
            return new MovieDTO();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<MovieDTO> all(){
        Iterable<Movie> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(movie -> mapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }
}
