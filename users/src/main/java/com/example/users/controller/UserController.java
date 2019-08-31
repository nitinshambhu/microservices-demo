package com.example.users.controller;

import com.example.users.model.dto.UserDTO;
import com.example.users.repo.ReadonlyUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final ReadonlyUserRepository readonlyUserRepository;

    private final ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDTO getUser(@PathVariable int userId) {
        return readonlyUserRepository.findById(userId)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElseGet(UserDTO::new);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<UserDTO> getUser() {
        return readonlyUserRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        // Add paging support
    }
}
