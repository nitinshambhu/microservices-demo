package com.example.ratings.controller;

import com.example.ratings.model.MovieRatingDTO;
import com.example.ratings.model.UserRatingDTO;
import com.example.ratings.service.RatingService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-ratings")
public class RatingsController {

    private final RatingService ratingService;

    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
    public MovieRatingDTO getByMovieId(@PathVariable int movieId){
        return ratingService.getByMovieId(movieId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public UserRatingDTO getByUserId(@PathVariable int userId){
        return ratingService.getByUserId(userId);
    }
}
