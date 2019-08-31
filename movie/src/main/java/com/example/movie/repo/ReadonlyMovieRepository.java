package com.example.movie.repo;

import com.example.movie.model.Movie;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ReadonlyMovieRepository extends Repository<Movie, Integer> {

    Optional<Movie> findById(final Integer movieId);

    List<Movie> findAll();
}
