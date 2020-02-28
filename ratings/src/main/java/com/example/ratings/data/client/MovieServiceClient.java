package com.example.ratings.data.client;

import com.example.ratings.model.Movie;
import com.example.ratings.model.Response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "MOVIE-LOOKUP-SERVICE")
public interface MovieServiceClient {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response<Movie> getMovieById(@PathVariable(name = "id") int id);

}
