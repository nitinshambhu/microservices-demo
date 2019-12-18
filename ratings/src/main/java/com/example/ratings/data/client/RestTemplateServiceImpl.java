package com.example.ratings.data.client;

import com.example.ratings.model.Movie;
import com.example.ratings.model.Response;
import com.example.ratings.model.User;
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
        String response = restTemplate.getForObject("http://USER-LOOKUP-SERVICE/user/" + userId, String.class);
        log.info("RESPONSE ===> getUserbyId() : " + response);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType type = mapper.getTypeFactory().constructParametricType(Response.class, User.class);
        try {
            return mapper.readValue(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Response<Movie> getMoviebyId(int movieId) {
        return restTemplate.exchange("http://MOVIE-LOOKUP-SERVICE/movie/" + movieId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Response<Movie>>() {
                }
        ).getBody();
    }
}
