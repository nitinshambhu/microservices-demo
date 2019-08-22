package com.example.ratings.model;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String userName;
    private int rating;
}