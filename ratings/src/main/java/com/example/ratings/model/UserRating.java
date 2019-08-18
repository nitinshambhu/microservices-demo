package com.example.ratings.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class UserRating {

    @Id
    private int id;
    private int uid;
    private int mid;
    private int rating;

    public UserRating(int id, int uid, int mid, int rating) {
        this.id = id;
        this.uid = uid;
        this.mid = mid;
        this.rating = rating;
    }
}
