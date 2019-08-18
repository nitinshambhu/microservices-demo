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

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public List<UserRating> getRatings(@PathVariable int userId){
        return null; //userRatingRepo.find;
    }
}
