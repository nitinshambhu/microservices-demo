package com.example.ratings.data.client;

import com.example.ratings.model.Response;
import com.example.ratings.model.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "USER-LOOKUP-SERVICE")
public interface UserServiceClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Response<User> getUserById(@PathVariable(name = "id") int id);

}
