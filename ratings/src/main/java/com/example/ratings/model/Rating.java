package com.example.ratings.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rating {
    @Id
    private int id;
    private int uid;
    private int mid;
    private int rating;

    public Rating(int id, int uid, int mid, int rating) {
        this.id = id;
        this.uid = uid;
        this.mid = mid;
        this.rating = rating;
    }

    public int getId() {
        return this.id;
    }

    public int getUid() {
        return this.uid;
    }

    public int getMid() {
        return this.mid;
    }

    public int getRating() {
        return this.rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String toString() {
        return "Rating(id=" + this.getId() + ", uid=" + this.getUid() + ", mid=" + this.getMid() + ", rating=" + this.getRating() + ")";
    }

    public Rating() {
    }
}
