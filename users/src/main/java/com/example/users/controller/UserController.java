package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public Optional<User> getUser(@PathVariable int userId) {
        return repository.findById(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<User> getUser() {
        List<User> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }
}
