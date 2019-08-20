package com.example.ratings.controller;

import com.example.ratings.model.Movie;
import com.example.ratings.model.Rating;
import com.example.ratings.model.Result;
import com.example.ratings.model.User;
import com.example.ratings.repo.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/user-ratings")
public class RatingsController {

    @Autowired
    RatingRepository ratingRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public Result getByUserId(@PathVariable int userId) {
        RestTemplate restTemplate = new RestTemplate();
        Result result = new Result();
        User user = restTemplate.getForObject("http://localhost:8082/", User.class);
        result.setName(user.getName());

        List<Rating> ratings = ratingRepository.findByUid(userId);

        ratings.stream()
                .map((Function<Rating, Pair>) rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8083/", Movie.class);
                    return Pair.of(movie.getName(), rating.getRating());
                })
                .forEach(pair -> result.getList().add(pair));
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
    public Result getByMovieId(@PathVariable int movieId) {
        RestTemplate restTemplate = new RestTemplate();
        Result result = new Result();
        Movie movie = restTemplate.getForObject("http://localhost:8083/", Movie.class);
        result.setName(movie.getName());

        List<Rating> ratings = ratingRepository.findByMid(movieId);

        ratings.stream()
                .map((Function<Rating, Pair>) rating -> {
                    User user = restTemplate.getForObject("http://localhost:8082/", User.class);
                    return Pair.of(user.getName(), rating.getRating());
                })
                .forEach(pair -> result.getList().add(pair));
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rating/{rating}")
    public List<Rating> getByRating(@PathVariable int rating) {
        return ratingRepository.findByRating(rating);
    }
}
