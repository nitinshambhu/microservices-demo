package com.example.movie.model;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response<T> {
    private int statusCode;
    @Nullable
    private String statusMessage;
    @Nullable
    private T data;
}
