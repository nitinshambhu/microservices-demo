package com.example.ratings.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {
    private int id;
    private String movieName;
    private int rating;
}
