package com.example.ratings.model;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {
    @EqualsAndHashCode.Exclude
    private int statusCode;
    @EqualsAndHashCode.Exclude
    private String statusMessage;
    @Nullable
    @EqualsAndHashCode.Exclude
    private T data;
}
