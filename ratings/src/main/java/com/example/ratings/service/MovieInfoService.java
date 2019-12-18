package com.example.ratings.service;

import com.example.ratings.data.client.MovieServiceClient;
import com.example.ratings.model.Movie;
import com.example.ratings.model.Response;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieInfoService {

    private final MovieServiceClient movieServiceClient;

    @HystrixCommand(fallbackMethod = "getByMovieIdFallback")
    public Response<Movie> getMovieById(int movieId) {
        return movieServiceClient.getMovieById(movieId);
    }

    public Response<Movie> getByMovieIdFallback(int movieId) {
        Movie movie =  new Movie();
        movie.setName("");
        movie.setId(movieId);
        return Response.<Movie>builder()
                .data(movie)
                .build();
    }
}
