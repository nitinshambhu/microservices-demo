package com.example.movie;

import com.example.movie.exception.MovieCreationFailedException;
import com.example.movie.exception.MovieNotFoundException;
import com.example.movie.model.Movie;
import com.example.movie.model.MovieDTO;
import com.example.movie.model.Response;
import com.example.movie.repo.MovieRepository;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class MovieService {

    private final MovieRepository repository;

    private final ModelMapper mapper;

    Response<MovieDTO> getMovieById(int movieId) throws MovieNotFoundException {
        MovieDTO movieDTO = repository.findById(movieId)
                .map(movie -> mapper.map(movie, MovieDTO.class))
                .orElseThrow(() -> new MovieNotFoundException("Movie not found for id : " + movieId));

        return Response.<MovieDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .data(movieDTO)
                .build();
    }

    Response<List<MovieDTO>> getAllMovies() {

        List<MovieDTO> movieList = repository.findAll()
                .stream()
                .map(movie -> mapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());

        return Response.<List<MovieDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .data(movieList)
                .build();
    }

    Response<MovieDTO> createOrUpdate(Movie movie) throws MovieCreationFailedException {

        try {

            MovieDTO movieDTO = mapper.map(repository.save(movie), MovieDTO.class);

            return Response.<MovieDTO>builder()
                    .statusCode(HttpStatus.OK.value())
                    .statusMessage(HttpStatus.OK.getReasonPhrase())
                    .data(movieDTO)
                    .build();
        } catch (Exception ex) {
            throw new MovieCreationFailedException(ex.getMessage());
        }
    }
}