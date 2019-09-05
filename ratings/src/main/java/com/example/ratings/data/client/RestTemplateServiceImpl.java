package com.example.ratings.data.client;

import com.example.ratings.model.Movie;
import com.example.ratings.model.User;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestTemplateServiceImpl implements RestTemplateService {

    private final RestTemplate restTemplate;

    @Override
    public User getUserbyId(int userId) {
        return restTemplate.getForObject("http://USER-LOOKUP-SERVICE/user/" + userId, User.class);
    }

    @Override
    public Movie getMoviebyId(int movieId) {
        return restTemplate.getForObject("http://MOVIE-LOOKUP-SERVICE/movie/" + movieId, Movie.class);
    }
}
