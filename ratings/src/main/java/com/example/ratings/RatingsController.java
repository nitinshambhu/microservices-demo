package com.example.ratings;

import com.example.ratings.model.MovieRatingDTO;
import com.example.ratings.model.Response;
import com.example.ratings.model.UserRatingDTO;
import com.example.ratings.service.RatingService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/rating")
public class RatingsController {

    private final RatingService ratingService;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public Response<UserRatingDTO> getByUserId(@PathVariable int userId){
        return ratingService.getByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
    public Response<MovieRatingDTO> getByMovieId(@PathVariable int movieId) throws Exception {
        return ratingService.getByMovieId(movieId);
    }
}
