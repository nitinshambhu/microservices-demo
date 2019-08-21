package com.example.ratings.controller;

import com.example.ratings.model.Movie;
import com.example.ratings.model.Rating;
import com.example.ratings.model.RatingDTO;
import com.example.ratings.model.User;
import com.example.ratings.repo.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/user-ratings")
public class RatingsController {

    @Autowired
    RatingRepository ratingRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public RatingDTO getByUserId(@PathVariable int userId) {

        List<Rating> ratings = ratingRepository.findByUid(userId);

        RestTemplate restTemplate = new RestTemplate();
        RatingDTO ratingDTO = new RatingDTO();
        ArrayList<Pair> list = new ArrayList<>();

        User user = restTemplate.getForObject("http://localhost:8082/user/"+userId, User.class);
        System.out.println("TinTin : ratings = "+ratings);

        ratings.stream()
                .map((Function<Rating, Pair>) rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8083/movie/"+rating.getMid(), Movie.class);
                    System.out.println("TinTin : movie = "+movie);
                    return Pair.of(movie.getName(), rating.getRating());
                })
                .forEach(list::add);

        if(user != null) {
            ratingDTO.setName(user.getName());
            ratingDTO.setList(list);
        }

        System.out.println("TinTin : ratingDTO = "+ratingDTO);
        return ratingDTO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
    public RatingDTO getByMovieId(@PathVariable int movieId) {

        List<Rating> ratings = ratingRepository.findByMid(movieId);

        RestTemplate restTemplate = new RestTemplate();
        RatingDTO ratingDTO = new RatingDTO();
        ArrayList<Pair> list = new ArrayList<>();

        Movie movie = restTemplate.getForObject("http://localhost:8083/movie/"+movieId, Movie.class);

        ratings.stream()
                .map((Function<Rating, Pair>) rating -> {
                    User user = restTemplate.getForObject("http://localhost:8082/user/"+rating.getUid(), User.class);
                    return Pair.of(user.getName(), rating.getRating());
                })
                .forEach(list::add);

        if(movie != null) {
            ratingDTO.setName(movie.getName());
            ratingDTO.setList(list);
        }

        return ratingDTO;
    }

    /*@RequestMapping(method = RequestMethod.GET, value = "/userId/{userId}")
    public List<Rating> getByUId(@PathVariable int userId) {
        return ratingRepository.findByUid(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movieId/{movieId}")
    public List<Rating> getByMId(@PathVariable int movieId) {
        return ratingRepository.findByMid(movieId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rating/{rating}")
    public List<Rating> getByRating(@PathVariable int rating) {
        return ratingRepository.findByRating(rating);
    }*/
}
