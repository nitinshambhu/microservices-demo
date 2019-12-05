package com.example.users;

import com.example.users.exception.UserCreationFailedException;
import com.example.users.exception.UserNotFoundException;
import com.example.users.model.Response;
import com.example.users.model.User;
import com.example.users.model.UserDTO;
import com.example.users.repo.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RequestMapping("/user")
public class UserServiceController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Response<UserDTO> getUser(@PathVariable int userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @ResponseStatus(code = HttpStatus.OK)
    public Response<List<UserDTO>> getUser() {
        return userService.getUser();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public Response<UserDTO> addUser(@RequestBody User user) throws UserCreationFailedException {
        return userService.addUser(user);
    }
}
