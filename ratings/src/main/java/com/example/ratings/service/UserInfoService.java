package com.example.ratings.service;

import com.example.ratings.data.client.UserServiceClient;
import com.example.ratings.model.Response;
import com.example.ratings.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserServiceClient userServiceClient;

    @HystrixCommand(fallbackMethod = "getGetUserByIdFallback")
    public Response<User> getUserById(int userId) {
        return userServiceClient.getUserById(userId);
    }

    Response<User> getGetUserByIdFallback(int userId) {
        User user = new User();
        user.setName("");
        user.setId(userId);
        return Response.<User>builder()
                .data(user)
                .build();
    }
}
