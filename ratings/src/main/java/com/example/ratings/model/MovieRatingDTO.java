package com.example.ratings.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@ToString
@Builder
public class MovieRatingDTO {
    private String movieName;
    @Singular
    private List<UserDTO> users;
}
