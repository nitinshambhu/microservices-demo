package com.example.users;

import com.example.users.exception.UserCreationFailedException;
import com.example.users.exception.UserNotFoundException;
import com.example.users.model.Response;
import com.example.users.model.User;
import com.example.users.model.UserDTO;
import com.example.users.repo.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class UserService {

    private final UserRepository repository;

    private final ModelMapper mapper;

    Response<UserDTO> getUser(int userId) throws UserNotFoundException {

        UserDTO userDTO = repository.findById(userId)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElseThrow(() -> new UserNotFoundException("User not found for userId = " + userId));

        return Response.<UserDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .data(userDTO)
                .build();
    }

    Response<List<UserDTO>> getUser() {

        List<UserDTO> list = repository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return Response.<List<UserDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .data(list)
                .build();
    }


    Response<UserDTO> addUser(User user) throws UserCreationFailedException {

        try {

            UserDTO userDTO = mapper.map(repository.save(user), UserDTO.class);

            return Response.<UserDTO>builder()
                    .statusCode(HttpStatus.OK.value())
                    .statusMessage(HttpStatus.OK.getReasonPhrase())
                    .data(userDTO)
                    .build();

        } catch (Exception ex) {
            throw new UserCreationFailedException(ex.getMessage());
        }

    }
}
