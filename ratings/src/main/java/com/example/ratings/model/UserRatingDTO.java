package com.example.ratings.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRatingDTO {
    private String userName;
    private List<MovieDTO> list;
}
