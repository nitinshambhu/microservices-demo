package com.example.ratings.data.client;

import com.example.ratings.model.Movie;
import com.example.ratings.model.User;

public interface RestTemplateService {
    User getUserbyId(int userId);
    Movie getMoviebyId(int movieId);
}
