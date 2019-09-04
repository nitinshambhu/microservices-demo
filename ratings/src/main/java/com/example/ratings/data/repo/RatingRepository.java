package com.example.ratings.data.repo;


import com.example.ratings.model.Rating;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    List<Rating> findByUid(int userId);

    List<Rating> findByMid(int movieId);

    List<Rating> findByRating(int rating);
}
