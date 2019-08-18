package com.example.ratings.repo;

import com.example.ratings.model.UserRating;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRatingRepository extends JpaRepository<UserRating, Integer> {

    List<UserRating> findByuid(int userId);
}
