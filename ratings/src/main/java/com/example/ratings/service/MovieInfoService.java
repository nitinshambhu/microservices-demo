package com.example.ratings.service;

import com.example.ratings.configuration.MovieServiceClient;
import com.example.ratings.model.Movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieInfoService {

    private final MovieServiceClient movieServiceClient;

    @HystrixCommand(fallbackMethod = "getByMovieIdFallback")
    public Movie getMovieById(@PathVariable int movieId) {
        return movieServiceClient.getMovieById(movieId);
    }

    public Movie getByMovieIdFallback(int movieId) {
        Movie movie =  new Movie();
        movie.setName("");
        movie.setId(movieId);
        return movie;
    }
}
