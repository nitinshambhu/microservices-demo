package com.example.ratings;

import com.example.ratings.model.Response;
import com.example.ratings.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        String response = " {\"statusCode\":200,\"statusMessage\":\"OK\",\"data\":{\"id\":103,\"name\":\"Aniruddha\"}}";

        System.out.println("response ==> " + response);

        ObjectMapper mapper = new ObjectMapper();
        Response<User> userResponse = mapper.readValue(response, new TypeReference<Response<User>>(){});

        System.out.println("userResponse ==> " + userResponse);
    }
}
