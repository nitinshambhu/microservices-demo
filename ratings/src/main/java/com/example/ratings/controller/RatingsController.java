package com.example.ratings.controller;

import com.example.ratings.configuration.MovieServiceClient;
import com.example.ratings.configuration.UserServiceClient;
import com.example.ratings.model.Movie;
import com.example.ratings.model.MovieDTO;
import com.example.ratings.model.MovieRatingDTO;
import com.example.ratings.model.Rating;
import com.example.ratings.model.User;
import com.example.ratings.model.UserDTO;
import com.example.ratings.model.UserRatingDTO;
import com.example.ratings.repo.RatingRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user-ratings")
public class RatingsController {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieServiceClient movieServiceClient;

    @Autowired
    UserServiceClient userServiceClient;

    /**
     * Uses RestTemplate to fetch UserRating
     * @param userId - Integer
     * @return UserRating
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public UserRatingDTO getByUserId(@PathVariable int userId) {
        log.info("Finding movies rated by user {} ", userId);

        List<Rating> ratings = ratingRepository.findByUid(userId);
        log.info("ratings = {} ", ratings);

        User user = restTemplate.getForObject("http://USER-LOOKUP-SERVICE/user/"+userId, User.class);
        List<MovieDTO> list = ratings.stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://MOVIE-LOOKUP-SERVICE/movie/" + rating.getMid(), Movie.class);
                    //How to handle null? Shouldn't we be returning something like 404 ?
                    MovieDTO movieDTO = mapper.map(movie, MovieDTO.class);
                    movieDTO.setRating(rating.getRating());
                    return movieDTO;
                })
                .collect(Collectors.toList());

        UserRatingDTO userRatingDTO = new UserRatingDTO();
        if(user != null) {
            userRatingDTO.setUserName(user.getName());
            userRatingDTO.setList(list);
        }

        log.info("returning DTO = {} ", userRatingDTO);
        return userRatingDTO;
    }

    /**
     * Uses Feign Client to fetch UserRating
     * @param movieId - Integer
     * @return MovieRating
     */
    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
    public MovieRatingDTO getByMovieId(@PathVariable int movieId) {
        log.info("Finding users who rated movie {} ", movieId);

        List<Rating> ratings = ratingRepository.findByMid(movieId);
        log.info("ratings = {} ", ratings);

        Movie movie = movieServiceClient.getMovieById(movieId);
        List<UserDTO> list = ratings.stream()
                .map(rating -> {
                    User user = userServiceClient.getUserById(rating.getUid());
                    UserDTO userDTO = mapper.map(user, UserDTO.class);
                    userDTO.setRating(rating.getRating());
                    return userDTO;
                })
                .collect(Collectors.toList());

        MovieRatingDTO ratingDTO = new MovieRatingDTO();
        if(movie != null) {
            ratingDTO.setMovieName(movie.getName());
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
