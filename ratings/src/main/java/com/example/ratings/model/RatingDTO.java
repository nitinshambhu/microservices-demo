package com.example.ratings.model;

import org.springframework.data.util.Pair;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private String name;
    private List<Pair<String, Integer>> list;
}
