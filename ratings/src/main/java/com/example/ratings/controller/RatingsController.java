package com.example.ratings.controller;

import com.example.ratings.model.UserRating;
import com.example.ratings.repo.UserRatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-ratings")
public class RatingsController {

    @Autowired
    UserRatingRepository userRatingRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public List<UserRating> getByUserId(@PathVariable int userId){
        return userRatingRepo.findByUid(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
    public List<UserRating> getByMovieId(@PathVariable int movieId){
        return userRatingRepo.findByMid(movieId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rating/{rating}")
    public List<UserRating> getByRating(@PathVariable int rating){
        return userRatingRepo.findByRating(rating);
    }
}
