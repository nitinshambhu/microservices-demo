package com.example.ratings.service;

import com.example.ratings.data.client.RestTemplateService;
import com.example.ratings.data.repo.RatingRepository;
import com.example.ratings.exception.ResourceNotFoundException;
import com.example.ratings.model.Movie;
import com.example.ratings.model.MovieDTO;
import com.example.ratings.model.MovieRatingDTO;
import com.example.ratings.model.Rating;
import com.example.ratings.model.User;
import com.example.ratings.model.UserDTO;
import com.example.ratings.model.UserRatingDTO;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MovieInfoService movieInfoService;
    private final UserInfoService userInfoService;
    private final ModelMapper mapper;
    private final RestTemplateService restTemplateService;

    /**
     * Uses RestTemplate to fetch UserRating
     * @param userId - Integer
     * @return UserRating
     */
    public UserRatingDTO getByUserId(@PathVariable int userId) {
        log.info("Finding movies rated by user {} ", userId);

        List<Rating> ratings = ratingRepository.findByUid(userId);
        log.info("ratings = {} ", ratings);

        User user = restTemplateService.getUserbyId(userId);
        List<MovieDTO> list = ratings.stream()
                .map(rating -> {
                    Movie movie = restTemplateService.getMoviebyId(rating.getMid());
                    //How to handle null? Shouldn't we be returning something like 404 ?
                    MovieDTO movieDTO = mapper.map(movie, MovieDTO.class);
                    movieDTO.setRating(rating.getRating());
                    return movieDTO;
                })
                .collect(Collectors.toList());

        UserRatingDTO userRatingDTO = new UserRatingDTO();
        if(user != null) {
            userRatingDTO.setUserName(user.getName());
            userRatingDTO.setList(list);
        }

        log.info("returning DTO = {} ", userRatingDTO);
        return userRatingDTO;
    }

    /**
     * Uses Feign Client to fetch UserRating
     *
     * @param movieId - Integer
     * @return MovieRating
     */
    public MovieRatingDTO getByMovieId(int movieId) throws Exception {
        log.info("Finding users who rated movie {} ", movieId);

        List<Rating> ratings = ratingRepository.findByMid(movieId);
        log.info("ratings = {} ", ratings);

        if(ratings == null)
            throw new ResourceNotFoundException("Ratings not found for movieId : " + movieId);

        Movie movie = movieInfoService.getMovieById(movieId);
        List<UserDTO> list = Stream.of(movie)
                .filter(mov -> mov != null && !mov.getName().isEmpty())
                .flatMap(movi -> ratings.stream())
                .map(rating -> {
                    User user = userInfoService.getUserById(rating.getUid());
                    UserDTO userDTO = mapper.map(user, UserDTO.class);
                    userDTO.setRating(rating.getRating());
                    return userDTO;
                })
                .filter(userDTO -> !userDTO.getUserName().isEmpty())
                .collect(Collectors.toList());

        MovieRatingDTO ratingDTO = new MovieRatingDTO();
        if (movie != null) {
            ratingDTO.setMovieName(movie.getName());
            ratingDTO.setList(list);
        }

        return ratingDTO;
    }
}