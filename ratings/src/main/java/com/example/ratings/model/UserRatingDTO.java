package com.example.ratings.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRatingDTO {
    private String userName;
    private List<MovieDTO> list;
}
