package com.example.users.repo;

import com.example.users.model.User;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Integer> {

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);
}
