package com.example.movie;

import com.example.movie.exception.MovieCreationFailedException;
import com.example.movie.exception.MovieNotFoundException;
import com.example.movie.model.Movie;
import com.example.movie.model.MovieDTO;
import com.example.movie.model.Response;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public Response<MovieDTO> get(@PathVariable int movieId) throws MovieNotFoundException {
        return movieService.getMovieById(movieId);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Response<List<MovieDTO>> all() {
        return movieService.getAllMovies();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response<MovieDTO> createOrUpdateMovie(@Valid @RequestBody Movie movie) throws MovieCreationFailedException {
        return movieService.createOrUpdate(movie);
    }
}
