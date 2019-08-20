package com.example.ratings.model;

import org.springframework.data.util.Pair;

import java.util.List;

public class Result {
    private String name;
    private List<Pair> list;

    public Result() {
    }

    public String getName() {
        return this.name;
    }

    public List<Pair> getList() {
        return this.list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<Pair> list) {
        this.list = list;
    }

    public String toString() {
        return "Result(name=" + this.getName() + ", list=" + this.getList() + ")";
    }
}
