package com.example.ratings.repo;

import com.example.ratings.model.UserRating;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRatingRepository extends JpaRepository<UserRating, Integer> {

    List<UserRating> findByUid(int userId);

    List<UserRating> findByMid(int movieId);

    List<UserRating> findByRating(int rating);
}
