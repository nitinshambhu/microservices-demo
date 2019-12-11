package com.example.movie.repo;

import com.example.movie.model.Movie;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends Repository<Movie, Integer> {

    Optional<Movie> findById(Integer id);

    List<Movie> findAll();

    Movie save(Movie movie);
}
