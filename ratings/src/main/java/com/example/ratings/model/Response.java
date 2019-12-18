package com.example.ratings.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = Response.ResponseBuilder.class)
public class Response<T> {
    @Nullable
    public int statusCode;
    @Nullable
    public String statusMessage;
    @Nullable
    public T data;

    @JsonCreator
    public static <T> Response<T> newInstance(
            @JsonProperty("statusCode") int statusCode,
            @JsonProperty("statusMessage") String statusMessage,
            @JsonProperty("data") T data) {
        return new ResponseBuilder<T>()
                .statusCode(statusCode)
                .statusMessage(statusMessage)
                .data(data)
                .build();
    }
}
