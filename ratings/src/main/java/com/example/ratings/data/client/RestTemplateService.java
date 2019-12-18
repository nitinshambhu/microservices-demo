package com.example.ratings.data.client;

import com.example.ratings.model.Movie;
import com.example.ratings.model.Response;
import com.example.ratings.model.User;

public interface RestTemplateService {
    Response<User> getUserbyId(int userId);
    Response<Movie> getMoviebyId(int movieId);
}
