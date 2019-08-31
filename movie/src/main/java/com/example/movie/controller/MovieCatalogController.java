package com.example.movie.controller;

import com.example.movie.dto.MovieDTO;
import com.example.movie.repo.ReadonlyMovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieCatalogController {

    private final ReadonlyMovieRepository readonlyMovieRepository;

    private final ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public MovieDTO get(@PathVariable Integer movieId) {
        return readonlyMovieRepository.findById(movieId)
                .map(movie -> mapper.map(movie, MovieDTO.class))
//                .orElseThrow(() -> new NotFoundException(String.format("Movie with id [%d] not found", movieId))) // This should be ideal behaviour
                .orElseGet(MovieDTO::new);  // I did this just to  make the codee refactor not change service behaviour
    }

    // Actually /all  is not required. General REST convention is  that when you call "/" it returns all resources.
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<MovieDTO> all() {
        return readonlyMovieRepository.findAll()
                .stream()
                .map(movie -> mapper.map(movie, MovieDTO.class))
                .collect(toList());

        // Returning ALL records is not a good idea. Better to introduce paging.
    }
}
