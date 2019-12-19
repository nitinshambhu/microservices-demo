package com.example.ratings.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;


@Getter
@ToString
@Builder
public class UserRatingDTO {
    private String userName;
    @Singular
    private List<MovieDTO> movies;
}
