package com.example.ratings.client;

import com.example.ratings.model.Movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "MOVIE-LOOKUP-SERVICE")
public interface MovieServiceClient {

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable(name = "id") int id);

}
