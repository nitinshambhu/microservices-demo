package com.example.ratings.data.client;

import com.example.ratings.model.Movie;
import com.example.ratings.model.Response;
import com.example.ratings.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestTemplateServiceImpl implements RestTemplateService {

    private final RestTemplate restTemplate;

    @Override
    public Response<User> getUserbyId(int userId) {
        return restTemplate.exchange("http://USER-LOOKUP-SERVICE/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Response<User>>() {
                }
        ).getBody();
    }

    @Override
    public Response<Movie> getMoviebyId(int movieId) {
        return restTemplate.exchange("http://MOVIE-LOOKUP-SERVICE/" + movieId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Response<Movie>>() {
                }
        ).getBody();
    }
}
