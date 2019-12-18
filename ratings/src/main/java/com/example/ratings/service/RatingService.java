package com.example.ratings.service;

import com.example.ratings.data.client.RestTemplateService;
import com.example.ratings.data.repo.RatingRepository;
import com.example.ratings.exception.ResourceNotFoundException;
import com.example.ratings.model.Movie;
import com.example.ratings.model.MovieDTO;
import com.example.ratings.model.MovieRatingDTO;
import com.example.ratings.model.Rating;
import com.example.ratings.model.Response;
import com.example.ratings.model.User;
import com.example.ratings.model.UserDTO;
import com.example.ratings.model.UserRatingDTO;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    // MovieInfoService uses FeignClient
    private final MovieInfoService movieInfoService;
    // UserInfoService uses FeignClient
    private final UserInfoService userInfoService;
    private final ModelMapper mapper;
    private final RestTemplateService restTemplateService;
    private final RestTemplate restTemplate;

    /**
     * Uses RestTemplate to fetch UserRating
     *
     * @param userId - Integer
     * @return UserRating
     */
    public Response<UserRatingDTO> getByUserId(@PathVariable int userId) throws ResourceNotFoundException {
        log.info("Finding movies rated by user ");

        List<Rating> ratings = ratingRepository.findByUid(userId);
        log.info("ratings = {} ", ratings);

        if (ratings.isEmpty())
            throw new ResourceNotFoundException("No Ratings found for user Id : " + userId);

        Response<User> response = restTemplateService.getUserbyId(userId);
        User user = response.data();
        log.info("response = {} ", response);
        log.info("user = {} ", user);
        if (user == null)
            throw new ResourceNotFoundException("No user found with id : " + userId);

        List<MovieDTO> list = ratings.stream()
                .map(rating -> {
                    // Not checking if movie is null. Movie can be null only when data is inconsistent
                    Movie movie = restTemplateService.getMoviebyId(rating.getMid()).data();
                    log.info("movie = {} ", movie);
                    MovieDTO movieDTO = mapper.map(movie, MovieDTO.class);
                    movieDTO.setRating(rating.getRating());
                    return movieDTO;
                })
                .collect(Collectors.toList());

        UserRatingDTO userRatingDTO = new UserRatingDTO();
        userRatingDTO.setUserName(user.getName());
        userRatingDTO.setList(list);

        log.info("returning DTO = {} ", userRatingDTO);
        log.info("Returning list of movies rated by user");
        return Response.<UserRatingDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .data(userRatingDTO)
                .build();
    }

    /**
     * Uses Feign Client to fetch UserRating
     *
     * @param movieId - Integer
     * @return MovieRating
     */
    public Response<MovieRatingDTO> getByMovieId(int movieId) throws Exception {
        log.info("Finding users who rated movie ");

        List<Rating> ratings = ratingRepository.findByMid(movieId);
        log.info("ratings = {} ", ratings);

        if (ratings.isEmpty())
            throw new ResourceNotFoundException("No Ratings found for movie Id : " + movieId);

        Response<Movie> movieResponse = movieInfoService.getMovieById(movieId);
        log.info("movieResponse = {} ", movieResponse);
        if (movieResponse.data() == null)
            throw new ResourceNotFoundException("No movie found with id : " + movieId);

        List<UserDTO> list = ratings.stream()
                .map(rating -> {
                    // Not checking if user is null. User can be null only when data is inconsistent
                    Response<User> userResponse = userInfoService.getUserById(rating.getUid());
                    log.info("userResponse = {} ", userResponse);
                    UserDTO userDTO = mapper.map(userResponse.data(), UserDTO.class);
                    userDTO.setRating(rating.getRating());
                    return userDTO;
                })
                .collect(Collectors.toList());

        MovieRatingDTO movieRatingDTO = new MovieRatingDTO();
        movieRatingDTO.setMovieName(movieResponse.data().getName());
        movieRatingDTO.setList(list);

        log.info("returning DTO = {} ", movieRatingDTO);
        log.info("Returning list of movies rated by user");
        return Response.<MovieRatingDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .data(movieRatingDTO)
                .build();
    }
}