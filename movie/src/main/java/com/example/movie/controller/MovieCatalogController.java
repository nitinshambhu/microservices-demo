package com.example.movie.controller;

import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.model.MovieDTO;
import com.example.movie.repo.MovieRepository;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RequestMapping("/movie")
public class MovieCatalogController {

    private final MovieRepository repository;

    private final ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public MovieDTO get(@PathVariable int movieId) throws ResourceNotFoundException {
        return repository.findById(movieId)
                .map(movie -> mapper.map(movie, MovieDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found for id : " + movieId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<MovieDTO> all() {
        return repository.findAll()
                .stream()
                .map(movie -> mapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }
}
