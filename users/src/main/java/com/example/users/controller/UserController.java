package com.example.users.controller;

import com.example.users.exception.ResourceNotFoundException;
import com.example.users.model.UserDTO;
import com.example.users.repo.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    private final ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDTO getUser(@PathVariable int userId) throws ResourceNotFoundException {
        return repository.findById(userId)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found for id : " + userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<UserDTO> getUser() {
        return repository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
