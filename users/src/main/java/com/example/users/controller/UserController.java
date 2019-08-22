package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.model.UserDTO;
import com.example.users.repo.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDTO getUser(@PathVariable int userId) {
        Optional<User> optionalUser = repository.findById(userId);
        if(optionalUser.isPresent()){
            return mapper.map(optionalUser.get(), UserDTO.class);
        } else {
            return new UserDTO();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<UserDTO> getUser() {
        Iterable<User> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
